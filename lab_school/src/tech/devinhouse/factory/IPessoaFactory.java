package tech.devinhouse.factory;

import tech.devinhouse.exception.DataNascimentoInvalidaException;
import tech.devinhouse.exception.EntradaNumerosInvalidaException;
import tech.devinhouse.models.Aluno;
import tech.devinhouse.models.Pedagogo;
import tech.devinhouse.models.Pessoa;
import tech.devinhouse.models.Professor;
import tech.devinhouse.models.enums.*;
import tech.devinhouse.utils.Utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;

public interface IPessoaFactory {

     static Pessoa cadastrarProfessor(String nome, String telefone, Date dataNascimento, String cpf, String codigo,
                                      FormacaoAcademica formacaoAcademica, ExperienciaDesenvolvimento experienciaDesenvolvimento,
                                      EstadoProfessor estadoProfessor){

        Pessoa pessoa = new Professor(nome, telefone, dataNascimento, cpf, codigo, formacaoAcademica,
                experienciaDesenvolvimento, estadoProfessor);

        return pessoa;
    };

    static Pessoa cadastrarAluno(String nome, String telefone, Date dataNascimento, String cpf, String codigo,
                                 SituacaoMatricula situacaoMatricula, Double nota, int numeroAtendimentos){

        Pessoa pessoa = new Aluno(nome, telefone, dataNascimento, cpf, codigo, situacaoMatricula, nota, numeroAtendimentos);

        return pessoa;
    }

    static Pessoa cadastrarPedagogo(String nome, String telefone, Date dataNascimento, String cpf, String codigo, int numeroAtendimentos) {
        Pessoa pessoa = new Pedagogo(nome, telefone, dataNascimento, cpf, codigo, numeroAtendimentos);

        return pessoa;
    }
}
