package pjwstk.football_manager.card;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pjwstk.football_manager.club.ClubService;

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

    @GetMapping("/add-to-starting11")
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
        return "redirect:/club/starting11?clubId=" + card.getClub().getId();
    }

    @GetMapping("/renew-contract")
    String renewTheContract(Model model, @CookieValue("id") String id, @RequestParam String cardId) {
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
        float cost = (28 - card.getMatchesInContract()) * card.getSalaryPerMatch();
        if (cost > card.getClub().getBudget()) {
            model.addAttribute("errorMessage", "Club doesn't have enough budget");
            return "error";
        }
        footballerCardService.renewCardsContract(card);
        return manageCard(model, id, card.getId().toString());
    }
}
