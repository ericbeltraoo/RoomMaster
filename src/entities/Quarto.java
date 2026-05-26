package entities;

public class Quarto {
    private int numero;
    private String tipo;
    private double precoDiaria;
    private String statusAtual;

    public Quarto(int numero, String tipo, double precoDiaria) {
        this.numero = numero;
        this.tipo = tipo;
        this.precoDiaria = precoDiaria;
        this.statusAtual = "Disponível";
    }

    public int getNumero() {
        return numero;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getPrecoDiaria() {
        return precoDiaria;
    }

    public void setPrecoDiaria(double precoDiaria) {
        this.precoDiaria = precoDiaria;
    }

    public String getStatusAtual() {
        return statusAtual;
    }

    public void setStatusAtual(String statusAtual) {
        this.statusAtual = statusAtual;
    }

    @Override
    public String toString() {
        return "Numero do quarto: "+numero
                +"Tipo do quarto: "+tipo
                +"Preço da Diária: "+precoDiaria
                +"Status: "+statusAtual;
    }

}
