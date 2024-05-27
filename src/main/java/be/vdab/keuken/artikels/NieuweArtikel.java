package be.vdab.keuken.artikels;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record NieuweArtikel(
        @NotBlank String naam,
        @NotNull @PositiveOrZero BigDecimal aankoopprijs,
        @NotNull @PositiveOrZero BigDecimal verkoopprijs
) {
}
