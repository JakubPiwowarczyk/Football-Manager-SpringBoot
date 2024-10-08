package pjwstk.football_manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pjwstk.football_manager.card.BasicCard;
import pjwstk.football_manager.card.FootballerCard;
import pjwstk.football_manager.card.FootballerCardRepository;
import pjwstk.football_manager.card.PlayStyle;
import pjwstk.football_manager.club.Club;
import pjwstk.football_manager.club.ClubRepository;
import pjwstk.football_manager.footballer.*;
import pjwstk.football_manager.match.Match;
import pjwstk.football_manager.match.MatchRepository;
import pjwstk.football_manager.match.MatchVsBot;
import pjwstk.football_manager.match.MatchVsPlayer;
import pjwstk.football_manager.player.Player;
import pjwstk.football_manager.player.PlayerRepository;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class AppConfig {

    @Bean
    @Autowired
    CommandLineRunner commandLineRunner(PlayerRepository playerRepository,
                                        ClubRepository clubRepository,
                                        LeagueRepository leagueRepository,
                                        TeamRepository teamRepository,
                                        NationalityRepository nationalityRepository,
                                        FootballerRepository footballerRepository,
                                        FootballerCardRepository footballerCardRepository,
                                        MatchRepository matchRepository) {
        return args -> {

            // Creation of sample data

            League laliga = new League("LaLiga");
            League premierLeague = new League("Premier League");
            League bundesliga = new League("Bundesliga");
            leagueRepository.saveAll(List.of(laliga, bundesliga, premierLeague));

            Team barcelona = new Team("FC Barcelona", laliga);
            Team real = new Team("Real Madrid", laliga);
            Team city = new Team("Manchester City", premierLeague);
            teamRepository.saveAll(List.of(barcelona, city, real));

            Nationality spain = new Nationality("Spain");
            Nationality germany = new Nationality("Germany");
            Nationality france = new Nationality("France");
            Nationality england = new Nationality("England");
            Nationality poland = new Nationality("Poland");
            Nationality brazil = new Nationality("Brazil");
            Nationality argentina = new Nationality("Argentina");
            Nationality norway = new Nationality("Norway");
            Nationality belgium = new Nationality("Belgium");
            nationalityRepository.saveAll(List.of(spain, germany, france, england, poland, brazil, argentina, belgium, norway));

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
            footballerRepository.saveAll(List.of(lewandowski, messi, yamal, marc, vini, mbappe, bellingham, courtois, haaland, kevin, stones, ederson));

            FootballerCard lewyBasic = new BasicCard(26, 150.00f, lewandowski,
                    75, 91, 80, 87, 44, 84, PlayStyle.POWER_SHOT);
            FootballerCard messiBasic = new BasicCard(28, 200f, messi,
                    80, 87, 90, 94, 33, 64, PlayStyle.RAPID);
            FootballerCard yamalBasic = new BasicCard(28, 50f, yamal,
                    82, 75, 76, 82, 23, 48, PlayStyle.FINESSE_SHOT);
            FootballerCard marcBasic = new BasicCard(28, 100f, marc,
                    86, 85, 89, 91, 47, 86, PlayStyle.FAR_REACH);
            FootballerCard viniBasic = new BasicCard(28, 200f, vini,
                    95, 82, 78, 90, 29, 68, PlayStyle.RAPID);
            FootballerCard mbappeBasic = new BasicCard(28, 200f, mbappe,
                    97, 90, 80, 92, 36, 78, PlayStyle.QUICK_STEP);
            FootballerCard bellinghamBasic = new BasicCard(28, 200f, bellingham,
                    76, 75, 79, 85, 78, 82, PlayStyle.ANTICIPATE);
            FootballerCard courtoisBasic = new BasicCard(28, 100f, courtois,
                    85, 89, 76, 93, 46, 90, PlayStyle.RELENTLESS);
            FootballerCard haalandBasic = new BasicCard(28, 200f, haaland,
                    89, 93, 66, 80, 45, 88, PlayStyle.POWER_SHOT);
            FootballerCard kevinBasic = new BasicCard(28, 200f, kevin,
                    72, 88, 94, 87, 65, 78, PlayStyle.TIKI_TAKA);
            FootballerCard stonesBasic = new BasicCard(28, 100f, stones,
                    72, 52, 75, 77, 85, 77, PlayStyle.SLIDE_TACKLE);
            FootballerCard edersonBasic = new BasicCard(28, 100f, ederson,
                    86, 82, 91, 86, 64, 86, PlayStyle.FAR_THROW);
            footballerCardRepository.saveAll(List.of(lewyBasic, messiBasic, yamalBasic, marcBasic,
                    viniBasic, mbappeBasic, bellinghamBasic, courtoisBasic,
                    haalandBasic, kevinBasic, stonesBasic, edersonBasic));

            Player player1 = new Player("Maciej_LOL", "maciek@gmail.com", "123");
            Player player2 = new Player("Eddie", "eddie@gmail.com", "qwerty");

            Club club1 = new Club("Barcelonina", 5000f, 17, player1);
            club1.addCard(lewyBasic);
            club1.addCard(messiBasic);
            club1.addCard(yamalBasic);
            club1.addCard(marcBasic);
            club1.addCard(viniBasic);
            club1.addCard(stonesBasic);
            Match match1 = new MatchVsBot(2, 5, LocalDate.now(), club1);

            playerRepository.saveAll(List.of(player1, player2));
            Club club2 = new Club("KS Wadowice", 2500.70f, 11, player1);
            Match match2 = new MatchVsPlayer(3, 2, LocalDate.now(), club1, 400f, club2);
            playerRepository.save(player1);

        };
    }

}
