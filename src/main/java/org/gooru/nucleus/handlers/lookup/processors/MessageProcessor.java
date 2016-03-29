package org.gooru.nucleus.handlers.lookup.processors;

import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

import org.gooru.nucleus.handlers.lookup.constants.HelperConstants;
import org.gooru.nucleus.handlers.lookup.constants.MessageConstants;
import org.gooru.nucleus.handlers.lookup.processors.repositories.RepoBuilder;
import org.gooru.nucleus.handlers.lookup.processors.responses.ExecutionResult;
import org.gooru.nucleus.handlers.lookup.processors.responses.MessageResponse;
import org.gooru.nucleus.handlers.lookup.processors.responses.MessageResponseFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ResourceBundle;
import java.util.UUID;

class MessageProcessor implements Processor {

  private static final Logger LOGGER = LoggerFactory.getLogger(Processor.class);
  private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("messages");
  private JsonObject prefs;
  private JsonObject request;
  private String keyword;
  private int limit = 20;
  private int offset = 0;
  private String countryId;
  private String stateId;
  private String schoolDistrictId;
  private final Message<Object> message;

  public MessageProcessor(Message<Object> message) {
    this.message = message;
  }

  @Override
  public MessageResponse process() {
    MessageResponse result;
    try {
      ExecutionResult<MessageResponse> validateResult = validateAndInitialize();
      if (validateResult.isCompleted()) {
        return validateResult.result();
      }
      final String msgOp = message.headers().get(MessageConstants.MSG_HEADER_OP);

      switch (msgOp) {
      case MessageConstants.MSG_OP_LKUP_ACCESS_HAZARDS:
        result = processAccessHazards();
        break;
      case MessageConstants.MSG_OP_LKUP_21_CEN_SKILLS:
        result = process21CenSkills();
        break;
      case MessageConstants.MSG_OP_LKUP_AD_STATUS:
        result = processAdStatus();
        break;
      case MessageConstants.MSG_OP_LKUP_EDU_USE:
        result = processEducationalUse();
        break;
      case MessageConstants.MSG_OP_LKUP_GRADE:
        result = processGrades();
        break;
      case MessageConstants.MSG_OP_LKUP_MEDIA_FEATURES:
        result = processMediaFeatures();
        break;
      case MessageConstants.MSG_OP_LKUP_READ_LEVEL:
        result = processReadingLevels();
        break;
      case MessageConstants.MSG_OP_LKUP_DOK:
        result = processDepthOfKnowledge();
        break;
      case MessageConstants.MSG_OP_LKUP_AUDIENCE:
        result = processAudience();
        break;
      case MessageConstants.MSG_OP_LKUP_MOMENTS:
        result = processMomentsOfLearning();
        break;
      case MessageConstants.MSG_OP_LKUP_COUNTRIES:
        result = processCountries();
        break;
      case MessageConstants.MSG_OP_LKUP_STATES:
        result = processStates(countryId);
        break;
      case MessageConstants.MSG_OP_LKUP_SCHOOLDISTRICTS:
        result = processSchoolDistricts();
        break;
      case MessageConstants.MSG_OP_LKUP_SCHOOLS:
        result = processSchools();
        break;
      default:
        LOGGER.error("Invalid operation type passed in, not able to handle");
        return MessageResponseFactory.createInvalidRequestResponse(RESOURCE_BUNDLE.getString("invalid.item.lookup"));
      }
      return result;
    } catch (Throwable e) {
      LOGGER.error("Unhandled exception in processing", e);
      return MessageResponseFactory.createInternalErrorResponse();
    }
  }

  private MessageResponse processReadingLevels() {
    MessageResponse response;
    JsonObject result = ProcessorCache.getInstance().getReadingLevels();
    if (result == null) {
      response = RepoBuilder.buildMetadataRepo().getReadingLevels();
      if (response.isSuccessful()) {
        JsonObject itemToCache = response.getSuccessResult();
        if (itemToCache != null && !itemToCache.isEmpty()) {
          // Update the cache item
          ProcessorCache.getInstance().setReadingLevels(itemToCache);
        }
      }
    } else {
      response = MessageResponseFactory.createOkayResponse(result);
    }
    return response;
  }

  private MessageResponse processMediaFeatures() {
    MessageResponse response;
    JsonObject result = ProcessorCache.getInstance().getMediaFeatures();
    if (result == null) {
      response = RepoBuilder.buildMetadataRepo().getMediaFeatures();
      if (response.isSuccessful()) {
        JsonObject itemToCache = response.getSuccessResult();
        if (itemToCache != null && !itemToCache.isEmpty()) {
          // Update the cache item
          ProcessorCache.getInstance().setMediaFeatures(itemToCache);
        }
      }
    } else {
      response = MessageResponseFactory.createOkayResponse(result);
    }
    return response;
  }

  private MessageResponse processGrades() {
    MessageResponse response;
    JsonObject result = ProcessorCache.getInstance().getGrades();
    if (result == null) {
      response = RepoBuilder.buildMetadataRepo().getGrades();
      if (response.isSuccessful()) {
        JsonObject itemToCache = response.getSuccessResult();
        if (itemToCache != null && !itemToCache.isEmpty()) {
          // Update the cache item
          ProcessorCache.getInstance().setGrades(itemToCache);
        }
      }
    } else {
      response = MessageResponseFactory.createOkayResponse(result);
    }
    return response;
  }

  private MessageResponse processEducationalUse() {
    MessageResponse response;
    JsonObject result = ProcessorCache.getInstance().getEducationalUse();
    if (result == null) {
      response = RepoBuilder.buildMetadataRepo().getEducationalUse();
      if (response.isSuccessful()) {
        JsonObject itemToCache = response.getSuccessResult();
        if (itemToCache != null && !itemToCache.isEmpty()) {
          // Update the cache item
          ProcessorCache.getInstance().setEducationalUse(itemToCache);
        }
      }
    } else {
      response = MessageResponseFactory.createOkayResponse(result);
    }
    return response;
  }

  private MessageResponse processAdStatus() {
    MessageResponse response;
    JsonObject result = ProcessorCache.getInstance().getAdStatus();
    if (result == null) {
      response = RepoBuilder.buildMetadataRepo().getAdStatus();
      if (response.isSuccessful()) {
        JsonObject itemToCache = response.getSuccessResult();
        if (itemToCache != null && !itemToCache.isEmpty()) {
          // Update the cache item
          ProcessorCache.getInstance().setAdStatus(itemToCache);
        }
      }
    } else {
      response = MessageResponseFactory.createOkayResponse(result);
    }
    return response;
  }

  private MessageResponse process21CenSkills() {
    MessageResponse response;
    JsonObject result = ProcessorCache.getInstance().getCenSkills();
    if (result == null) {
      response = RepoBuilder.buildCen21SkillsRepo().getCen21Skills();
      if (response.isSuccessful()) {
        JsonObject itemToCache = response.getSuccessResult();
        if (itemToCache != null && !itemToCache.isEmpty()) {
          // Update the cache item
          ProcessorCache.getInstance().setCenSkills(itemToCache);
        }
      }
    } else {
      response = MessageResponseFactory.createOkayResponse(result);
    }
    return response;
  }

  private MessageResponse processAccessHazards() {
    MessageResponse response;
    JsonObject result = ProcessorCache.getInstance().getAccessHazards();
    if (result == null) {
      response = RepoBuilder.buildMetadataRepo().getAccessHazards();
      if (response.isSuccessful()) {
        JsonObject itemToCache = response.getSuccessResult();
        if (itemToCache != null && !itemToCache.isEmpty()) {
          // Update the cache item
          ProcessorCache.getInstance().setAccessHazards(itemToCache);
        }
      }
    } else {
      response = MessageResponseFactory.createOkayResponse(result);
    }
    return response;
  }

  private MessageResponse processMomentsOfLearning() {
    MessageResponse response;
    JsonObject result = ProcessorCache.getInstance().getMomentsOfLearning();
    if (result == null) {
      response = RepoBuilder.buildMetadataRepo().getMomentsOfLearning();
      if (response.isSuccessful()) {
        JsonObject itemToCache = response.getSuccessResult();
        if (itemToCache != null && !itemToCache.isEmpty()) {
          // Update the cache item
          ProcessorCache.getInstance().setMomentsOfLearning(itemToCache);
        }
      }
    } else {
      response = MessageResponseFactory.createOkayResponse(result);
    }
    return response;
  }

  private MessageResponse processDepthOfKnowledge() {
    MessageResponse response;
    JsonObject result = ProcessorCache.getInstance().getDepthOfKnowledge();
    if (result == null) {
      response = RepoBuilder.buildMetadataRepo().getDepthOfKnowledge();
      if (response.isSuccessful()) {
        JsonObject itemToCache = response.getSuccessResult();
        if (itemToCache != null && !itemToCache.isEmpty()) {
          // Update the cache item
          ProcessorCache.getInstance().setDepthOfKnowledge(itemToCache);
        }
      }
    } else {
      response = MessageResponseFactory.createOkayResponse(result);
    }
    return response;
  }

  private MessageResponse processAudience() {
    MessageResponse response;
    JsonObject result = ProcessorCache.getInstance().getAudience();
    if (result == null) {
      response = RepoBuilder.buildMetadataRepo().getAudience();
      if (response.isSuccessful()) {
        JsonObject itemToCache = response.getSuccessResult();
        if (itemToCache != null && !itemToCache.isEmpty()) {
          // Update the cache item
          ProcessorCache.getInstance().setAudience(itemToCache);
        }
      }
    } else {
      response = MessageResponseFactory.createOkayResponse(result);
    }
    return response;
  }

  private MessageResponse processCountries() {
    getRequestParam();
    return RepoBuilder.buildCountriesRepo().getCountries(this.keyword);
  }

  private MessageResponse processStates(String countryId) {
    getRequestParam();
    return RepoBuilder.buildStatesRepo().getStates(countryId, this.keyword);
  }

  private MessageResponse processSchoolDistricts() {
    getRequestParam();
    return RepoBuilder.buildSchoolDistrictsRepo().getSchoolDistricts(this.keyword, this.stateId, this.limit, this.offset);
  }

  private MessageResponse processSchools() {
    getRequestParam();
    return RepoBuilder.buildSchoolsRepo().getSchools(this.keyword, this.schoolDistrictId, this.limit, this.offset);
  }

  private ExecutionResult<MessageResponse> validateAndInitialize() {
    if (message == null || !(message.body() instanceof JsonObject)) {
      LOGGER.error("Invalid message received, either null or body of message is not JsonObject ");
      return new ExecutionResult<>(MessageResponseFactory.createInvalidRequestResponse(RESOURCE_BUNDLE.getString("invalid.message")),
          ExecutionResult.ExecutionStatus.FAILED);
    }

    String userId = ((JsonObject) message.body()).getString(MessageConstants.MSG_USER_ID);
    if (!validateUser(userId)) {
      LOGGER.error("Invalid user id passed. Not authorized.");
      return new ExecutionResult<>(MessageResponseFactory.createForbiddenResponse(RESOURCE_BUNDLE.getString("missing.user")),
          ExecutionResult.ExecutionStatus.FAILED);
    }

    prefs = ((JsonObject) message.body()).getJsonObject(MessageConstants.MSG_KEY_PREFS);
    request = ((JsonObject) message.body()).getJsonObject(MessageConstants.MSG_HTTP_BODY);

    if (prefs == null || prefs.isEmpty()) {
      LOGGER.error("Invalid preferences obtained, probably not authorized properly");
      return new ExecutionResult<>(MessageResponseFactory.createForbiddenResponse(RESOURCE_BUNDLE.getString("missing.preferences")),
          ExecutionResult.ExecutionStatus.FAILED);
    }

    if (request == null) {
      LOGGER.error("Invalid JSON payload on Message Bus");
      return new ExecutionResult<>(MessageResponseFactory.createInvalidRequestResponse(RESOURCE_BUNDLE.getString("invalid.payload")),
          ExecutionResult.ExecutionStatus.FAILED);
    }

    countryId = message.headers().get(HelperConstants.COUNTRY_ID);

    // All is well, continue processing
    return new ExecutionResult<>(null, ExecutionResult.ExecutionStatus.CONTINUE_PROCESSING);
  }

  private void getRequestParam() {
    JsonArray keywordParam = null;
    if (request.containsKey(HelperConstants.KEYWORD)) {
      keywordParam = request.getJsonArray(HelperConstants.KEYWORD);
      if (keywordParam != null) {
        this.keyword = keywordParam.getString(0);
      }
    }

    JsonArray stateParam = null;
    if (request.containsKey(HelperConstants.STATE_ID)) {
      stateParam = request.getJsonArray(HelperConstants.STATE_ID);
      if (stateParam != null) {
        this.stateId = stateParam.getString(0);
      }
    }

    JsonArray schoolDistrictParam = null;
    if (request.containsKey(HelperConstants.SCHOOL_DISTRICT_ID)) {
      schoolDistrictParam = request.getJsonArray(HelperConstants.SCHOOL_DISTRICT_ID);
      if (schoolDistrictParam != null) {
        this.schoolDistrictId = schoolDistrictParam.getString(0);
      }
    }

    JsonArray limitParam = null;
    if (request.containsKey(HelperConstants.LIMIT)) {
      limitParam = request.getJsonArray(HelperConstants.LIMIT);
      if (limitParam != null) {
        this.limit = Integer.parseInt(limitParam.getString(0));
      }
    }
    JsonArray offsetParam = null;
    if (request.containsKey(HelperConstants.OFFSET)) {
      offsetParam = request.getJsonArray(HelperConstants.OFFSET);
      if (offsetParam != null) {
        this.offset = Integer.parseInt(offsetParam.getString(0));
      }
    }

  }

  private boolean validateUser(String userId) {
    return !(userId == null || userId.isEmpty()) && (userId.equalsIgnoreCase(MessageConstants.MSG_USER_ANONYMOUS) || validateUuid(userId));
  }

  private boolean validateUuid(String uuidString) {
    try {
      UUID.fromString(uuidString);
      return true;
    } catch (IllegalArgumentException e) {
      return false;
    } catch (Exception e) {
      return false;
    }
  }

}
