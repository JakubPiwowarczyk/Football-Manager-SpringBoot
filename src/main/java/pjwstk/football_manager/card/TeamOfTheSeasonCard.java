package pjwstk.football_manager.card;

import jakarta.persistence.Entity;

@Entity
public class TeamOfTheSeasonCard extends FootballerCard {

    private PlayStyle playStyle1;
    private PlayStyle playStyle2;
    private PlayStyle playStyle3;
    private static final int STATS_BOOST = 10;

}
