package org.gooru.nucleus.handlers.lookup.processors.repositories.activejdbc.dbhandlers;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

import org.gooru.nucleus.handlers.lookup.constants.HelperConstants;
import org.gooru.nucleus.handlers.lookup.constants.MessageConstants;
import org.gooru.nucleus.handlers.lookup.processors.repositories.activejdbc.entities.AJEntityCountry;
import org.gooru.nucleus.handlers.lookup.processors.responses.ExecutionResult;
import org.gooru.nucleus.handlers.lookup.processors.responses.MessageResponse;
import org.gooru.nucleus.handlers.lookup.processors.responses.MessageResponseFactory;
import org.javalite.activejdbc.LazyList;

class CountriesHandler implements DBHandler {

  private final String keyword;
  private final String[] RESPONSE_FIELDS = { "id", "name", "code" };
  private final String LIST_COUNTRY_FLT_BY_QUERY = "name ilike ?";

  public CountriesHandler(String keyword) {
    this.keyword = keyword;
  }

  @Override
  public ExecutionResult<MessageResponse> executeRequest() {
    JsonObject returnValue = null;
    LazyList<AJEntityCountry> result = null;
    if (keyword != null && !keyword.isEmpty()) {
      result = AJEntityCountry.where(LIST_COUNTRY_FLT_BY_QUERY, keyword + HelperConstants.PRECENTAGE).orderBy(HelperConstants.NAME);
    } else {
      result = AJEntityCountry.findAll().orderBy(HelperConstants.NAME);
    }
    returnValue = new JsonObject().put(MessageConstants.MSG_OP_LKUP_COUNTRIES, new JsonArray(result.toJson(true, RESPONSE_FIELDS)));
    return new ExecutionResult<>(MessageResponseFactory.createOkayResponse(returnValue), ExecutionResult.ExecutionStatus.SUCCESSFUL);
  }

  @Override
  public boolean handlerReadOnly() {
    return true;
  }
}
