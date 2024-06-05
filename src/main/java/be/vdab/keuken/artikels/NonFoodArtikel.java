package be.vdab.keuken.artikels;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.math.BigDecimal;

@Entity
@DiscriminatorValue("NF")
public class NonFoodArtikel extends Artikel {

    protected NonFoodArtikel() {
    }

    public NonFoodArtikel(String naam, BigDecimal aankoopprijs, BigDecimal verkoopprijs, int garantie,ArtikelGroep artikelGroep) {
        super(naam, aankoopprijs, verkoopprijs,artikelGroep);
        this.garantie = garantie;
    }

    private int garantie;

    public int getGarantie() {
        return garantie;
    }
}
