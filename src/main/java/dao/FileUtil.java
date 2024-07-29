package dao;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.Autor;
import model.Livro;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe utilitária para manipulação de arquivos de dados.
 */
public class FileUtil {

    private static final String LIVROS_FILE = "livros.json";
    private static final String AUTORES_FILE = "autores.json";
    private static final Gson gson = new Gson();

    /**
     * Salva a lista de livros em um arquivo.
     *
     * @param livros a lista de livros a ser salva
     * @throws IOException se ocorrer um erro ao escrever no arquivo
     */
    public static void salvarLivros(List<Livro> livros) throws IOException {
        try (FileWriter writer = new FileWriter(LIVROS_FILE)) {
            gson.toJson(livros, writer);
        }
    }

    /**
     * Carrega a lista de livros de um arquivo.
     *
     * @return a lista de livros carregada do arquivo
     * @throws IOException se ocorrer um erro ao ler o arquivo
     */
    public static List<Livro> carregarLivros() throws IOException {
        try (FileReader reader = new FileReader(LIVROS_FILE)) {
            Type listType = new TypeToken<ArrayList<Livro>>() {}.getType();
            return gson.fromJson(reader, listType);
        }
    }

    /**
     * Salva a lista de autores em um arquivo.
     *
     * @param autores a lista de autores a ser salva
     * @throws IOException se ocorrer um erro ao escrever no arquivo
     */
    public static void salvarAutores(List<Autor> autores) throws IOException {
        try (FileWriter writer = new FileWriter(AUTORES_FILE)) {
            gson.toJson(autores, writer);
        }
    }

    /**
     * Carrega a lista de autores de um arquivo.
     *
     * @return a lista de autores carregada do arquivo
     * @throws IOException se ocorrer um erro ao ler o arquivo
     */
    public static List<Autor> carregarAutores() throws IOException {
        try (FileReader reader = new FileReader(AUTORES_FILE)) {
            Type listType = new TypeToken<ArrayList<Autor>>() {}.getType();
            return gson.fromJson(reader, listType);
        }
    }
}
