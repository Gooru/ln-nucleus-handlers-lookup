package org.gooru.nucleus.handlers.lookup.processors.repositories.activejdbc;

import org.gooru.nucleus.handlers.lookup.processors.repositories.LanguagesRepo;
import org.gooru.nucleus.handlers.lookup.processors.repositories.activejdbc.dbhandlers.DBHandlerBuilder;
import org.gooru.nucleus.handlers.lookup.processors.repositories.activejdbc.transactions.TransactionExecutor;
import org.gooru.nucleus.handlers.lookup.processors.responses.MessageResponse;

public class AJLanguagesRepo implements LanguagesRepo {

  @Override
  public MessageResponse getLanguages() {
    return TransactionExecutor.executeTransaction(DBHandlerBuilder.buildLanguagesHandlerBuilder());
  }

}
