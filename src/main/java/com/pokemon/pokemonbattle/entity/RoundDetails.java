package com.pokemon.pokemonbattle.entity;

import lombok.Getter;

/**
 * The type Round details.
 */
@Getter
public class RoundDetails {

    /**
     * Instantiates a new Round details.
     */
    public RoundDetails(){
        this.firstPlayerRoundsWin=0;
        this.secondPlayerRoundsWin=0;
    }

    private static final int TOTAL_ROUNDS = 3;

    private int firstPlayerRoundsWin;

    private int secondPlayerRoundsWin;

    private int currentRound = 0;

    /**
     * Get required rounds to win int.
     *
     * @return the int
     */
    public static int getRequiredRoundsToWin(){
        return (int) Math.ceil((double) TOTAL_ROUNDS/2);
    }

    private void incrementRoundsPlayerOne(){firstPlayerRoundsWin++;}

    private void incrementRoundsPlayerTwo(){secondPlayerRoundsWin++;}

    /**
     * Increment round.
     *
     * @param firstPlayerWon the first player won
     */
    public void incrementRound(boolean firstPlayerWon){
        if(firstPlayerWon) incrementRoundsPlayerOne();
        else incrementRoundsPlayerTwo();
        currentRound++;
    }

}
