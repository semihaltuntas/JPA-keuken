package be.vdab.keuken.artikels;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus (HttpStatus.NOT_FOUND)
public class ArtikelGroepIdInArtikelNietGevondenException extends RuntimeException{
    public ArtikelGroepIdInArtikelNietGevondenException() {
        super("ArtikelGroep niet gevonden");
    }
}
