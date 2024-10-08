package pjwstk.football_manager.card;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FootballerCardService {

    private final FootballerCardRepository footballerCardRepository;

    @Autowired
    public FootballerCardService(FootballerCardRepository footballerCardRepository) {
        this.footballerCardRepository = footballerCardRepository;
    }

    public List<FootballerCard> getFootballerCardsByClubId(UUID clubId) {
        return footballerCardRepository.findByClubId(clubId);
    }

    public List<FootballerCard> getStarting11ByClubId(UUID clubId) {
        return footballerCardRepository.findByClubIdAndIsPartOfStarting11IsTrue(clubId);
    }

    public Optional<FootballerCard> getFootballerCardById(UUID cardId) {
        return footballerCardRepository.findById(cardId);
    }

    public void renewCardsContract(FootballerCard card) {
        float cost = (28 - card.getMatchesInContract()) * card.getSalaryPerMatch();
        card.getClub().setBudget(card.getClub().getBudget() - cost);
        card.setMatchesInContract(28);
        footballerCardRepository.save(card);
    }
}
