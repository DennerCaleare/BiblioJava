package dao;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import model.Autor;
import model.Livro;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Classe para integração com a API Open Library.
 */
public class BookAPI {

    private static final String BASE_URL = "https://openlibrary.org/search.json";

    /**
     * Busca livros pelo título utilizando a API Open Library.
     *
     * @param titulo o título do livro a ser buscado
     * @return uma lista de livros correspondentes ao título
     * @throws Exception se ocorrer um erro na requisição HTTP
     */
    public List<Livro> buscarLivrosPorTitulo(String titulo) throws Exception {
        // Codificar o parâmetro de busca
        String encodedTitulo = URLEncoder.encode(titulo, StandardCharsets.UTF_8.toString());
        String url = BASE_URL + "?title=" + encodedTitulo;
        String jsonResponse = fazerRequisicaoHTTP(url);

        // Parse JSON response
        JsonObject jsonObject = JsonParser.parseString(jsonResponse).getAsJsonObject();
        List<Livro> livros = parseLivrosFromJson(jsonObject);

        // Filtrar e ordenar os melhores resultados (por exemplo, por ano de publicação mais recente)
        return livros.stream()
                .sorted(Comparator.comparing(Livro::getAnoPublicacao).reversed())
                .limit(10)
                .collect(Collectors.toList());
    }

    /**
     * Faz uma requisição HTTP para a URL fornecida.
     *
     * @param urlString a URL para a qual a requisição será feita
     * @return a resposta JSON como uma string
     * @throws Exception se ocorrer um erro na requisição HTTP
     */
    private String fazerRequisicaoHTTP(String urlString) throws Exception {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return response.toString();
    }

    /**
     * Parse JSON para extrair informações dos livros.
     *
     * @param jsonObject o objeto JSON contendo a resposta da API
     * @return uma lista de objetos Livro
     */
    private List<Livro> parseLivrosFromJson(JsonObject jsonObject) {
        List<Livro> livros = new ArrayList<>();
        Gson gson = new Gson();

        for (var item : jsonObject.getAsJsonArray("docs")) {
            JsonObject livroJson = item.getAsJsonObject();

            // Extrair informações do livro
            String titulo = livroJson.has("title") ? livroJson.get("title").getAsString() : "Título Desconhecido";
            String idioma = livroJson.has("language") ? livroJson.getAsJsonArray("language").get(0).getAsString() : "Desconhecido";
            int anoPublicacao = livroJson.has("first_publish_year") ? livroJson.get("first_publish_year").getAsInt() : 0;

            // Extrair informações do autor
            String nomeAutor = livroJson.has("author_name") ? livroJson.getAsJsonArray("author_name").get(0).getAsString() : "Autor Desconhecido";
            Autor autor = new Autor(nomeAutor, null, null);

            // Criar objeto Livro e adicionar à lista
            Livro livro = new Livro(titulo, autor, anoPublicacao, idioma, 0);
            livros.add(livro);
        }

        return livros;
    }
}
