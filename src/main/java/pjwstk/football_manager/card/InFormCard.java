package pjwstk.football_manager.card;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import pjwstk.football_manager.footballer.Footballer;

import java.util.UUID;

@Entity
@DiscriminatorValue("INFORM")
public class InFormCard extends FootballerCard{

    private PlayStyle playStyle1;
    private PlayStyle playStyle2;
    private static final int STATS_BOOST = 5;

    public InFormCard() {
    }

    public InFormCard(UUID id, int matchesInContract, float salaryPerMatch, Footballer footballer,
                      int pace, int shooting, int passing, int dribbling, int defending, int physical,
                      int diving, int handling, int kicking, int reflexes, int speed, int positioning,
                      PlayStyle playStyle1, PlayStyle playStyle2) {
        super(id, matchesInContract, salaryPerMatch, footballer, pace, shooting, passing, dribbling, defending, physical,
                diving, handling, kicking, reflexes, speed, positioning);
        this.playStyle1 = playStyle1;
        this.playStyle2 = playStyle2;
    }

    public InFormCard(int matchesInContract, float salaryPerMatch, Footballer footballer,
                      int stat1, int stat2, int stat3, int stat4, int stat5, int stat6,
                      PlayStyle playStyle1, PlayStyle playStyle2) {
        super(matchesInContract, salaryPerMatch, footballer, stat1, stat2, stat3, stat4, stat5, stat6);
        this.playStyle1 = playStyle1;
        this.playStyle2 = playStyle2;
    }

    public PlayStyle getPlayStyle1() {
        return playStyle1;
    }

    public void setPlayStyle1(PlayStyle playStyle1) {
        this.playStyle1 = playStyle1;
    }

    public PlayStyle getPlayStyle2() {
        return playStyle2;
    }

    public void setPlayStyle2(PlayStyle playStyle2) {
        this.playStyle2 = playStyle2;
    }
}
