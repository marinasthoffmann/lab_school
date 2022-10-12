package tech.devinhouse.cli;

import tech.devinhouse.exception.DataNascimentoInvalidaException;
import tech.devinhouse.exception.EntradaNumerosInvalidaException;
import tech.devinhouse.exception.OpcaoInvalidaException;
import tech.devinhouse.factory.IPessoaFactory;
import tech.devinhouse.factory.PessoaFactory;
import tech.devinhouse.models.Aluno;
import tech.devinhouse.models.Pessoa;
import tech.devinhouse.models.Professor;
import tech.devinhouse.models.enums.*;
import tech.devinhouse.repository.PessoaRepository;
import tech.devinhouse.utils.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Display {
    private static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_RESET = "\u001B[0m";
    private PessoaRepository repository;
    public Display(PessoaRepository repository) {
        this.repository = repository;
    }

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
        System.out.println("                        ________________________________________________");
        System.out.println("                       | [1] - Cadastrar Professor                      |");
        System.out.println("                       | [2] - Cadastrar Aluno                          |");
        System.out.println("                       | [3] - Cadastrar Pedagogo                       |");
        System.out.println("                       | [4] - Atualizar situação de matrícula do aluno |");
        System.out.println("                       | [5] - Realizar atendimento pedagógico          |");
        System.out.println("                       | [6] - Imprimir relatório                       |");
        System.out.println("                       | [7] - Encerrar                                 |");
        System.out.println("                       |________________________________________________|");

    }

    public Operacao solicitarOperacao() throws OpcaoInvalidaException {
        Scanner scanner = new Scanner(System.in);
        exibirMensagem("Digite o número referente à opção desejada: ", Cores.YELLOW);
        int codigoOperacao = scanner.nextInt();

        if (codigoOperacao <= 0 || codigoOperacao > Operacao.values().length){
            throw new OpcaoInvalidaException();
        }

        return Operacao.obterPeloCodigo(codigoOperacao);
    }
    private static int verificarInput(int input ,int limite) throws OpcaoInvalidaException {
        int resposta = Utils.tryParseInt(String.valueOf(input));

        if (resposta == 0 || input > limite || input <= 0)
            throw new OpcaoInvalidaException();

        return input;
    }

    public static void exibirMensagem(String mensagem, String cor) {
        System.out.print("\n" + cor + mensagem + Cores.RESET);
    }

    public void exibirMensagemOpcoes(String mensagem, List<String> opcoes) {
        System.out.print("\n" + Cores.YELLOW + mensagem + "\n");
        int numeroOpcao = 1;
        for(String opcao : opcoes){
            System.out.println(String.format("[%d] - %s", numeroOpcao, opcao));
            numeroOpcao ++;
        }
        System.out.print("Escolha a opção desejada: " + Cores.RESET);
    }

    public boolean solicitarContinuar() throws OpcaoInvalidaException {
        exibirMensagem("Deseja realizar outra operação? \n [1] Sim \n [2] Não", Cores.YELLOW);
        exibirMensagem("Escolha a opção desejada: ", Cores.YELLOW);

        Scanner scanner = new Scanner(System.in);
        int opcao = scanner.nextInt();

        if (opcao <= 0 || opcao > 2){
            throw new OpcaoInvalidaException();
        }

        return opcao == 1;
    }

    public Pessoa solicitarCadastroProfessor() throws ParseException, EntradaNumerosInvalidaException,
            DataNascimentoInvalidaException, OpcaoInvalidaException {
        Scanner scanner = new Scanner(System.in);
        exibirMensagem(String.format("Digite o nome do %s: ", TipoPessoa.PROFESSOR), Cores.YELLOW);
        String nome = scanner.nextLine();

        exibirMensagem(String.format("Digite o telefone do %s (somente números) : ", TipoPessoa.PROFESSOR), Cores.YELLOW);
        String telefone = scanner.nextLine();
        if (Utils.tryParseLong(telefone) == 0) throw new EntradaNumerosInvalidaException("telefone");

        exibirMensagem(String.format("Digite a data de nascimento do %s (dd/MM/yyyy): ", TipoPessoa.PROFESSOR), Cores.YELLOW);
        String dataNascimentoInput = scanner.nextLine();
        Date dataNascimento = Utils.formatarData(dataNascimentoInput);

        exibirMensagem(String.format("Digite o cpf do %s: ", TipoPessoa.PROFESSOR), Cores.YELLOW);
        String cpf = scanner.nextLine();
        if (Utils.tryParseLong(cpf) == 0) throw new EntradaNumerosInvalidaException("cpf");

        String [] formacoesAcademicas = {"Graduação incompleta", "Graduação completa", "Mestrado", "Doutorado"};
        exibirMensagemOpcoes("Digite a formação acadêmica: ", List.of(formacoesAcademicas));
        int formacaoAcademicaInput = scanner.nextInt();
        verificarInput(formacaoAcademicaInput, formacoesAcademicas.length);
        FormacaoAcademica formacaoAcademica = FormacaoAcademica.obterPeloCodigo(formacaoAcademicaInput);

        String [] experienciasDesenvolvimento = {"Front-end", "Back-end", "Full-stack"};
        exibirMensagemOpcoes("Digite a experiência de desenvolvimento: ", List.of(experienciasDesenvolvimento));
        int experienciaDesenvolvimentoInput = scanner.nextInt();
        verificarInput(experienciaDesenvolvimentoInput, experienciasDesenvolvimento.length);
        ExperienciaDesenvolvimento experienciaDesenvolvimento = ExperienciaDesenvolvimento.obterPeloCodigo(experienciaDesenvolvimentoInput);

        String [] estados = {"Ativo", "Inativo"};
        exibirMensagemOpcoes("Digite o estado do professor: ", List.of(estados));
        int estadosInput = scanner.nextInt();
        EstadoProfessor estadoProfessor = EstadoProfessor.obterPeloCodigo(estadosInput);
        verificarInput(estadosInput, estados.length);

        String codigo = repository.gerarCodigoProfessor();

        return IPessoaFactory.cadastrarProfessor(nome, telefone, dataNascimento, cpf, codigo, formacaoAcademica, experienciaDesenvolvimento, estadoProfessor);
    }

    public Pessoa solicitarCadastroAluno() throws ParseException, EntradaNumerosInvalidaException,
            DataNascimentoInvalidaException, OpcaoInvalidaException {
        Scanner scanner = new Scanner(System.in);
        exibirMensagem(String.format("Digite o nome do %s: ", TipoPessoa.ALUNO), Cores.YELLOW);
        String nome = scanner.nextLine();

        exibirMensagem(String.format("Digite o telefone do %s (somente números) : ", TipoPessoa.ALUNO), Cores.YELLOW);
        String telefone = scanner.nextLine();
        if (Utils.tryParseLong(telefone) == 0) throw new EntradaNumerosInvalidaException("telefone");

        exibirMensagem(String.format("Digite a data de nascimento do %s (dd/MM/yyyy): ", TipoPessoa.ALUNO), Cores.YELLOW);
        String dataNascimentoInput = scanner.nextLine();
        Date dataNascimento = Utils.formatarData(dataNascimentoInput);

        exibirMensagem(String.format("Digite o cpf do %s: ", TipoPessoa.ALUNO), Cores.YELLOW);
        String cpf = scanner.nextLine();
        if (Utils.tryParseLong(cpf) == 0) throw new EntradaNumerosInvalidaException("cpf");

        String codigo = repository.gerarCodigoAluno();

        String [] situacoesMatricula = {"Ativo", "Irregular", "Atendimento pedagógico", "Inativo"};
        exibirMensagemOpcoes("Digite a situação da matrícula: ", List.of(situacoesMatricula));
        int situacaoMatriculaInput = scanner.nextInt();
        verificarInput(situacaoMatriculaInput, situacoesMatricula.length);
        SituacaoMatricula situacaoMatricula = SituacaoMatricula.obterPeloCodigo(situacaoMatriculaInput);

        exibirMensagem(String.format("Digite a nota do processo seletivo do %s: ", TipoPessoa.ALUNO), Cores.YELLOW);
        Double nota = scanner.nextDouble();
        if (Utils.tryParseDouble(nota) == 0) throw new EntradaNumerosInvalidaException(cpf);

        int numeroAtendimentos = 0;

        return IPessoaFactory.cadastrarAluno(nome, telefone, dataNascimento, cpf, codigo, situacaoMatricula, nota, numeroAtendimentos);
    }

    public Pessoa solicitarCadastroPedagogo() throws ParseException, EntradaNumerosInvalidaException, DataNascimentoInvalidaException {
        Scanner scanner = new Scanner(System.in);
        exibirMensagem(String.format("Digite o nome do %s: ", TipoPessoa.PEDAGOGO), Cores.YELLOW);
        String nome = scanner.nextLine();

        exibirMensagem(String.format("Digite o telefone do %s (somente números): ", TipoPessoa.PEDAGOGO), Cores.YELLOW);
        String telefone = scanner.nextLine();
        if (Utils.tryParseLong(telefone) == 0) throw new EntradaNumerosInvalidaException("telefone");

        exibirMensagem(String.format("Digite a data de nascimento do %s (dd/MM/yyyy): ", TipoPessoa.PEDAGOGO), Cores.YELLOW);
        String dataNascimentoInput = scanner.nextLine();
        Date dataNascimento = Utils.formatarData(dataNascimentoInput);

        exibirMensagem(String.format("Digite o cpf do %s: ", TipoPessoa.PEDAGOGO), Cores.YELLOW);
        String cpf = scanner.nextLine();
        if (Utils.tryParseLong(cpf) == 0) throw new EntradaNumerosInvalidaException("cpf");

        String codigo = repository.gerarCodigoPedagogo();

        int numeroAtendimentos = 0;

        return IPessoaFactory.cadastrarPedagogo(nome, telefone, dataNascimento, cpf, codigo, numeroAtendimentos);
    }
}
