package org.gooru.nucleus.handlers.lookup.processors;

import java.io.InputStream;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicReference;

import org.gooru.nucleus.handlers.lookup.constants.MessageConstants;
import org.gooru.nucleus.handlers.lookup.processors.commands.CommandProcessorBuilder;
import org.gooru.nucleus.handlers.lookup.processors.exceptions.VersionDeprecatedException;
import org.gooru.nucleus.handlers.lookup.processors.responses.ExecutionResult;
import org.gooru.nucleus.handlers.lookup.processors.responses.MessageResponse;
import org.gooru.nucleus.handlers.lookup.processors.responses.MessageResponseFactory;
import org.gooru.nucleus.handlers.lookup.processors.utils.ValidationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.util.concurrent.SettableFuture;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.tasks.OnCompleteListener;
import com.google.firebase.tasks.OnSuccessListener;
import com.google.firebase.tasks.Task;
import com.google.firebase.tasks.Tasks;

import io.vertx.core.Vertx;
import io.vertx.core.eventbus.Message;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonObject;

class MessageProcessor implements Processor {

    private static final Logger LOGGER = LoggerFactory.getLogger(Processor.class);
    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("messages");
    private JsonObject session;
    private JsonObject request;
    private final Message<Object> message;
    private ProcessorContext context;

    public MessageProcessor(Message<Object> message) {
        this.message = message;
    }

    @Override
    public MessageResponse process() {
        MessageResponse result = null;
        try {
            ExecutionResult<MessageResponse> validateResult = validateAndInitialize();
            if (validateResult.isCompleted()) {
                return validateResult.result();
            }
            context = new ProcessorContext(request, session, message.headers());
            final String msgOp = message.headers().get(MessageConstants.MSG_HEADER_OP);
            
            switch (msgOp) {
                case MessageConstants.MSG_OP_FIREBASE_JWT_CREATION:
                    result = processJwtGeneration(context);
                    break;
                default:
                    result = CommandProcessorBuilder.lookupBuilder(msgOp).build(context).process();
            }
        } catch (VersionDeprecatedException e) {
            LOGGER.error("Version is deprecated");
            return MessageResponseFactory.createVersionDeprecatedResponse();
        } catch (Throwable e) {
            LOGGER.error("Unhandled exception in processing", e);
            return MessageResponseFactory.createInternalErrorResponse();
        }
        
        return result;
    }

    private ExecutionResult<MessageResponse> validateAndInitialize() {
        if (message == null || !(message.body() instanceof JsonObject)) {
            LOGGER.error("Invalid message received, either null or body of message is not JsonObject ");
            return new ExecutionResult<>(
                MessageResponseFactory.createInvalidRequestResponse(RESOURCE_BUNDLE.getString("invalid.message")),
                ExecutionResult.ExecutionStatus.FAILED);
        }

        String userId = ((JsonObject) message.body()).getString(MessageConstants.MSG_USER_ID);
        if (!ValidationUtils.validateUser(userId)) {
            LOGGER.error("Invalid user id passed. Not authorized.");
            return new ExecutionResult<>(
                MessageResponseFactory.createForbiddenResponse(RESOURCE_BUNDLE.getString("missing.user")),
                ExecutionResult.ExecutionStatus.FAILED);
        }

        session = ((JsonObject) message.body()).getJsonObject(MessageConstants.MSG_KEY_SESSION);
        request = ((JsonObject) message.body()).getJsonObject(MessageConstants.MSG_HTTP_BODY);

        if (session == null || session.isEmpty()) {
            LOGGER.error("Invalid session obtained, probably not authorized properly");
            return new ExecutionResult<>(
                MessageResponseFactory.createForbiddenResponse(RESOURCE_BUNDLE.getString("missing.session")),
                ExecutionResult.ExecutionStatus.FAILED);
        }

        if (request == null) {
            LOGGER.error("Invalid JSON payload on Message Bus");
            return new ExecutionResult<>(
                MessageResponseFactory.createInvalidRequestResponse(RESOURCE_BUNDLE.getString("invalid.payload")),
                ExecutionResult.ExecutionStatus.FAILED);
        }

        return new ExecutionResult<>(null, ExecutionResult.ExecutionStatus.CONTINUE_PROCESSING);
    }

    /*
     * Generate a JWT using information already contained in the Auth-Gateway token.
     * Since we are receiving the token, there is no need for us to authenticate the user again.
     * Instead we generate the JWT based off of the user's uid and send it back to the front end.
     * Currently there are no permissions associated with the token (though we can add "premiumAccount" if needed)
     * and instead the permissions to the DB are set in the DB rules. 
     */
    private MessageResponse processJwtGeneration(ProcessorContext context) {
        LOGGER.info("In the processJwtGeneration");
        try {
            String uid = context.session().getString("user_id");// authenticatedUser.getUuid();
            String jwtToJson;
            HashMap<String, Object> additionalClaims = new HashMap<String, Object>();
            additionalClaims.put(MessageConstants.CLAIM_USERNAME, context.session().getString("username"));

            jwtToJson = FirebaseAuth.getInstance().createCustomToken(uid, additionalClaims).getResult();
            
            if (jwtToJson != null || !jwtToJson.isEmpty()) {
                JsonObject job = new JsonObject();
                job.put("jwt", jwtToJson);
                jwtToJson = job.toString();
                LOGGER.debug("JWT contains: " + jwtToJson);
                return MessageResponseFactory.createOkayResponse(job);
            } else {
                LOGGER.debug("JWT creation failed ");
            }
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        return null;
    }
}
