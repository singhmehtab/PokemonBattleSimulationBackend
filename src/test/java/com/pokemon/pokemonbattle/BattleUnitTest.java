package com.pokemon.pokemonbattle;

import com.pokemon.pokemonbattle.dto.BattleDetails;
import com.pokemon.pokemonbattle.entity.Battle;
import com.pokemon.pokemonbattle.entity.Player;
import com.pokemon.pokemonbattle.entity.Pokemon;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BattleUnitTest {

    private static Battle battle;
    static {
        Pokemon firstPokemon = Pokemon.builder()
                .name("testPokemon1")
                .height(10)
                .weight(100)
                .power(20)
                .build();

        Pokemon secondPokemon = Pokemon.builder()
                .name("testPokemon2")
                .height(10)
                .weight(100)
                .power(20)
                .build();

        Player firstPlayer = Player.builder()
                .name("Test1")
                .pokemon(firstPokemon)
                .build();

        Player secondPlayer = Player.builder()
                .name("Test2")
                .pokemon(secondPokemon)
                .build();
        battle = new Battle(firstPlayer, secondPlayer, 1);
    }
    @Test
    void checkBattleInitialization(){
        BattleDetails expectedBattleDetails = BattleDetails.builder()
                .firstPlayerDetails(BattleDetails.PlayerDetails.builder().playerName("Test1")
                        .playerPokemonName("testPokemon1")
                        .playerPokemonPower(20)
                        .playerRoundsWinner(0)
                        .build())
                .secondPlayerDetails(BattleDetails.PlayerDetails.builder().playerName("Test2")
                        .playerPokemonName("testPokemon2")
                        .playerPokemonPower(20)
                        .playerRoundsWinner(0)
                        .build())
                .battleId(1)
                .battleCompleted(false)
                .winner(null)
                .playerOneTurn(true)
                .build();
        Assertions.assertEquals(expectedBattleDetails, battle.getBattleDetails());
    }
}
