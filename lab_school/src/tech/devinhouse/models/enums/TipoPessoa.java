package tech.devinhouse.models.enums;

public enum TipoPessoa {

    PROFESSOR,
    ALUNO,
    PEDAGOGO,
    TODOS;

    public static TipoPessoa obterPeloCodigo(int codigo){
        TipoPessoa[] tiposPessoas = TipoPessoa.values();
        return tiposPessoas[codigo-1];
    }

}
