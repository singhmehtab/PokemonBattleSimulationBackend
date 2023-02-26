package com.pokemon.pokemonbattle.proxy;

import com.pokemon.pokemonbattle.proxy.dto.pokeApi.PokeApiPokemonResponse;
import com.pokemon.pokemonbattle.proxy.dto.pokeApi.PokemonListResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URI;

/**
 * The interface Poke api proxy.
 */
@FeignClient(value = "pokeApi", url = "https://pokeapi.co")
public interface PokeApiProxy {

    /**
     * Gets pokemon list.
     *
     * @param limit  the limit
     * @param offset the offset
     * @return the pokemon list
     */
    @RequestMapping(method = RequestMethod.GET, value = "/api/v2/pokemon")
    PokemonListResponseDto getPokemonList(@RequestParam(value = "limit") Integer limit, @RequestParam(value = "offset") Integer offset);

    /**
     * Gets pokemon details.
     *
     * @param uri the uri
     * @return the pokemon details
     */
    @RequestMapping(method = RequestMethod.GET)
    PokeApiPokemonResponse getPokemonDetails(URI uri);

}
