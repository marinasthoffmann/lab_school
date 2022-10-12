package tech.devinhouse.models.enums;

public enum EstadoProfessor {

    ATIVO,
    INATIVO;

    public static EstadoProfessor obterPeloCodigo(int codigo){
        EstadoProfessor[] estadoProfessores = EstadoProfessor.values();
        return estadoProfessores[codigo-1];
    }
}
