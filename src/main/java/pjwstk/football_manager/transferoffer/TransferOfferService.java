package pjwstk.football_manager.transferoffer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pjwstk.football_manager.card.FootballerCard;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class TransferOfferService {

    private final TransferOfferRepository transferOfferRepository;

    @Autowired
    public TransferOfferService(TransferOfferRepository transferOfferRepository) {
        this.transferOfferRepository = transferOfferRepository;
    }

    public void createTransferOffer(FootballerCard card, float price, LocalDate deadline) {
        TransferOffer offer = new TransferOffer(price, deadline, card, card.getClub());
        card.getClub().removeCard(card);
        transferOfferRepository.save(offer);
    }
}
