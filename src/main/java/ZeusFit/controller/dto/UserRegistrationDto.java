package ZeusFit.controller.dto;

import ZeusFit.model.Ruolo;

import java.util.Collection;
import java.util.Date;

public class UserRegistrationDto {
    private String nome;
    private String cognome;
    private String cf;
    private String genere;
    private String data_nascita;
    private String email;
    private String indirizzo;
    private String telefono;
    private String password;
    private String pswconfirm;
    private String ruolo;


    public UserRegistrationDto() {
    }

    public UserRegistrationDto(String nome, String cognome, String cf, String genere, String data_nascita, String email, String indirizzo, String telefono, String password, String pswconfirm,String ruolo) {
        this.nome = nome;
        this.cognome = cognome;
        this.cf = cf;
        this.genere = genere;
        this.data_nascita = data_nascita;
        this.email = email;
        this.indirizzo = indirizzo;
        this.telefono = telefono;
        this.password = password;
        this.pswconfirm = pswconfirm;
        this.ruolo = ruolo;

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

    public String getCf() {
        return cf;
    }

    public void setCf(String cf) {
        this.cf = cf;
    }

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }

    public String getData_nascita() {
        return data_nascita;
    }

    public void setData_nascita(String data_nascita) {
        this.data_nascita = data_nascita;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPswconfirm() {
        return pswconfirm;
    }

    public void setPswconfirm(String pswconfirm) {
        this.pswconfirm = pswconfirm;
    }

    public String getRuolo() {
        return this.ruolo;
    }

    public void setRuoli(String ruolo) {
        this.ruolo = ruolo;
    }
}
