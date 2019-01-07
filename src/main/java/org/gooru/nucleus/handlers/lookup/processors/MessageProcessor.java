package org.gooru.nucleus.handlers.lookup.processors;

import java.util.ResourceBundle;
import org.gooru.nucleus.handlers.lookup.constants.MessageConstants;
import org.gooru.nucleus.handlers.lookup.processors.commands.CommandProcessorBuilder;
import org.gooru.nucleus.handlers.lookup.processors.exceptions.VersionDeprecatedException;
import org.gooru.nucleus.handlers.lookup.processors.responses.ExecutionResult;
import org.gooru.nucleus.handlers.lookup.processors.responses.MessageResponse;
import org.gooru.nucleus.handlers.lookup.processors.responses.MessageResponseFactory;
import org.gooru.nucleus.handlers.lookup.processors.utils.ValidationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.vertx.core.eventbus.Message;
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

      result = CommandProcessorBuilder.lookupBuilder(msgOp).build(context).process();
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
          MessageResponseFactory
              .createInvalidRequestResponse(RESOURCE_BUNDLE.getString("invalid.message")),
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
          MessageResponseFactory
              .createForbiddenResponse(RESOURCE_BUNDLE.getString("missing.session")),
          ExecutionResult.ExecutionStatus.FAILED);
    }

    if (request == null) {
      LOGGER.error("Invalid JSON payload on Message Bus");
      return new ExecutionResult<>(
          MessageResponseFactory
              .createInvalidRequestResponse(RESOURCE_BUNDLE.getString("invalid.payload")),
          ExecutionResult.ExecutionStatus.FAILED);
    }

    return new ExecutionResult<>(null, ExecutionResult.ExecutionStatus.CONTINUE_PROCESSING);
  }

}
