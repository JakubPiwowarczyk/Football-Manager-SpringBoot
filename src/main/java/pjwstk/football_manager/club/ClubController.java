package pjwstk.football_manager.club;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pjwstk.football_manager.card.FootballerCard;
import pjwstk.football_manager.match.Match;
import pjwstk.football_manager.transferoffer.TransferOffer;

import java.util.List;
import java.util.UUID;


/**
 * Controller for handling club-related requests in the football manager system.
 * This controller manages club listing, club management, match history, footballer cards, starting eleven, and transfer offers.
 */
@Controller
@RequestMapping("club")
public class ClubController {

    private final ClubService clubService;

    @Autowired
    public ClubController(ClubService clubService) {
        this.clubService = clubService;
    }

    /**
     * Retrieves the list of clubs owned by the user and displays it on the 'clubs-list' page.
     *
     * @param model the model to pass attributes to the view
     * @param id the UUID of the owner stored in a cookie
     * @return the name of the view to be rendered ('clubs-list')
     */
    @GetMapping("/list")
    String getClubs(Model model, @CookieValue("id") String id) {
        UUID uuid = UUID.fromString(id);
        List<Club> clubs = clubService.getAllByOwnerId(uuid);
        model.addAttribute("clubs", clubs);
        return "clubs-list";
    }

    /**
     * Displays the details of a specific club owned by the user on the 'club-info' page.
     *
     * @param model the model to pass attributes to the view
     * @param id the UUID of the owner stored in a cookie
     * @param clubId the UUID of the club to be managed
     * @return the name of the view to be rendered ('club-info'), or an error page if validation fails
     */
    @GetMapping("/manage-club")
    String manageClub(Model model, @CookieValue("id") String id, @RequestParam String clubId) {
        Club club = clubService.getById(UUID.fromString(clubId)).orElseThrow();
        model.addAttribute("club", club);
        return "club-info";
    }

    /**
     * Retrieves the match history for a specific club and displays it on the 'match-list' page.
     *
     * @param model the model to pass attributes to the view
     * @param id the UUID of the owner stored in a cookie
     * @param clubId the UUID of the club whose match history is requested
     * @return the name of the view to be rendered ('match-list'), or an error page if validation fails
     */
    @GetMapping("/match-history")
    String getMatchHistory(Model model, @CookieValue("id") String id, @RequestParam String clubId) {
        Club club = clubService.getById(UUID.fromString(clubId)).orElseThrow();
        List<Match> matchHistory = club.getMatches();
        model.addAttribute("matches", matchHistory);
        return "match-list";
    }

    /**
     * Retrieves the list of footballer cards for a specific club and displays it on the 'cards-list' page.
     *
     * @param model the model to pass attributes to the view
     * @param id the UUID of the owner stored in a cookie
     * @param clubId the UUID of the club whose cards are requested
     * @return the name of the view to be rendered ('cards-list'), or an error page if validation fails
     */
    @GetMapping("/cards-list")
    String getCards(Model model, @CookieValue("id") String id, @RequestParam String clubId) {
        Club club = clubService.getById(UUID.fromString(clubId)).orElseThrow();
        List<FootballerCard> cards = club.getCards();
        model.addAttribute("cards", cards);
        return "cards-list";
    }

    /**
     * Retrieves the list of starting eleven footballer cards for a specific club and displays it on the 'cards-list' page.
     *
     * @param model the model to pass attributes to the view
     * @param id the UUID of the owner stored in a cookie
     * @param clubId the UUID of the club whose starting eleven is requested
     * @return the name of the view to be rendered ('cards-list'), or an error page if validation fails
     */
    @GetMapping("/starting11")
    String getStarting11(Model model, @CookieValue("id") String id, @RequestParam String clubId) {
        Club club = clubService.getById(UUID.fromString(clubId)).orElseThrow();
        List<FootballerCard> cards = club.getStarting11();
        model.addAttribute("cards", cards);
        return "cards-list";
    }

    /**
     * Retrieves the list of transfer offers for a specific club and displays it on the 'transfer-list' page.
     *
     * @param model the model to pass attributes to the view
     * @param id the UUID of the owner stored in a cookie
     * @param clubId the UUID of the club whose transfer offers are requested
     * @return the name of the view to be rendered ('transfer-list'), or an error page if validation fails
     */
    @GetMapping("/transfer-list")
    String getTransferOffers(Model model, @CookieValue("id") String id, @RequestParam String clubId) {
        Club club = clubService.getById(UUID.fromString(clubId)).orElseThrow();
        List<TransferOffer> offers = club.getTransferOffers();
        model.addAttribute("transferOffers", offers);
        return "transfer-list";
    }
}
