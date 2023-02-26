package com.pokemon.pokemonbattle.configuration;

import com.pokemon.pokemonbattle.constants.URIConstants;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * The type Socket broker config.
 */
@Configuration
@EnableWebSocketMessageBroker
public class SocketBrokerConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker(URIConstants.WEBSOCKET_BROKER_DESTINATION_PREFIX);
        config.setApplicationDestinationPrefixes(URIConstants.WEBSOCKET_DESTINATION_PREFIX);
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint(URIConstants.WEBSOCKET_BROKER_ENDPOINT).setAllowedOriginPatterns("*").withSockJS();
    }
}
