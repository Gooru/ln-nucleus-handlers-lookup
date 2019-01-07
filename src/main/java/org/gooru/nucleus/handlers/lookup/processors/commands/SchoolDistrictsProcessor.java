package org.gooru.nucleus.handlers.lookup.processors.commands;

import org.gooru.nucleus.handlers.lookup.processors.ProcessorContext;
import org.gooru.nucleus.handlers.lookup.processors.repositories.RepoBuilder;
import org.gooru.nucleus.handlers.lookup.processors.responses.MessageResponse;

/**
 * @author ashish on 2/1/17.
 */
class SchoolDistrictsProcessor extends AbstractCommandProcessor {
  public SchoolDistrictsProcessor(ProcessorContext context) {
    super(context);
  }

  @Override
  protected void setDeprecatedVersions() {

  }

  @Override
  protected MessageResponse processCommand() {
    return RepoBuilder.buildSchoolDistrictsRepo().getSchoolDistricts(context.keyword(),
        context.stateId(), context.limit(), context.offset());
  }
}
