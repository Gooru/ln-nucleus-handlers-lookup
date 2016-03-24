package org.gooru.nucleus.handlers.lookup.processors.repositories.activejdbc;

import org.gooru.nucleus.handlers.lookup.processors.repositories.CountryRepo;
import org.gooru.nucleus.handlers.lookup.processors.repositories.activejdbc.dbhandlers.DBHandlerBuilder;
import org.gooru.nucleus.handlers.lookup.processors.repositories.activejdbc.transactions.TransactionExecutor;
import org.gooru.nucleus.handlers.lookup.processors.responses.MessageResponse;

public class AJCountryRepo implements CountryRepo {

  @Override
  public MessageResponse getCountries(String keyword, int limit, int offset) {
    return TransactionExecutor.executeTransaction(DBHandlerBuilder.countriesHandlerBuilder(keyword, limit, offset));
  }
}