package be.vdab.keuken.artikelGroeps;

import be.vdab.keuken.artikels.Artikel;
import be.vdab.keuken.artikels.ArtikelNietGevondenException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Stream;

@RestController
@RequestMapping("artikelGroepen")
public class ArtikelGroepController {
    private final ArtikelGroepService artikelGroepService;

    public ArtikelGroepController(ArtikelGroepService artikelGroepService) {
        this.artikelGroepService = artikelGroepService;
    }

    @GetMapping("{id}/artikels")
    Stream<String> findArtikelsVan(@PathVariable long id) {
        ArtikelGroep artikelGroep1 = artikelGroepService.findById(id).orElseThrow(ArtikelGroepNietGevondenException::new);
        artikelGroep1.getArtikels().forEach(artikel -> System.out.println(artikel.getNaam()));
        return artikelGroepService.findById(id)
                .map(artikelGroep -> artikelGroep.getArtikels()
                                .stream()
                        .map(artikel -> artikel.getNaam()))
                .orElseThrow(ArtikelGroepNietGevondenException::new);
    }
}
