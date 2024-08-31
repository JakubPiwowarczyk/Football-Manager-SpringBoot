package pjwstk.football_manager.transferoffer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pjwstk.football_manager.card.FootballerCard;
import pjwstk.football_manager.card.FootballerCardService;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

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

    @GetMapping("/create-offer-page")
    String getCreateOfferPage(Model model, @CookieValue("id") String id, @RequestParam String cardId) {
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
        return "create-transfer";
    }

    @PostMapping("/create-offer")
    String createOffer(Model model, @CookieValue("id") String id, @RequestParam int price,
                       @RequestParam LocalDate endDate, @RequestParam String cardId) {
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
        transferOfferService.createTransferOffer(card, price, endDate);
        return "redirect:/club/manage-club?clubId=" + card.getClub().getId();
    }

}
