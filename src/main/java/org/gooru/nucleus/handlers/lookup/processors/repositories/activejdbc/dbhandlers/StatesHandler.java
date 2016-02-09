package org.gooru.nucleus.handlers.lookup.processors.repositories.activejdbc.dbhandlers;

import org.gooru.nucleus.handlers.lookup.processors.repositories.activejdbc.entities.AJEntityState;
import org.gooru.nucleus.handlers.lookup.processors.responses.ExecutionResult;
import org.gooru.nucleus.handlers.lookup.processors.responses.MessageResponse;
import org.gooru.nucleus.handlers.lookup.processors.responses.MessageResponseFactory;
import org.javalite.activejdbc.LazyList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

class StatesHandler implements DBHandler {
  private static final Logger LOGGER = LoggerFactory.getLogger(StatesHandler.class);
  private final String countryId;
  private final String keyword;

  public StatesHandler(String countryId, String keyword) {
    this.keyword = keyword;
    this.countryId = countryId;
  }

  @Override
  public ExecutionResult<MessageResponse> executeRequest() {
    JsonObject returnValue = null;
    if (keyword == null || keyword.isEmpty()) {
      return new ExecutionResult<>(MessageResponseFactory.createInvalidRequestResponse(), ExecutionResult.ExecutionStatus.FAILED);
    }
    String state_keyword = "%" + keyword + "%";
    LOGGER.debug(state_keyword + "-" + countryId);
    LazyList<AJEntityState> result = AJEntityState.where("country_id = ?::uuid AND name ilike ?", countryId, state_keyword);
    returnValue = new JsonObject().put("states", new JsonArray(result.toJson(false, "id", "name", "code", "country_id")));
    return new ExecutionResult<>(MessageResponseFactory.createOkayResponse(returnValue), ExecutionResult.ExecutionStatus.SUCCESSFUL);
   
  }


  @Override
  public boolean handlerReadOnly() {
    return true;
  }

}
