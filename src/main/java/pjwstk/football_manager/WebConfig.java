package pjwstk.football_manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import pjwstk.football_manager.card.CardOwnershipInterceptor;
import pjwstk.football_manager.club.ClubOwnershipInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final ClubOwnershipInterceptor clubOwnershipInterceptor;
    private final CardOwnershipInterceptor cardOwnershipInterceptor;

    @Autowired
    public WebConfig(ClubOwnershipInterceptor clubOwnershipInterceptor,
                     CardOwnershipInterceptor cardOwnershipInterceptor) {
        this.clubOwnershipInterceptor = clubOwnershipInterceptor;
        this.cardOwnershipInterceptor = cardOwnershipInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(clubOwnershipInterceptor)
                .addPathPatterns(
                        "/club/manage-club",
                        "/club/match-history",
                        "/club/cards-list",
                        "/club/starting11",
                        "/club/transfer-list"
                );
        registry.addInterceptor(cardOwnershipInterceptor)
                .addPathPatterns("/card/**");
    }
}
