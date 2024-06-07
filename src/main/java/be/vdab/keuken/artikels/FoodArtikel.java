package be.vdab.keuken.artikels;

import be.vdab.keuken.artikelGroeps.ArtikelGroep;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.math.BigDecimal;

@Entity
@DiscriminatorValue("F")
public class FoodArtikel extends Artikel {
    protected FoodArtikel() {
    }

    public FoodArtikel(String naam, BigDecimal aankoopprijs, BigDecimal verkoopprijs, int houdbaarheid, ArtikelGroep artikelGroep) {
        super(naam, aankoopprijs, verkoopprijs,artikelGroep);
        this.houdbaarheid = houdbaarheid;
    }

    private int houdbaarheid;

    public int getHoudbaarheid() {
        return houdbaarheid;
    }
}
