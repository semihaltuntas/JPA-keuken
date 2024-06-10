package be.vdab.keuken.artikels;

import be.vdab.keuken.artikelGroeps.ArtikelGroepIdInArtikelNietGevondenException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping("artikels")
public class ArtikelController {
    private final ArtikelService artikelService;

    public ArtikelController(ArtikelService artikelService) {
        this.artikelService = artikelService;
    }

    private record IdNaamVerkoopprijs(long id, String naam, BigDecimal verkoopprijs) {
        IdNaamVerkoopprijs(Artikel artikel) {
            this(artikel.getId(), artikel.getNaam(), artikel.getVerkoopprijs());
        }
    }

    private record ArtikelBeknoptMetArtikelgrop(long id, String naam, BigDecimal verkoopprijs,
                                                String artikelGroep) {
        ArtikelBeknoptMetArtikelgrop(Artikel artikel) {
            this(artikel.getId(), artikel.getNaam(), artikel.getVerkoopprijs(),
                    artikel.getArtikelGroep().getNaam());
        }
    }


    @GetMapping("{id}")
    IdNaamVerkoopprijs findById(@PathVariable long id) {
        return artikelService.findById(id)
                .map(IdNaamVerkoopprijs::new)
                .orElseThrow(ArtikelNietGevondenException::new);
    }

    @PostMapping("food")
    long create(@RequestBody @Valid NieuwFoodArtikel nieuwFoodArtikel) {
        return artikelService.create(nieuwFoodArtikel);
    }

    @PostMapping("nonfood")
    long create(@RequestBody @Valid NieuwNonFoodArtikel nieuwNonFoodArtikel) {
        return artikelService.create(nieuwNonFoodArtikel);
    }

    @GetMapping(value = "naamBevat", params = "woord")
    Stream<ArtikelBeknoptMetArtikelgrop> findByNaamBevatWoord(String woord) {
        return artikelService.findByNaamBevatWoord(woord)
                .stream()
                .map(ArtikelBeknoptMetArtikelgrop::new);
    }

    @GetMapping(params = "minimumWinst")
    Stream<IdNaamVerkoopprijs> findByMinimumWinst(BigDecimal minimumWinst) {
        return artikelService.findMinimumWinst(minimumWinst)
                .stream()
                .map(IdNaamVerkoopprijs::new);
    }

    @GetMapping("verkoopprijzen/goodkoopste")
    BigDecimal findGoodkoopsteVerkoopprijs() {
        return artikelService.findGoodkoopsteVerkoopprijs();
    }

    @GetMapping("namen")
    List<EnkelNamen> findNamen() {
        return artikelService.findNamen();
    }

    @PatchMapping("{id}/verkoopprijs")
    void wijzigVerkoopprijsById(@PathVariable long id, @RequestBody @NotNull @Positive BigDecimal verkoopprijs) {
        artikelService.wijzigVerkoopprijs(id, verkoopprijs);
    }

    @GetMapping()
    List<Artikel> findAllArtikels() {
        return artikelService.findAll();
    }

    @GetMapping("{id}/artikelGroepNaam")
    String findArtikelGroepNaamByArtikelId(@PathVariable long id) {
        return artikelService.findById(id)
                .map(artikel -> artikel.getArtikelGroep().getNaam())
                .orElseThrow(ArtikelGroepIdInArtikelNietGevondenException::new);
    }

}
