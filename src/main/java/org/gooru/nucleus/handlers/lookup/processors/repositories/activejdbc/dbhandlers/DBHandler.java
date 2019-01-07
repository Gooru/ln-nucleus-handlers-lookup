package org.gooru.nucleus.handlers.lookup.processors.repositories.activejdbc.dbhandlers;

import org.gooru.nucleus.handlers.lookup.processors.responses.ExecutionResult;
import org.gooru.nucleus.handlers.lookup.processors.responses.MessageResponse;

/**
 * Created by ashish on 11/1/16.
 */
public interface DBHandler {

  default ExecutionResult<MessageResponse> checkSanity() {
    return new ExecutionResult<>(null, ExecutionResult.ExecutionStatus.CONTINUE_PROCESSING);
  }

  default ExecutionResult<MessageResponse> validateRequest() {
    return new ExecutionResult<>(null, ExecutionResult.ExecutionStatus.CONTINUE_PROCESSING);

  }

  ExecutionResult<MessageResponse> executeRequest();

  boolean handlerReadOnly();
}
