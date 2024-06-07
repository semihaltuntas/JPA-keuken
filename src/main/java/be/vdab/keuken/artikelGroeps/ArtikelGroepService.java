package be.vdab.keuken.artikelGroeps;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ArtikelGroepService {
    private final ArtikelGroepRepository artikelGroepRepository;

    public ArtikelGroepService(ArtikelGroepRepository artikelGroepRepository) {
        this.artikelGroepRepository = artikelGroepRepository;
    }
    Optional<ArtikelGroep> findById(long id){
        return artikelGroepRepository.findById(id);
    }
}
