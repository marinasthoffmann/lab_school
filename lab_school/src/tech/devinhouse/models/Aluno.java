package tech.devinhouse.models;

import tech.devinhouse.models.enums.SituacaoMatricula;

import java.util.Date;

public class Aluno extends Pessoa{

    private SituacaoMatricula situacaoMatricula;
    private double notaProcessoSeletivo;
    private static int numeroAtendimentos = 0;


    public Aluno(String nome, String telefone, Date dataDeNascimento, String cpf, String codigo, String situacaoMatricula, double notaProcessoSeletivo, int numeroAtendimentos) {
        super(nome, telefone, dataDeNascimento, cpf, codigo);
        this.situacaoMatricula = SituacaoMatricula.valueOf(situacaoMatricula);
        this.notaProcessoSeletivo = notaProcessoSeletivo;
    }

    public SituacaoMatricula getSituacaoMatricula() {
        return situacaoMatricula;
    }

    public void setSituacaoMatricula(String situacaoMatricula) {
        this.situacaoMatricula = SituacaoMatricula.valueOf(situacaoMatricula);
    }

    public double getNotaProcessoSeletivo() {
        return notaProcessoSeletivo;
    }

    public void setNotaProcessoSeletivo(double notaProcessoSeletivo) {
        this.notaProcessoSeletivo = notaProcessoSeletivo;
    }

    public int getNumeroAtendimentos() {
        return numeroAtendimentos;
    }
}
