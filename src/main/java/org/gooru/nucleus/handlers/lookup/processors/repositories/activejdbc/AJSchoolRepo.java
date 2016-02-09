package org.gooru.nucleus.handlers.lookup.processors.repositories.activejdbc;

import org.gooru.nucleus.handlers.lookup.processors.repositories.SchoolRepo;
import org.gooru.nucleus.handlers.lookup.processors.repositories.activejdbc.dbhandlers.DBHandlerBuilder;
import org.gooru.nucleus.handlers.lookup.processors.repositories.activejdbc.transactions.TransactionExecutor;
import org.gooru.nucleus.handlers.lookup.processors.responses.MessageResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AJSchoolRepo implements SchoolRepo {
  
  private static final Logger LOGGER = LoggerFactory.getLogger(AJSchoolRepo.class);
  @Override
  public MessageResponse getSchools(String keyword, String schoolDistrictId) {
    LOGGER.info("In handlers");
    return TransactionExecutor.executeTransaction(DBHandlerBuilder.schoolHandlerBuilder(keyword, schoolDistrictId));
  }
  
 }
