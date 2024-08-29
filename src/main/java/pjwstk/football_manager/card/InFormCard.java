package pjwstk.football_manager.card;

import jakarta.persistence.Entity;

@Entity
public class InFormCard extends FootballerCard{

    private PlayStyle playStyle1;
    private PlayStyle playStyle2;
    private static final int STATS_BOOST = 5;

}
