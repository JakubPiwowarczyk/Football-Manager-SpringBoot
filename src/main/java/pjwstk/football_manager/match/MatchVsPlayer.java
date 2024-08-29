package pjwstk.football_manager.match;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import pjwstk.football_manager.club.Club;

@Entity
public class MatchVsPlayer extends Match {

    @Column(nullable = false)
    private float moneyForClubs;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Club guest;

}
