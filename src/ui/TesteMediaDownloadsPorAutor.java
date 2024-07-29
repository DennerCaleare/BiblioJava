package ui;

import model.Autor;
import service.LivroService;

import java.io.IOException;
import java.util.Map;

/**
 * Classe para testar o cálculo da média de downloads por autor.
 */
public class TesteMediaDownloadsPorAutor {
    public static void main(String[] args) {
        LivroService livroService = new LivroService();

        try {
            // Calcular média de downloads por autor
            Map<Autor, Double> mediaDownloadsPorAutor = livroService.calcularMediaDownloadsPorAutor();
            System.out.println("Média de Downloads por Autor:");
            for (Map.Entry<Autor, Double> entry : mediaDownloadsPorAutor.entrySet()) {
                Autor autor = entry.getKey();
                Double mediaDownloads = entry.getValue();
                System.out.println("Autor: " + autor.getNome());
                System.out.println("Média de Downloads: " + mediaDownloads);
                System.out.println("------");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
