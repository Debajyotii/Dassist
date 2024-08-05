package banquemisr.challenge05.taskmanager.util;

import java.util.HashMap;
import java.util.Map;

import static banquemisr.challenge05.taskmanager.util.AppConstants.MSG;
import static banquemisr.challenge05.taskmanager.util.AppConstants.VAL;

public class ResponseCreator {

    private ResponseCreator() {
    }

    public static Map<String, Object> createResponse(String message, Object value) {
        Map<String, Object> response = new HashMap<>();
        response.put(MSG, message);
        response.put(VAL, value);
        return response;
    }

    public static Map<String, Object> createResponse(String message) {
        Map<String, Object> response = new HashMap<>();
        response.put(MSG, message);
        return response;
    }
}
