package pjwstk.football_manager.footballer;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.UUID;

@Entity
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(unique=true)
    private String name;
    @ManyToOne
    private League league;

    public Team() {
    }

    public Team(UUID id, String name, League league) {
        this.id = id;
        this.setName(name);
        this.setLeague(league);
    }

    public Team(String name, League league) {
        this.setName(name);
        this.setLeague(league);
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

    public League getLeague() {
        return league;
    }

    public void setLeague(League league) {
        if (league == null)
            throw new IllegalArgumentException("League cannot be null");
        this.league = league;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return Objects.equals(id, team.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", league=" + league +
                '}';
    }
}
