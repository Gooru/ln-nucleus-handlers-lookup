package org.gooru.nucleus.handlers.lookup.processors.commands;

import org.gooru.nucleus.handlers.lookup.processors.ProcessorContext;
import org.gooru.nucleus.handlers.lookup.processors.repositories.RepoBuilder;
import org.gooru.nucleus.handlers.lookup.processors.responses.MessageResponse;

/**
 * @author ashish on 2/1/17.
 */
class CountriesProcessor extends AbstractCommandProcessor {
  public CountriesProcessor(ProcessorContext context) {
    super(context);
  }

  @Override
  protected void setDeprecatedVersions() {

  }

  @Override
  protected MessageResponse processCommand() {
    return RepoBuilder.buildCountriesRepo().getCountries(context.keyword());
  }
}
