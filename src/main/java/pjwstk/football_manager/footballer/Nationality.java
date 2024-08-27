package pjwstk.football_manager.footballer;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.UUID;

@Entity
public class Nationality {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(unique=true)
    private String name;

    public Nationality() {
    }

    public Nationality(UUID id, String name) {
        this.id = id;
        this.setName(name);
    }

    public Nationality(String name) {
        this.setName(name);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Nationality that = (Nationality) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Nationality{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
