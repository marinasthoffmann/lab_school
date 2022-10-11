package tech.devinhouse.models;

import java.util.Date;

public class Pedagogo extends Pessoa implements AtendimentoPedagogico{

    private int numeroAtendimentos = 0;


    public Pedagogo(String nome, String telefone, Date dataDeNascimento, String cpf, String codigo, int numeroAtendimentos) {
        super(nome, telefone, dataDeNascimento, cpf, codigo);
        this.numeroAtendimentos = numeroAtendimentos;
    }

    public int getNumeroAtendimentos() {
        return numeroAtendimentos;
    }

    public void setNumeroAtendimentos(int numeroAtendimentos) {
        this.numeroAtendimentos = numeroAtendimentos;
    }

    @Override
    public void realizarAtendimento(Aluno aluno) {
        return;
    }
}
