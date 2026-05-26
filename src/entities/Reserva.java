package entities;

public class Reserva {

    private String hospedeResponsavel;
    private int quartoReservado;
    private int qtdDiariasAgendadas;
    private String statusReserva;

    public Reserva(String hospedeResponsavel, int quartoReservado, int qtdDiariasAgendadas, String statusReserva) {
        this.hospedeResponsavel = hospedeResponsavel;
        this.quartoReservado = quartoReservado;
        this.qtdDiariasAgendadas = qtdDiariasAgendadas;
        this.statusReserva = statusReserva;
    }

    public String getHospedeResponsavel() {
        return hospedeResponsavel;
    }

    public void setHospedeResponsavel(String hospedeResponsavel) {
        this.hospedeResponsavel = hospedeResponsavel;
    }

    public int getQuartoReservado() {
        return quartoReservado;
    }

    public int getQtdDiariasAgendadas() {
        return qtdDiariasAgendadas;
    }

    public String getStatusReserva() {
        return statusReserva;
    }

    public void setStatusReserva(String statusReserva) {
        this.statusReserva = statusReserva;
    }
}
