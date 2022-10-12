package tech.devinhouse;

import tech.devinhouse.exception.OpcaoInvalidaException;

public class Principal {

    public static void main(String[] args) throws OpcaoInvalidaException {
        Aplicacao aplicacao = new Aplicacao();
        aplicacao.executar();
    }
}