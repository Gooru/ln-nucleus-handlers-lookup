package org.gooru.nucleus.handlers.lookup.processors.repositories.activejdbc;

import org.gooru.nucleus.handlers.lookup.processors.repositories.CountryRepo;
import org.gooru.nucleus.handlers.lookup.processors.repositories.activejdbc.dbhandlers.DBHandlerBuilder;
import org.gooru.nucleus.handlers.lookup.processors.repositories.activejdbc.transactions.TransactionExecutor;
import org.gooru.nucleus.handlers.lookup.processors.responses.MessageResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AJCountryRepo implements CountryRepo {
  private static final Logger LOGGER = LoggerFactory.getLogger(CountryRepo.class);

  @Override
  public MessageResponse getCountries(String query) {
    LOGGER.info("In handlers");
    return TransactionExecutor.executeTransaction(DBHandlerBuilder.countriesHandlerBuilder(query));
  }
}