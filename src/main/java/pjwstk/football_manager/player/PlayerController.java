package pjwstk.football_manager.player;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Controller responsible for managing player-related operations.
 * Handles the login process and manages user sessions using cookies.
 */
@Controller
@RequestMapping(path = "player")
public class PlayerController {

    private final PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    /**
     * Displays the login page.
     *
     * @return The login view template.
     */
    @GetMapping("/login-page")
    String getLoginPage() {
        return "login";
    }

    /**
     * Handles the login request for a player.
     * Validates the player's email and password, and if successful, adds a session cookie and redirects to the home page.
     * If authentication fails, returns an error page with an appropriate message.
     *
     * @param email     The player's email used for login.
     * @param password  The player's password used for login.
     * @param model     Model to pass data or error messages to the view.
     * @param response  HttpServletResponse to manage the session cookie.
     * @return The home page if login is successful, otherwise the error page.
     */
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
