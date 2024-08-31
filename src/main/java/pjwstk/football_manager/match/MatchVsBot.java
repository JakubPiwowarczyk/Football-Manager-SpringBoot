package pjwstk.football_manager.match;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import pjwstk.football_manager.club.Club;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@DiscriminatorValue("VS_BOT")
public class MatchVsBot extends Match {

    public MatchVsBot() {
    }

    public MatchVsBot(UUID id, int hostScore, int guestsScore, LocalDate matchDate, Club host) {
        super(id, hostScore, guestsScore, matchDate, host);
    }

    public MatchVsBot(int hostScore, int guestsScore, LocalDate matchDate, Club host) {
        super(hostScore, guestsScore, matchDate, host);
    }

    @Override
    public String getOpponent() {
        return "BOT";
    }

    @Override
    public String getProfit() {
        return "$0";
    }
}
