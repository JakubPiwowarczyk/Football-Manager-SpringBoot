package pjwstk.football_manager.card;

import jakarta.persistence.*;
import pjwstk.football_manager.club.Club;
import pjwstk.football_manager.footballer.Footballer;
import pjwstk.football_manager.footballer.Position;

import java.util.Objects;
import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "card_type", discriminatorType = DiscriminatorType.STRING)
public abstract class FootballerCard {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    protected UUID id;
    protected int matchesInContract;
    protected float salaryPerMatch;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = false)
    protected Footballer footballer;
    @ManyToOne
    protected Club club;

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

    public FootballerCard() {
    }

    public FootballerCard(UUID id, int matchesInContract, float salaryPerMatch, Footballer footballer, Club club,
                          int pace, int shooting, int passing, int dribbling, int defending, int physical,
                          int diving, int handling, int kicking, int reflexes, int speed, int positioning) {
        this.id = id;
        this.setMatchesInContract(matchesInContract);
        this.setSalaryPerMatch(salaryPerMatch);
        if (footballer == null) throw new IllegalArgumentException("Footballer is null");
        this.footballer = footballer;
        this.club = club;
        this.setPace(pace);
        this.setShooting(shooting);
        this.setPassing(passing);
        this.setDribbling(dribbling);
        this.setDefending(defending);
        this.setPhysical(physical);
        this.setDiving(diving);
        this.setHandling(handling);
        this.setKicking(kicking);
        this.setReflexes(reflexes);
        this.setSpeed(speed);
        this.setPositioning(positioning);
    }

    public FootballerCard(int matchesInContract, float salaryPerMatch, Footballer footballer,
                          int stat1, int stat2, int stat3, int stat4, int stat5, int stat6) {
        this.setMatchesInContract(matchesInContract);
        this.setSalaryPerMatch(salaryPerMatch);
        if (footballer == null) throw new IllegalArgumentException("Footballer is null");
        this.footballer = footballer;

        if (footballer.getPosition() == Position.GOALKEEPER) {
            this.setDiving(stat1);
            this.setHandling(stat2);
            this.setKicking(stat3);
            this.setReflexes(stat4);
            this.setSpeed(stat5);
            this.setPositioning(stat6);
        } else {
            this.setPace(stat1);
            this.setShooting(stat2);
            this.setPassing(stat3);
            this.setDribbling(stat4);
            this.setDefending(stat5);
            this.setPhysical(stat6);
        }
    }

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
        if (matchesInContract < 0) matchesInContract = 0;
        this.matchesInContract = matchesInContract;
    }

    public float getSalaryPerMatch() {
        return salaryPerMatch;
    }

    public void setSalaryPerMatch(float salaryPerMatch) {
        if (salaryPerMatch < 0) salaryPerMatch = 0;
        this.salaryPerMatch = salaryPerMatch;
    }

    public Footballer getFootballer() {
        return footballer;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    public int getPace() {
        return pace;
    }

    public void setPace(int pace) {
        if (pace < 0) pace = 0;
        if (pace > 99) pace = 99;
        this.pace = pace;
    }

    public int getShooting() {
        return shooting;
    }

    public void setShooting(int shooting) {
        if (shooting < 0) shooting = 0;
        if (shooting > 99) shooting = 99;
        this.shooting = shooting;
    }

    public int getPassing() {
        return passing;
    }

    public void setPassing(int passing) {
        if (passing < 0) passing = 0;
        if (passing > 99) passing = 99;
        this.passing = passing;
    }

    public int getDribbling() {
        return dribbling;
    }

    public void setDribbling(int dribbling) {
        if (dribbling < 0) dribbling = 0;
        if (dribbling > 99) dribbling = 99;
        this.dribbling = dribbling;
    }

    public int getDefending() {
        return defending;
    }

    public void setDefending(int defending) {
        if (defending < 0) defending = 0;
        if (defending > 99) defending = 99;
        this.defending = defending;
    }

    public int getPhysical() {
        return physical;
    }

    public void setPhysical(int physical) {
        if (physical < 0) physical = 0;
        if (physical > 99) physical = 99;
        this.physical = physical;
    }

    public int getDiving() {
        return diving;
    }

    public void setDiving(int diving) {
        if (diving < 0) diving = 0;
        if (diving > 99) diving = 99;
        this.diving = diving;
    }

    public int getHandling() {
        return handling;
    }

    public void setHandling(int handling) {
        if (handling < 0) handling = 0;
        if (handling > 99) handling = 99;
        this.handling = handling;
    }

    public int getKicking() {
        return kicking;
    }

    public void setKicking(int kicking) {
        if (kicking < 0) kicking = 0;
        if (kicking > 99) kicking = 99;
        this.kicking = kicking;
    }

    public int getReflexes() {
        return reflexes;
    }

    public void setReflexes(int reflexes) {
        if (reflexes < 0) reflexes = 0;
        if (reflexes > 99) reflexes = 99;
        this.reflexes = reflexes;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        if (speed < 0) speed = 0;
        if (speed > 99) speed = 99;
        this.speed = speed;
    }

    public int getPositioning() {
        return positioning;
    }

    public void setPositioning(int positioning) {
        if (positioning < 0) positioning = 0;
        if (positioning > 99) positioning = 99;
        this.positioning = positioning;
    }

    public int getOverallRating() {
        int overallRating = 0;
        if (this.footballer.getPosition() == Position.GOALKEEPER) {
            overallRating = (this.diving + this.handling + this.kicking +
                    this.reflexes + this.speed + this.positioning)/6;
        } else {
            overallRating = (this.pace + this.shooting + this.passing +
                    this.dribbling + this.defending + this.physical)/6;
        }
        return overallRating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FootballerCard that = (FootballerCard) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "FootballerCard{" +
                "id=" + id +
                ", matchesInContract=" + matchesInContract +
                ", salaryPerMatch=" + salaryPerMatch +
                ", footballer=" + footballer +
                ", pace=" + pace +
                ", shooting=" + shooting +
                ", passing=" + passing +
                ", dribbling=" + dribbling +
                ", defending=" + defending +
                ", physical=" + physical +
                ", diving=" + diving +
                ", handling=" + handling +
                ", kicking=" + kicking +
                ", reflexes=" + reflexes +
                ", speed=" + speed +
                ", positioning=" + positioning +
                '}';
    }
}
