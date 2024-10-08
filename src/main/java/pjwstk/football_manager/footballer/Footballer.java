package pjwstk.football_manager.footballer;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Entity
public class Footballer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String surname;
    @Column(nullable = false)
    private LocalDate dateOfBirth;
    @Column(nullable = false)
    private Position position;
    @ManyToOne
    private Nationality nationality;
    @ManyToOne
    private Team team;

    public Footballer() {
    }

    public Footballer(UUID id, String name, String surname, LocalDate dateOfBirth,
                      Position position, Nationality nationality, Team team) {
        this.id = id;
        this.setName(name);
        this.setSurname(surname);
        this.dateOfBirth = dateOfBirth;
        this.setPosition(position);
        this.setNationality(nationality);
        this.setTeam(team);
    }

    public Footballer(String name, String surname, LocalDate dateOfBirth,
                      Position position, Nationality nationality, Team team) {
        this.setName(name);
        this.setSurname(surname);
        this.dateOfBirth = dateOfBirth;
        this.setPosition(position);
        this.setNationality(nationality);
        this.setTeam(team);
    }

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
        if (team == null)
            throw new IllegalArgumentException("Team cannot be null");
        this.team = team;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Footballer that = (Footballer) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Footballer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", position=" + position +
                ", nationality=" + nationality.getName() +
                ", team=" + team.getName() +
                '}';
    }
}
