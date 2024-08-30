package pjwstk.football_manager.card;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("card")
public class FootballerCardController {

    private final FootballerCardService footballerCardService;

    @Autowired
    public FootballerCardController(FootballerCardService footballerCardService) {
        this.footballerCardService = footballerCardService;
    }

    @GetMapping("/list")
    String getCards(Model model, @RequestParam String clubId) {
        UUID uuid = UUID.fromString(clubId);
        List<FootballerCard> cards = footballerCardService.getFootballerCardsByClubId(uuid);
        model.addAttribute("cards", cards);
        return "cards-list";
    }
}
