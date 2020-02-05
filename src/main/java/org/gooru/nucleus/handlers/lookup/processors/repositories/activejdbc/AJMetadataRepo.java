package org.gooru.nucleus.handlers.lookup.processors.repositories.activejdbc;

import static org.gooru.nucleus.handlers.lookup.processors.repositories.activejdbc.entities.AJEntityMetadataReference.TYPE_ADVERTISEMENT_LEVEL;
import static org.gooru.nucleus.handlers.lookup.processors.repositories.activejdbc.entities.AJEntityMetadataReference.TYPE_AUDIENCE;
import static org.gooru.nucleus.handlers.lookup.processors.repositories.activejdbc.entities.AJEntityMetadataReference.TYPE_DEPTH_OF_KNOWLEDGE;
import static org.gooru.nucleus.handlers.lookup.processors.repositories.activejdbc.entities.AJEntityMetadataReference.TYPE_EDUCATIONAL_USE;
import static org.gooru.nucleus.handlers.lookup.processors.repositories.activejdbc.entities.AJEntityMetadataReference.TYPE_GRADE;
import static org.gooru.nucleus.handlers.lookup.processors.repositories.activejdbc.entities.AJEntityMetadataReference.TYPE_HAZARD_LEVEL;
import static org.gooru.nucleus.handlers.lookup.processors.repositories.activejdbc.entities.AJEntityMetadataReference.TYPE_LICENSE;
import static org.gooru.nucleus.handlers.lookup.processors.repositories.activejdbc.entities.AJEntityMetadataReference.TYPE_MEDIA_FEATURE;
import static org.gooru.nucleus.handlers.lookup.processors.repositories.activejdbc.entities.AJEntityMetadataReference.TYPE_MOMENTS_OF_LEARNING;
import static org.gooru.nucleus.handlers.lookup.processors.repositories.activejdbc.entities.AJEntityMetadataReference.TYPE_READING_LEVEL;
import java.util.List;
import org.gooru.nucleus.handlers.lookup.processors.repositories.MetadataRepo;
import org.gooru.nucleus.handlers.lookup.processors.repositories.activejdbc.dbhandlers.DBHandlerBuilder;
import org.gooru.nucleus.handlers.lookup.processors.repositories.activejdbc.entities.AJEntityMetadataReference;
import org.gooru.nucleus.handlers.lookup.processors.repositories.activejdbc.transactions.TransactionExecutor;
import org.gooru.nucleus.handlers.lookup.processors.responses.MessageResponse;

/**
 * Created by ashish on 29/12/15.
 */
public class AJMetadataRepo implements MetadataRepo {

  @Override
  public MessageResponse getReadingLevels() {
    return getMetadata(TYPE_READING_LEVEL, AJEntityMetadataReference.FETCH_METADATA_QUERY);
  }

  @Override
  public MessageResponse getMediaFeatures() {
    return getMetadata(TYPE_MEDIA_FEATURE, AJEntityMetadataReference.FETCH_METADATA_QUERY);
  }

  @Override
  public MessageResponse getGrades() {
    return getMetadata(TYPE_GRADE, AJEntityMetadataReference.FETCH_METADATA_QUERY);
  }

  @Override
  public MessageResponse getEducationalUse() {
    return getMetadata(TYPE_EDUCATIONAL_USE, AJEntityMetadataReference.FETCH_METADATA_QUERY);
  }

  @Override
  public MessageResponse getAdStatus() {
    return getMetadata(TYPE_ADVERTISEMENT_LEVEL, AJEntityMetadataReference.FETCH_METADATA_QUERY);
  }

  @Override
  public MessageResponse getAccessHazards() {
    return getMetadata(TYPE_HAZARD_LEVEL, AJEntityMetadataReference.FETCH_METADATA_QUERY);

  }

  @Override
  public MessageResponse getMomentsOfLearning() {
    return getMetadata(TYPE_MOMENTS_OF_LEARNING, AJEntityMetadataReference.FETCH_METADATA_QUERY);

  }

  @Override
  public MessageResponse getDepthOfKnowledge() {
    return getMetadata(TYPE_DEPTH_OF_KNOWLEDGE, AJEntityMetadataReference.FETCH_METADATA_QUERY);
  }

  @Override
  public MessageResponse getAudience() {
    return getMetadata(TYPE_AUDIENCE, AJEntityMetadataReference.FETCH_METADATA_QUERY);
  }

  @Override
  public MessageResponse getLicenses() {
    return getMetadata(TYPE_LICENSE, AJEntityMetadataReference.FETCH_METADATA_LICENSE_QUERY,
        AJEntityMetadataReference.FETCH_LICENSE_FIELDS);
  }

  private MessageResponse getMetadata(String name, String sql) {
    return TransactionExecutor.executeTransaction(DBHandlerBuilder
        .fetchRowlistExecutorHandlerBuilder(name, sql, AJEntityMetadataReference.FETCH_FIELDS));
  }

  private MessageResponse getMetadata(String name, String sql, List<String> fieldsInJson) {
    return TransactionExecutor.executeTransaction(
        DBHandlerBuilder.fetchRowlistExecutorHandlerBuilder(name, sql, fieldsInJson));
  }
}
