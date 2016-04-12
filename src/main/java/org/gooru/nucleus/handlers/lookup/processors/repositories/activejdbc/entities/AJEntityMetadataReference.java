package org.gooru.nucleus.handlers.lookup.processors.repositories.activejdbc.entities;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.Table;

import java.util.Arrays;
import java.util.List;

/**
 * Created by ashish on 29/12/15.
 */
@Table("metadata_reference")
public class AJEntityMetadataReference extends Model {

  // Meta data types
  public static final String TYPE_READING_LEVEL = "reading_level";
  public static final String TYPE_MEDIA_FEATURE = "media_feature";
  public static final String TYPE_GRADE = "grade";
  public static final String TYPE_EDUCATIONAL_USE = "educational_use";
  public static final String TYPE_ADVERTISEMENT_LEVEL = "advertisement_level";
  public static final String TYPE_HAZARD_LEVEL = "hazard_level";
  public static final String TYPE_MOMENTS_OF_LEARNING = "moments_of_learning";
  public static final String TYPE_DEPTH_OF_KNOWLEDGE = "depth_of_knowledge";
  public static final String TYPE_AUDIENCE = "audience";
  public static final String TYPE_LICENSE = "license";

  public static final List<String> FETCH_FIELDS = Arrays.asList("id", "label", "sequence_id");
  public static final List<String> FETCH_LICENSE_FIELDS = Arrays.asList("id", "label", "info", "sequence_id");

  public static final String FETCH_METADATA_QUERY =
    "select id, label, sequence_id from metadata_reference where format = ?::metadata_reference_type and is_visible = true order by sequence_id";

  public static final String FETCH_METADATA_LICENSE_QUERY =
    "select id, label, info, sequence_id from metadata_reference where format = ?::metadata_reference_type and is_visible = true order by " +
      "sequence_id";

}
