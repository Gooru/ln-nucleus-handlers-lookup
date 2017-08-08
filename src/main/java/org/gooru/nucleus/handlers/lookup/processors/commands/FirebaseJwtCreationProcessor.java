package org.gooru.nucleus.handlers.lookup.processors.commands;

import java.util.HashMap;

import org.gooru.nucleus.handlers.lookup.constants.MessageConstants;
import org.gooru.nucleus.handlers.lookup.processors.Processor;
import org.gooru.nucleus.handlers.lookup.processors.ProcessorContext;
import org.gooru.nucleus.handlers.lookup.processors.responses.MessageResponse;
import org.gooru.nucleus.handlers.lookup.processors.responses.MessageResponseFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.firebase.auth.FirebaseAuth;

import io.vertx.core.json.JsonObject;

/**
 * @author ashish on 3/2/17.
 */
class FirebaseJwtCreationProcessor  extends AbstractCommandProcessor {
    private static final Logger LOGGER = LoggerFactory.getLogger(FirebaseJwtCreationProcessor.class);
    private static final String JWT = "jwt";

    FirebaseJwtCreationProcessor(ProcessorContext context) {
        super(context);
    }

    @Override
    protected void setDeprecatedVersions() {
        // NOOP
    }

    /*
     * Generate a JWT using information already contained in the Auth-Gateway token.
     * Since we are receiving the token, there is no need for us to authenticate the user again.
     * Instead we generate the JWT based off of the user's uid and send it back to the front end.
     * Currently there are no permissions associated with the token (though we can add "premiumAccount" if needed)
     * and instead the permissions to the DB are set in the DB rules.
     */
    @Override
    protected MessageResponse processCommand() {
        LOGGER.debug("In the processJwtGeneration");
        String uid = context.session().getString(MessageConstants.MSG_USER_ID);// authenticatedUser.getUuid();
        String jwtToJson;
        HashMap<String, Object> additionalClaims = new HashMap<>();
        additionalClaims
            .put(MessageConstants.CLAIM_USERNAME, context.session().getString(MessageConstants.CLAIM_USERNAME));

        jwtToJson = FirebaseAuth.getInstance().createCustomToken(uid, additionalClaims).getResult();

        if (jwtToJson != null || !jwtToJson.isEmpty()) {
            JsonObject job = new JsonObject();
            job.put(JWT, jwtToJson);
            jwtToJson = job.toString();
            LOGGER.debug("JWT contains: {}", jwtToJson);
            return MessageResponseFactory.createOkayResponse(job);
        } else {
            LOGGER.debug("JWT creation failed ");
            throw new IllegalStateException("JWT creation returned null");
        }
    }
}
