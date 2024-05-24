package be.vdab.keuken.artikels;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
