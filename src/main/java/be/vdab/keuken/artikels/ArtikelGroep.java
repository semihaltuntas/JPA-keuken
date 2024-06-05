package be.vdab.keuken.artikels;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "artikelgroepen")
public class ArtikelGroep {
    @Id //PrimaryKey
    private long id;
    private String naam;

    protected ArtikelGroep() {
    }

    public ArtikelGroep(long id, String naam) {
        this.id = id;
        this.naam = naam;
    }

    public long getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }
}
