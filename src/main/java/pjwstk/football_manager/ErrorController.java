package pjwstk.football_manager;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {

    @GetMapping("/error")
    String error(Model model, HttpServletRequest request) {
        String errorMessage = request.getAttribute("errorMessage").toString();
        if (errorMessage == null) {
            errorMessage = "Something went wrong.";
        }
        model.addAttribute("errorMessage", errorMessage);
        return "error";
    }
}
