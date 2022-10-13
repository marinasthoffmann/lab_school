package tech.devinhouse.service;

import tech.devinhouse.exception.AlunoJaEmAtendimentoPedagogicoException;
import tech.devinhouse.exception.CodigoNaoCadastradoException;

public interface AtendimentoPedagogicoService {

    void realizarAtendimento(String codigoPedagogo, String codigoAluno) throws CodigoNaoCadastradoException, AlunoJaEmAtendimentoPedagogicoException;
}
