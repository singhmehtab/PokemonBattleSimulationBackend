package com.pokemon.pokemonbattle.proxy.dto.pokeApi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * The type Pokemon list response dto.
 */
@Getter
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class PokemonListResponseDto {

    @JsonProperty("results")
    private List<PokemonOverview> pokemonOverviewList;

    /**
     * The type Pokemon overview.
     */
    @Getter
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    @NoArgsConstructor
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    public static class PokemonOverview{

        @JsonProperty("name")
        private String name;

        @JsonProperty("url")
        private String url;
    }
}
