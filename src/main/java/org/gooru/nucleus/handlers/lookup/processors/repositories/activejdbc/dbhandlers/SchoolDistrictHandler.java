package org.gooru.nucleus.handlers.lookup.processors.repositories.activejdbc.dbhandlers;

import org.gooru.nucleus.handlers.lookup.constants.HelperConstants;
import org.gooru.nucleus.handlers.lookup.constants.MessageConstants;
import org.gooru.nucleus.handlers.lookup.processors.repositories.activejdbc.entities.AJEntitySchoolDistrict;
import org.gooru.nucleus.handlers.lookup.processors.responses.ExecutionResult;
import org.gooru.nucleus.handlers.lookup.processors.responses.MessageResponse;
import org.gooru.nucleus.handlers.lookup.processors.responses.MessageResponseFactory;
import org.javalite.activejdbc.LazyList;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

class SchoolDistrictHandler implements DBHandler {
  private final String keyword;
  private final String stateId;
  private final int limit;
  private final int offset;
  private final String[] RESPONSE_FIELDS = {"id", "name", "code"};
  private final String LIST_SCHOOL_DISTRICT = "state_id = ?::uuid and name ilike ? ";
  private final String LIST_SCHOOL_DISTRICT_FLT_BY_STATE = "state_id = ?::uuid";
  private final String LIST_SCHOOL_DISTRICT_FLT_BY_QUERY = "name ilike ?";

  public SchoolDistrictHandler(String keyword, String stateId, int limit, int offset) {
    this.keyword = keyword;
    this.stateId = stateId;
    this.limit = limit;
    this.offset = offset;
  }

  @Override
  public ExecutionResult<MessageResponse> executeRequest() {
    JsonObject returnValue;
    LazyList<AJEntitySchoolDistrict> result;
    if (keyword != null && !keyword.isEmpty() && stateId != null) {
      result = AJEntitySchoolDistrict
          .where(LIST_SCHOOL_DISTRICT, stateId, keyword + HelperConstants.PRECENTAGE).limit(limit)
          .offset(offset).orderBy(HelperConstants.NAME);
    } else if (stateId != null) {
      result = AJEntitySchoolDistrict.where(LIST_SCHOOL_DISTRICT_FLT_BY_STATE, stateId).limit(limit)
          .offset(offset).orderBy(HelperConstants.NAME);
    } else if (keyword != null && !keyword.isEmpty()) {
      result = AJEntitySchoolDistrict
          .where(LIST_SCHOOL_DISTRICT_FLT_BY_QUERY, keyword + HelperConstants.PRECENTAGE)
          .limit(limit).offset(offset).orderBy(HelperConstants.NAME);
    } else {
      result = AJEntitySchoolDistrict.findAll().limit(limit).offset(offset)
          .orderBy(HelperConstants.NAME);
    }
    returnValue = new JsonObject().put(MessageConstants.MSG_OP_LKUP_SCHOOLDISTRICTS,
        new JsonArray(result.toJson(false, RESPONSE_FIELDS)));
    return new ExecutionResult<>(MessageResponseFactory.createOkayResponse(returnValue),
        ExecutionResult.ExecutionStatus.SUCCESSFUL);

  }

  @Override
  public boolean handlerReadOnly() {
    return true;
  }
}
