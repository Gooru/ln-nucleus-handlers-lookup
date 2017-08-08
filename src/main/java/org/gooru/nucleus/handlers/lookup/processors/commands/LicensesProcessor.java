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
class LicensesProcessor extends AbstractCommandProcessor {
    public LicensesProcessor(ProcessorContext context) {
        super(context);
    }

    @Override
    protected void setDeprecatedVersions() {

    }

    @Override
    protected MessageResponse processCommand() {
        MessageResponse response;
        JsonObject result = ProcessorCache.getInstance().getLicenses();
        if (result == null) {
            response = RepoBuilder.buildMetadataRepo().getLicenses();
            if (response.isSuccessful()) {
                JsonObject itemToCache = response.getSuccessResult();
                if (itemToCache != null && !itemToCache.isEmpty()) {
                    // Update the cache item
                    ProcessorCache.getInstance().setLicenses(itemToCache);
                }
            }
        } else {
            response = MessageResponseFactory.createOkayResponse(result);
        }
        return response;
    }
}
