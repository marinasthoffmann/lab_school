package tech.devinhouse;

import tech.devinhouse.cli.Display;
import tech.devinhouse.exception.OpcaoInvalidaException;
import tech.devinhouse.models.Aluno;
import tech.devinhouse.models.Pessoa;
import tech.devinhouse.models.enums.Operacao;

public class Aplicacao {

    private Display display = new Display();

    public void executar(){
        Operacao operacao = null;

        while (operacao != Operacao.ENCERRAR){
            display.exibirMenu();
            try{
                operacao = display.solicitarOperacao();
                processar(operacao);
            } catch (OpcaoInvalidaException e) {
                display.exibirMensagem("Opção informada é inválida!");
            }
        }
    }

    private void processar(Operacao operacao){
        switch (operacao){
            case CADASTRAR_PROFESSOR:
                break;
            case CADASTRAR_ALUNO:
                break;
            case CADASTRAR_PEDAGOGO:
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
