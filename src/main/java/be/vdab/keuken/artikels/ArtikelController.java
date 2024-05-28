package be.vdab.keuken.artikels;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("artikels")
public class ArtikelController {
    private final ArtikelService artikelService;

    public ArtikelController(ArtikelService artikelService) {
        this.artikelService = artikelService;
    }

    @GetMapping("{id}")
    Artikel findById(@PathVariable long id) {
        return artikelService.findById(id)
                .orElseThrow(ArtikelNietGevondenException::new);
    }

    @PostMapping
    long create(@RequestBody @Valid NieuweArtikel nieuweArtikel) {
        return artikelService.create(nieuweArtikel);
    }

    @GetMapping(value = "naamBevat", params = "woord")
    List<Artikel> findByNaamBevatWoord(String woord) {
        return artikelService.findByNaamBevatWoord(woord);
    }

    @GetMapping(params = "minimumWinst")
    List<Artikel> findByMinimumWinst(BigDecimal minimumWinst) {
        return artikelService.findMinimumWinst(minimumWinst);
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
}
