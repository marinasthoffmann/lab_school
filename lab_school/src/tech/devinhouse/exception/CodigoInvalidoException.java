package tech.devinhouse.exception;

import tech.devinhouse.cli.Cores;

public class CodigoInvalidoException extends Exception{

    public CodigoInvalidoException() {

        super(Cores.RED + "\nCódigo inválido! Deve seguir o padrão ALXXXXX para alunos ou PEXXXXX para pedagogos." + Cores.RESET);

    }
}
