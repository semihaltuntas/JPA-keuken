package be.vdab.keuken.artikels;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.jdbc.JdbcTestUtils;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@Sql({"/artikelgroepen.sql", "/artikels.sql"})
class ArtikelRepositoryTest {
    private static final String ARTIKELS_TABLE = "artikels";
    private final ArtikelRepository artikelRepository;
    private final JdbcClient jdbcClient;

    public ArtikelRepositoryTest(ArtikelRepository artikelRepository, JdbcClient jdbcClient) {
        this.artikelRepository = artikelRepository;
        this.jdbcClient = jdbcClient;
    }

    private long idVanBanana() {
        var sql = """
                select id
                from artikels
                where naam = 'banana'
                """;
        return jdbcClient.sql(sql)
                .query(Long.class)
                .single();
    }

    @Test
    void findByIdMetBestaandeIdVindtArtikel() {
        var id = idVanBanana();
        assertThat(artikelRepository.findById(id).get()
                .getNaam())
                .isEqualTo("banana");
    }

    @Test
    void findByIdMetOnbestaandeIdVindtArtikel() {
        assertThat(artikelRepository.findById(Long.MAX_VALUE)).isEmpty();
    }
}