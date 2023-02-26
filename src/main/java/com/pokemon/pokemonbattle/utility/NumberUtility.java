package com.pokemon.pokemonbattle.utility;

/**
 * The type Number utility.
 */
public class NumberUtility {

    /**
     * Generate random integer between range int.
     *
     * @param low  the low
     * @param high the high
     * @return the int
     */
    public static int generateRandomIntegerBetweenRange(int low, int high){
        return (int) ((Math.random() * (high - low)) + low);
    }

}
