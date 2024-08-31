package pjwstk.football_manager.transferoffer;

import jakarta.persistence.*;
import pjwstk.football_manager.card.FootballerCard;
import pjwstk.football_manager.club.Club;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Entity
public class TransferOffer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private float price;
    private LocalDate deadline;
    @OneToOne
    private FootballerCard card;
    @ManyToOne
    private Club seller;
    @ManyToOne
    private Club buyer;

    public TransferOffer() {
    }

    public TransferOffer(UUID id, float price, LocalDate deadline, FootballerCard card, Club seller, Club buyer) {
        this.id = id;
        this.setPrice(price);
        this.setDeadline(deadline);
        if (card == null) throw new IllegalArgumentException("card cannot be null");
        this.card = card;
        this.seller = seller;
        if (seller != null) seller.addToTransferOffers(this);
        this.buyer = buyer;
    }

    public TransferOffer(float price, LocalDate deadline, FootballerCard card, Club seller) {
        this.setPrice(price);
        this.setDeadline(deadline);
        if (card == null) throw new IllegalArgumentException("card cannot be null");
        this.card = card;
        this.seller = seller;
        if (seller != null) seller.addToTransferOffers(this);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransferOffer that = (TransferOffer) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "TransferOffer{" +
                "id=" + id +
                ", price=" + price +
                ", deadline=" + deadline +
                ", card=" + card +
                ", seller=" + seller +
                ", buyer=" + buyer +
                '}';
    }
}
