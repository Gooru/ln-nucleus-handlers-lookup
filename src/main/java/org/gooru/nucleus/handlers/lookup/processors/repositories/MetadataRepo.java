package org.gooru.nucleus.handlers.lookup.processors.repositories;

import org.gooru.nucleus.handlers.lookup.processors.responses.MessageResponse;

/**
 * Created by ashish on 29/12/15.
 */
public interface MetadataRepo {
  MessageResponse getReadingLevels();

  MessageResponse getMediaFeatures();

  MessageResponse getGrades();

  MessageResponse getEducationalUse();

  MessageResponse getAdStatus();

  MessageResponse getAccessHazards();

  MessageResponse getMomentsOfLearning();

  MessageResponse getDepthOfKnowledge();

  MessageResponse getAudience();

  MessageResponse getLicenses();
}
