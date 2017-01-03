package org.gooru.nucleus.handlers.lookup.processors.commands;

import org.gooru.nucleus.handlers.lookup.processors.ProcessorContext;
import org.gooru.nucleus.handlers.lookup.processors.responses.MessageResponse;
import org.gooru.nucleus.handlers.lookup.processors.responses.MessageResponseFactory;
import org.gooru.nucleus.handlers.lookup.processors.utils.APIKeyConfigUtil;

import io.vertx.core.json.JsonObject;

/**
 * @author ashish on 2/1/17.
 */
class ApiKeyConfigProcessor extends AbstractCommandProcessor {
    public ApiKeyConfigProcessor(ProcessorContext context) {
        super(context);
    }

    @Override
    protected void setDeprecatedVersions() {

    }

    @Override
    protected MessageResponse processCommand() {
        JsonObject result = APIKeyConfigUtil.getAPIKeyConfig();
        return MessageResponseFactory.createOkayResponse(result);
    }
}
