package org.gooru.nucleus.handlers.lookup.processors.repositories.activejdbc.dbhandlers;

import org.gooru.nucleus.handlers.lookup.constants.HelperConstants;
import org.gooru.nucleus.handlers.lookup.constants.MessageConstants;
import org.gooru.nucleus.handlers.lookup.processors.repositories.activejdbc.entities.AJEntitySchool;
import org.gooru.nucleus.handlers.lookup.processors.responses.ExecutionResult;
import org.gooru.nucleus.handlers.lookup.processors.responses.MessageResponse;
import org.gooru.nucleus.handlers.lookup.processors.responses.MessageResponseFactory;
import org.javalite.activejdbc.LazyList;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

class SchoolHandler implements DBHandler {

    private final String keyword;
    private final String schoolDistrictId;
    private final int limit;
    private final int offset;
    private final String[] RESPONSE_FIELDS = { "id", "name", "code" };
    private final String LIST_SCHOOL = "name ilike ? AND school_district_id = ?::uuid";
    private final String LIST_SCHOOL_FLT_BY_SCHOOL_DISTRICT = "school_district_id = ?::uuid";
    private final String LIST_SCHOOL_FLT_BY_QUERY = "name ilike ?";

    public SchoolHandler(String keyword, String schoolDistrictId, int limit, int offset) {
        this.keyword = keyword;
        this.schoolDistrictId = schoolDistrictId;
        this.limit = limit;
        this.offset = offset;
    }

    @Override
    public ExecutionResult<MessageResponse> executeRequest() {
        JsonObject returnValue;
        LazyList<AJEntitySchool> result;
        if (keyword != null && !keyword.isEmpty() && schoolDistrictId != null) {
            result = AJEntitySchool.where(LIST_SCHOOL, keyword + HelperConstants.PRECENTAGE, schoolDistrictId)
                .limit(limit).offset(offset).orderBy(HelperConstants.NAME);
        } else if (keyword != null && !keyword.isEmpty()) {
            result = AJEntitySchool.where(LIST_SCHOOL_FLT_BY_QUERY, keyword + HelperConstants.PRECENTAGE).limit(limit)
                .offset(offset).orderBy(HelperConstants.NAME);
        } else if (schoolDistrictId != null) {
            result = AJEntitySchool.where(LIST_SCHOOL_FLT_BY_SCHOOL_DISTRICT, schoolDistrictId).limit(limit)
                .offset(offset).orderBy(HelperConstants.NAME);
        } else {
            result = AJEntitySchool.findAll().limit(limit).offset(offset).orderBy(HelperConstants.NAME);
        }
        returnValue = new JsonObject().put(MessageConstants.MSG_OP_LKUP_SCHOOLS,
            new JsonArray(result.toJson(false, RESPONSE_FIELDS)));
        return new ExecutionResult<>(MessageResponseFactory.createOkayResponse(returnValue),
            ExecutionResult.ExecutionStatus.SUCCESSFUL);

    }

    @Override
    public boolean handlerReadOnly() {
        return true;
    }

}
