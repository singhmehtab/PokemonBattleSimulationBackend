package com.pokemon.pokemonbattle.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.Objects;

/**
 * The type Battle details.
 */
@Getter
@Builder
public class BattleDetails {

    /**
     * The First player details.
     */
    private PlayerDetails firstPlayerDetails;

    /**
     * The Second player details.
     */
    private PlayerDetails secondPlayerDetails;

    private boolean battleCompleted;

    private String winner;

    private Integer battleId;

    private boolean playerOneTurn;

    /**
     * The type Player details.
     */
    @Getter
    @Builder
    public static class PlayerDetails{

        private String playerName;

        private String playerPokemonName;

        private Integer playerPokemonPower;

        private int playerRoundsWinner;

        @Override
        public boolean equals(Object playerDetails){
            if(playerDetails == this) return true;

            if(!(playerDetails instanceof PlayerDetails)) return false;

            PlayerDetails details = (PlayerDetails) playerDetails;

            return details.playerName.equals(this.playerName) && details.playerPokemonName.equals(this.getPlayerPokemonName())
                    && Objects.equals(details.playerPokemonPower, this.playerPokemonPower) && Objects.equals(details.playerRoundsWinner, this.playerRoundsWinner);
        }

    }

    @Override
    public boolean equals(Object battleDetails){
        if (battleDetails == this) {
            return true;
        }
        if (!(battleDetails instanceof BattleDetails)) {
            return false;
        }

        BattleDetails details = (BattleDetails) battleDetails;
        return details.firstPlayerDetails.equals(this.firstPlayerDetails) && details.secondPlayerDetails.equals(this.secondPlayerDetails)
                && details.battleCompleted == this.battleCompleted && Objects.equals(details.battleId, this.battleId) && Objects.equals(details.winner, this.winner);

    }

}
