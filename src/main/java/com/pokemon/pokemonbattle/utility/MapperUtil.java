package com.pokemon.pokemonbattle.utility;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * The type Mapper util.
 */
@Component
public class MapperUtil {

    private final ObjectMapper objectMapper;

    /**
     * Instantiates a new Mapper util.
     *
     * @param objectMapper the object mapper
     */
    public MapperUtil(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    /**
     * To json string.
     *
     * @param object the object
     * @return the string
     * @throws JsonProcessingException the json processing exception
     */
    public String toJson(Object object) throws JsonProcessingException {
        String value;
        if (object instanceof String) {
            value = (String) object;
        } else {
            value = objectMapper.writeValueAsString(object);
        }
        return value;
    }

    /**
     * From json t.
     *
     * @param <T>        the type parameter
     * @param jsonString the json string
     * @param classType  the class type
     * @return the t
     * @throws IOException the io exception
     */
    public <T> T fromJson(String jsonString, Class<T> classType) throws IOException {
        T value = null;
        if (jsonString != null) {
            if (!String.class.equals(classType)) {
                value = objectMapper.readValue(jsonString, classType);
            } else {
                value = (T) jsonString;
            }
        }
        return value;
    }

}
