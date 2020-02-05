package org.gooru.nucleus.handlers.lookup.processors.exceptions;

import org.gooru.nucleus.handlers.lookup.processors.responses.MessageResponse;

public class MessageResponseWrapperException extends RuntimeException {

  private final MessageResponse messageResponse;

  public MessageResponseWrapperException(MessageResponse messageResponse) {
    this.messageResponse = messageResponse;
  }

  public MessageResponse getMessageResponse() {
    return messageResponse;
  }
}
