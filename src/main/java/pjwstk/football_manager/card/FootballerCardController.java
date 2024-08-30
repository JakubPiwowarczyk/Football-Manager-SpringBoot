package pjwstk.football_manager.card;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pjwstk.football_manager.club.Club;
import pjwstk.football_manager.club.ClubService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("card")
public class FootballerCardController {

    private final FootballerCardService footballerCardService;
    private final ClubService clubService;

    @Autowired
    public FootballerCardController(FootballerCardService footballerCardService, ClubService clubService) {
        this.footballerCardService = footballerCardService;
        this.clubService = clubService;
    }

    @GetMapping("/list")
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
        List<FootballerCard> cards = footballerCardService.getFootballerCardsByClubId(club.getId());
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
        List<FootballerCard> cards = footballerCardService.getStarting11ByClubId(club.getId());
        model.addAttribute("cards", cards);
        return "cards-list";
    }

    @GetMapping("/manage-card")
    String manageCard(Model model, @CookieValue("id") String id, @RequestParam String cardId) {
        Optional<FootballerCard> cardOptional = footballerCardService.getFootballerCardById(UUID.fromString(cardId));
        if (cardOptional.isEmpty()) {
            model.addAttribute("errorMessage", "Card not found");
            return "error";
        }
        FootballerCard card = cardOptional.get();
        UUID ownerId = UUID.fromString(id);
        if (!card.getClub().getOwner().getId().equals(ownerId)) {
            model.addAttribute("errorMessage", "Access denied");
            return "error";
        }
        model.addAttribute("card", card);
        return "footballer-card";
    }

    @GetMapping("/addToStarting11")
    String addToStarting11(Model model, @CookieValue("id") String id, @RequestParam String cardId) {
        Optional<FootballerCard> cardOptional = footballerCardService.getFootballerCardById(UUID.fromString(cardId));
        if (cardOptional.isEmpty()) {
            model.addAttribute("errorMessage", "Card not found");
            return "error";
        }
        FootballerCard card = cardOptional.get();
        UUID ownerId = UUID.fromString(id);
        if (!card.getClub().getOwner().getId().equals(ownerId)) {
            model.addAttribute("errorMessage", "Access denied");
            return "error";
        }
        clubService.addCardToStarting11(card);
        return getStarting11(model, id, card.club.getId().toString());
    }
}
