package tech.devinhouse.exception;

import tech.devinhouse.cli.Cores;

public class EntradaNumerosInvalidaException extends Exception{

    public EntradaNumerosInvalidaException(String campo) {
        super(Cores.RED + String.format("Entrada inválida. O campo %s deve conter apenas números!", campo) + Cores.RESET);
    }
}
