package com.pokemon.pokemonbattle;

import com.pokemon.pokemonbattle.entity.Pokemon;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PokemonUnitTest {

    private static Pokemon firstPokemon;
    private static Pokemon secondPokemon;

   static {
       firstPokemon = Pokemon.builder()
               .name("testPokemon1")
               .height(10)
               .weight(100)
               .power(20)
               .build();

       secondPokemon = Pokemon.builder()
               .name("testPokemon2")
               .height(10)
               .weight(100)
               .power(20)
               .build();
   }

   @Test
    void testReducePower(){
       firstPokemon.resetPower();
       firstPokemon.reducePower(10);
       Assertions.assertEquals(10, firstPokemon.getPower());
   }

   @Test
    void testNormalAttack(){
       firstPokemon.resetPower();
       secondPokemon.resetPower();
       int secondPokemonPower = secondPokemon.getPower();
       firstPokemon.normalAttack(secondPokemon);
       Assertions.assertTrue(secondPokemon.getPower() <= secondPokemonPower - 1 && secondPokemon.getPower() >= secondPokemonPower - 10 );
   }

    @Test
    void testSpecialAttack(){
        firstPokemon.resetPower();
        secondPokemon.resetPower();
        int secondPokemonPower = secondPokemon.getPower();
        // Two turns are required for special attack
        firstPokemon.specialAttack(secondPokemon);
        firstPokemon.specialAttack(secondPokemon);
        Assertions.assertTrue(secondPokemon.getPower() <= secondPokemonPower - 5 && secondPokemon.getPower() >= secondPokemonPower - 15 );
    }
}
