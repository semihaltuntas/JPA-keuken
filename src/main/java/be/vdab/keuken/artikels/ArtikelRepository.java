package be.vdab.keuken.artikels;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ArtikelRepository extends JpaRepository<Artikel, Long> {
    List<Artikel> findByNaamContainingOrderByNaam(String woord);

    @Query("SELECT a from Artikel a WHERE a.verkoopprijs - a.aankoopprijs >= :minimumWinst order by a.verkoopprijs")
    List<Artikel> findByMinumumWinst(BigDecimal minimumWinst);

    @Query("select min(a.verkoopprijs) from Artikel a")
    BigDecimal findGoedkoopsteAankoopprijs();

    @Query("select a.naam as naam from Artikel a order by naam") //Extra Code
    List<EnkelNamen> findNamenVanArtikel();

    List<Artikel> findAll();
}
