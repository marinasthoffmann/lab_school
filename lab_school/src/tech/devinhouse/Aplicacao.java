package tech.devinhouse;

import tech.devinhouse.cli.Cores;
import tech.devinhouse.cli.Display;
import tech.devinhouse.exception.*;
import tech.devinhouse.models.Aluno;
import tech.devinhouse.models.Pedagogo;
import tech.devinhouse.models.Pessoa;
import tech.devinhouse.models.Professor;
import tech.devinhouse.models.enums.*;
import tech.devinhouse.service.RelatorioService;
import tech.devinhouse.service.enums.Relatorio;
import tech.devinhouse.repository.PessoaRepository;

import java.text.ParseException;

public class Aplicacao {
    private PessoaRepository repository = new PessoaRepository();
    private Display display = new Display(repository);

    private RelatorioService relatorioService = new RelatorioService();

    public void executar() throws OpcaoInvalidaException, ParseException {
        repository.popular();
        Operacao operacao = null;
        boolean continua = true;

        while (continua == true){
            display.exibirMenu();
            try{
                operacao = display.solicitarOperacao();
                processar(operacao);
            } catch (OpcaoInvalidaException | EntradaNumerosInvalidaException | DataNascimentoInvalidaException |
                     CodigoInvalidoException | CodigoNaoCadastradoException | AlunoJaEmAtendimentoPedagogicoException e) {
                System.out.println(e.getMessage());
            } catch (ParseException e) {
                Display.exibirMensagem("Input com formato errado!\n", Cores.RED);
            } finally {
                continua = display.solicitarContinuar();
                if (!continua)
                    break;
            }
        }
    }

    private void processar(Operacao operacao) throws ParseException, EntradaNumerosInvalidaException,
            DataNascimentoInvalidaException, OpcaoInvalidaException, CodigoInvalidoException, CodigoNaoCadastradoException, AlunoJaEmAtendimentoPedagogicoException {
        switch (operacao){
            case CADASTRAR_PROFESSOR:
                Pessoa professor = display.solicitarCadastroProfessor();
                repository.inserir(professor);
                Display.exibirMensagem("Professor cadastrado com sucesso!", Cores.GREEN);
                repository.consultar();
                break;
            case CADASTRAR_ALUNO:
                Pessoa aluno = display.solicitarCadastroAluno();
                repository.inserir(aluno);
                Display.exibirMensagem("Aluno cadastrado com sucesso!", Cores.GREEN);
                repository.consultar();
                break;
            case CADASTRAR_PEDAGOGO:
                Pessoa pedagogo = display.solicitarCadastroPedagogo();
                repository.inserir(pedagogo);
                Display.exibirMensagem("Pedagogo cadastrado com sucesso!", Cores.GREEN);
                repository.consultar();
                break;
            case ATUALIZAR_SITUACAO:
                String codigo = display.solicitarCodigoAluno();
                SituacaoMatricula situacaoMatricula= display.solicitaSituacaoAluno();
                repository.atualizaSituacaoMatricula(codigo, situacaoMatricula);
                Display.exibirMensagem("Situação da matrícula do aluno atualizada com sucesso!\n", Cores.GREEN);
                repository.consultar();
                break;
            case ATENDIMENTO_PEDAGOGICO:
                String codigoPedagogo = display.solicitarCodigoPedagogo();
                String codigoAluno = display.solicitarCodigoAluno();
                repository.realizarAtendimento(codigoPedagogo, codigoAluno);
                Display.exibirMensagem("Atendimento pedagógico realizado com sucesso!\n", Cores.GREEN);
                repository.consultar();
                break;
            case IMPRIMIR_RELATORIO:
                Relatorio relatorio = display.solicitarTipoRelatorio();
                processar(relatorio);
                break;
        }
    }
    private void processar(Relatorio relatorio) throws OpcaoInvalidaException {
        switch (relatorio){
            case LISTAGEM_PESSOAS:
                TipoPessoa tipo = display.solicitarCategoriaPessoas();
                relatorioService.exibeListagem(tipo);
                break;
            case RELATORIO_ALUNOS:
                SituacaoMatricula situacao = display.solicitaSituacaoAluno();
                relatorioService.exibeRelatorioAluno(situacao);
                break;
            case RELATORIO_PROFESSORES:
                ExperienciaDesenvolvimento experiencia = display.solicitaExperienciaProfessor();
                relatorioService.exibeRelatorioProfessor(experiencia);
                break;
            case RELATORIO_ALUNOS_MAIS_ATENDIMENTOS_PEDAGOGICOS:
                break;
            case RELATORIO_PEDAGOGOS_MAIS_ATENDIMENTOS_PEDAGOGICOS:
                break;
        }
    }
}
