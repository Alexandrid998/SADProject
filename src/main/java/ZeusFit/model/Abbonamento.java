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
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.exception.DataException;

@Entity
@Table(name =  "ABBONAMENTI", uniqueConstraints = @UniqueConstraint(columnNames = "id"))


public class Abbonamento {

    @Id
    @Column(name = "id",insertable = false,updatable = false)
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @Column(name = "durata",nullable = false)
    private String durata;

    @Column(name = "data",nullable = false)
    private Date data;

    @Column(name = "stato",nullable = false,length = 20)
    private String stato;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_utente", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Utente utente;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_corso", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Corso corso;


    @Override
    public String toString() {
        return "Abbonamento{" +
                "id=" + id +
                ", utente=" + utente +
                ", corso=" + corso +
                ", durata='" + durata + '\'' +
                ", data=" + data +
                ", stato='" + stato + '\'' +
                '}';
    }


    public Abbonamento() {

    }

    public Abbonamento(Utente utente, Corso corso, String durata, Date data, String stato) {
        this.utente = utente;
        this.corso = corso;
        this.durata = durata;
        this.data = data;
        this.stato = stato;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public Corso getCorso() {
        return corso;
    }

    public void setCorso(Corso corso) {
        this.corso = corso;
    }

    public String getDurata() {
        return durata;
    }

    public void setDurata(String durata) {
        this.durata = durata;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }


}
