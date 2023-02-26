package com.pokemon.pokemonbattle.proxy;

import com.pokemon.pokemonbattle.entity.Pokemon;

import java.util.List;

/**
 * The interface Poke api service.
 */
public interface IPokeApiService {

    /**
     * Gets pokemon list.
     *
     * @return the pokemon list
     */
    List<Pokemon> getPokemonList(Integer numberOfPokemons);

}
