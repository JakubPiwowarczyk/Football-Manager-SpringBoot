package pjwstk.football_manager.player;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
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
    String login(@RequestParam String email, @RequestParam String password,
                 Model model, HttpServletResponse response) {
        Optional<Player> player = playerService.findByEmail(email);
        if (player.isPresent()) {
            Player p = player.get();
            if (p.getPassword().equals(password)) {
                model.addAttribute("nickname", p.getNickname());
                Cookie cookie = new Cookie("id", p.getId().toString());
                cookie.setPath("/");
                response.addCookie(cookie);
                return "home";
            }
        }
        model.addAttribute("errorMessage", "Invalid email or password");
        return "error";
    }
}
