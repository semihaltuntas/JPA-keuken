package be.vdab.keuken.artikels;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
@Sql({"/artikelgroepen.sql","/artikels.sql"})
class ArtikelControllerTest {
    private final MockMvc mockMvc;
    private final JdbcClient jdbcClient;

    public ArtikelControllerTest(MockMvc mockMvc, JdbcClient jdbcClient) {
        this.mockMvc = mockMvc;
        this.jdbcClient = jdbcClient;
    }

    long idVanBanana() {
        var sql = """
                select id from artikels where naam = 'banana'
                """;
        return jdbcClient.sql(sql).query(Long.class).single();
    }

    @Test
    void findByIdMetBestaandeIdVindtArtikel() throws Exception {
        var id = idVanBanana();
        System.out.println(id);
        mockMvc.perform(get("/artikels/{id}", id))
                .andExpectAll(status().isOk(),
                        jsonPath("naam").value("banana"));
    }

    @Test
    void findByIdMetOnestaandeIdVindtArtikel() throws Exception {
        mockMvc.perform(get("/artikels/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }
}