package ZeusFit.controller.dto;

public class SaldoDto {
    private float ricarica;

    public SaldoDto(float ricarica) {
        this.ricarica = ricarica;
    }

    public SaldoDto() {
    }

    public float getRicarica() {
        return ricarica;
    }

    public void setRicarica(float ricarica) {
        this.ricarica = ricarica;
    }
}
