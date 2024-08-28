package pjwstk.football_manager.card;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FootballerCardService {

    private final FootballerCardRepository footballerCardRepository;

    @Autowired
    public FootballerCardService(FootballerCardRepository footballerCardRepository) {
        this.footballerCardRepository = footballerCardRepository;
    }
}
