package tech.devinhouse.models;

import java.util.Date;

public abstract class Pessoa{

    private final String nome;
    private final String telefone;
    private final Date dataDeNascimento;
    private final String cpf;
    private String codigo;


    public Pessoa(String nome, String telefone, Date dataDeNascimento, String cpf, String codigo) {
        this.nome = nome;
        this.telefone = telefone;
        this.dataDeNascimento = dataDeNascimento;
        this.cpf = cpf;
        this.codigo = codigo;
    }

    @Override
    public String toString() {
        return "Nome = " + nome + '\n' +
                "Telefone = " + telefone + '\n' +
                "Data De Nascimento = " + dataDeNascimento + '\n' +
                "CPF = " + cpf + '\n' +
                "Código = " + codigo + '\n';
    }

    public String toStringListagem() {
        return "Código: " + codigo + " | Nome: " + nome + " | CPF: " + cpf;
    }

    public String toStringRelatorio() {
        return "Código = " + codigo + "\n" +
                "Nome = " + nome + '\n';
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
