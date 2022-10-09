package cli;

public class Display {
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_RESET = "\u001B[0m";

    public void exibirMenu(){
        System.out.println(ANSI_BLUE +
                " __         ______     ______        ______     ______     __  __     ______     ______     __        \n" +
                "/\\ \\       /\\  __ \\   /\\  == \\      /\\  ___\\   /\\  ___\\   /\\ \\_\\ \\   /\\  __ \\   /\\  __ \\   /\\ \\       \n" +
                "\\ \\ \\____  \\ \\  __ \\  \\ \\  __<      \\ \\___  \\  \\ \\ \\____  \\ \\  __ \\  \\ \\ \\/\\ \\  \\ \\ \\/\\ \\  \\ \\ \\____  \n" +
                " \\ \\_____\\  \\ \\_\\ \\_\\  \\ \\_____\\     \\/\\_____\\  \\ \\_____\\  \\ \\_\\ \\_\\  \\ \\_____\\  \\ \\_____\\  \\ \\_____\\ \n" +
                "  \\/_____/   \\/_/\\/_/   \\/_____/      \\/_____/   \\/_____/   \\/_/\\/_/   \\/_____/   \\/_____/   \\/_____/ \n" +
                "                                                                                                      "
                + ANSI_RESET);
        System.out.println("=====================================================================================================\n");
        System.out.println("   [1] - Cadastrar Professor");
        System.out.println("   [2] - Cadastrar Aluno");
        System.out.println("   [3] - Cadastrar Pedagogo");
        System.out.println("   [4] - Atualizar situação de matrícula do aluno");
        System.out.println("   [5] - Realizar atendimento pedagógico");
        System.out.println("   [6] - Imprimir relatório");
        System.out.print("\n Digite o número referente à opção desejada:");



    }
}
