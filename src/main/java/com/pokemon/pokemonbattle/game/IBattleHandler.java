package com.pokemon.pokemonbattle.game;

import com.pokemon.pokemonbattle.dto.BattleDetails;
import com.pokemon.pokemonbattle.dto.BattleInputData;

public interface IBattleHandler {

    public BattleDetails playBattleMove(BattleInputData battleInputData);

}
