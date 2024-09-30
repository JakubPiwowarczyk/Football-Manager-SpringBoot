package pjwstk.football_manager.card;

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
public class CardOwnershipInterceptor implements HandlerInterceptor {

    private final FootballerCardService footballerCardService;

    @Autowired
    public CardOwnershipInterceptor(FootballerCardService footballerCardService) {
        this.footballerCardService = footballerCardService;
    }

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) throws Exception {
        String userIdAsString = getUserIdFromCookie(request);
        String cardIdAsString = request.getParameter("cardId");

        if (userIdAsString == null || cardIdAsString == null) {
            request.setAttribute("errorMessage", "Missing required parameters");
            request.getRequestDispatcher("/error").forward(request, response);
            return false;
        }

        UUID userId = UUID.fromString(userIdAsString);
        UUID cardId = UUID.fromString(cardIdAsString);

        Optional<FootballerCard> cardOptional = footballerCardService.getFootballerCardById(cardId);
        if (cardOptional.isEmpty() || !cardOptional.get().getClub().getOwner().getId().equals(userId)) {
            request.setAttribute("errorMessage", "Card doesn't exists");
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
