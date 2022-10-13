package tech.devinhouse;

import tech.devinhouse.exception.OpcaoInvalidaException;

import java.text.ParseException;

public class Principal {

    public static void main(String[] args) throws OpcaoInvalidaException, ParseException {
        Aplicacao aplicacao = new Aplicacao();
        aplicacao.executar();
    }
}