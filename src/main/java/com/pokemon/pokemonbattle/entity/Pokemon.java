package com.pokemon.pokemonbattle.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.pokemon.pokemonbattle.utility.NumberUtility;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * The type Pokemon.
 */
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Pokemon {

    /**
     * The constant INITIAL_POWER.
     */
    public final static int INITIAL_POWER = 20;

    private int power;

    private String name;

    private Integer height;

    private Integer weight;

    private boolean specialAttackInitiated;

    /**
     * Instantiates a new Pokemon.
     *
     * @param name   the name
     * @param height the height
     * @param weight the weight
     */
    public Pokemon(String name, int height, int weight) {
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.power = INITIAL_POWER;
    }

    /**
     * Reduce power.
     *
     * @param power the power
     */
    public void reducePower(int power){
        this.setPower(this.getPower() - power);
    }

    private void setPower(int power){
        this.power = power;
    }

    /**
     * Normal attack.
     *
     * @param opponentPokemon the opponent pokemon
     */
    public void normalAttack(Pokemon opponentPokemon){
        // If special Attack is initiated, normal attack cannot be played. Normal attack would be
        // considered as part of special attack.
        if(specialAttackInitiated){
            specialAttack(opponentPokemon);
        }
        int powerToReduce = NumberUtility.generateRandomIntegerBetweenRange(1,10);
        opponentPokemon.reducePower(powerToReduce);
    }

    /**
     * Special attack.
     *
     * @param opponentPokemon the opponent pokemon
     */
    public void specialAttack(Pokemon opponentPokemon){
        if(specialAttackInitiated) {
            int powerToReduce = NumberUtility.generateRandomIntegerBetweenRange(5, 15);
            opponentPokemon.reducePower(powerToReduce);
            specialAttackInitiated = false;
        }
        else{
            specialAttackInitiated = true;
        }
    }

    /**
     * Reset power.
     */
    public void resetPower(){
        this.power = INITIAL_POWER;
    }

}
