package org.gooru.nucleus.handlers.lookup.processors.repositories.activejdbc.entities;

import java.util.Arrays;
import java.util.List;
import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.Table;

/**
 * Created by renuka
 */
@Table("gooru_language")
public class AJEntityGooruLanguage extends Model {
  public static final String SEQUENCE_ID = "sequence_id";
  public static final String LANGUAGES = "languages";
  public static final List<String> FETCH_FIELDS =
      Arrays.asList("id", "name", "display_name", "description", "sequence_id");

  public static final String FETCH_LANGUAGES = "is_visible = true";

}
