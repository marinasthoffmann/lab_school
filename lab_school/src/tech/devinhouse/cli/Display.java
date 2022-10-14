package tech.devinhouse.cli;

import tech.devinhouse.exception.*;
import tech.devinhouse.factory.IPessoaFactory;
import tech.devinhouse.models.Pessoa;
import tech.devinhouse.models.enums.*;
import tech.devinhouse.repository.PessoaRepository;
import tech.devinhouse.service.enums.Relatorio;
import tech.devinhouse.utils.Utils;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Display {
    private PessoaRepository repository;
    public Display(PessoaRepository repository) {
        this.repository = repository;
    }

    public void exibirMenu(){
        System.out.println(Cores.BLUE +
                " __         ______     ______        ______     ______     __  __     ______     ______     __        \n" +
                "/\\ \\       /\\  __ \\   /\\  == \\      /\\  ___\\   /\\  ___\\   /\\ \\_\\ \\   /\\  __ \\   /\\  __ \\   /\\ \\       \n" +
                "\\ \\ \\____  \\ \\  __ \\  \\ \\  __<      \\ \\___  \\  \\ \\ \\____  \\ \\  __ \\  \\ \\ \\/\\ \\  \\ \\ \\/\\ \\  \\ \\ \\____  \n" +
                " \\ \\_____\\  \\ \\_\\ \\_\\  \\ \\_____\\     \\/\\_____\\  \\ \\_____\\  \\ \\_\\ \\_\\  \\ \\_____\\  \\ \\_____\\  \\ \\_____\\ \n" +
                "  \\/_____/   \\/_/\\/_/   \\/_____/      \\/_____/   \\/_____/   \\/_/\\/_/   \\/_____/   \\/_____/   \\/_____/ \n" +
                "                                                                                                      "
                + Cores.RESET);
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
    private static int verificarInput(String input ,int limite) throws OpcaoInvalidaException {
        int resposta = Utils.tryParseInt(input);

        if (resposta == 0 || resposta > limite || resposta <= 0)
            throw new OpcaoInvalidaException();

        return resposta;
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
            DataNascimentoInvalidaException, OpcaoInvalidaException, CPFinvalidoException {
        Scanner scanner = new Scanner(System.in);
        exibirMensagem(String.format("Digite o nome do %s: ", TipoPessoa.PROFESSOR), Cores.YELLOW);
        String nome = scanner.nextLine();

        exibirMensagem(String.format("Digite o telefone do %s (somente números) : ", TipoPessoa.PROFESSOR), Cores.YELLOW);
        String telefone = scanner.nextLine();
        if (Utils.tryParseLong(telefone) == 0) throw new EntradaNumerosInvalidaException("telefone");

        exibirMensagem(String.format("Digite a data de nascimento do %s (dd/MM/yyyy): ", TipoPessoa.PROFESSOR), Cores.YELLOW);
        String dataNascimentoInput = scanner.nextLine();
        Date dataNascimento = Utils.formatarData(dataNascimentoInput);

        exibirMensagem(String.format("Digite o cpf do %s (somente números): ", TipoPessoa.PROFESSOR), Cores.YELLOW);
        String cpf = scanner.nextLine();
        Utils.validaCPF(cpf);
        if (Utils.tryParseLong(cpf) == 0) throw new EntradaNumerosInvalidaException("cpf");

        String [] formacoesAcademicas = {"Graduação incompleta", "Graduação completa", "Mestrado", "Doutorado"};
        exibirMensagemOpcoes("Digite a formação acadêmica: ", List.of(formacoesAcademicas));
        String formacaoAcademicaInput = scanner.nextLine();
        int opcaoFormacaoAcademica = verificarInput(formacaoAcademicaInput, formacoesAcademicas.length);
        FormacaoAcademica formacaoAcademica = FormacaoAcademica.obterPeloCodigo(opcaoFormacaoAcademica);

        String [] experienciasDesenvolvimento = {"Front-end", "Back-end", "Full-stack"};
        exibirMensagemOpcoes("Digite a experiência de desenvolvimento: ", List.of(experienciasDesenvolvimento));
        String experienciaDesenvolvimentoInput = scanner.nextLine();
        int opcaoExperienciaDesenvolvimento = verificarInput(experienciaDesenvolvimentoInput, experienciasDesenvolvimento.length);
        ExperienciaDesenvolvimento experienciaDesenvolvimento = ExperienciaDesenvolvimento.obterPeloCodigo(opcaoExperienciaDesenvolvimento);

        String [] estados = {"Ativo", "Inativo"};
        exibirMensagemOpcoes("Digite o estado do professor: ", List.of(estados));
        String estadosInput = scanner.nextLine();
        int opcaoEstado = verificarInput(estadosInput, estados.length);
        EstadoProfessor estadoProfessor = EstadoProfessor.obterPeloCodigo(opcaoEstado);


        String codigo = repository.gerarCodigoProfessor();

        return IPessoaFactory.cadastrarProfessor(nome, telefone, dataNascimento, cpf, codigo, formacaoAcademica, experienciaDesenvolvimento, estadoProfessor);
    }

    public Pessoa solicitarCadastroAluno() throws ParseException, EntradaNumerosInvalidaException,
            DataNascimentoInvalidaException, OpcaoInvalidaException, CPFinvalidoException {
        Scanner scanner = new Scanner(System.in);
        exibirMensagem(String.format("Digite o nome do %s: ", TipoPessoa.ALUNO), Cores.YELLOW);
        String nome = scanner.nextLine();

        exibirMensagem(String.format("Digite o telefone do %s (somente números) : ", TipoPessoa.ALUNO), Cores.YELLOW);
        String telefone = scanner.nextLine();
        if (Utils.tryParseLong(telefone) == 0) throw new EntradaNumerosInvalidaException("telefone");

        exibirMensagem(String.format("Digite a data de nascimento do %s (dd/MM/yyyy): ", TipoPessoa.ALUNO), Cores.YELLOW);
        String dataNascimentoInput = scanner.nextLine();
        Date dataNascimento = Utils.formatarData(dataNascimentoInput);

        exibirMensagem(String.format("Digite o cpf do %s (somente números): ", TipoPessoa.ALUNO), Cores.YELLOW);
        String cpf = scanner.nextLine();
        Utils.validaCPF(cpf);
        if (Utils.tryParseLong(cpf) == 0) throw new EntradaNumerosInvalidaException("cpf");

        String codigo = repository.gerarCodigoAluno();

        SituacaoMatricula situacaoMatricula = solicitaSituacaoAluno();

        exibirMensagem(String.format("Digite a nota do processo seletivo do %s (somente números): ", TipoPessoa.ALUNO), Cores.YELLOW);
        String notaInput = scanner.nextLine();
        if (Utils.tryParseDouble(notaInput) == 0) throw new EntradaNumerosInvalidaException("nota");
        double nota = Utils.tryParseDouble(notaInput);

        int numeroAtendimentos = 0;

        return IPessoaFactory.cadastrarAluno(nome, telefone, dataNascimento, cpf, codigo, situacaoMatricula, nota, numeroAtendimentos);
    }

    public Pessoa solicitarCadastroPedagogo() throws ParseException, EntradaNumerosInvalidaException, DataNascimentoInvalidaException, CPFinvalidoException {
        Scanner scanner = new Scanner(System.in);
        exibirMensagem(String.format("Digite o nome do %s: ", TipoPessoa.PEDAGOGO), Cores.YELLOW);
        String nome = scanner.nextLine();

        exibirMensagem(String.format("Digite o telefone do %s (somente números): ", TipoPessoa.PEDAGOGO), Cores.YELLOW);
        String telefone = scanner.nextLine();
        if (Utils.tryParseLong(telefone) == 0) throw new EntradaNumerosInvalidaException("telefone");

        exibirMensagem(String.format("Digite a data de nascimento do %s (dd/MM/yyyy): ", TipoPessoa.PEDAGOGO), Cores.YELLOW);
        String dataNascimentoInput = scanner.nextLine();
        Date dataNascimento = Utils.formatarData(dataNascimentoInput);

        exibirMensagem(String.format("Digite o cpf do %s (somente números): ", TipoPessoa.PEDAGOGO), Cores.YELLOW);
        String cpf = scanner.nextLine();
        Utils.validaCPF(cpf);
        if (Utils.tryParseLong(cpf) == 0) throw new EntradaNumerosInvalidaException("cpf");

        String codigo = repository.gerarCodigoPedagogo();

        int numeroAtendimentos = 0;

        return IPessoaFactory.cadastrarPedagogo(nome, telefone, dataNascimento, cpf, codigo, numeroAtendimentos);
    }

    public String solicitarCodigoAluno() throws CodigoInvalidoException {
        Scanner scanner = new Scanner(System.in);
        exibirMensagem("Digite o código do aluno: ", Cores.YELLOW);
        String codigoInput = scanner.nextLine();

        boolean valido = Pattern.matches("AL[0-9]{5}", codigoInput);
        if (!valido){
            throw new CodigoInvalidoException();
        }

        return codigoInput;
    }

    public String solicitarCodigoPedagogo() throws CodigoInvalidoException {
        Scanner scanner = new Scanner(System.in);
        exibirMensagem("Digite o código do pedagogo: ", Cores.YELLOW);
        String codigoInput = scanner.nextLine();

        boolean valido = Pattern.matches("PE[0-9]{5}", codigoInput);
        if (!valido){
            throw new CodigoInvalidoException();
        }

        return codigoInput;
    }

    public SituacaoMatricula solicitaSituacaoAluno() throws OpcaoInvalidaException {
        Scanner scanner = new Scanner(System.in);
        String [] situacoesMatricula = {"Ativo", "Irregular", "Atendimento pedagógico", "Inativo"};
        exibirMensagemOpcoes("Digite a situação da matrícula: ", List.of(situacoesMatricula));

        String situacaoMatriculaInput = scanner.nextLine();
        int opcaoSituacaoMatricula = verificarInput(situacaoMatriculaInput, situacoesMatricula.length);

        return SituacaoMatricula.obterPeloCodigo(opcaoSituacaoMatricula);
    }

    public SituacaoMatricula solicitaSituacaoAlunoRelatorio() throws OpcaoInvalidaException {
        Scanner scanner = new Scanner(System.in);
        String [] situacoesMatricula = {"Ativo", "Irregular", "Atendimento pedagógico", "Inativo", "Todos"};
        exibirMensagemOpcoes("Digite a situação da matrícula: ", List.of(situacoesMatricula));

        String situacaoMatriculaInput = scanner.nextLine();
        int opcaoSituacaoMatricula = verificarInput(situacaoMatriculaInput, situacoesMatricula.length);

        return SituacaoMatricula.obterPeloCodigo(opcaoSituacaoMatricula);
    }

    public Relatorio solicitarTipoRelatorio() throws OpcaoInvalidaException {

        Scanner scanner = new Scanner(System.in);
        String [] opcoesRelatorios = {"Listagem de pessoas", "Relatório de Alunos", "Relatório de Professores",
                "Relatório de Alunos com mais atendimentos pedagógicos", "Pedagogos com mais atendimentos pedagógicos"};
        exibirMensagemOpcoes("Digite a opção equivalente ao relatório desejado: ", List.of(opcoesRelatorios));
        int codigoRelatorio = scanner.nextInt();

        if (codigoRelatorio <= 0 || codigoRelatorio > Relatorio.values().length){
            throw new OpcaoInvalidaException();
        }
        return Relatorio.obterPeloCodigo(codigoRelatorio);
    }

    public TipoPessoa solicitarCategoriaPessoas() throws OpcaoInvalidaException {
        Scanner scanner = new Scanner(System.in);
        String [] categorias = {"Professores", "Alunos", "Pedagogos", "Todos"};
        exibirMensagemOpcoes("Digite a categoria de pessoas da listagem: ", List.of(categorias));
        int categoria = scanner.nextInt();

        if (categoria <= 0 || categoria > TipoPessoa.values().length){
            throw new OpcaoInvalidaException();
        }
        return TipoPessoa.obterPeloCodigo(categoria);
    }

    public ExperienciaDesenvolvimento solicitaExperienciaProfessor() throws OpcaoInvalidaException {
        Scanner scanner = new Scanner(System.in);
        String [] categorias = {"Front-end", "Back-end", "Full-stack", "Todos"};
        exibirMensagemOpcoes("Digite a experiência de desenvolvimento do professor: ", List.of(categorias));
        int categoria = scanner.nextInt();

        if (categoria <= 0 || categoria > TipoPessoa.values().length){
            throw new OpcaoInvalidaException();
        }
        return ExperienciaDesenvolvimento.obterPeloCodigo(categoria);
    }
}
