package tech.devinhouse.exception;

import tech.devinhouse.cli.Cores;

public class CodigoNaoCadastradoException extends Exception {
    public CodigoNaoCadastradoException() {

        super(Cores.RED + "\nCódigo não existe na base de dados!" + Cores.RESET);

    }
}
