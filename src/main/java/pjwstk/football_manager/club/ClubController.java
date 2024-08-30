package pjwstk.football_manager.club;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
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
}
