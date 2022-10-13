package tech.devinhouse.models;

import java.util.Date;

public class Pedagogo extends Pessoa{

    private int numeroAtendimentos;


    public Pedagogo(String nome, String telefone, Date dataDeNascimento, String cpf, String codigo, int numeroAtendimentos) {
        super(nome, telefone, dataDeNascimento, cpf, codigo);
        this.numeroAtendimentos = numeroAtendimentos;
    }

    @Override
    public String toString() {
        return "\nPedagogo:\n" +
                super.toString() +
                "Número de atendimentos = " + numeroAtendimentos;
    }

    public int getNumeroAtendimentos() {
        return numeroAtendimentos;
    }

    public void setNumeroAtendimentos() {
        this.numeroAtendimentos ++;
    }

}
