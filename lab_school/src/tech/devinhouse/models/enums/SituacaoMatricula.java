package tech.devinhouse.models.enums;

public enum SituacaoMatricula {

    ATIVO,
    IRREGULAR,
    ATENDIMENTO_PEDAGOGICO,
    INATIVO,
    TODOS;

    public static SituacaoMatricula obterPeloCodigo(int codigo) {
        SituacaoMatricula[] situacaoMatriculas = SituacaoMatricula.values();
        return situacaoMatriculas[codigo-1];
    }
}
