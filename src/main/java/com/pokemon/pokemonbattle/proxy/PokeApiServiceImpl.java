package com.pokemon.pokemonbattle.proxy;

import com.pokemon.pokemonbattle.cache.LocalCacheService;
import com.pokemon.pokemonbattle.entity.Pokemon;
import com.pokemon.pokemonbattle.proxy.dto.pokeApi.PokeApiPokemonResponse;
import com.pokemon.pokemonbattle.proxy.dto.pokeApi.PokemonListResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * The type Poke api service.
 */
@Service
public class PokeApiServiceImpl implements IPokeApiService{

    private final PokeApiProxy pokeApiProxy;

    /**
     * The Local cache service.
     */
    @Autowired
    LocalCacheService localCacheService;

    /**
     * Instantiates a new Poke api service.
     *
     * @param pokeApiProxy the poke api proxy
     */
    public PokeApiServiceImpl(PokeApiProxy pokeApiProxy) {
        this.pokeApiProxy = pokeApiProxy;
    }

    @Override
    public List<Pokemon> getPokemonList(Integer numberOfPokemons) {
        PokemonListResponseDto pokemonListResponseDto = pokeApiProxy.getPokemonList(50,0);
        List<CompletableFuture<Pokemon>> pokemonList;
        pokemonList = pokemonListResponseDto.getPokemonOverviewList().parallelStream().map(
                pokemonOverview -> CompletableFuture.supplyAsync(() -> getPokemonDetails(pokemonOverview.getName(), pokemonOverview.getUrl())))
                .collect(Collectors.toList());
        return pokemonList.stream().map(CompletableFuture::join).collect(Collectors.toList());
    }

    private Pokemon getPokemonDetails(String name, String url){
        Pokemon pokemon = localCacheService.get(name, Pokemon.class);
        if(Objects.nonNull(pokemon)) return pokemon;
        PokeApiPokemonResponse pokeApiPokemonResponse = pokeApiProxy.getPokemonDetails(URI.create(url));
        pokemon = new Pokemon(name, pokeApiPokemonResponse.getHeight(), pokeApiPokemonResponse.getWeight());
        localCacheService.set(name, pokemon, 1000);
        return pokemon;
    }
}
