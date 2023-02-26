package com.pokemon.pokemonbattle.game.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * The enum Attack type.
 */
@Getter
@AllArgsConstructor
public enum AttackType {

    /**
     * Normal attack type.
     */
    NORMAL("normal"),
    /**
     * Special attack type.
     */
    SPECIAL("special");

    private String attackIdentifier;

    private static Map<String, AttackType> attackTypeMap = new HashMap<>();

    static {
        for(AttackType attackType : AttackType.values()){
            attackTypeMap.put(attackType.attackIdentifier, attackType);
        }
    }

    /**
     * Get attack type attack type.
     *
     * @param attackIdentifier the attack identifier
     * @return the attack type
     */
    public static  AttackType getAttackType(String attackIdentifier){
        return attackTypeMap.get(attackIdentifier);
    }

}
