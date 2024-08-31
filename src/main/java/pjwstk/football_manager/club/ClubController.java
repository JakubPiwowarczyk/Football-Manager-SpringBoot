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

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("club")
public class ClubController {

    private final ClubService clubService;

    @Autowired
    public ClubController(ClubService clubService) {
        this.clubService = clubService;
    }

    @GetMapping("/list")
    String getClubs(Model model, @CookieValue("id") String id) {
        UUID uuid = UUID.fromString(id);
        List<Club> clubs = clubService.getAllByOwnerId(uuid);
        model.addAttribute("clubs", clubs);
        return "clubs-list";
    }

    @GetMapping("/manage-club")
    String manageClub(Model model, @CookieValue("id") String id, @RequestParam String clubId) {
        Optional<Club> clubOptional = clubService.getById(UUID.fromString(clubId));
        if (clubOptional.isEmpty()) {
            model.addAttribute("errorMessage", "Club not found");
            return "error";
        }
        Club club = clubOptional.get();
        UUID ownerId = UUID.fromString(id);
        if (!club.getOwner().getId().equals(ownerId)) {
            model.addAttribute("errorMessage", "Access denied");
            return "error";
        }
        model.addAttribute("club", club);
        return "club-info";
    }

    @GetMapping("/match-history")
    String getMatchHistory(Model model, @CookieValue("id") String id, @RequestParam String clubId) {
        Optional<Club> clubOptional = clubService.getById(UUID.fromString(clubId));
        if (clubOptional.isEmpty()) {
            model.addAttribute("errorMessage", "Club not found");
            return "error";
        }
        Club club = clubOptional.get();
        UUID ownerId = UUID.fromString(id);
        if (!club.getOwner().getId().equals(ownerId)) {
            model.addAttribute("errorMessage", "Access denied");
            return "error";
        }
        List<Match> matchHistory = club.getMatches();
        model.addAttribute("matches", matchHistory);
        return "match-list";
    }

    @GetMapping("/cards-list")
    String getCards(Model model, @CookieValue("id") String id, @RequestParam String clubId) {
        Optional<Club> clubOptional = clubService.getById(UUID.fromString(clubId));
        if (clubOptional.isEmpty()) {
            model.addAttribute("errorMessage", "Club not found");
            return "error";
        }
        Club club = clubOptional.get();
        UUID ownerId = UUID.fromString(id);
        if (!club.getOwner().getId().equals(ownerId)) {
            model.addAttribute("errorMessage", "Access denied");
            return "error";
        }
        List<FootballerCard> cards = club.getCards();
        model.addAttribute("cards", cards);
        return "cards-list";
    }

    @GetMapping("/starting11")
    String getStarting11(Model model, @CookieValue("id") String id, @RequestParam String clubId) {
        Optional<Club> clubOptional = clubService.getById(UUID.fromString(clubId));
        if (clubOptional.isEmpty()) {
            model.addAttribute("errorMessage", "Club not found");
            return "error";
        }
        Club club = clubOptional.get();
        UUID ownerId = UUID.fromString(id);
        if (!club.getOwner().getId().equals(ownerId)) {
            model.addAttribute("errorMessage", "Access denied");
            return "error";
        }
        List<FootballerCard> cards = club.getStarting11();
        model.addAttribute("cards", cards);
        return "cards-list";
    }
}
