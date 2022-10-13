package tech.devinhouse.repository;
import tech.devinhouse.exception.AlunoJaEmAtendimentoPedagogicoException;
import tech.devinhouse.exception.CodigoNaoCadastradoException;
import tech.devinhouse.models.Aluno;
import tech.devinhouse.models.Professor;
import tech.devinhouse.models.enums.EstadoProfessor;
import tech.devinhouse.models.enums.ExperienciaDesenvolvimento;
import tech.devinhouse.models.enums.FormacaoAcademica;
import tech.devinhouse.service.AtendimentoPedagogicoService;
import tech.devinhouse.models.Pedagogo;
import tech.devinhouse.models.Pessoa;
import tech.devinhouse.models.enums.SituacaoMatricula;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PessoaRepository implements AtendimentoPedagogicoService {

    static List<Pessoa> pessoas = new ArrayList<>();
    int numeroSequencialProfessor = 5;
    int numeroSequencialAluno = 5;
    int numeroSequencialPedagogo = 3;

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

    public void popular() throws ParseException {
        Pessoa professor1 = new Professor("Mario Gonçalves", "32325256", new SimpleDateFormat( "yyyyMMdd" ).parse( "20100520" ), "880635565", "PR00001", FormacaoAcademica.DOUTORADO, ExperienciaDesenvolvimento.BACK_END, EstadoProfessor.ATIVO);
        inserir(professor1);
        Pessoa professor2 = new Professor("Joana Maria", "32325356", new SimpleDateFormat( "yyyyMMdd" ).parse( "20100520" ), "880635565", "PR00002", FormacaoAcademica.MESTRADO, ExperienciaDesenvolvimento.FRONT_END, EstadoProfessor.INATIVO);
        inserir(professor2);
        Pessoa professor3 = new Professor("Juliano Cé", "3232175", new SimpleDateFormat( "yyyyMMdd" ).parse( "20100520" ), "844635565", "PR00003", FormacaoAcademica.GRADUACAO_COMPLETA, ExperienciaDesenvolvimento.BACK_END, EstadoProfessor.ATIVO);
        inserir(professor3);
        Pessoa professor4 = new Professor("Sonia Braga", "99325256", new SimpleDateFormat( "yyyyMMdd" ).parse( "20100520" ), "880677765", "PR00004", FormacaoAcademica.DOUTORADO, ExperienciaDesenvolvimento.FULL_STACK, EstadoProfessor.ATIVO);
        inserir(professor4);
        Pessoa professor5 = new Professor("Geromel Matias", "84325256", new SimpleDateFormat( "yyyyMMdd" ).parse( "20100520" ), "880635565", "PR00005", FormacaoAcademica.MESTRADO, ExperienciaDesenvolvimento.FRONT_END, EstadoProfessor.INATIVO);
        inserir(professor5);
        Pessoa aluno1 = new Aluno("Marina Hoffmann", "991025544", new SimpleDateFormat( "yyyyMMdd" ).parse( "19930513" ), "08283091915", "AL00001", SituacaoMatricula.ATIVO, 10, 0);
        inserir(aluno1);
        Pessoa aluno2 = new Aluno("Julia Mourão", "991037544", new SimpleDateFormat( "yyyyMMdd" ).parse( "19930513" ), "08283098915", "AL00002", SituacaoMatricula.IRREGULAR, 7, 2);
        inserir(aluno2);
        Pessoa aluno3 = new Aluno("Lucas MAtias", "991031644", new SimpleDateFormat( "yyyyMMdd" ).parse( "19930513" ), "08283098915", "AL00003", SituacaoMatricula.ATIVO, 7, 0);
        inserir(aluno3);
        Pessoa aluno4 = new Aluno("MAteus VInicius", "991037544", new SimpleDateFormat( "yyyyMMdd" ).parse( "19930513" ), "08283098915", "AL00004", SituacaoMatricula.ATENDIMENTO_PEDAGOGICO, 7, 0);
        inserir(aluno4);
        Pessoa aluno5 = new Aluno("Luana Andrade", "991037544", new SimpleDateFormat( "yyyyMMdd" ).parse( "19930513" ), "08283098915", "AL00005", SituacaoMatricula.INATIVO, 7, 1);
        inserir(aluno5);
        Pessoa pedagogo1 = new Pedagogo("José Fernando", "32325555", new SimpleDateFormat( "yyyyMMdd" ).parse( "19930513" ), "085555555", "PE00001", 2);
        inserir(pedagogo1);
        Pessoa pedagogo2 = new Pedagogo("MAteus Henrique", "32325555", new SimpleDateFormat( "yyyyMMdd" ).parse( "19930513" ), "085555555", "PE00002", 0);
        inserir(pedagogo2);
        Pessoa pedagogo3 = new Pedagogo("Maria Fernanda", "32325555", new SimpleDateFormat( "yyyyMMdd" ).parse( "19930513" ), "085555555", "PE00003", 5);
        inserir(pedagogo3);
    }

    public static List<Pessoa> getPessoas() {
        return pessoas;
    }
}
