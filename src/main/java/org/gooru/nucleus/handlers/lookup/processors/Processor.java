package org.gooru.nucleus.handlers.lookup.processors;

import org.gooru.nucleus.handlers.lookup.processors.responses.MessageResponse;

public interface Processor {
    MessageResponse process();
}
