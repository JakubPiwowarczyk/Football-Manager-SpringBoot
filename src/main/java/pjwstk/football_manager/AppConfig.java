package pjwstk.football_manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pjwstk.football_manager.player.Player;
import pjwstk.football_manager.player.PlayerRepository;

@Configuration
public class AppConfig {

    private PlayerRepository playerRepository;

    @Bean
    @Autowired
    CommandLineRunner commandLineRunner(PlayerRepository playerRepository) {
        return args -> {
            Player player1 = new Player("Maciej_LOL", "maciek@gmail.com", "123");
            playerRepository.save(player1);
        };
    }

}
