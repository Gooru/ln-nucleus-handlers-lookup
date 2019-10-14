package org.gooru.nucleus.handlers.lookup.constants;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class HelperConstants {
  public static final String COUNTRY_ID = "countryId";
  public static final String STATE_ID = "state_id";
  public static final String SCHOOL_DISTRICT_ID = "school_district_id";
  public static final String SCHOOL_ID = "school_id";
  public static final String KEYWORD = "keyword";
  public static final String LIMIT = "limit";
  public static final String OFFSET = "offset";
  public static final String NAME = "name";
  public static final String PRECENTAGE = "%";
  public static final String CATEGORIES = "categories";
  public static final List<String> CEN21SKILLS_MODELS =
      Collections.unmodifiableList(Arrays.asList("Hewlett Deep Learning Model", "Conley Four Key",
          "P21 Framework", "National Research Center for Life and Work"));
  public static final String CONTENT_TYPE = "content_type";
  public static final String USER_CATEGORY_ID = "user_category_id";

  private HelperConstants() {
    throw new AssertionError();
  }
}
