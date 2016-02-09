package org.gooru.nucleus.handlers.lookup.processors.repositories.activejdbc.dbhandlers;

import org.gooru.nucleus.handlers.lookup.processors.repositories.activejdbc.entities.AJEntityCountry;
import org.gooru.nucleus.handlers.lookup.processors.responses.ExecutionResult;
import org.gooru.nucleus.handlers.lookup.processors.responses.MessageResponse;
import org.gooru.nucleus.handlers.lookup.processors.responses.MessageResponseFactory;
import org.javalite.activejdbc.LazyList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

class CountriesHandler implements DBHandler {

  private static final Logger LOGGER = LoggerFactory.getLogger(CountriesHandler.class);
  private final String keyword;

  public CountriesHandler(String keyword) {
    this.keyword = keyword;
  }
  
  @Override
  public ExecutionResult<MessageResponse> executeRequest() {
    JsonObject returnValue = null;
    LOGGER.debug(keyword);
    if (keyword == null || keyword.isEmpty()) {
      return new ExecutionResult<>(MessageResponseFactory.createInvalidRequestResponse(), ExecutionResult.ExecutionStatus.FAILED);
    }
    String country_keyword = "%" + keyword + "%";
    LOGGER.debug(country_keyword);
    LazyList<AJEntityCountry> result = AJEntityCountry.where("name ilike ? ", country_keyword);
    returnValue = new JsonObject().put("countries", new JsonArray(result.toJson(true, "id", "name", "code")));
    return new ExecutionResult<>(MessageResponseFactory.createOkayResponse(returnValue), ExecutionResult.ExecutionStatus.SUCCESSFUL);
  }

  @Override
  public boolean handlerReadOnly() {
    return true;
  }
}

