package ZeusFit.model;

import javax.persistence.*;
import javax.xml.crypto.Data;
import java.io.Serializable;
import java.util.*;

import com.sun.istack.NotNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.exception.DataException;
import org.springframework.lang.NonNull;

@Entity
@Table(name =  "UTENTI", uniqueConstraints = @UniqueConstraint(columnNames = "cf"))

public class Utente {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cf",nullable=false,unique = true,length = 16)
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private String cf;

    @Column(name = "nome",nullable = false,length = 20)
    private String nome;

    @Column(name = "cognome",nullable = false,length = 20)
    private String cognome;

    @Column(name = "data_nascita",nullable = false)
    private String data_nascita;

    @Column(name = "genere",nullable = false,length = 1)
    private String genere;

    @Column(name = "email",nullable = false,unique = true,length = 45)
    private String email;

    @Column(name = "indirizzo",nullable = false,length = 100)
    private String indirizzo;

    @Column(name = "telefono",nullable = false,length = 45)
    private String telefono;

    @Column(name = "password",nullable = false,length = 64)
    @NonNull private String password;

    @Column(name = "saldo")
    private float saldo;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "id")
    private List<Abbonamento> abbonamenti;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "id")
    private List<Prenotazione> prenotazioni;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "ruoli_utente",
            joinColumns = @JoinColumn(
                    name = "id_utente", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "id_ruolo", referencedColumnName = "id"))

    private Collection<Ruolo> ruoli;

    @Column(name ="ruolo")
    private String ruolo;

    public Utente() {
    }

    public Utente(String cf, String nome, String cognome, String data_nascita, String genere, String email, String indirizzo, String telefono, @NonNull String password, float saldo,Collection<Ruolo> ruoli,String ruolo) {
        this.cf = cf;
        this.nome = nome;
        this.cognome = cognome;
        this.data_nascita = data_nascita;
        this.genere = genere;
        this.email = email;
        this.indirizzo = indirizzo;
        this.telefono = telefono;
        this.password = password;
        this.saldo = saldo;
        this.ruoli = ruoli;
        this.ruolo = ruolo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCf() {
        return cf;
    }

    public void setCf(String cf) {
        this.cf = cf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String  getData_nascita() {
        return data_nascita;
    }

    public void setData_nascita(String data_nascita) {
        this.data_nascita = data_nascita;
    }

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @NonNull
    public String getPassword() {
        return password;
    }

    public void setPassword(@NonNull String password) {
        this.password = password;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public List<Abbonamento> getAbbonamenti() {
        return abbonamenti;
    }

    public void setAbbonamenti(List<Abbonamento> abbonamenti) {
        this.abbonamenti = abbonamenti;
    }

    public List<Prenotazione> getPrenotazioni() {
        return prenotazioni;
    }

    public void setPrenotazioni(List<Prenotazione> prenotazioni) {
        this.prenotazioni = prenotazioni;
    }

    public Collection<Ruolo> getRuoli() {
        return ruoli;
    }

    public void setRuoli(Collection<Ruolo> ruoli) {
        this.ruoli = ruoli;
    }

    public String getRuolo() {
        return ruolo;
    }

    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Utente utente = (Utente) o;
        return cf.equals(utente.cf) && email.equals(utente.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cf, email);
    }

    @Override
    public String toString() {
        return "Utente{" +
                "id=" + id +
                ", cf='" + cf + '\'' +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", data_nascita=" + data_nascita +
                ", genere='" + genere + '\'' +
                ", email='" + email + '\'' +
                ", indirizzo='" + indirizzo + '\'' +
                ", telefono='" + telefono + '\'' +
                '}';
    }
}












