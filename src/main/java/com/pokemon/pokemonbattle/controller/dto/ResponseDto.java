package com.pokemon.pokemonbattle.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ResponseDto<T> {

    private T data;
    private List<String> messages;
    private List<String> errors;

    public ResponseDto(T data) {
        this.data = data;
    }

    public ResponseDto(List<String> errors) {
        this.errors = Collections.unmodifiableList(errors);
        this.data = null;
    }

    public ResponseDto(T data, List<String> messages) {
        this.data = data;
        this.setMessages(messages);
    }
}
