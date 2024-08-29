package pjwstk.football_manager.match;

import jakarta.persistence.*;
import pjwstk.football_manager.club.Club;

import java.time.LocalDate;
import java.util.UUID;

@Entity
public abstract class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    protected UUID id;
    protected int hostScore;
    protected int guestsScore;
    protected LocalDate matchDate;
    @ManyToOne
    @JoinColumn(nullable = false)
    protected Club host;

    public Match() {
    }

    public Match(UUID id, int hostScore, int guestsScore, LocalDate matchDate, Club host) {
        this.id = id;
        this.setHostScore(hostScore);
        this.setGuestsScore(guestsScore);
        this.setMatchDate(matchDate);
        this.host = host;
    }

    public Match(int hostScore, int guestsScore, LocalDate matchDate, Club host) {
        this.hostScore = hostScore;
        this.guestsScore = guestsScore;
        this.matchDate = matchDate;
        this.host = host;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getHostScore() {
        return hostScore;
    }

    public void setHostScore(int hostScore) {
        if (hostScore < 0) hostScore = 0;
        this.hostScore = hostScore;
    }

    public int getGuestsScore() {
        return guestsScore;
    }

    public void setGuestsScore(int guestsScore) {
        if (guestsScore < 0) guestsScore = 0;
        this.guestsScore = guestsScore;
    }

    public LocalDate getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(LocalDate matchDate) {
        if (matchDate.isAfter(LocalDate.now()))
            throw new IllegalArgumentException("Match date cannot be after now");
        this.matchDate = matchDate;
    }

    public Club getHost() {
        return host;
    }
}
