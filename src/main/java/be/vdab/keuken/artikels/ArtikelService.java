package be.vdab.keuken.artikels;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ArtikelService {
    private final ArtikelRepository artikelRepository;

    public ArtikelService(ArtikelRepository artikelRepository) {
        this.artikelRepository = artikelRepository;
    }

    Optional<Artikel> findById(long id) {
        return artikelRepository.findById(id);
    }

    @Transactional
    long create(NieuweArtikel nieuweArtikel) {
        var artikel = new Artikel(nieuweArtikel.naam(), nieuweArtikel.aankoopprijs(),
                nieuweArtikel.verkoopprijs());
        artikelRepository.save(artikel);
        return artikel.getId();
    }

    List<Artikel> findByNaamBevatWoord(String woord) {
        return artikelRepository.findByNaamContainingOrderByNaam(woord);
    }

    List<Artikel> findMinimumWinst(BigDecimal minimumWinst) {
        return artikelRepository.findByMinumumWinst(minimumWinst);
    }

    BigDecimal findGoodkoopsteVerkoopprijs() {
        return artikelRepository.findGoedkoopsteAankoopprijs();
    }

    List<EnkelNamen> findNamen() {
        return artikelRepository.findNamenVanArtikel();
    }
}
