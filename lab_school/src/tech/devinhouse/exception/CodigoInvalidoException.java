package tech.devinhouse.exception;

import tech.devinhouse.cli.Cores;

public class CodigoInvalidoException extends Exception{

    public CodigoInvalidoException() {

        super(Cores.RED + "\nCódigo do aluno inválido! Deve seguir o padrão ALXXXXX." + Cores.RESET);

    }
}
