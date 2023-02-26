package com.pokemon.pokemonbattle.game.impl;

import com.pokemon.pokemonbattle.dto.BattleDetails;
import com.pokemon.pokemonbattle.dto.BattleInputData;
import com.pokemon.pokemonbattle.entity.Battle;
import com.pokemon.pokemonbattle.entity.Player;
import com.pokemon.pokemonbattle.entity.Pokemon;
import com.pokemon.pokemonbattle.game.IBattleHandler;

import java.util.HashMap;
import java.util.Objects;

/**
 * The type Battle handler.
 */
public class BattleHandler implements IBattleHandler {

    private static final HashMap<Integer, Battle> battleHashMap = new HashMap<>();

    private static Integer battleId = 0;

    private static BattleHandler battleHandler = null;

    private BattleHandler(){}

    public static BattleHandler getInstance(){
        if(Objects.isNull(battleHandler)) {
            battleHandler = new BattleHandler();
        }
        return battleHandler;
    }

    private Integer getBattleId(){
        battleId++;
        return battleId;
    }

    /**
     * Play battle move battle details.
     *
     * @param battleInputData the battle input data
     * @return the battle details
     */
    @Override
    public BattleDetails playBattleMove(BattleInputData battleInputData){
        Battle battle;
        if(Objects.isNull(battleInputData.getBattleId())){
            Pokemon firstPokemon = Pokemon.builder().name(battleInputData.getInitialData().getFirstPlayerPokemonName()).power(Pokemon.INITIAL_POWER).build();
            Pokemon secondPokemon = Pokemon.builder().name(battleInputData.getInitialData().getSecondPlayerPokemonName()).power(Pokemon.INITIAL_POWER).build();
            Player firstPlayer = Player.builder().name(battleInputData.getInitialData().getFirstPlayerName()).pokemon(firstPokemon).build();
            Player secondPlayer = Player.builder().name(battleInputData.getInitialData().getSecondPlayerName()).pokemon(secondPokemon).build();
            battle = new Battle(firstPlayer, secondPlayer, getBattleId());
            battleHashMap.put(battle.getBattleId(), battle);
        }
        else{
            battle = battleHashMap.get(battleInputData.getBattleId());
            battle.playAttack(battleInputData.getAttackType());
        }
        return battle.getBattleDetails();
    }

}
