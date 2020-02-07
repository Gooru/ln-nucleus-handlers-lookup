package org.gooru.nucleus.handlers.lookup.processors.repositories.activejdbc;

import org.gooru.nucleus.handlers.lookup.processors.repositories.FeedbackCategoryRepo;
import org.gooru.nucleus.handlers.lookup.processors.repositories.activejdbc.dbhandlers.DBHandlerBuilder;
import org.gooru.nucleus.handlers.lookup.processors.repositories.activejdbc.transactions.TransactionExecutor;
import org.gooru.nucleus.handlers.lookup.processors.responses.MessageResponse;

public class AJFeedbackCategoryRepo implements FeedbackCategoryRepo {

  @Override
  public MessageResponse getFeedbackCategories(String userCategoryId) {
    return TransactionExecutor
        .executeTransaction(DBHandlerBuilder.buildFeedbackCategoriesHandlerBuilder(userCategoryId));
  }

}
