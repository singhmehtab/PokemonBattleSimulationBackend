package com.pokemon.pokemonbattle.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * The type Message.
 */
@Getter
@AllArgsConstructor
public class BattleInputData {

    private String to;

    private Integer battleId;

    private InitialData initialData;

    private String attackType;

    /**
     * The type Initial data.
     */
    @Getter
    @AllArgsConstructor
    public static class InitialData{

        private String firstPlayerName;

        private String secondPlayerName;

        private String firstPlayerPokemonName;

        private String secondPlayerPokemonName;
    }

}
