package tech.devinhouse.repository;

import tech.devinhouse.cli.Display;
import tech.devinhouse.models.Pessoa;

import java.util.ArrayList;
import java.util.List;

public class PessoaRepository {

    List<Pessoa> pessoas = new ArrayList<>();
    int numeroSequencialProfessor = 0;
    int numeroSequencialAluno = 0;


    public String gerarCodigoProfessor(){
        numeroSequencialProfessor ++;
        return String.format("PR%05d", numeroSequencialProfessor);
    }

    public String gerarCodigoAluno() {
        numeroSequencialAluno ++;
        return String.format("AL%05d", numeroSequencialAluno);
    }

    public void inserir(Pessoa pessoa){
        pessoas.add(pessoa);

    }
    public void consultar(){
        for (Pessoa pessoa : pessoas){
            System.out.println(pessoa);
        };
    }
}
