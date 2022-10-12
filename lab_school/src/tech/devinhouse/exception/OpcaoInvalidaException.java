package tech.devinhouse.exception;

import tech.devinhouse.cli.Cores;

public class OpcaoInvalidaException extends Exception{

    public OpcaoInvalidaException() {
        super(Cores.RED + "\nOpção inválida!" + Cores.RESET);
    }
}
