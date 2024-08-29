package pjwstk.football_manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pjwstk.football_manager.footballer.*;
import pjwstk.football_manager.player.Player;
import pjwstk.football_manager.player.PlayerRepository;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class AppConfig {

    private PlayerRepository playerRepository;
    private LeagueRepository leagueRepository;
    private TeamRepository teamRepository;
    private NationalityRepository nationalityRepository;

    @Bean
    @Autowired
    CommandLineRunner commandLineRunner(PlayerRepository playerRepository,
                                        LeagueRepository leagueRepository,
                                        TeamRepository teamRepository,
                                        NationalityRepository nationalityRepository) {
        return args -> {

            // Creation of sample data

            Player player1 = new Player("Maciej_LOL", "maciek@gmail.com", "123");
            Player player2 = new Player("Eddie", "eddie@gmail.com", "qwerty");
            playerRepository.saveAll(List.of(player1, player2));

            League laliga = new League("LaLiga");
            League premierLeague = new League("Premier League");
            League bundesliga = new League("Bundesliga");
            leagueRepository.saveAll(List.of(laliga, bundesliga, premierLeague));

            Team barcelona = new Team("FC Barcelona", laliga);
            Team real = new Team("Real Madrid", laliga);
            Team city = new Team("Manchester City", premierLeague);

            Nationality spain = new Nationality("Spain");
            Nationality germany = new Nationality("Germany");
            Nationality france = new Nationality("France");
            Nationality england = new Nationality("England");
            Nationality poland = new Nationality("Poland");
            Nationality brazil = new Nationality("Brazil");

            Footballer lewandowski = new Footballer("Robert", "Lewandowski",
                    LocalDate.of(1988, 8, 21), Position.STRIKER, poland, barcelona);


        };
    }

}
