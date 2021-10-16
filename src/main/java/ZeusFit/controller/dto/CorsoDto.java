package ZeusFit.controller.dto;

public class CorsoDto {
    private String nome;
    private String descrizione;
    private String urlimm;
    private Long id;

    public CorsoDto() {
    }

    public CorsoDto(String nome, String descrizione,String urlimm) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.urlimm = urlimm;
    }

    public String getUrlimm() {
        return urlimm;
    }

    public void setUrlimm(String urlimm) {
        this.urlimm = urlimm;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
