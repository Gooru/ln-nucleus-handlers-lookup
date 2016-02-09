package org.gooru.nucleus.handlers.lookup.constants;

public final class MessageConstants {

  public static final String MSG_HEADER_OP = "mb.operation";
  public static final String MSG_HEADER_TOKEN = "session.token";
  public static final String MSG_OP_AUTH_WITH_PREFS = "auth.with.prefs";
  public static final String MSG_OP_STATUS = "mb.operation.status";
  public static final String MSG_KEY_PREFS = "prefs";
  public static final String MSG_OP_STATUS_SUCCESS = "success";
  public static final String MSG_OP_STATUS_ERROR = "error";
  public static final String MSG_OP_STATUS_VALIDATION_ERROR = "error.validation";
  public static final String MSG_USER_ANONYMOUS = "anonymous";
  public static final String MSG_USER_ID = "user_id";
  public static final String MSG_HTTP_STATUS = "http.status";
  public static final String MSG_HTTP_BODY = "http.body";
  public static final String MSG_HTTP_RESPONSE = "http.response";
  public static final String MSG_HTTP_ERROR = "http.error";
  public static final String MSG_HTTP_VALIDATION_ERROR = "http.validation.error";
  public static final String MSG_HTTP_HEADERS = "http.headers";

  // Operation names: Also need to be updated in corresponding handlers
  public static final String MSG_OP_LKUP_ACCESS_HAZARDS = "access.hazards";
  public static final String MSG_OP_LKUP_EDU_USE = "educational.use";
  public static final String MSG_OP_LKUP_READ_LEVEL = "reading.level";
  public static final String MSG_OP_LKUP_AD_STATUS = "ad.status";
  public static final String MSG_OP_LKUP_MEDIA_FEATURES = "media.features";
  public static final String MSG_OP_LKUP_GRADE = "grades";
  public static final String MSG_OP_LKUP_21_CEN_SKILLS = "21.cen.skills";

  public static final String MSG_OP_LKUP_AUDIENCE = "audience";
  public static final String MSG_OP_LKUP_MOMENTS = "moments";
  public static final String MSG_OP_LKUP_DOK = "dok";
  public static final String MSG_OP_LKUP_COUNTRIES = "countries";
  public static final String MSG_OP_LKUP_STATES = "states";
  public static final String MSG_OP_LKUP_SCHOOLDISTRICTS = "school-districts";
  public static final String MSG_OP_LKUP_SCHOOLS = "schools";
  public static final String ID_COUNTRY = "countryId";
  public static final String ID_SCHOOLDISTRICT = "school_district_id";
  public static final String ID_KEYWORD = "keyword";

  // Containers for different responses
  public static final String RESP_CONTAINER_MBUS = "mb.container";
  public static final String RESP_CONTAINER_EVENT = "mb.event";

  private MessageConstants() {
    throw new AssertionError();
  }

}
