package pjwstk.football_manager.club;

import jakarta.persistence.*;
import pjwstk.football_manager.card.FootballerCard;

import java.time.LocalDate;
import java.util.UUID;

@Entity
public class TransferOffer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private float price;
    private LocalDate deadline;
    @OneToOne(cascade = CascadeType.ALL)
    private FootballerCard card;
    @ManyToOne
    private Club seller;
    @ManyToOne
    private Club buyer;

    public TransferOffer() {
    }

    public TransferOffer(UUID id, float price, LocalDate deadline, FootballerCard card, Club seller, Club buyer) {
        this.id = id;
        this.price = price;
        this.deadline = deadline;
        this.card = card;
        this.seller = seller;
        this.buyer = buyer;
    }

    public TransferOffer(float price, LocalDate deadline, FootballerCard card, Club seller, Club buyer) {
        this.price = price;
        this.deadline = deadline;
        this.card = card;
        this.seller = seller;
        this.buyer = buyer;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        if (price < 0) price = 0;
        this.price = price;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        if (deadline.isBefore(LocalDate.now().minusDays(1)))
            deadline = LocalDate.now().plusDays(1);
        this.deadline = deadline;
    }

    public FootballerCard getCard() {
        return card;
    }

    public Club getSeller() {
        return seller;
    }

    public Club getBuyer() {
        return buyer;
    }

    public void setBuyer(Club buyer) {
        this.buyer = buyer;
    }
}
