package pjwstk.football_manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pjwstk.football_manager.footballer.League;
import pjwstk.football_manager.footballer.LeagueRepository;
import pjwstk.football_manager.footballer.Team;
import pjwstk.football_manager.player.Player;
import pjwstk.football_manager.player.PlayerRepository;

import java.util.List;

@Configuration
public class AppConfig {

    private PlayerRepository playerRepository;
    private LeagueRepository leagueRepository;

    @Bean
    @Autowired
    CommandLineRunner commandLineRunner(PlayerRepository playerRepository,
                                        LeagueRepository leagueRepository) {
        return args -> {

            // Creation of sample data

            Player player1 = new Player("Maciej_LOL", "maciek@gmail.com", "123");
            Player player2 = new Player("Eddie", "eddie@gmail.com", "qwerty");
            playerRepository.saveAll(List.of(player1, player2));

            League laliga = new League("LaLiga");
            League premierLeague = new League("Premier League");
            League serieA = new League("Serie A");
            leagueRepository.saveAll(List.of(laliga, serieA, premierLeague));

            Team barcelona = new Team("FC Barcelona", laliga);
            Team real = new Team("Real Madrid", laliga);
            Team city = new Team("Manchester City", premierLeague);

        };
    }

}
