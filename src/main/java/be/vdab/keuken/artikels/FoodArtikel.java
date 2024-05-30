package be.vdab.keuken.artikels;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.math.BigDecimal;

@Entity
@DiscriminatorValue("F")
public class FoodArtikel extends Artikel {
    protected FoodArtikel() {
    }

    public FoodArtikel(String naam, BigDecimal aankoopprijs, BigDecimal verkoopprijs, int houdbaarheid) {
        super(naam, aankoopprijs, verkoopprijs);
        this.houdbaarheid = houdbaarheid;
    }

    private int houdbaarheid;

    public int getHoudbaarheid() {
        return houdbaarheid;
    }
}