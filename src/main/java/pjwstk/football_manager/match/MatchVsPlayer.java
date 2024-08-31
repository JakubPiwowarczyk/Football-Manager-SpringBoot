package pjwstk.football_manager.match;

import jakarta.persistence.*;
import pjwstk.football_manager.club.Club;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@DiscriminatorValue("VS_PLAYER")
public class MatchVsPlayer extends Match {

    private float moneyForClubs;
    @ManyToOne
    private Club guest;

    public MatchVsPlayer() {
    }

    public MatchVsPlayer(UUID id, int hostScore, int guestsScore, LocalDate matchDate, Club host, float moneyForClubs, Club guest) {
        super(id, hostScore, guestsScore, matchDate, host);
        this.moneyForClubs = moneyForClubs;
        if (guest == null) throw new IllegalArgumentException("guest is null");
        this.guest = guest;
    }

    public MatchVsPlayer(int hostScore, int guestsScore, LocalDate matchDate, Club host, float moneyForClubs, Club guest) {
        super(hostScore, guestsScore, matchDate, host);
        this.moneyForClubs = moneyForClubs;
        if (guest == null) throw new IllegalArgumentException("guest is null");
        this.guest = guest;
    }

    public float getMoneyForClubs() {
        return moneyForClubs;
    }

    public void setMoneyForClubs(float moneyForClubs) {
        if (moneyForClubs < 0) moneyForClubs = 0;
        this.moneyForClubs = moneyForClubs;
    }

    public Club getGuest() {
        return guest;
    }

    public void setGuest(Club guest) {
        if (guest == null) throw new IllegalArgumentException("guest is null");
        if (guest == this.host) throw new IllegalArgumentException("guest cannot be the host");
        this.guest = guest;
    }

    @Override
    public String getOpponent() {
        return guest.getName();
    }

    @Override
    public String getProfit() {
        return "$" + moneyForClubs;
    }
}
