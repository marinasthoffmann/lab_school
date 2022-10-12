package tech.devinhouse.factory;

import tech.devinhouse.models.Pessoa;
import tech.devinhouse.models.Professor;
import tech.devinhouse.models.enums.EstadoProfessor;
import tech.devinhouse.models.enums.ExperienciaDesenvolvimento;
import tech.devinhouse.models.enums.FormacaoAcademica;
import tech.devinhouse.models.enums.TipoPessoa;

import java.util.Date;

public interface IPessoaFactory {

     static Pessoa cadastrarProfessor(String nome, String telefone, Date dataNascimento, String cpf, String codigo,
                                      FormacaoAcademica formacaoAcademica, ExperienciaDesenvolvimento experienciaDesenvolvimento,
                                      EstadoProfessor estadoProfessor){
        Pessoa pessoa = new Professor(nome, telefone, dataNascimento, cpf, codigo, formacaoAcademica,
                experienciaDesenvolvimento, estadoProfessor);

        return pessoa;
    };
}
