package pjwstk.football_manager.card;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import pjwstk.football_manager.club.Club;
import pjwstk.football_manager.footballer.Footballer;

import java.util.UUID;

@Entity
@DiscriminatorValue("TOTS")
public class TeamOfTheSeasonCard extends FootballerCard {

    private PlayStyle playStyle1;
    private PlayStyle playStyle2;
    private PlayStyle playStyle3;
    private static final int STATS_BOOST = 10;

    public TeamOfTheSeasonCard() {
    }

    public TeamOfTheSeasonCard(UUID id, int matchesInContract, float salaryPerMatch, Footballer footballer, Club club, boolean isPartOfStarting11,
                               int pace, int shooting, int passing, int dribbling, int defending, int physical,
                               int diving, int handling, int kicking, int reflexes, int speed, int positioning,
                               PlayStyle playStyle1, PlayStyle playStyle2, PlayStyle playStyle3) {
        super(id, matchesInContract, salaryPerMatch, footballer, club, isPartOfStarting11,
                pace+STATS_BOOST, shooting+STATS_BOOST, passing+STATS_BOOST, dribbling+STATS_BOOST, defending+STATS_BOOST, physical+STATS_BOOST,
                diving+STATS_BOOST, handling+STATS_BOOST, kicking+STATS_BOOST, reflexes+STATS_BOOST, speed+STATS_BOOST, positioning+STATS_BOOST);
        this.playStyle1 = playStyle1;
        this.playStyle2 = playStyle2;
        this.playStyle3 = playStyle3;
    }

    public TeamOfTheSeasonCard(int matchesInContract, float salaryPerMatch, Footballer footballer,
                               int stat1, int stat2, int stat3, int stat4, int stat5, int stat6,
                               PlayStyle playStyle1, PlayStyle playStyle2, PlayStyle playStyle3) {
        super(matchesInContract, salaryPerMatch, footballer, stat1+STATS_BOOST, stat2+STATS_BOOST, stat3+STATS_BOOST, stat4+STATS_BOOST, stat5+STATS_BOOST, stat6+STATS_BOOST);
        this.playStyle1 = playStyle1;
        this.playStyle2 = playStyle2;
        this.playStyle3 = playStyle3;
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

    public PlayStyle getPlayStyle3() {
        return playStyle3;
    }

    public void setPlayStyle3(PlayStyle playStyle3) {
        this.playStyle3 = playStyle3;
    }

    @Override
    public String getCardType() {
        return "TOTS";
    }
}
