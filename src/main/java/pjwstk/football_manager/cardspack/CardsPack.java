package pjwstk.football_manager.cardspack;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Objects;
import java.util.UUID;

@Entity
public class CardsPack {

    @Id
    private UUID id;
    @Column(nullable = false)
    private String title;
    private String description;
    private int numberOfCards;
    private Integer minOverallRating;
    private Integer maxOverallRating;
    private float price;

    public CardsPack() {
    }

    public CardsPack(UUID id, String title, String description, int numberOfCards, Integer minOverallRating, Integer maxOverallRating, float price) {
        this.id = id;
        this.setTitle(title);
        this.description = description;
        this.numberOfCards = numberOfCards;
        this.minOverallRating = minOverallRating;
        this.maxOverallRating = maxOverallRating;
        this.setPrice(price);
    }

    public CardsPack(String title, String description, int numberOfCards, Integer minOverallRating, Integer maxOverallRating, float price) {
        this.setTitle(title);
        this.description = description;
        this.numberOfCards = numberOfCards;
        this.minOverallRating = minOverallRating;
        this.maxOverallRating = maxOverallRating;
        this.setPrice(price);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title.isBlank())
            throw new IllegalArgumentException("Title cannot be blank");
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNumberOfCards() {
        return numberOfCards;
    }

    public void setNumberOfCards(int numberOfCards) {
        this.numberOfCards = numberOfCards;
    }

    public Integer getMinOverallRating() {
        return minOverallRating;
    }

    public void setMinOverallRating(Integer minOverallRating) {
        this.minOverallRating = minOverallRating;
    }

    public Integer getMaxOverallRating() {
        return maxOverallRating;
    }

    public void setMaxOverallRating(Integer maxOverallRating) {
        this.maxOverallRating = maxOverallRating;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        if (price < 0)
            price = 0;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CardsPack cardsPack = (CardsPack) o;
        return Objects.equals(id, cardsPack.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "CardsPack{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", numberOfCards=" + numberOfCards +
                ", minOverallRating=" + minOverallRating +
                ", maxOverallRating=" + maxOverallRating +
                ", price=" + price +
                '}';
    }
}
