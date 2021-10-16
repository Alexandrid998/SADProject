package ZeusFit.controller.dto;

import java.util.ArrayList;

public class CorsoLezioneDto {
    private String nome;
    private String descrizione;
    private String urlimm;
    private String giorno;
    private String orario;
    private Integer durata;
    private Integer sala;
    private Integer num_posti_disponibili;
    private float costo;


    public CorsoLezioneDto() {

    }

    public CorsoLezioneDto(String nome, String descrizione, String urlimm, String giorno, String orario, Integer durata, Integer sala, Integer num_posti_disponibili,float costo) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.urlimm = urlimm;
        this.giorno = giorno;
        this.orario = orario;
        this.durata = durata;
        this.sala = sala;
        this.num_posti_disponibili = num_posti_disponibili;
        this.costo = costo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getUrlimm() {
        return urlimm;
    }

    public void setUrlimm(String urlimm) {
        this.urlimm = urlimm;
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
        this.orario = orario;
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

    public Integer getNum_posti_disponibili() {
        return num_posti_disponibili;
    }

    public void setNum_posti_disponibili(Integer num_posti_disponibili) {
        this.num_posti_disponibili = num_posti_disponibili;
    }

    public float getCosto() {
        return costo;
    }

    public void setCosto(float costo) {
        this.costo = costo;
    }
}


