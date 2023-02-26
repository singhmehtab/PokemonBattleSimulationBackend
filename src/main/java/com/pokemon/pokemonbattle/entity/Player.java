package com.pokemon.pokemonbattle.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * The type Player.
 */
@Getter
@AllArgsConstructor
@Builder
public class Player {

    private String name;

    private Pokemon pokemon;

}
