package tech.devinhouse.models.enums;

public enum Operacao {

    CADASTRAR_PROFESSOR,
    CADASTRAR_ALUNO,
    CADASTRAR_PEDAGOGO,
    ATUALIZAR_SITUACAO,
    ATENDIMENTO_PEDAGOGICO,
    IMPRIMIR_RELATORIO,
    ENCERRAR;


    public static Operacao obterPeloCodigo(int codigo){
        Operacao[] operacoes = Operacao.values();
        return operacoes[codigo-1];
    }

}
