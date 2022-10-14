package tech.devinhouse.utils;

import tech.devinhouse.cli.Cores;
import tech.devinhouse.exception.CPFinvalidoException;
import tech.devinhouse.exception.CodigoInvalidoException;
import tech.devinhouse.exception.DataNascimentoInvalidaException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.regex.Pattern;

import static tech.devinhouse.cli.Display.exibirMensagem;

public class Utils {

    public static long tryParseLong(String valor) {
        try {
            return Long.parseLong(valor);
        } catch (NumberFormatException e) {
//            exibirMensagem("Opção informada é inválida!", Cores.RED);
            return 0;
        }
    }

    public static int tryParseInt(String valor) {
        try {
            return Integer.parseInt(valor);
        } catch (NumberFormatException e) {
//            exibirMensagem("\nOpção inválida! ", Cores.RED);
            return 0;
        }
    }

    public static double tryParseDouble(String valor) {
        try {
            return Double.parseDouble(valor);
        } catch (NumberFormatException e) {
            return 0;
        }
    }
    public static void validaCPF(String cpfInput) throws CPFinvalidoException {
        boolean valido = Pattern.matches("[0-9]{11}", cpfInput);
        if (!valido){
            throw new CPFinvalidoException();
        }
    }

    public static Date formatarData(String dataNascimentoInput) throws ParseException, DataNascimentoInvalidaException {
        SimpleDateFormat formatar = new SimpleDateFormat("dd/MM/yyyy");
        Date dataNascimento = formatar.parse(dataNascimentoInput);
        boolean valido = Pattern.matches("[0-9]{2}/[0-9]{2}/[1-2][0,9][0-9]{2}", dataNascimentoInput);

        if (dataNascimento.after(Date.from(Instant.now())) || !valido) throw new DataNascimentoInvalidaException();

        return dataNascimento;
    }
}
