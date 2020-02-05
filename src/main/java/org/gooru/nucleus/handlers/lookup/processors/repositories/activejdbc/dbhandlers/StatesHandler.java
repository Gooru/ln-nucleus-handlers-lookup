package org.gooru.nucleus.handlers.lookup.processors.repositories.activejdbc.dbhandlers;

import org.gooru.nucleus.handlers.lookup.constants.HelperConstants;
import org.gooru.nucleus.handlers.lookup.constants.MessageConstants;
import org.gooru.nucleus.handlers.lookup.processors.repositories.activejdbc.entities.AJEntityState;
import org.gooru.nucleus.handlers.lookup.processors.responses.ExecutionResult;
import org.gooru.nucleus.handlers.lookup.processors.responses.MessageResponse;
import org.gooru.nucleus.handlers.lookup.processors.responses.MessageResponseFactory;
import org.javalite.activejdbc.LazyList;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

class StatesHandler implements DBHandler {
  private final String countryId;
  private final String keyword;
  private final String[] RESPONSE_FIELDS = {"id", "name", "code"};
  private final String LIST_STATE = "country_id = ?::uuid AND name ilike ?";
  private final String LIST_STATE_FLT_BY_COUNTRY = "country_id = ?::uuid";

  public StatesHandler(String countryId, String keyword) {
    this.keyword = keyword;
    this.countryId = countryId;
  }

  @Override
  public ExecutionResult<MessageResponse> executeRequest() {
    LazyList<AJEntityState> result;
    JsonObject returnValue;
    if (keyword != null && !keyword.isEmpty()) {
      result = AJEntityState.where(LIST_STATE, countryId, keyword + HelperConstants.PRECENTAGE)
          .orderBy(HelperConstants.NAME);
    } else {
      result =
          AJEntityState.where(LIST_STATE_FLT_BY_COUNTRY, countryId).orderBy(HelperConstants.NAME);
    }
    returnValue = new JsonObject().put(MessageConstants.MSG_OP_LKUP_STATES,
        new JsonArray(result.toJson(false, RESPONSE_FIELDS)));
    return new ExecutionResult<>(MessageResponseFactory.createOkayResponse(returnValue),
        ExecutionResult.ExecutionStatus.SUCCESSFUL);
  }

  @Override
  public boolean handlerReadOnly() {
    return true;
  }

}
