package org.gooru.nucleus.handlers.lookup.processors.repositories.activejdbc;

import org.gooru.nucleus.handlers.lookup.processors.repositories.StateRepo;
import org.gooru.nucleus.handlers.lookup.processors.repositories.activejdbc.dbhandlers.DBHandlerBuilder;
import org.gooru.nucleus.handlers.lookup.processors.repositories.activejdbc.transactions.TransactionExecutor;
import org.gooru.nucleus.handlers.lookup.processors.responses.MessageResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AJStateRepo implements StateRepo {
  private static final Logger LOGGER = LoggerFactory.getLogger(AJStateRepo.class);

  @Override
  public MessageResponse getStates(String countryId, String query) {
    LOGGER.info("In handlers");
    return TransactionExecutor.executeTransaction(DBHandlerBuilder.statesHandlerBuilder(countryId, query));
  }
}
