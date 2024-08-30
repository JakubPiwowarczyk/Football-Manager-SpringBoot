package pjwstk.football_manager.club;

import jakarta.persistence.*;
import pjwstk.football_manager.card.FootballerCard;
import pjwstk.football_manager.match.Match;
import pjwstk.football_manager.player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
public class Club {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private float budget;
    @Column(nullable = false)
    private int leaguePoints;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Player owner;
    @OneToMany
    private List<FootballerCard> cards = new ArrayList<>();
    @OneToMany
    private List<FootballerCard> starting11 = new ArrayList<>(11);
    @OneToMany
    private List<Match> matches = new ArrayList<>();
    @OneToMany
    private List<TransferOffer> transferOffers = new ArrayList<>();

    public Club() {
    }

    public Club(UUID id, String name, float budget, int leaguePoints, Player owner,
                List<FootballerCard> cards, List<FootballerCard> starting11,
                List<Match> matches, List<TransferOffer> transferOffers) {
        this.id = id;
        this.setName(name);
        this.budget = budget;
        this.leaguePoints = leaguePoints;
        if (owner == null) throw new IllegalArgumentException("Owner cannot be null");
        this.owner = owner;
        owner.addClub(this);
        this.cards = cards;
        this.starting11 = starting11;
        this.matches = matches;
        this.transferOffers = transferOffers;
    }

    public Club(String name, float budget, int leaguePoints, Player owner) {
        this.name = name;
        this.budget = budget;
        this.leaguePoints = leaguePoints;
        if (owner == null) throw new IllegalArgumentException("Owner cannot be null");
        this.owner = owner;
        owner.addClub(this);
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

    public float getBudget() {
        return budget;
    }

    public void setBudget(float budget) {
        this.budget = budget;
    }

    public int getLeaguePoints() {
        return leaguePoints;
    }

    public void setLeaguePoints(int leaguePoints) {
        this.leaguePoints = leaguePoints;
    }

    public Player getOwner() {
        return owner;
    }

    public List<FootballerCard> getCards() {
        return List.copyOf(cards);
    }

    public void setCards(List<FootballerCard> cards) {
        this.cards = cards;
    }

    public void addCard(FootballerCard card) {
        if (card == null) throw new IllegalArgumentException("Card cannot be null");
        if (!cards.contains(card)) cards.add(card);
    }

    public void removeCard(FootballerCard card) {
        cards.remove(card);
    }

    public List<FootballerCard> getStarting11() {
        return List.copyOf(starting11);
    }

    public void setStarting11(List<FootballerCard> starting11) {
        if (starting11.size() >= 11) throw new IllegalArgumentException("Starting 11 max capacity is 11!!!");
        this.starting11 = starting11;
    }

    public void addToStarting11(FootballerCard card) {
        if (card == null) throw new IllegalArgumentException("Card cannot be null");
        if (!cards.contains(card)) throw new IllegalArgumentException("Card does not belongs to this Club");
        if (starting11.size() >= 11) throw new IllegalArgumentException("Starting 11 is full!");
        if (!starting11.contains(card)) starting11.add(card);
    }

    public void removeFromStarting11(FootballerCard card) {
        starting11.remove(card);
    }

    public List<Match> getMatches() {
        return List.copyOf(matches);
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }

    public void addToMatches(Match match) {
        if (match == null) throw new IllegalArgumentException("Match cannot be null");
        if (!matches.contains(match)) matches.add(match);
    }

    public void removeFromMatches(Match match) {
        matches.remove(match);
    }

    public List<TransferOffer> getTransferOffers() {
        return List.copyOf(transferOffers);
    }

    public void setTransferOffers(List<TransferOffer> transferOffers) {
        this.transferOffers = transferOffers;
    }

    public void addToTransferOffers(TransferOffer transferOffer) {
        if (transferOffer == null) throw new IllegalArgumentException("TransferOffer cannot be null");
        transferOffers.add(transferOffer);
    }

    public void removeFromTransferOffers(TransferOffer transferOffer) {
        transferOffers.remove(transferOffer);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Club club = (Club) o;
        return Objects.equals(id, club.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Club{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", budget=" + budget +
                ", leaguePoints=" + leaguePoints +
                ", owner=" + owner.getEmail() +
                '}';
    }
}
