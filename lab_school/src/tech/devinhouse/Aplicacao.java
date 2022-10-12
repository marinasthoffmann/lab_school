package tech.devinhouse;

import tech.devinhouse.cli.Cores;
import tech.devinhouse.cli.Display;
import tech.devinhouse.exception.DataNascimentoInvalidaException;
import tech.devinhouse.exception.EntradaNumerosInvalidaException;
import tech.devinhouse.exception.OpcaoInvalidaException;
import tech.devinhouse.factory.IPessoaFactory;
import tech.devinhouse.factory.PessoaFactory;
import tech.devinhouse.models.Aluno;
import tech.devinhouse.models.Pessoa;
import tech.devinhouse.models.enums.Operacao;
import tech.devinhouse.models.enums.TipoPessoa;
import tech.devinhouse.repository.PessoaRepository;

import java.text.ParseException;

public class Aplicacao {
    private PessoaRepository repository = new PessoaRepository();
    private Display display = new Display(repository);

    public void executar() throws OpcaoInvalidaException {
        Operacao operacao = null;
        boolean continua = true;

        while (continua == true){
            display.exibirMenu();
            try{
                operacao = display.solicitarOperacao();
                processar(operacao);
            } catch (OpcaoInvalidaException e) {
                display.exibirMensagem("Opção informada é inválida!\n", Cores.RED);
                System.out.println();
            } catch (ParseException e) {
                Display.exibirMensagem("Input com formato errado!\n", Cores.RED);
            } catch (EntradaNumerosInvalidaException e) {
                System.out.println(e.getMessage());
            } catch (DataNascimentoInvalidaException e) {
                System.out.println(e.getMessage());
            } finally {
                continua = display.solicitarContinuar();
                if (!continua)
                    break;
            }
        }
    }

    private void processar(Operacao operacao) throws ParseException, EntradaNumerosInvalidaException, DataNascimentoInvalidaException, OpcaoInvalidaException {
        switch (operacao){
            case CADASTRAR_PROFESSOR:
                Pessoa professor = display.solicitarCadastroProfessor();
                repository.inserir(professor);
                display.exibirMensagem("Professor cadastrado com sucesso!", Cores.GREEN);
                repository.consultar();
                break;
            case CADASTRAR_ALUNO:
                Pessoa aluno = display.solicitarCadastroAluno();
                repository.inserir(aluno);
                display.exibirMensagem("Aluno cadastrado com sucesso!", Cores.GREEN);
                repository.consultar();
                break;
            case CADASTRAR_PEDAGOGO:
                Pessoa pedagogo = display.solicitarCadastroPedagogo();
                repository.inserir(pedagogo);
                display.exibirMensagem("Pedagogo cadastrado com sucesso!", Cores.GREEN);
                repository.consultar();
                break;
            case ATUALIZAR_SITUACAO:
                break;
            case ATENDIMENTO_PEDAGOGICO:
                break;
            case IMPRIMIR_RELATORIO:
                break;
        }
    }
}
