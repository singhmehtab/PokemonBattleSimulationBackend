package com.pokemon.pokemonbattle;

import com.pokemon.pokemonbattle.entity.RoundDetails;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RoundDetailsUnitTest {

    RoundDetails roundDetails = new RoundDetails();

    @Test
    void testFirstPlayerRoundIncrement(){
        roundDetails.incrementRound(true);
        Assertions.assertEquals(roundDetails.getFirstPlayerRoundsWin(), 1);
    }

    @Test
    void testSecondPlayerRoundIncrement(){
        roundDetails.incrementRound(false);
        Assertions.assertEquals(roundDetails.getSecondPlayerRoundsWin(), 1);
    }

}
