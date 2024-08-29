package pjwstk.football_manager.card;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import pjwstk.football_manager.player.Player;

@Entity
@DiscriminatorValue("BASIC")
public class BasicCard extends FootballerCard {

    private PlayStyle playStyle1;

}
