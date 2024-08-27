package pjwstk.football_manager.employee;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.UUID;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String surname;
    @Column(unique=true)
    private String login;
    @Column(nullable = false)
    private String password;

    public Employee() {
    }

    public Employee(UUID id, String name, String surname, String login, String password) {
        this.id = id;
        this.setName(name);
        this.setSurname(surname);
        this.setLogin(login);
        this.setPassword(password);
    }

    public Employee(String name, String surname, String login, String password) {
        this.setName(name);
        this.setSurname(surname);
        this.setLogin(login);
        this.setPassword(password);
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        if (login.isBlank())
            throw new IllegalArgumentException("Login cannot be blank");
        this.login = login;
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
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}
