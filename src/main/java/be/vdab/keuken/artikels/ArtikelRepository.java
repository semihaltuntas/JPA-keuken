package be.vdab.keuken.artikels;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ArtikelRepository extends JpaRepository<Artikel, Long> {
    //N + 1 problemi, veri tabanından ana kayıtları çekerken, her bir ana kayıt için ek sorguların çalıştırılması durumunda ortaya çıkar
    //@EntityGraph anotasyonu, JPA'ya, ana sorgu sırasında belirli ilişkili varlıkları da yüklemesi gerektiğini belirtir.
    // Bu sayede, tek bir sorgu ile hem ana varlık hem de ilişkili varlıklar çekilir.
    @EntityGraph(attributePaths = "artikelGroep") //her Artikel ile ilişkili artikelGroep verileri de bu sorgu ile birlikte yüklenir
    List<Artikel> findByNaamContainingOrderByNaam(String woord);

    @Query("SELECT a from Artikel a WHERE a.verkoopprijs - a.aankoopprijs >= :minimumWinst order by a.verkoopprijs")
    List<Artikel> findByMinumumWinst(BigDecimal minimumWinst);

    @Query("select min(a.verkoopprijs) from Artikel a")
    BigDecimal findGoedkoopsteAankoopprijs();

    @Query("select a.naam as naam from Artikel a order by naam") //Extra Code
    List<EnkelNamen> findNamenVanArtikel();

    List<Artikel> findAll();
}
