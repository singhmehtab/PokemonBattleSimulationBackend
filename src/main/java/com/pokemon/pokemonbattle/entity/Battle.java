package com.pokemon.pokemonbattle.entity;

import com.pokemon.pokemonbattle.dto.BattleDetails;
import com.pokemon.pokemonbattle.game.enums.AttackType;
import com.pokemon.pokemonbattle.utility.NumberUtility;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * The type Battle.
 */
@Getter
@Slf4j
public class Battle {

   private final RoundDetails roundDetails;

    private final Player firstPlayer;

    private final Player secondPlayer;

    private boolean playerOneTurn = NumberUtility.generateRandomIntegerBetweenRange(0, 1) == 0;

    private boolean battleCompleted = false;

    private String winnerName;

    private Integer battleId;

    /**
     * Instantiates a new Battle.
     *
     * @param firstPlayer  the first player
     * @param secondPlayer the second player
     */
    public Battle(Player firstPlayer, Player secondPlayer, Integer battleId){
        assert !firstPlayer.getName().equals(secondPlayer.getName());
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
        this.roundDetails = new RoundDetails();
        this.battleId = battleId;
    }

    /**
     * Play attack.
     *
     * @param attackType the attack type
     */
    public void playAttack(String attackType){
        assert !battleCompleted;
        AttackType attack = AttackType.getAttackType(attackType);
        if(playerOneTurn){
            if(attack.equals(AttackType.NORMAL)) firstPlayer.getPokemon().normalAttack(secondPlayer.getPokemon());
            else firstPlayer.getPokemon().specialAttack(secondPlayer.getPokemon());
        }
        else {
            if(attack.equals(AttackType.NORMAL)) secondPlayer.getPokemon().normalAttack(firstPlayer.getPokemon());
            else secondPlayer.getPokemon().specialAttack(firstPlayer.getPokemon());
        }
        playerOneTurn = !playerOneTurn;
        if(checkRoundCompletion()){
            resetPowers();
            if(checkBattleCompletion()){
                log.info("Battle completed for battle id: " + getBattleId());
            }
        }
    }

    /**
     * Get battle details.
     *
     * @return the battle details
     */
    public BattleDetails getBattleDetails(){

        BattleDetails.PlayerDetails firstPlayerDetails = BattleDetails.PlayerDetails.builder()
                .playerName(firstPlayer.getName())
                .playerPokemonName(firstPlayer.getPokemon().getName())
                .playerPokemonPower(firstPlayer.getPokemon().getPower())
                .playerRoundsWinner(roundDetails.getFirstPlayerRoundsWin())
                .build();
        BattleDetails.PlayerDetails secondPlayerDetails = BattleDetails.PlayerDetails.builder()
                .playerName(secondPlayer.getName())
                .playerPokemonName(secondPlayer.getPokemon().getName())
                .playerPokemonPower(secondPlayer.getPokemon().getPower())
                .playerRoundsWinner(roundDetails.getSecondPlayerRoundsWin())
                .build();
        return BattleDetails.builder()
                .firstPlayerDetails(firstPlayerDetails)
                .secondPlayerDetails(secondPlayerDetails)
                .playerOneTurn(playerOneTurn)
                .winner(winnerName)
                .battleCompleted(battleCompleted)
                .battleId(battleId)
                .build();
    }

    private boolean checkRoundCompletion(){
        if(firstPlayer.getPokemon().getPower() <= 0){
            roundDetails.incrementRound(false);
            return true;
        }
        else if(secondPlayer.getPokemon().getPower() <= 0){
            roundDetails.incrementRound(true);
            return true;
        }
        return false;
    }

    private boolean checkBattleCompletion(){
        if(roundDetails.getCurrentRound() >= RoundDetails.getRequiredRoundsToWin()) {
            if (roundDetails.getFirstPlayerRoundsWin() >= RoundDetails.getRequiredRoundsToWin()) {
                battleCompleted = true;
                winnerName = firstPlayer.getName();
            } else if (roundDetails.getSecondPlayerRoundsWin() >= RoundDetails.getRequiredRoundsToWin()) {
                battleCompleted = true;
                winnerName = secondPlayer.getName();
            }
        }
        return false;
    }

    private void resetPowers(){
        firstPlayer.getPokemon().resetPower();
        secondPlayer.getPokemon().resetPower();
    }

}
