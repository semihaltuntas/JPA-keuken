package be.vdab.keuken.artikelGroeps;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@ResponseStatus(HttpStatus.CONFLICT)
public class ArtikelGroepHeeftDezeArtikelAlException extends RuntimeException {
    public ArtikelGroepHeeftDezeArtikelAlException() {
        super("ArtikelGroep heeft al deze artikel.!");
    }
}
