package ZeusFit.controller.dto;

import ZeusFit.model.Lezione;

public class LezioneDto {

    private String giorno;
    private String orario;
    private Integer durata;
    private Integer sala;
    private Integer num_posti_disponibili;
    private float costo;
    private Long corso;
    private Long id;

    public LezioneDto() {
    }

    public LezioneDto(String giorno, String orario, Integer durata, Integer sala, Integer num_posti_disponibili,float costo, Long corso) {
        this.giorno = giorno;
        this.orario = orario;
        this.durata = durata;
        this.sala = sala;
        this.num_posti_disponibili = num_posti_disponibili;
        this.costo = costo;
        this.corso = corso;
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

    public Long getCorso() {
        return corso;
    }

    public void setCorso(Long corso) {
        this.corso = corso;
    }

    public float getCosto() {
        return costo;
    }

    public void setCosto(float costo) {
        this.costo = costo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
