package tech.devinhouse.utils;

import tech.devinhouse.exception.CPFinvalidoException;
import tech.devinhouse.exception.DataNascimentoInvalidaException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.regex.Pattern;


public class Utils {
    /**
     * <p>Método criado para tentar converter String em valor long</p>
     * @param valor: valor recebido via input pelo usuário
     * @return numero convertido: se for possível converter valor
     * @return 0: quando não for possível converter String em Long
     */
    public static long tryParseLong(String valor) {
        try {
            return Long.parseLong(valor);
        } catch (NumberFormatException e) {
            return 0;
        }
    }
    /**
     * <p>Método criado para tentar converter String em valor inteiro</p>
     * @param valor: valor recebido via input pelo usuário
     * @return numero convertido: se for possível converter valor
     * @return 0: quando não for possível converter String em Integer
     */
    public static int tryParseInt(String valor) {
        try {
            return Integer.parseInt(valor);
        } catch (NumberFormatException e) {
//            exibirMensagem("\nOpção inválida! ", Cores.RED);
            return 0;
        }
    }

    /**
     * <p>Método criado para tentar converter String em valor double</p>
     * @param valor: valor recebido via input pelo usuário
     * @return numero convertido: se for possível converter valor
     * @return 0: quando não for possível converter String em Double
     */
    public static double tryParseDouble(String valor) {
        try {
            return Double.parseDouble(valor);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    /**
     * <p>Método criado para validar se cpf é um valor numérico contendo 11 digitos</p>
     * @param cpfInput: valor recebido via input pelo usuário
     * @throws CPFinvalidoException: caso CPF não seja validado
     */
    public static void validaCPF(String cpfInput) throws CPFinvalidoException {
        boolean valido = Pattern.matches("[0-9]{11}", cpfInput);
        if (!valido){
            throw new CPFinvalidoException();
        }
    }

    /**
     * <p>Método criado para formatar datas</p>
     * @param dataNascimentoInput: valor recebido via input pelo usuário
     * @return dataNascimento: formatado no padrão dd/MM/yyyy
     * @throws ParseException: quando não é possível realizar a conversão
     * @throws DataNascimentoInvalidaException: quando data de nascimento informada for maior que data atual
     */
    public static Date formatarData(String dataNascimentoInput) throws ParseException, DataNascimentoInvalidaException {
        SimpleDateFormat formatar = new SimpleDateFormat("dd/MM/yyyy");
        Date dataNascimento = formatar.parse(dataNascimentoInput);
        boolean valido = Pattern.matches("[0-9]{2}/[0-9]{2}/[1-2][0,9][0-9]{2}", dataNascimentoInput);

        if (dataNascimento.after(Date.from(Instant.now())) || !valido) throw new DataNascimentoInvalidaException();

        return dataNascimento;
    }
}
