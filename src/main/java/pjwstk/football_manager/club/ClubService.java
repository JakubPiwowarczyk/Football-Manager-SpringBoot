package pjwstk.football_manager.club;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClubService {

    private final ClubRepository clubRepository;

    @Autowired
    public ClubService(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }

    public List<Club> getAllByOwnerId(UUID ownerId) {
        return clubRepository.findAllByOwnerId(ownerId);
    }

    public Optional<Club> getById(UUID id) {
        return clubRepository.findById(id);
    }
}
