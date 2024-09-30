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

/**
 * Controller responsible for managing operations related to footballer cards.
 * Provides functionality for managing footballer cards, adding them to the starting 11,
 * and renewing their contracts.
 */
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

    /**
     * Displays details for a specific footballer card.
     *
     * @param model  Model to pass data to the view.
     * @param cardId UUID of the footballer card to be managed.
     * @return The footballer card view template.
     */
    @GetMapping("/manage-card")
    String manageCard(Model model, @RequestParam String cardId) {
        FootballerCard card = footballerCardService.getFootballerCardById(UUID.fromString(cardId)).orElseThrow();
        model.addAttribute("card", card);
        return "footballer-card";
    }

    /**
     * Adds the footballer card to the starting 11 of its club.
     *
     * @param cardId UUID of the footballer card to be added to the starting 11.
     * @return Redirects to the starting 11 view for the associated club.
     */
    @GetMapping("/add-to-starting11")
    String addToStarting11(@RequestParam String cardId) {
        FootballerCard card = footballerCardService.getFootballerCardById(UUID.fromString(cardId)).orElseThrow();
        clubService.addCardToStarting11(card);
        return "redirect:/club/starting11?clubId=" + card.getClub().getId();
    }

    /**
     * Renews the contract of a footballer card.
     * Checks if the club has enough budget to renew the contract based on the remaining matches and the player's salary.
     *
     * @param model  Model to pass data or error messages to the view.
     * @param cardId UUID of the footballer card whose contract is being renewed.
     * @return Redirects to the footballer card view if successful, otherwise returns the error view.
     */
    @GetMapping("/renew-contract")
    String renewTheContract(Model model, @RequestParam String cardId) {
        FootballerCard card = footballerCardService.getFootballerCardById(UUID.fromString(cardId)).orElseThrow();
        float cost = (28 - card.getMatchesInContract()) * card.getSalaryPerMatch();
        if (cost > card.getClub().getBudget()) {
            model.addAttribute("errorMessage", "Club doesn't have enough budget");
            return "error";
        }
        footballerCardService.renewCardsContract(card);
        return manageCard(model, card.getId().toString());
    }
}
