package org.gooru.nucleus.handlers.lookup.processors.repositories.activejdbc;

import org.gooru.nucleus.handlers.lookup.processors.repositories.StateRepo;
import org.gooru.nucleus.handlers.lookup.processors.repositories.activejdbc.dbhandlers.DBHandlerBuilder;
import org.gooru.nucleus.handlers.lookup.processors.repositories.activejdbc.transactions.TransactionExecutor;
import org.gooru.nucleus.handlers.lookup.processors.responses.MessageResponse;

public class AJStateRepo implements StateRepo {

    @Override
    public MessageResponse getStates(String countryId, String query) {
        return TransactionExecutor.executeTransaction(DBHandlerBuilder.statesHandlerBuilder(countryId, query));
    }
}
