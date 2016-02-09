package org.gooru.nucleus.handlers.lookup.processors.repositories.activejdbc;

import org.gooru.nucleus.handlers.lookup.processors.repositories.MetadataRepo;
import org.gooru.nucleus.handlers.lookup.processors.repositories.activejdbc.dbhandlers.DBHandlerBuilder;
import org.gooru.nucleus.handlers.lookup.processors.repositories.activejdbc.transactions.TransactionExecutor;
import org.gooru.nucleus.handlers.lookup.processors.responses.MessageResponse;

/**
 * Created by ashish on 29/12/15.
 */
public class AJMetadataRepo implements MetadataRepo {

  private static final String[] FETCH_FIELDS = {"id", "label", "sequence_id"};

  @Override
  public MessageResponse getReadingLevels() {
    return getMetadata("reading_level", "select id, label, sequence_id from metadata_reference where format = 'reading_level' order by sequence_id");
  }

  @Override
  public MessageResponse getMediaFeatures() {
    return getMetadata("media_feature", "select id, label, sequence_id from metadata_reference where format = 'media_feature' order by sequence_id");
  }

  @Override
  public MessageResponse getGrades() {
    // TODO: Verify if this is fixed at DB level
    return getMetadata("grade", "select id, label, sequence_id from metadata_reference where format = 'grade' order by sequence_id");
  }

  @Override
  public MessageResponse getEducationalUse() {
    return getMetadata("educational_use",
      "select id, label, sequence_id from metadata_reference where format = 'educational_use' order by sequence_id");
  }

  @Override
  public MessageResponse getAdStatus() {
    return getMetadata("advertisement_level",
      "select id, label, sequence_id from metadata_reference where format = 'advertisement_level' order by sequence_id");
  }

  @Override
  public MessageResponse getAccessHazards() {
    return getMetadata("hazard_level", "select id, label, sequence_id from metadata_reference where format = 'hazard_level' order by sequence_id");

  }

  @Override
  public MessageResponse getMomentsOfLearning() {
    return getMetadata("moments_of_learning",
      "select id, label, sequence_id from metadata_reference where format = 'moments_of_learning' order by sequence_id");

  }

  @Override
  public MessageResponse getDepthOfKnowledge() {
    return getMetadata("depth_of_knowledge",
      "select id, label, sequence_id from metadata_reference where format = 'depth_of_knowledge' order by sequence_id");
  }

  @Override
  public MessageResponse getAudience() {
    return getMetadata("audience", "select id, label, sequence_id from metadata_reference where format = 'audience' order by sequence_id");
  }

  private MessageResponse getMetadata(String name, String sql) {
    return TransactionExecutor.executeTransaction(DBHandlerBuilder.fetchRowlistExecutorHandlerBuilder(name, sql, FETCH_FIELDS));
  }

 
}
