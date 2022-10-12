package tech.devinhouse.models.enums;

public enum FormacaoAcademica {

    GRADUACAO_INCOMPLETA,
    GRADUACAO_COMPLETA,
    MESTRADO,
    DOUTORADO;

    public static FormacaoAcademica obterPeloCodigo(int codigo){
        FormacaoAcademica[] formacoesAcademicas = FormacaoAcademica.values();
        return formacoesAcademicas[codigo-1];
    }
}
