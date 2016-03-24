package org.gooru.nucleus.handlers.lookup.processors.repositories;


import org.gooru.nucleus.handlers.lookup.processors.responses.MessageResponse;
/**
 * Created by ashish on 29/12/15.
 */
public interface SchoolRepo {
  MessageResponse getSchools(String keyword, String schoolDistrictId, int limit, int offset);
}
