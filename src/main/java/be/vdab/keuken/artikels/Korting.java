package be.vdab.keuken.artikels;

import jakarta.persistence.Embeddable;

import java.math.BigDecimal;

@Embeddable
public class Korting {
    private int vanafAantal;
    private BigDecimal percentage;

    public int getVanafAantal() {
        return vanafAantal;
    }

    public BigDecimal getPercentage() {
        return percentage;
    }

    @Override
    public boolean equals(Object object) {
        return object instanceof Korting korting && vanafAantal == korting.vanafAantal;
    }

    @Override
    public int hashCode() {
        return vanafAantal;
    }
}
