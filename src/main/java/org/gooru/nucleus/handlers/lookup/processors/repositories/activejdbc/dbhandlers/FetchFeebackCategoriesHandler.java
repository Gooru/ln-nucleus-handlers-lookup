package org.gooru.nucleus.handlers.lookup.processors.repositories.activejdbc.dbhandlers;

import java.util.Arrays;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
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

  private final String contentType;
  private final String userCategoryId;
  private final String[] RESPONSE_FIELDS = {"category_name", "feedback_type_id", "max_scale", "id"};
  private final String LIST_FEEDBACK_CATEGORY_FLT_BY_QUERY =
      "content_type = ? AND user_category_id = ?::smallint";
  private static final Set<String> CONTENT_TYPES =
      new HashSet<>(Arrays.asList("assessment", "collection", "assessment-external",
          "collection-external", "offline-activity", "resource", "question", "course"));
  private static final Logger LOGGER = LoggerFactory.getLogger(FetchFeebackCategoriesHandler.class);

  private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("messages");

  public FetchFeebackCategoriesHandler(String contentType, String userCategoryId) {
    this.contentType = contentType;
    this.userCategoryId = userCategoryId;
  }

  @Override
  public ExecutionResult<MessageResponse> checkSanity() {
    try {
      validateContentType();
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
    LazyList<AJEntityFeebackCategory> result =
        AJEntityFeebackCategory.where(LIST_FEEDBACK_CATEGORY_FLT_BY_QUERY, contentType, userCategoryId);
    JsonObject response = new JsonObject().put(MessageConstants.RESP_FEEDBACK_CATEGORIES,
        new JsonArray(result.toJson(true, RESPONSE_FIELDS)));
    return new ExecutionResult<>(MessageResponseFactory.createOkayResponse(response),
        ExecutionResult.ExecutionStatus.SUCCESSFUL);
  }

  private void validateContentType() {
    if (contentType == null || contentType.isEmpty()) {
      throw new MessageResponseWrapperException(MessageResponseFactory
          .createInvalidRequestResponse(RESOURCE_BUNDLE.getString("missing.content.type")));
    }
    if (!CONTENT_TYPES.contains(contentType)) {
      throw new MessageResponseWrapperException(MessageResponseFactory
          .createInvalidRequestResponse(RESOURCE_BUNDLE.getString("invalid.content.type")));
    }
  }

  private void validateUserCategoryId() {
    if (userCategoryId == null) {
      throw new MessageResponseWrapperException(MessageResponseFactory
          .createInvalidRequestResponse(RESOURCE_BUNDLE.getString("missing.user.category.id")));
    }
  }

  @Override
  public boolean handlerReadOnly() {
    return true;
  }
}
