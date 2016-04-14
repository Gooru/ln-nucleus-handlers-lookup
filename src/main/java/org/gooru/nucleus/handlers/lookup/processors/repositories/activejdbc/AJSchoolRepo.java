package org.gooru.nucleus.handlers.lookup.processors.repositories.activejdbc;

import org.gooru.nucleus.handlers.lookup.processors.repositories.SchoolRepo;
import org.gooru.nucleus.handlers.lookup.processors.repositories.activejdbc.dbhandlers.DBHandlerBuilder;
import org.gooru.nucleus.handlers.lookup.processors.repositories.activejdbc.transactions.TransactionExecutor;
import org.gooru.nucleus.handlers.lookup.processors.responses.MessageResponse;

public class AJSchoolRepo implements SchoolRepo {

    @Override
    public MessageResponse getSchools(String keyword, String schoolDistrictId, int limit, int offset) {
        return TransactionExecutor
            .executeTransaction(DBHandlerBuilder.schoolHandlerBuilder(keyword, schoolDistrictId, limit, offset));
    }

}
