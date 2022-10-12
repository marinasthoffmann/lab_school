package tech.devinhouse.exception;

import tech.devinhouse.cli.Cores;

public class DataNascimentoInvalidaException extends Exception{

    public DataNascimentoInvalidaException() {
        super(Cores.RED + "\nData de nascimento inválida!" + Cores.RESET);
    }
}
