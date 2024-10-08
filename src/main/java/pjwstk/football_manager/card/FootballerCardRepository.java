package pjwstk.football_manager.card;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface FootballerCardRepository extends JpaRepository<FootballerCard, UUID> {

    List<FootballerCard> findByClubId(UUID clubId);

    List<FootballerCard> findByClubIdAndIsPartOfStarting11IsTrue(UUID clubId);
}
