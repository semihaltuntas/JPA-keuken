package be.vdab.keuken.artikelGroeps;

import be.vdab.keuken.artikels.Artikel;
import jakarta.persistence.*;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "artikelgroepen")
public class ArtikelGroep {
    @Id //PrimaryKey
    private long id;
    private String naam;

    @OneToMany(mappedBy = "artikelGroep")
    @OrderBy ("naam")
    private Set<Artikel> artikels;

    public Set<Artikel> getArtikels() {
        return Collections.unmodifiableSet(artikels);
    }
    public void voegArtikelToe(Artikel artikel){
        if (!artikels.add(artikel)){
            throw new ArtikelGroepHeeftDezeArtikelAlException();
        }
    }

    protected ArtikelGroep() {
    }

    public ArtikelGroep(long id, String naam) {
        this.id = id;
        this.naam = naam;
        artikels = new LinkedHashSet<Artikel>();
    }

    public long getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }
}
