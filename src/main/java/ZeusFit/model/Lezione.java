package ZeusFit.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.sql.Time;
import java.util.List;
import java.util.Objects;
@Entity
@Table(name="LEZIONI",uniqueConstraints = @UniqueConstraint(columnNames = "id"))
public class Lezione{

    @Id
    @Column(name = "id",insertable = false,updatable = false)
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @Column(name = "giorno",nullable = false,length = 10)
    private String giorno;

    @Column(name = "orario_corso",nullable = false)
    private String orario;

    @Column(name = "durata",nullable = false)
    private Integer durata;

    @Column(name = "sala",nullable = false)
    private Integer sala;

    @Column(name = "num_posti_disponibili",nullable = false)
    private Integer num_posti_disponibili;

    @Column(name = "costo",nullable = false)
    private float costo;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_corso", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Corso  corso;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "id")
    private List<Prenotazione> lista_prenotazioni;

    public Lezione() {
    }

    public Lezione(String giorno, String orario, Integer durata, Integer sala, Integer num_posti_disponibili,float costo, Corso corso, List<Prenotazione> lista_prenotazioni) {
        this.giorno = giorno;
        this.orario = orario;
        this.durata = durata;
        this.sala = sala;
        this.num_posti_disponibili = num_posti_disponibili;
        this.costo=costo;
        this.corso = corso;
        this.lista_prenotazioni = lista_prenotazioni;
    }

    public Integer getNum_posti_disponibili() {
        return num_posti_disponibili;
    }

    public void setNum_posti_disponibili(Integer num_posti_disponibili) {
        this.num_posti_disponibili = num_posti_disponibili;
    }

    public List<Prenotazione> getLista_prenotazioni() {
        return lista_prenotazioni;
    }

    public void setLista_prenotazioni(List<Prenotazione> lista_prenotazioni) {
        this.lista_prenotazioni = lista_prenotazioni;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGiorno() {
        return giorno;
    }

    public void setGiorno(String giorno) {
        this.giorno = giorno;
    }

    public String getOrario() {
        return orario;
    }

    public void setOrario(String orario) {
        this.orario= orario;
    }

    public Integer getDurata() {
        return durata;
    }

    public void setDurata(Integer durata) {
        this.durata = durata;
    }

    public Integer getSala() {
        return sala;
    }

    public void setSala(Integer sala) {
        this.sala = sala;
    }

    public Corso getCorso() {
        return corso;
    }

    public void setCorso(Corso corso) {
        this.corso = corso;
    }

    public float getCosto() {
        return costo;
    }

    public void setCosto(float costo) {
        this.costo = costo;
    }

    @Override
    public String toString() {
        return "Lezione{" +
                "id=" + id +
                ", giorno='" + giorno + '\'' +
                ", orario_corso=" + orario +
                ", durata=" + durata +
                ", sala=" + sala +
                ", num_posti_disponibili=" + num_posti_disponibili +
                ", corso=" + corso +
                ", lista_prenotazioni=" + lista_prenotazioni +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lezione lezione = (Lezione) o;
        return giorno.equals(lezione.giorno) && orario.equals(lezione.orario) && sala.equals(lezione.sala) && corso.equals(lezione.corso);
    }

    @Override
    public int hashCode() {
        return Objects.hash(giorno, orario, sala, corso);
    }
}
