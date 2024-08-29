package pjwstk.football_manager.club;

import jakarta.persistence.*;
import pjwstk.football_manager.card.FootballerCard;
import pjwstk.football_manager.player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Club {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private float budget;
    @Column(nullable = false)
    private int leaguePoints;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Player owner;
    @OneToMany
    private List<FootballerCard> cards = new ArrayList<>();
    @OneToMany
    private List<FootballerCard> starting11 = new ArrayList<>(11);

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name.isBlank())
            throw new IllegalArgumentException("Name cannot be blank");
        this.name = name;
    }

    public float getBudget() {
        return budget;
    }

    public void setBudget(float budget) {
        this.budget = budget;
    }

    public int getLeaguePoints() {
        return leaguePoints;
    }

    public void setLeaguePoints(int leaguePoints) {
        this.leaguePoints = leaguePoints;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        if (owner == null)
            throw new IllegalArgumentException("Owner cannot be null");
        this.owner = owner;
    }
}
