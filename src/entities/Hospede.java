package entities;

public class Hospede {
    private String nome;
    private int cpf, rg;

    public Hospede(String nome, int cpf , int rg) {
        this.nome = nome;
        this.cpf = cpf;
        this.rg = rg;
    }

    public String getNome() {
        return nome;
    }

    public int getCpf() {
        return cpf;
    }

    public int getRg() {
        return rg;
    }

}
