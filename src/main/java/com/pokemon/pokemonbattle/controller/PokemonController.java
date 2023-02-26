package com.pokemon.pokemonbattle.controller;

import com.pokemon.pokemonbattle.constants.URIConstants;
import com.pokemon.pokemonbattle.controller.dto.ResponseDto;
import com.pokemon.pokemonbattle.entity.Pokemon;
import com.pokemon.pokemonbattle.proxy.IPokeApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

/**
 * The type Pokemon controller.
 */
@RestController()
@CrossOrigin
@Slf4j
public class PokemonController {

    /**
     * The Poke api service.
     */
    private final IPokeApiService iPokeApiService;

    public PokemonController(IPokeApiService iPokeApiService) {
        this.iPokeApiService = iPokeApiService;
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, value = URIConstants.GET_POKEMON)
    private ResponseDto<List<Pokemon>> getPokemon(@RequestParam(value = "numberOfPokemons", required = false) Integer numberOfPokemons){
        List<Pokemon> pokemonList = null;
        try {
            log.info("Fetching pokemons from iPokeApiService");
            pokemonList = iPokeApiService.getPokemonList(numberOfPokemons);
        }
        catch (Exception e){
            log.error("Error while fetching pokemon list", e);
            new ResponseDto<>(Collections.singletonList("Error while fetching pokemon list"));
        }
        return new ResponseDto<>(pokemonList);
    }

}
