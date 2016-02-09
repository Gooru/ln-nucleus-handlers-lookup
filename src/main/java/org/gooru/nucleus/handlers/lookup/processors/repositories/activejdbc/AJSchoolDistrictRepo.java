package org.gooru.nucleus.handlers.lookup.processors.repositories.activejdbc;

import org.gooru.nucleus.handlers.lookup.processors.repositories.SchoolDistrictRepo;
import org.gooru.nucleus.handlers.lookup.processors.repositories.activejdbc.dbhandlers.DBHandlerBuilder;
import org.gooru.nucleus.handlers.lookup.processors.repositories.activejdbc.transactions.TransactionExecutor;
import org.gooru.nucleus.handlers.lookup.processors.responses.MessageResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AJSchoolDistrictRepo implements SchoolDistrictRepo {
  private static final Logger LOGGER = LoggerFactory.getLogger(AJSchoolDistrictRepo.class);

  @Override
  public MessageResponse getSchoolDistricts(String query) {
    LOGGER.info("In handlers");
    return TransactionExecutor.executeTransaction(DBHandlerBuilder.schoolDistrictHandlerBuilder(query));
  }
}
