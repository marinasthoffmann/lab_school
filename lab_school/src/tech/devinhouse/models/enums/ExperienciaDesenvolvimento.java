package tech.devinhouse.models.enums;

public enum ExperienciaDesenvolvimento {

    FRONT_END,
    BACK_END,
    FULL_STACK,
    TODOS;

    public static ExperienciaDesenvolvimento obterPeloCodigo(int codigo){
        ExperienciaDesenvolvimento[] experienciasDesenvolvimentos = ExperienciaDesenvolvimento.values();
        return experienciasDesenvolvimentos[codigo-1];
    }
}
