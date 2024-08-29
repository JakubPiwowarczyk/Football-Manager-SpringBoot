package pjwstk.football_manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cglib.core.Local;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pjwstk.football_manager.card.BasicCard;
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
            Nationality argentina = new Nationality("Argentina");
            Nationality norway = new Nationality("Norway");
            Nationality belgium = new Nationality("Belgium");

            Footballer lewandowski = new Footballer("Robert", "Lewandowski",
                    LocalDate.of(1988, 8, 21), Position.STRIKER, poland, barcelona);
            Footballer messi = new Footballer("Lionel", "Messi",
                    LocalDate.of(1987, 6, 24), Position.MIDFIELDER, argentina, barcelona);
            Footballer yamal = new Footballer("Lamine", "Yamal",
                    LocalDate.of(2007, 7, 13), Position.WINGER, spain, barcelona);
            Footballer marc = new Footballer("Marc-Andre", "ter Stegen",
                    LocalDate.of(1992, 4, 30), Position.GOALKEEPER, germany, barcelona);
            Footballer vini = new Footballer("Vinicius", "Junior",
                    LocalDate.of(2000, 7, 12), Position.WINGER, brazil, real);
            Footballer mbappe = new Footballer("Kylian", "Mbappe",
                    LocalDate.of(1998, 12, 20), Position.STRIKER, france, real);
            Footballer bellingham = new Footballer("Jude", "Bellingham",
                    LocalDate.of(2003, 6, 29), Position.MIDFIELDER, england, real);
            Footballer courtois = new Footballer("Thibaut", "Courtois",
                    LocalDate.of(1992, 5, 11), Position.GOALKEEPER, belgium, real);
            Footballer haaland = new Footballer("Erling", "Haaland",
                    LocalDate.of(2000, 7, 21), Position.STRIKER, norway, city);
            Footballer kevin = new Footballer("Kevin", "De Bruyne",
                    LocalDate.of(1991, 6, 28), Position.MIDFIELDER, belgium, city);
            Footballer stones = new Footballer("John", "Stones",
                    LocalDate.of(1994, 5, 28), Position.DEFENDER, england, city);
            Footballer ederson = new Footballer("Ederson", "Santana de Moraes",
                    LocalDate.of(1993, 8, 17), Position.GOALKEEPER, brazil, city);
        };
    }

}
