package org.gooru.nucleus.handlers.lookup.processors.utils;

import io.vertx.core.json.JsonObject;

public class APIKeyConfigUtil {

  private static JsonObject apikeyConfig = null;
  private static final String APIKEY_CONFIG_KEY = "apikey.config";

  private APIKeyConfigUtil() {
    throw new AssertionError();
  }

  public static JsonObject getAPIKeyConfig() {
    return apikeyConfig;
  }

  public static void initialize(JsonObject config) {
    apikeyConfig = config.getJsonObject(APIKEY_CONFIG_KEY);

    if (apikeyConfig == null || apikeyConfig.isEmpty()) {
      throw new IllegalStateException("API Key config missing, aborting");
    }
  }
}
