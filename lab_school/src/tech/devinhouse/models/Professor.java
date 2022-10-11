package tech.devinhouse.models;

import tech.devinhouse.models.enums.EstadoProfessor;
import tech.devinhouse.models.enums.ExperienciaDesenvolvimento;
import tech.devinhouse.models.enums.FormacaoAcademica;

import java.util.Date;

public class Professor extends Pessoa{

    private FormacaoAcademica formacaoAcademica;
    private ExperienciaDesenvolvimento experienciaDesenvolvimento;
    private EstadoProfessor estado;


    public Professor(String nome, String telefone, Date dataDeNascimento, String cpf, String codigo, String formacaoAcademica, String experienciaDesenvolvimento, String estado) {
        super(nome, telefone, dataDeNascimento, cpf, codigo);
        this.formacaoAcademica = FormacaoAcademica.valueOf(formacaoAcademica);
        this.experienciaDesenvolvimento = ExperienciaDesenvolvimento.valueOf(experienciaDesenvolvimento);
        this.estado = EstadoProfessor.valueOf(estado);
    }


    public FormacaoAcademica getFormacaoAcademica() {
        return formacaoAcademica;
    }

    public void setFormacaoAcademica(String formacaoAcademica) {
        this.formacaoAcademica = FormacaoAcademica.valueOf(formacaoAcademica);
    }

    public ExperienciaDesenvolvimento getExperienciaDesenvolvimento() {
        return experienciaDesenvolvimento;
    }

    public void setExperienciaDesenvolvimento(String experienciaDesenvolvimento) {
        this.experienciaDesenvolvimento = ExperienciaDesenvolvimento.valueOf(experienciaDesenvolvimento);
    }

    public EstadoProfessor getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = EstadoProfessor.valueOf(estado);
    }
}
