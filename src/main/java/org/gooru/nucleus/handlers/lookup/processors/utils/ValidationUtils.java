package org.gooru.nucleus.handlers.lookup.processors.utils;

import java.util.UUID;

import org.gooru.nucleus.handlers.lookup.constants.MessageConstants;

/**
 * @author ashish on 2/1/17.
 */
public final class ValidationUtils {

    private ValidationUtils() {
        throw new AssertionError();
    }

    public static boolean validateUser(String userId) {
        return !(userId == null || userId.isEmpty()) && (userId.equalsIgnoreCase(MessageConstants.MSG_USER_ANONYMOUS)
            || validateUuid(userId));
    }

    public static boolean validateUuid(String uuidString) {
        try {
            UUID.fromString(uuidString);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        } catch (Exception e) {
            return false;
        }
    }

}
