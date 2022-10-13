package tech.devinhouse.repository;
import tech.devinhouse.exception.AlunoJaEmAtendimentoPedagogicoException;
import tech.devinhouse.exception.CodigoNaoCadastradoException;
import tech.devinhouse.models.Aluno;
import tech.devinhouse.models.AtendimentoPedagogico;
import tech.devinhouse.models.Pedagogo;
import tech.devinhouse.models.Pessoa;
import tech.devinhouse.models.enums.SituacaoMatricula;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PessoaRepository implements AtendimentoPedagogico {

    static List<Pessoa> pessoas = new ArrayList<>();
    int numeroSequencialProfessor = 0;
    int numeroSequencialAluno = 0;
    int numeroSequencialPedagogo = 0;

    public static Pessoa consultaCodigo(String codigo) throws CodigoNaoCadastradoException {
        Pessoa pessoaSelecionada = null;

        for (Pessoa pessoa : pessoas){
            if(Objects.equals(pessoa.getCodigo(), codigo)){
                pessoaSelecionada = pessoa;
            }
        }

        if (pessoaSelecionada == null){
            throw new CodigoNaoCadastradoException();
        }

        return pessoaSelecionada;
    }

    public static void atualizaSituacaoMatricula(String codigo, SituacaoMatricula situacaoMatricula) throws CodigoNaoCadastradoException {
        Aluno pessoa = (Aluno) PessoaRepository.consultaCodigo(codigo);
        pessoa.setSituacaoMatricula(String.valueOf(situacaoMatricula));
    }


    public String gerarCodigoProfessor(){
        numeroSequencialProfessor ++;
        return String.format("PR%05d", numeroSequencialProfessor);
    }

    public String gerarCodigoAluno() {
        numeroSequencialAluno ++;
        return String.format("AL%05d", numeroSequencialAluno);
    }

    public String gerarCodigoPedagogo() {
        numeroSequencialPedagogo ++;
        return String.format("PE%05d", numeroSequencialPedagogo);
    }

    public void inserir(Pessoa pessoa){
        pessoas.add(pessoa);

    }
    public void consultar(){
        for (Pessoa pessoa : pessoas){
            System.out.println(pessoa);
        };
    }

    @Override
    public void realizarAtendimento(String codigoPedagogo, String codigoAluno) throws CodigoNaoCadastradoException,
            AlunoJaEmAtendimentoPedagogicoException {
        Pessoa pedagogo = consultaCodigo(codigoPedagogo);
        Pessoa aluno = consultaCodigo(codigoAluno);

        if (((Aluno) aluno).getSituacaoMatricula() == SituacaoMatricula.ATENDIMENTO_PEDAGOGICO)
            throw new AlunoJaEmAtendimentoPedagogicoException();

        ((Pedagogo) pedagogo).setNumeroAtendimentos();
        ((Aluno) aluno).setNumeroAtendimentos();
        ((Aluno) aluno).setSituacaoMatricula(String.valueOf(SituacaoMatricula.ATENDIMENTO_PEDAGOGICO));
    }
}
