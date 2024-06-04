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

    @Transactional
    void wijzigVerkoopprijs(long id, BigDecimal verkoopprijs) {
        artikelRepository.findById(id)
                .orElseThrow(ArtikelNietGevondenException::new)
                .setVerkoopprijs(verkoopprijs);
    }
    @Transactional
    long create(NieuwFoodArtikel nieuwFoodArtikel) {
        var artikel = new FoodArtikel(nieuwFoodArtikel.naam(), nieuwFoodArtikel.aankoopprijs(),
                nieuwFoodArtikel.verkoopprijs(), nieuwFoodArtikel.houdbaarheid());
        artikelRepository.save(artikel);
        return artikel.getId();
    }

    @Transactional
    long create(NieuwNonFoodArtikel nieuwNonFoodArtikel) {
        var artikel = new NonFoodArtikel(nieuwNonFoodArtikel.naam(), nieuwNonFoodArtikel.aankoopprijs(),
                nieuwNonFoodArtikel.verkoopprijs(), nieuwNonFoodArtikel.garantie());
        artikelRepository.save(artikel);
        return artikel.getId();
    }

    List<Artikel> findAll() {
        return artikelRepository.findAll();
    }
}
