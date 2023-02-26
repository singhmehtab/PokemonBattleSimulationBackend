package com.pokemon.pokemonbattle.proxy.dto.pokeApi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * The type Poke api pokemon response.
 */
@Getter
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class PokeApiPokemonResponse {

    @JsonProperty("height")
    private Integer height;

    @JsonProperty("weight")
    private Integer weight;

}
