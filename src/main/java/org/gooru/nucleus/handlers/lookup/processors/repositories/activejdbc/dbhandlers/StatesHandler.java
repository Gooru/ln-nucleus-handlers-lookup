package org.gooru.nucleus.handlers.lookup.processors.repositories.activejdbc.dbhandlers;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

import org.gooru.nucleus.handlers.lookup.constants.HelperConstants;
import org.gooru.nucleus.handlers.lookup.constants.MessageConstants;
import org.gooru.nucleus.handlers.lookup.processors.repositories.activejdbc.entities.AJEntityState;
import org.gooru.nucleus.handlers.lookup.processors.responses.ExecutionResult;
import org.gooru.nucleus.handlers.lookup.processors.responses.MessageResponse;
import org.gooru.nucleus.handlers.lookup.processors.responses.MessageResponseFactory;
import org.javalite.activejdbc.LazyList;

class StatesHandler implements DBHandler {
  private final String countryId;
  private final String keyword;
  private final int limit;
  private final int offset;
  private final String[] RESPONSE_FIELDS = { "id", "name", "code" };
  private final String LIST_STATE = "country_id = ?::uuid AND name ilike ?";
  private final String LIST_STATE_FLT_BY_COUNTRY = "country_id = ?::uuid";

  public StatesHandler(String countryId, String keyword, int limit, int offset) {
    this.keyword = keyword;
    this.countryId = countryId;
    this.limit = limit;
    this.offset = offset;
  }

  @Override
  public ExecutionResult<MessageResponse> executeRequest() {
    LazyList<AJEntityState> result = null;
    JsonObject returnValue = null;
    if (keyword != null && !keyword.isEmpty()) {
      result =
          AJEntityState.where(LIST_STATE, countryId, HelperConstants.PRECENTAGE + keyword + HelperConstants.PRECENTAGE).limit(limit).offset(offset)
              .orderBy(HelperConstants.NAME);
    } else {
      result = AJEntityState.where(LIST_STATE_FLT_BY_COUNTRY, countryId).limit(limit).offset(offset).orderBy(HelperConstants.NAME);
    }
    returnValue = new JsonObject().put(MessageConstants.MSG_OP_LKUP_STATES, new JsonArray(result.toJson(false, RESPONSE_FIELDS)));
    return new ExecutionResult<>(MessageResponseFactory.createOkayResponse(returnValue), ExecutionResult.ExecutionStatus.SUCCESSFUL);
  }

  @Override
  public boolean handlerReadOnly() {
    return true;
  }

}
