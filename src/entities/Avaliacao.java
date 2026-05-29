package entities;

public class Avaliacao {
    public double numEstrelas;
    public String comentario;

    public Avaliacao(double numEstrelas, String comentario) {
        this.numEstrelas = numEstrelas;
        this.comentario = comentario;
    }

    @Override
    public String toString() {
        return "\n---- Última Avaliação ----\n"+
                "\nQuantidade de estrelas: "+this.numEstrelas
                +"\nComentário: "+this.comentario;
    }

}
