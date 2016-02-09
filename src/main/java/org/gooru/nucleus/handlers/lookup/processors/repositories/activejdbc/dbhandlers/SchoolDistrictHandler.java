package org.gooru.nucleus.handlers.lookup.processors.repositories.activejdbc.dbhandlers;

import org.gooru.nucleus.handlers.lookup.processors.repositories.activejdbc.entities.AJEntitySchoolDistrict;
import org.gooru.nucleus.handlers.lookup.processors.responses.ExecutionResult;
import org.gooru.nucleus.handlers.lookup.processors.responses.MessageResponse;
import org.gooru.nucleus.handlers.lookup.processors.responses.MessageResponseFactory;
import org.javalite.activejdbc.LazyList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

class SchoolDistrictHandler implements DBHandler {
  private static final Logger LOGGER = LoggerFactory.getLogger(SchoolDistrictHandler.class);
  private final String keyword;

  public SchoolDistrictHandler(String keyword) {
    this.keyword = keyword;
  }

  @Override
  public ExecutionResult<MessageResponse> executeRequest() {
    JsonObject returnValue = null;
    if (keyword == null || keyword.isEmpty()) {
      return new ExecutionResult<>(MessageResponseFactory.createInvalidRequestResponse(), ExecutionResult.ExecutionStatus.FAILED);
    }
    String schooldistrict_keyword = "%" + keyword + "%";
    LOGGER.debug(schooldistrict_keyword);
    LazyList<AJEntitySchoolDistrict> result = AJEntitySchoolDistrict.where("name ilike ? " , schooldistrict_keyword);
    returnValue = new JsonObject().put("school-districts", new JsonArray(result.toJson(false, "id", "name", "code")));
    return new ExecutionResult<>(MessageResponseFactory.createOkayResponse(returnValue), ExecutionResult.ExecutionStatus.SUCCESSFUL);

  }


  @Override
  public boolean handlerReadOnly() {
    return true;
  }
}
