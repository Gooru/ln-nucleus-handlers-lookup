package org.gooru.nucleus.handlers.lookup.processors.repositories.activejdbc;

import org.gooru.nucleus.handlers.lookup.processors.repositories.Cen21SkillsRepo;
import org.gooru.nucleus.handlers.lookup.processors.repositories.activejdbc.dbhandlers.DBHandlerBuilder;
import org.gooru.nucleus.handlers.lookup.processors.repositories.activejdbc.transactions.TransactionExecutor;
import org.gooru.nucleus.handlers.lookup.processors.responses.MessageResponse;

public class AJCen21SkillsRepo implements Cen21SkillsRepo {
  @Override
  public MessageResponse getCen21Skills() {
    return TransactionExecutor
        .executeTransaction(DBHandlerBuilder.build21CenSkillsHandlerBuilder());
  }
}
