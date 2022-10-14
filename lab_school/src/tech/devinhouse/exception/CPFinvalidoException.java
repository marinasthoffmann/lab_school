package tech.devinhouse.exception;

import tech.devinhouse.cli.Cores;

public class CPFinvalidoException extends Exception {

    public CPFinvalidoException() {

        super(Cores.RED + "\nO CPF deve possuir 11 dígitos!" + Cores.RESET);

    }

}
