package tech.devinhouse.exception;

import tech.devinhouse.cli.Cores;

public class AlunoJaEmAtendimentoPedagogicoException extends Exception {

    public AlunoJaEmAtendimentoPedagogicoException() {

        super(Cores.RED + "\nO aluno informado já se encontra em atendimento pedagógico!" + Cores.RESET);

    }
}
