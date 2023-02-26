package com.pokemon.pokemonbattle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * The type Pokemon battle app application.
 */
@SpringBootApplication()
@EnableFeignClients
public class PokemonBattleAppApplication {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(PokemonBattleAppApplication.class, args);
    }

}
