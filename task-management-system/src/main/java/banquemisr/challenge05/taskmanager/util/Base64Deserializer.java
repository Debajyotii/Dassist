package banquemisr.challenge05.taskmanager.util;

import banquemisr.challenge05.taskmanager.exception.CustomException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.util.Base64;

public class Base64Deserializer extends JsonDeserializer<String> {
    @Override
    public String deserialize(JsonParser p, DeserializationContext ctx) throws IOException {
        String encodedPassword = p.getText();
        try {
            if (encodedPassword.length() < 4 || !encodedPassword.matches("^(?:[A-Za-z0-9+/]{4})*(?:[A-Za-z0-9+/]{2}==|[A-Za-z0-9+/]{3}=)?$"))
                throw new CustomException("Password seems not Base64 Encoded");

            byte[] decodedBytes = Base64.getDecoder().decode(encodedPassword);
            return new String(decodedBytes);
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

}