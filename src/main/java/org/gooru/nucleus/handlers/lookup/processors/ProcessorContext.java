package org.gooru.nucleus.handlers.lookup.processors;

import org.gooru.nucleus.handlers.lookup.constants.HelperConstants;
import io.vertx.core.MultiMap;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

/**
 * @author ashish on 2/1/17.
 */
public class ProcessorContext {
  private final JsonObject request;
  private final JsonObject session;
  private final MultiMap requestHeaders;

  public ProcessorContext(JsonObject request, JsonObject session, MultiMap headers) {
    if (request == null || session == null || session.isEmpty() || headers == null
        || headers.isEmpty()) {
      throw new IllegalStateException(
          "Processor Context creation failed because of invalid values");
    }
    this.session = session.copy();
    this.request = request != null ? request.copy() : null;
    this.requestHeaders = headers;
  }

  public JsonObject session() {
    return this.session.copy();
  }

  public JsonObject request() {
    return this.request;
  }

  public MultiMap requestHeaders() {
    return requestHeaders;
  }

  public String keyword() {
    return getRequestParamAsString(HelperConstants.KEYWORD);
  }

  public String stateId() {
    return getRequestParamAsString(HelperConstants.STATE_ID);
  }

  public String schoolId() {
    return getRequestParamAsString(HelperConstants.SCHOOL_ID);
  }

  public String schoolDistrictId() {
    return getRequestParamAsString(HelperConstants.SCHOOL_DISTRICT_ID);
  }

  public int limit() {
    return getRequestParamAsInt(HelperConstants.LIMIT, 20);
  }

  public int offset() {
    return getRequestParamAsInt(HelperConstants.OFFSET, 0);
  }

  public String countryId() {
    return requestHeaders.get(HelperConstants.COUNTRY_ID);
  }

  private String getRequestParamAsString(String keyword) {
    if (request.containsKey(keyword)) {
      JsonArray keywordParam = request.getJsonArray(keyword);
      if (keywordParam != null) {
        return keywordParam.getString(0);
      }
    }
    return null;
  }

  private int getRequestParamAsInt(String keyword, int defaultValue) {
    if (request.containsKey(HelperConstants.LIMIT)) {
      JsonArray keywordParam = request.getJsonArray(keyword);
      if (keywordParam != null) {
        return Integer.parseInt(keywordParam.getString(0));
      }
    }
    return defaultValue;
  }

}
