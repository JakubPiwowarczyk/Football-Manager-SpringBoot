package pjwstk.football_manager.cardspack;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CardsPackRepository extends JpaRepository<CardsPack, UUID> {
}
