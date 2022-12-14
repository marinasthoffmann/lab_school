package tech.devinhouse.models;

import tech.devinhouse.models.enums.SituacaoMatricula;

import java.util.Date;

public class Aluno extends Pessoa{

    private SituacaoMatricula situacaoMatricula;
    private final double notaProcessoSeletivo;
    private int numeroAtendimentos;

    public Aluno(String nome, String telefone, Date dataDeNascimento, String cpf, String codigo, SituacaoMatricula situacaoMatricula, double notaProcessoSeletivo, int numeroAtendimentos) {
        super(nome, telefone, dataDeNascimento, cpf, codigo);
        this.situacaoMatricula = SituacaoMatricula.valueOf(String.valueOf(situacaoMatricula));
        this.notaProcessoSeletivo = notaProcessoSeletivo;
        this.numeroAtendimentos = numeroAtendimentos;
    }

    @Override
    public String toString() {
        return "\nAluno:\n" +
                super.toString() +
                "Situação da matrículoa = " + situacaoMatricula + "\n" +
                "Nota do Processo Seletivo = " + notaProcessoSeletivo + "\n" +
                "Número de Atendimentos = " + numeroAtendimentos + "\n";
    }
    public String toStringRelatorio() {
        return super.toStringRelatorio() +
                "Nota do Processo Seletivo = " + notaProcessoSeletivo + "\n" +
                "Número de Atendimentos = " + numeroAtendimentos + "\n";
    }

    public String toStringAtendimento() {
        return super.toStringRelatorio() +
                "Número de Atendimentos = " + numeroAtendimentos + "\n";
    }

    public SituacaoMatricula getSituacaoMatricula() {
        return situacaoMatricula;
    }

    public void setSituacaoMatricula(String situacaoMatricula) {
        this.situacaoMatricula = SituacaoMatricula.valueOf(situacaoMatricula);
    }

    public int getNumeroAtendimentos() {
        return numeroAtendimentos;
    }

    public void setNumeroAtendimentos() {
        this.numeroAtendimentos ++;
    }
}
