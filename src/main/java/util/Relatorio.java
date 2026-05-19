package util;

import model.Medicamento;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Relatorio {

    public static String exportarTxt(List<Medicamento> lista, String titulo) throws IOException {
        String nomeArquivo = "relatorio_" +
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"))
                + ".txt";

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nomeArquivo))) {
            String linha = "=".repeat(75);
            bw.write(linha); bw.newLine();
            bw.write("  FARMÁCIA — " + titulo.toUpperCase()); bw.newLine();
            bw.write("  Gerado em: " +
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
            bw.newLine();
            bw.write(linha); bw.newLine();
            bw.newLine();

            if (lista.isEmpty()) {
                bw.write("  Nenhum registro encontrado.");
                bw.newLine();
            } else {
                for (Medicamento m : lista) {
                    bw.write(m.toString());
                    bw.newLine();
                }
                bw.newLine();
                bw.write("  Total: " + lista.size() + " medicamento(s).");
                bw.newLine();
            }

            bw.newLine();
            bw.write(linha); bw.newLine();
        }

        return nomeArquivo;
    }
}