package org.gooru.nucleus.handlers.lookup.processors.repositories;

import org.gooru.nucleus.handlers.lookup.processors.responses.MessageResponse;

/**
 * Created by ashish on 29/12/15.
 */
public interface StateRepo {
  MessageResponse getStates(String countryId, String query);
}
