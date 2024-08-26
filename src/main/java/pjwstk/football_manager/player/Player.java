package pjwstk.football_manager.player;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Objects;
import java.util.UUID;

@Entity
public class Player {

    @Id
    private UUID id;
    @Column(nullable = false)
    private String nickname;
    @Column(unique = true)
    private String email;
    @Column(nullable = false)
    private String password;

    public Player() {
    }

    public Player(UUID id, String nickname, String email, String password) {
        this.id = id;
        this.setNickname(nickname);
        this.setEmail(email);
        this.setPassword(password);
    }

    public Player(String nickname, String email, String password) {
        this.setNickname(nickname);
        this.setEmail(email);
        this.setPassword(password);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        if (nickname.isBlank())
            throw new IllegalArgumentException("Nickname cannot be blank");
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email.isBlank())
            throw new IllegalArgumentException("Email cannot be blank");
        if (!email.contains("@"))
            throw new IllegalArgumentException("Invalid email format");
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password.isBlank())
            throw new IllegalArgumentException("Password cannot be blank");
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(id, player.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
