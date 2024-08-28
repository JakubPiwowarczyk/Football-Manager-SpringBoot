package pjwstk.football_manager.card;

import jakarta.persistence.*;
import pjwstk.football_manager.footballer.Footballer;

import java.util.UUID;

@Entity
public abstract class FootballerCard {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    protected UUID id;
    protected int matchesInContract;
    @ManyToOne
    protected Footballer footballer;

    // Stats for field players
    protected int pace;
    protected int shooting;
    protected int passing;
    protected int dribbling;
    protected int defending;
    protected int physical;

    // Stats for goalkeepers
    protected int diving;
    protected int handling;
    protected int kicking;
    protected int reflexes;
    protected int speed;
    protected int positioning;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getMatchesInContract() {
        return matchesInContract;
    }

    public void setMatchesInContract(int matchesInContract) {
        if (matchesInContract < 0)
            matchesInContract = 0;
        this.matchesInContract = matchesInContract;
    }

    public Footballer getFootballer() {
        return footballer;
    }

    public void setFootballer(Footballer footballer) {
        if (footballer == null)
            throw new IllegalArgumentException("Footballer cannot be null");
        this.footballer = footballer;
    }

    public int getPace() {
        return pace;
    }

    public void setPace(int pace) {
        this.pace = pace;
    }

    public int getShooting() {
        return shooting;
    }

    public void setShooting(int shooting) {
        this.shooting = shooting;
    }

    public int getPassing() {
        return passing;
    }

    public void setPassing(int passing) {
        this.passing = passing;
    }

    public int getDribbling() {
        return dribbling;
    }

    public void setDribbling(int dribbling) {
        this.dribbling = dribbling;
    }

    public int getDefending() {
        return defending;
    }

    public void setDefending(int defending) {
        this.defending = defending;
    }

    public int getPhysical() {
        return physical;
    }

    public void setPhysical(int physical) {
        this.physical = physical;
    }

    public int getDiving() {
        return diving;
    }

    public void setDiving(int diving) {
        this.diving = diving;
    }

    public int getHandling() {
        return handling;
    }

    public void setHandling(int handling) {
        this.handling = handling;
    }

    public int getKicking() {
        return kicking;
    }

    public void setKicking(int kicking) {
        this.kicking = kicking;
    }

    public int getReflexes() {
        return reflexes;
    }

    public void setReflexes(int reflexes) {
        this.reflexes = reflexes;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getPositioning() {
        return positioning;
    }

    public void setPositioning(int positioning) {
        this.positioning = positioning;
    }
}
