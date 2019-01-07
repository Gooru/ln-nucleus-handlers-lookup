package org.gooru.nucleus.handlers.lookup.processors.commands;

import org.gooru.nucleus.handlers.lookup.processors.ProcessorCache;
import org.gooru.nucleus.handlers.lookup.processors.ProcessorContext;
import org.gooru.nucleus.handlers.lookup.processors.repositories.RepoBuilder;
import org.gooru.nucleus.handlers.lookup.processors.responses.MessageResponse;
import org.gooru.nucleus.handlers.lookup.processors.responses.MessageResponseFactory;
import io.vertx.core.json.JsonObject;

/**
 * @author ashish on 2/1/17.
 */
class AdStatusProcessor extends AbstractCommandProcessor {
  public AdStatusProcessor(ProcessorContext context) {
    super(context);
  }

  @Override
  protected void setDeprecatedVersions() {

  }

  @Override
  protected MessageResponse processCommand() {
    MessageResponse response;
    JsonObject result = ProcessorCache.getInstance().getAdStatus();
    if (result == null) {
      response = RepoBuilder.buildMetadataRepo().getAdStatus();
      if (response.isSuccessful()) {
        JsonObject itemToCache = response.getSuccessResult();
        if (itemToCache != null && !itemToCache.isEmpty()) {
          // Update the cache item
          ProcessorCache.getInstance().setAdStatus(itemToCache);
        }
      }
    } else {
      response = MessageResponseFactory.createOkayResponse(result);
    }
    return response;
  }
}
