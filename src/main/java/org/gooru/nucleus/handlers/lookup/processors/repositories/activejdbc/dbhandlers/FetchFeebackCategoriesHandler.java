package org.gooru.nucleus.handlers.lookup.processors.repositories.activejdbc.dbhandlers;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import org.gooru.nucleus.handlers.lookup.constants.MessageConstants;
import org.gooru.nucleus.handlers.lookup.processors.exceptions.MessageResponseWrapperException;
import org.gooru.nucleus.handlers.lookup.processors.repositories.activejdbc.entities.AJEntityFeebackCategory;
import org.gooru.nucleus.handlers.lookup.processors.responses.ExecutionResult;
import org.gooru.nucleus.handlers.lookup.processors.responses.ExecutionResult.ExecutionStatus;
import org.gooru.nucleus.handlers.lookup.processors.responses.MessageResponse;
import org.gooru.nucleus.handlers.lookup.processors.responses.MessageResponseFactory;
import org.javalite.activejdbc.LazyList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

class FetchFeebackCategoriesHandler implements DBHandler {

  private final String userCategoryId;
  private final String[] RESPONSE_FIELDS = {"category_name", "feedback_type_id", "max_scale", "id"};
  private final String LIST_FEEDBACK_CATEGORY_FLT_BY_QUERY = "user_category_id = ?::smallint";
  private static final Map<String, String> CONTENT_TYPES = new HashMap<>();
  private final String CONTENT_TYPE = "content_type";

  static {
    CONTENT_TYPES.put("assessment", "assessments");
    CONTENT_TYPES.put("collection", "collections");
    CONTENT_TYPES.put("assessment-external", "externalAssessments");
    CONTENT_TYPES.put("collection-external", "externalCollections");
    CONTENT_TYPES.put("offline-activity", "offlineActivities");
    CONTENT_TYPES.put("resource", "resources");
    CONTENT_TYPES.put("question", "questions");
    CONTENT_TYPES.put("course", "courses");
    Collections.unmodifiableMap(CONTENT_TYPES);
  }
  private static final Logger LOGGER = LoggerFactory.getLogger(FetchFeebackCategoriesHandler.class);

  private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("messages");

  public FetchFeebackCategoriesHandler(String userCategoryId) {
    this.userCategoryId = userCategoryId;
  }

  @Override
  public ExecutionResult<MessageResponse> checkSanity() {
    try {
      validateUserCategoryId();
    } catch (MessageResponseWrapperException mrwe) {
      return new ExecutionResult<>(mrwe.getMessageResponse(),
          ExecutionResult.ExecutionStatus.FAILED);
    }
    LOGGER.debug("checkSanity() OK");
    return new ExecutionResult<>(null, ExecutionStatus.CONTINUE_PROCESSING);
  }

  @Override
  public ExecutionResult<MessageResponse> executeRequest() {
    LazyList<AJEntityFeebackCategory> resultSet =
        AJEntityFeebackCategory.where(LIST_FEEDBACK_CATEGORY_FLT_BY_QUERY, userCategoryId);
    JsonObject response = new JsonObject().put(MessageConstants.RESP_FEEDBACK_CATEGORIES,
        populateFeedbacks(resultSet));
    return new ExecutionResult<>(MessageResponseFactory.createOkayResponse(response),
        ExecutionResult.ExecutionStatus.SUCCESSFUL);
  }


  private void validateUserCategoryId() {
    if (userCategoryId == null) {
      throw new MessageResponseWrapperException(MessageResponseFactory
          .createInvalidRequestResponse(RESOURCE_BUNDLE.getString("missing.user.category.id")));
    }
  }

  private JsonObject populateFeedbacks(LazyList<AJEntityFeebackCategory> resultSet) {
    JsonObject responses = new JsonObject();
    CONTENT_TYPES.forEach((contentType, value) -> {
      JsonArray feedbacks = new JsonArray();
      resultSet.forEach(feedback -> {
        if (feedback.getString(CONTENT_TYPE).equalsIgnoreCase(contentType)) {
          feedbacks.add(new JsonObject(feedback.toJson(true, RESPONSE_FIELDS)));
        }
      });
      responses.put(value, feedbacks);
    });
    return responses;
  }

  @Override
  public boolean handlerReadOnly() {
    return true;
  }

}
