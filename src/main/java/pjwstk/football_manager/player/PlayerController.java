package pjwstk.football_manager.player;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(path = "player")
public class PlayerController {

    private final PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/login-page")
    String getLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    String login(@RequestParam String email, @RequestParam String password, Model model) {
        Optional<Player> player = playerService.findByEmail(email);
        if (player.isPresent()) {
            Player p = player.get();
            if (p.getPassword().equals(password)) {
                model.addAttribute("nickname", p.getNickname());
                return "home";
            }
        }
        model.addAttribute("errorMessage", "Invalid email or password");
        return "error";
    }
}
