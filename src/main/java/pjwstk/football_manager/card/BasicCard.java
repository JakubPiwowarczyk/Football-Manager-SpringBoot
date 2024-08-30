package pjwstk.football_manager.card;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import pjwstk.football_manager.club.Club;
import pjwstk.football_manager.footballer.Footballer;
import pjwstk.football_manager.player.Player;

import java.util.UUID;

@Entity
@DiscriminatorValue("BASIC")
public class BasicCard extends FootballerCard {

    private PlayStyle playStyle1;

    public BasicCard() {
    }

    public BasicCard(UUID id, int matchesInContract, float salaryPerMatch, Footballer footballer, Club club,
                     int pace, int shooting, int passing, int dribbling, int defending, int physical,
                     int diving, int handling, int kicking, int reflexes, int speed, int positioning,
                     PlayStyle playStyle1) {
        super(id, matchesInContract, salaryPerMatch, footballer, club, pace, shooting, passing, dribbling, defending, physical,
                diving, handling, kicking, reflexes, speed, positioning);
        this.playStyle1 = playStyle1;
    }

    public BasicCard(int matchesInContract, float salaryPerMatch, Footballer footballer,
                     int stat1, int stat2, int stat3, int stat4, int stat5, int stat6, PlayStyle playStyle1) {
        super(matchesInContract, salaryPerMatch, footballer, stat1, stat2, stat3, stat4, stat5, stat6);
        this.playStyle1 = playStyle1;
    }

    public PlayStyle getPlayStyle1() {
        return playStyle1;
    }

    public void setPlayStyle1(PlayStyle playStyle1) {
        this.playStyle1 = playStyle1;
    }
}
