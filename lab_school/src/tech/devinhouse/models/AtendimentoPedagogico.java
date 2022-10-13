package tech.devinhouse.models;

import tech.devinhouse.exception.AlunoJaEmAtendimentoPedagogicoException;
import tech.devinhouse.exception.CodigoNaoCadastradoException;

public interface AtendimentoPedagogico {

    void realizarAtendimento(String codigoPedagogo, String codigoAluno) throws CodigoNaoCadastradoException, AlunoJaEmAtendimentoPedagogicoException;
}
