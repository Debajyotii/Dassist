package banquemisr.challenge05.taskmanager.util;

import banquemisr.challenge05.taskmanager.exception.CustomException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class LocalDateDeserializer extends JsonDeserializer<LocalDate> {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public LocalDate deserialize(JsonParser p, DeserializationContext ctx) throws IOException {
        String date = p.getText();
        try {
            return LocalDate.parse(date, formatter);
        } catch (DateTimeParseException e) {
            throw new CustomException("Invalid date format- " + date + ". Expected format is yyyy-MM-dd.");
        }
    }
}
