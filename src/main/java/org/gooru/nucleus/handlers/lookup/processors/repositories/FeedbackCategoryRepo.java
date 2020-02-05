package org.gooru.nucleus.handlers.lookup.processors.repositories;

import org.gooru.nucleus.handlers.lookup.processors.responses.MessageResponse;


public interface FeedbackCategoryRepo {
  MessageResponse getFeedbackCategories(String contentType, String userCategoryId);
}
