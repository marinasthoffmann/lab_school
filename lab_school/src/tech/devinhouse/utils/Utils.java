package tech.devinhouse.utils;

import tech.devinhouse.cli.Cores;
import tech.devinhouse.exception.DataNascimentoInvalidaException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

import static tech.devinhouse.cli.Display.exibirMensagem;

public class Utils {

    public static long tryParseLong(String valor) {
        try {
            return Long.parseLong(valor);
        } catch (NumberFormatException e) {
            exibirMensagem("Opção informada é inválida!", Cores.RED);
            return 0;
        }
    }

    public static int tryParseInt(String valor) {
        try {
            return Integer.parseInt(valor);
        } catch (NumberFormatException e) {
            exibirMensagem("Opção informada é inválida!", Cores.RED);
            return 0;
        }
    }

    public static double tryParseDouble(Double valor) {
        try {
            return Double.parseDouble(String.valueOf(valor));
        } catch (NumberFormatException e) {
            exibirMensagem("Opção informada é inválida!", Cores.RED);
            return 0;
        }
    }

    public static Date formatarData(String dataNascimentoInput) throws ParseException, DataNascimentoInvalidaException {
        SimpleDateFormat formatar = new SimpleDateFormat("dd/MM/yyyy");
        Date dataNascimento = formatar.parse(dataNascimentoInput);

        if (dataNascimento.after(Date.from(Instant.now()))) throw new DataNascimentoInvalidaException();

        return dataNascimento;
    }
}
