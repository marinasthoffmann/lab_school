package tech.devinhouse.service;

import tech.devinhouse.models.Aluno;
import tech.devinhouse.models.Pedagogo;
import tech.devinhouse.models.Pessoa;
import tech.devinhouse.models.Professor;
import tech.devinhouse.models.enums.TipoPessoa;
import tech.devinhouse.repository.PessoaRepository;

import java.util.List;
import java.util.stream.Collectors;

public class RelatorioService {

    public void exibeListagem(TipoPessoa tipo) {

        List<Pessoa> pessoas = PessoaRepository.getPessoas();

        if (tipo == TipoPessoa.ALUNO) {
            List<Pessoa> alunos = pessoas.stream().filter(p -> p instanceof Aluno).toList();
            for (Pessoa aluno : alunos) System.out.println("\n" + aluno.toStringListagem());
        }
        else if (tipo == TipoPessoa.PROFESSOR) {
            List<Pessoa> professores = pessoas.stream().filter(p -> p instanceof Professor).toList();
            for (Pessoa professor : professores) System.out.println("\n" + professor.toStringListagem());
        }
        else if (tipo == TipoPessoa.PEDAGOGO) {
            List<Pessoa> pedagogos = pessoas.stream().filter(p -> p instanceof Pedagogo).toList();
            for (Pessoa pedagogo : pedagogos) System.out.println("\n" + pedagogo.toStringListagem());
        }
        else for (Pessoa pessoa : pessoas) System.out.println("\n" + pessoa.toStringListagem());
    }
}
