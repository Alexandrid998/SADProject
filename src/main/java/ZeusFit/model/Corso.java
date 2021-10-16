package ZeusFit.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.UniqueConstraint;
import javax.xml.crypto.Data;
import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.exception.DataException;

@Entity
@Table(name =  "CORSI", uniqueConstraints = @UniqueConstraint(columnNames = "id"))


public class Corso {

    @Id
    @Column(name = "id",insertable = false,updatable = false)
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome",nullable = false,length = 40)
    private String nome;

    @Column(name = "descrizione",nullable = false)
    private String descrizione;

    @Column(name ="urlimmagine",nullable = false)
    private String urlimm;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "id")
    private List<Abbonamento> lista_abbonamenti;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "id")
    private List<Lezione> lista_lezioni;

    @Override
    public String toString() {
        return "Corso{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", lista_abbonamenti=" + lista_abbonamenti +
                ", lista_lezioni=" + lista_lezioni +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Corso corso = (Corso) o;
        return id.equals(corso.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Corso() {

    }

    public Corso(String nome,String descrizione,String urlimm, List<Abbonamento> lista_abbonamenti, List<Lezione> lista_lezioni) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.urlimm = urlimm;
        this.lista_abbonamenti = lista_abbonamenti;
        this.lista_lezioni = lista_lezioni;

    }

    public String getUrlimm() {
        return urlimm;
    }

    public void setUrlimm(String urlimm) {
        this.urlimm = urlimm;
    }

    public List<Abbonamento> getLista_abbonamenti() {
        return lista_abbonamenti;
    }

    public void setLista_abbonamenti(List<Abbonamento> lista_abbonamenti) {
        this.lista_abbonamenti = lista_abbonamenti;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Lezione> getLista_lezioni() {
        return lista_lezioni;
    }

    public void setLista_lezioni(List<Lezione> lista_lezioni) {
        this.lista_lezioni = lista_lezioni;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
}
