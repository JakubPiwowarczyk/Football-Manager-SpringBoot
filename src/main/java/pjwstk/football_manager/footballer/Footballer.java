package pjwstk.football_manager.footballer;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;
import java.util.UUID;

@Entity
public class Footballer {

    @Id
    private UUID id;
    private String name;
    private String surname;
    private LocalDate dateOfBirth;
    private Position position;
    @ManyToOne
    private Nationality nationality;
    @ManyToOne
    private Team team;

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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        if (surname.isBlank())
            throw new IllegalArgumentException("Surname cannot be blank");
        this.surname = surname;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        if (position == null)
            throw new IllegalArgumentException("Position cannot be null");
        this.position = position;
    }

    public Nationality getNationality() {
        return nationality;
    }

    public void setNationality(Nationality nationality) {
        if (nationality == null)
            throw new IllegalArgumentException("Nationality cannot be null");
        this.nationality = nationality;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        if(team == null)
            throw new IllegalArgumentException("Team cannot be null");
        this.team = team;
    }
}
