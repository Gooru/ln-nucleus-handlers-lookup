package org.gooru.nucleus.handlers.lookup.processors.repositories.activejdbc.dbhandlers;

import org.gooru.nucleus.handlers.lookup.constants.HelperConstants;
import org.gooru.nucleus.handlers.lookup.processors.repositories.activejdbc.entities.AJEntityCen21Skills;
import org.gooru.nucleus.handlers.lookup.processors.responses.ExecutionResult;
import org.gooru.nucleus.handlers.lookup.processors.responses.MessageResponse;
import org.gooru.nucleus.handlers.lookup.processors.responses.MessageResponseFactory;
import org.javalite.activejdbc.LazyList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

class Cen21SkillsHandler implements DBHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(Cen21SkillsHandler.class);

    @Override
    public ExecutionResult<MessageResponse> executeRequest() {
        JsonObject returnValue;

        String key_classification = null;
        JsonObject topLevelObject = new JsonObject();
        JsonArray subLevelArr = null;
        LazyList<AJEntityCen21Skills> result = AJEntityCen21Skills.findAll()
            .orderBy(AJEntityCen21Skills.KEY_CLASSIFICATION).orderBy(AJEntityCen21Skills.SEQUENCE_ID);
        if (result.size() > 0) {
            topLevelObject.put(HelperConstants.CATEGORIES,HelperConstants.CEN21SKILLS_MODELS);
            for (AJEntityCen21Skills row : result) {

                if ((key_classification == null)
                    || (!row.get(AJEntityCen21Skills.KEY_CLASSIFICATION).equals(key_classification))) {
                    LOGGER.debug(row.get(AJEntityCen21Skills.KEY_CLASSIFICATION).toString());
                    // key_classification value has changed...
                    key_classification = row.get(AJEntityCen21Skills.KEY_CLASSIFICATION).toString();
                    subLevelArr = new JsonArray();
                    topLevelObject.put(key_classification, subLevelArr);
                }

                // key classification value has not changed.
                JsonObject subLevelObject = new JsonObject();
                subLevelObject.put(AJEntityCen21Skills.ID, row.get(AJEntityCen21Skills.ID));
                subLevelObject.put(AJEntityCen21Skills.LABEL, row.get(AJEntityCen21Skills.LABEL).toString());
                subLevelObject.put(AJEntityCen21Skills.TWENTY_LEARNING_MODELS_1,
                    row.get(AJEntityCen21Skills.TWENTY_LEARNING_MODELS_1));
                subLevelObject.put(AJEntityCen21Skills.TWENTY_LEARNING_MODELS_2,
                    row.get(AJEntityCen21Skills.TWENTY_LEARNING_MODELS_2));
                subLevelObject.put(AJEntityCen21Skills.TWENTY_LEARNING_MODELS_3,
                    row.get(AJEntityCen21Skills.TWENTY_LEARNING_MODELS_3));
                subLevelObject.put(AJEntityCen21Skills.TWENTY_LEARNING_MODELS_4,
                    row.get(AJEntityCen21Skills.TWENTY_LEARNING_MODELS_4));

                subLevelArr.add(subLevelObject);  // keep adding just to
                                                  // array...we already added
                                                  // this to toplevel object
            }
            returnValue = new JsonObject().put("21_century_skills", topLevelObject);
            return new ExecutionResult<>(MessageResponseFactory.createOkayResponse(returnValue),
                ExecutionResult.ExecutionStatus.SUCCESSFUL);
        }
        return new ExecutionResult<>(MessageResponseFactory.createInternalErrorResponse(),
            ExecutionResult.ExecutionStatus.FAILED);
    }

    @Override
    public boolean handlerReadOnly() {
        return true;
    }

}
