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
class DepthOfKnowledgeProcessor extends AbstractCommandProcessor {
    public DepthOfKnowledgeProcessor(ProcessorContext context) {
        super(context);
    }

    @Override
    protected void setDeprecatedVersions() {

    }

    @Override
    protected MessageResponse processCommand() {
        MessageResponse response;
        JsonObject result = ProcessorCache.getInstance().getDepthOfKnowledge();
        if (result == null) {
            response = RepoBuilder.buildMetadataRepo().getDepthOfKnowledge();
            if (response.isSuccessful()) {
                JsonObject itemToCache = response.getSuccessResult();
                if (itemToCache != null && !itemToCache.isEmpty()) {
                    // Update the cache item
                    ProcessorCache.getInstance().setDepthOfKnowledge(itemToCache);
                }
            }
        } else {
            response = MessageResponseFactory.createOkayResponse(result);
        }
        return response;
    }
}
