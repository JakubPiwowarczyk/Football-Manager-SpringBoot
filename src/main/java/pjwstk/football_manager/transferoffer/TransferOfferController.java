package pjwstk.football_manager.transferoffer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pjwstk.football_manager.card.FootballerCard;
import pjwstk.football_manager.card.FootballerCardService;

import java.time.LocalDate;
import java.util.UUID;

/**
 * Controller responsible for handling transfer offers.
 * Provides functionality for creating transfer offers for footballer cards.
 */
@Controller
@RequestMapping("transfer")
public class TransferOfferController {

    private final TransferOfferService transferOfferService;
    private final FootballerCardService footballerCardService;

    @Autowired
    public TransferOfferController(TransferOfferService transferOfferService, FootballerCardService footballerCardService) {
        this.transferOfferService = transferOfferService;
        this.footballerCardService = footballerCardService;
    }

    /**
     * Displays the page to create a transfer offer for a footballer card.
     *
     * @param model  Model to pass data to the view.
     * @param cardId The UUID of the footballer card for which the offer is being created.
     * @return The create-transfer view template.
     */
    @GetMapping("/create-offer-page")
    String getCreateOfferPage(Model model, @RequestParam String cardId) {
        FootballerCard card = footballerCardService.getFootballerCardById(UUID.fromString(cardId)).orElseThrow();
        model.addAttribute("card", card);
        return "create-transfer";
    }

    /**
     * Creates a transfer offer for a footballer card.
     *
     * @param price   The price of the transfer offer.
     * @param endDate The end date of the transfer offer.
     * @param cardId  The UUID of the footballer card for which the offer is created.
     * @return Redirects to the manage club page after the offer is created.
     */
    @PostMapping("/create-offer")
    String createOffer(@RequestParam int price, @RequestParam LocalDate endDate, @RequestParam String cardId) {
        FootballerCard card = footballerCardService.getFootballerCardById(UUID.fromString(cardId)).orElseThrow();
        transferOfferService.createTransferOffer(card, price, endDate);
        return "redirect:/club/manage-club?clubId=" + card.getClub().getId();
    }

}
