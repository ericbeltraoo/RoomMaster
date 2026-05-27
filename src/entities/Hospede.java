package entities;

public class Hospede {
    private String nome;
    private String cpf, rg;

    public Hospede(String nome, String cpf , String rg) {
        this.nome = nome;
        this.cpf = cpf;
        this.rg = rg;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getRg() {
        return rg;
    }

}
