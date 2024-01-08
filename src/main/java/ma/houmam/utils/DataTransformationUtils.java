package ma.houmam.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public interface DataTransformationUtils {
     static String toJson(Object o){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(o);
            return json;
        } catch (JsonProcessingException e) {
            return "{}";
        }
    }
}
