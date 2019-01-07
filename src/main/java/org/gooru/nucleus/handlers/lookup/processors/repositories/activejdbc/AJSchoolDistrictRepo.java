package org.gooru.nucleus.handlers.lookup.processors.repositories.activejdbc;

import org.gooru.nucleus.handlers.lookup.processors.repositories.SchoolDistrictRepo;
import org.gooru.nucleus.handlers.lookup.processors.repositories.activejdbc.dbhandlers.DBHandlerBuilder;
import org.gooru.nucleus.handlers.lookup.processors.repositories.activejdbc.transactions.TransactionExecutor;
import org.gooru.nucleus.handlers.lookup.processors.responses.MessageResponse;

public class AJSchoolDistrictRepo implements SchoolDistrictRepo {
  @Override
  public MessageResponse getSchoolDistricts(String keyword, String stateId, int limit, int offset) {
    return TransactionExecutor.executeTransaction(
        DBHandlerBuilder.schoolDistrictHandlerBuilder(keyword, stateId, limit, offset));
  }
}
