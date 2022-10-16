package tech.devinhouse;

import tech.devinhouse.cli.Cores;
import tech.devinhouse.cli.Display;
import tech.devinhouse.exception.*;
import tech.devinhouse.models.Pessoa;
import tech.devinhouse.models.enums.*;
import tech.devinhouse.service.RelatorioService;
import tech.devinhouse.service.enums.Relatorio;
import tech.devinhouse.repository.PessoaRepository;

import java.text.ParseException;

/**
 * <p>Classe com métodos de execução e processamento das operações do programa</p>
 */
public class Aplicacao {
    private final PessoaRepository repository = new PessoaRepository();
    private final Display display = new Display(repository);

    private final RelatorioService relatorioService = new RelatorioService();

    public void executar() throws OpcaoInvalidaException, ParseException {
        repository.popular();
        Operacao operacao;
        boolean continua = true;

        while (continua){
            display.exibirMenu();
            try{
                operacao = display.solicitarOperacao();
                processar(operacao);
            } catch (OpcaoInvalidaException | EntradaNumerosInvalidaException | DataNascimentoInvalidaException |
                     CodigoInvalidoException | CodigoNaoCadastradoException | AlunoJaEmAtendimentoPedagogicoException |
                     CPFinvalidoException e) {
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

    /**
     *<p>Método contendo estrutura lógica para processar operação</p>
     * @param operacao opção de operação definida pelo usuário
     * @throws ParseException: Ao tentar converter variaveis
     * @throws EntradaNumerosInvalidaException: Quando o sistema espera entrada numérica e isso não ocorre
     * @throws DataNascimentoInvalidaException: No caso de entrada de data de nascimento em formato inválido
     * @throws OpcaoInvalidaException: Opção indicada pelo usuário não é válida para o menu de seleção
     * @throws CodigoInvalidoException: Código identificador das classes não foi inserido de forma correta
     * @throws CodigoNaoCadastradoException: Código identificador das classes ainda não foi cadastrado no sistema
     * @throws AlunoJaEmAtendimentoPedagogicoException: Ao tentar realizar atendimento pedagógico em aluno que tem situação da matricula como Atendimento Pedagógico
     * @throws CPFinvalidoException: CPF não possui apenas 11 dígitos
     */
    private void processar(Operacao operacao) throws ParseException, EntradaNumerosInvalidaException,
            DataNascimentoInvalidaException, OpcaoInvalidaException, CodigoInvalidoException, CodigoNaoCadastradoException,
            AlunoJaEmAtendimentoPedagogicoException, CPFinvalidoException {
        switch (operacao) {
            case CADASTRAR_PROFESSOR -> {
                Pessoa professor = display.solicitarCadastroProfessor();
                repository.inserir(professor);
                Display.exibirMensagem("Professor cadastrado com sucesso!", Cores.GREEN);
            }
            case CADASTRAR_ALUNO -> {
                Pessoa aluno = display.solicitarCadastroAluno();
                repository.inserir(aluno);
                Display.exibirMensagem("Aluno cadastrado com sucesso!", Cores.GREEN);
            }
            case CADASTRAR_PEDAGOGO -> {
                Pessoa pedagogo = display.solicitarCadastroPedagogo();
                repository.inserir(pedagogo);
                Display.exibirMensagem("Pedagogo cadastrado com sucesso!", Cores.GREEN);
            }
            case ATUALIZAR_SITUACAO -> {
                String codigo = display.solicitarCodigoAluno();
                SituacaoMatricula situacaoMatricula = display.solicitaSituacaoAluno();
                repository.atualizaSituacaoMatricula(codigo, situacaoMatricula);
                Display.exibirMensagem("Situação da matrícula do aluno atualizada com sucesso!\n", Cores.GREEN);
            }
            case ATENDIMENTO_PEDAGOGICO -> {
                String codigoPedagogo = display.solicitarCodigoPedagogo();
                String codigoAluno = display.solicitarCodigoAluno();
                repository.realizarAtendimento(codigoPedagogo, codigoAluno);
                Display.exibirMensagem("Atendimento pedagógico realizado com sucesso!\n", Cores.GREEN);
            }
            case IMPRIMIR_RELATORIO -> {
                    Relatorio relatorio = display.solicitarTipoRelatorio();
                    processar(relatorio);
            }
            case ENCERRAR -> {
            }
        }
    }

    /**
     * <p>Método contendo estrutura lógica para processar relatórios</p>
     * @param relatorio: opção de relatório selecionada pelo usuário
     * @throws OpcaoInvalidaException: Opção indicada pelo usuário não é válida para o menu de seleção
     */
    private void processar(Relatorio relatorio) throws OpcaoInvalidaException {
        switch (relatorio) {
            case LISTAGEM_PESSOAS -> {
                TipoPessoa tipo = display.solicitarCategoriaPessoas();
                relatorioService.exibeListagem(tipo);
            }
            case RELATORIO_ALUNOS -> {
                SituacaoMatricula situacao = display.solicitaSituacaoAlunoRelatorio();
                relatorioService.exibeRelatorioAluno(situacao);
            }
            case RELATORIO_PROFESSORES -> {
                ExperienciaDesenvolvimento experiencia = display.solicitaExperienciaProfessor();
                relatorioService.exibeRelatorioProfessor(experiencia);
            }
            case RELATORIO_ALUNOS_MAIS_ATENDIMENTOS_PEDAGOGICOS ->
                    relatorioService.exibeRelatorioAlunosComMaisAtendimentoPedagogico();
            case RELATORIO_PEDAGOGOS_MAIS_ATENDIMENTOS_PEDAGOGICOS ->
                    relatorioService.exibeRelatorioPedagogosComMaisAtendimentoPedagogico();
        }
    }
}
