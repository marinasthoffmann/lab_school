package tech.devinhouse.service;

import tech.devinhouse.cli.Cores;
import tech.devinhouse.cli.Display;
import tech.devinhouse.models.Aluno;
import tech.devinhouse.models.Pedagogo;
import tech.devinhouse.models.Pessoa;
import tech.devinhouse.models.Professor;
import tech.devinhouse.models.enums.SituacaoMatricula;
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

    public void exibeRelatorioAluno(SituacaoMatricula situacao) {
        Display.exibirMensagem("***************************************************************\n", Cores.RESET);
        Display.exibirMensagem(String.format("           RELATÓRIO DE ALUNOS %s\n", situacao), Cores.RESET);

        List<Pessoa> pessoas = PessoaRepository.getPessoas();

        if(situacao == SituacaoMatricula.ATIVO){
            List<Pessoa> ativos = pessoas.stream()
                    .filter(p -> p instanceof Aluno && ((Aluno) p).getSituacaoMatricula() == SituacaoMatricula.ATIVO)
                    .toList();
            for (Pessoa ativo : ativos) System.out.println("\n" + ativo.toStringRelatorio());
        }
        else if(situacao == SituacaoMatricula.IRREGULAR){
            List<Pessoa> irregulares = pessoas.stream()
                    .filter(p -> p instanceof Aluno && ((Aluno) p).getSituacaoMatricula() == SituacaoMatricula.IRREGULAR)
                    .toList();
            for (Pessoa irregular : irregulares) System.out.println("\n" + irregular.toStringRelatorio());
        }
        else if(situacao == SituacaoMatricula.ATENDIMENTO_PEDAGOGICO){
            List<Pessoa> atendimentos = pessoas.stream()
                    .filter(p -> p instanceof Aluno && ((Aluno) p).getSituacaoMatricula() == SituacaoMatricula.ATENDIMENTO_PEDAGOGICO)
                    .toList();
            for (Pessoa atendimento : atendimentos) System.out.println("\n" + atendimento.toStringRelatorio());
        }
        else if(situacao == SituacaoMatricula.INATIVO){
            List<Pessoa> inativos = pessoas.stream()
                    .filter(p -> p instanceof Aluno && ((Aluno) p).getSituacaoMatricula() == SituacaoMatricula.INATIVO)
                    .toList();
            for (Pessoa inativo : inativos) System.out.println("\n" + inativo.toStringRelatorio());
        }
        else{
            List<Pessoa> alunos = pessoas.stream().filter(p -> p instanceof Aluno).toList();
            for (Pessoa aluno : alunos) System.out.println("\n" + aluno.toStringRelatorio());
        }
        Display.exibirMensagem("***************************************************************", Cores.RESET);
    }
}
