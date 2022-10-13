package tech.devinhouse.service.enums;

public enum Relatorio {

    LISTAGEM_PESSOAS,
    RELATORIO_ALUNOS,
    RELATORIO_PROFESSORES,
    RELATORIO_ALUNOS_MAIS_ATENDIMENTOS_PEDAGOGICOS,
    RELATORIO_PEDAGOGOS_MAIS_ATENDIMENTOS_PEDAGOGICOS;

    public static Relatorio obterPeloCodigo(int codigo){
        Relatorio[] relatorios = Relatorio.values();
        return relatorios[codigo-1];
    }
}
