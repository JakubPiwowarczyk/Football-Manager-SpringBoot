package pjwstk.football_manager.club;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

@Component
public class ClubOwnershipInterceptor implements HandlerInterceptor {

    private final ClubService clubService;

    @Autowired
    public ClubOwnershipInterceptor(ClubService clubService) {
        this.clubService = clubService;
    }

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) throws Exception {
        String userIdAsString = getUserIdFromCookie(request);
        String clubIdAsString = request.getParameter("clubId");

        if (userIdAsString == null || clubIdAsString == null) {
            request.setAttribute("errorMessage", "Missing required parameters");
            request.getRequestDispatcher("/error").forward(request, response);
            return false;
        }

        UUID userId = UUID.fromString(userIdAsString);
        UUID clubId = UUID.fromString(clubIdAsString);

        Optional<Club> clubOptional = clubService.getById(clubId);
        if (clubOptional.isEmpty() || !clubOptional.get().getOwner().getId().equals(userId)) {
            request.setAttribute("errorMessage", "Club doesn't exists");
            request.getRequestDispatcher("/error").forward(request, response);
            return false;
        }
        return true;
    }

    private String getUserIdFromCookie(HttpServletRequest request) {
        if (request.getCookies() == null) return null;
        return Arrays.stream(request.getCookies())
                .filter(cookie -> cookie.getName().equals("id"))
                .map(Cookie::getValue)
                .findFirst()
                .orElse(null);
    }
}
