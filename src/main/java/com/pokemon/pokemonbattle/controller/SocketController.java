package com.pokemon.pokemonbattle.controller;

import com.pokemon.pokemonbattle.constants.URIConstants;
import com.pokemon.pokemonbattle.dto.BattleInputData;
import com.pokemon.pokemonbattle.game.impl.BattleHandler;
import com.pokemon.pokemonbattle.game.IBattleHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;


/**
 * The type Socket controller.
 */
@Controller
@CrossOrigin
public class SocketController {

    private final SimpMessagingTemplate simpMessagingTemplate;

    private static final IBattleHandler battleHandler = BattleHandler.getInstance();

    @Autowired
    public SocketController(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    /**
     * Greeting.
     *
     * @param battleInputData the message
     */
    @MessageMapping(URIConstants.WEBSOCKET_BROKER_BATTLE_MAPPING)
    public void battle(BattleInputData battleInputData) {
        simpMessagingTemplate.convertAndSend(URIConstants.WEBSOCKET_BATTLE_DATA_PRODUCER_ENDPOINT + "/" + battleInputData.getTo(), battleHandler.playBattleMove(battleInputData));
    }

}
