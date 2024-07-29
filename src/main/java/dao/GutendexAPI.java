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
import java.util.List;

/**
 * Classe para integração com a API Gutendex.
 */
public class GutendexAPI {

    private static final String BASE_URL = "https://gutendex.com/books";

    /**
     * Busca livros pelo título utilizando a API Gutendex.
     *
     * @param titulo o título do livro a ser buscado
     * @return uma lista de livros correspondentes ao título
     * @throws Exception se ocorrer um erro na requisição HTTP
     */
    public List<Livro> buscarLivrosPorTitulo(String titulo) throws Exception {
        // Codificar o parâmetro de busca
        String encodedTitulo = URLEncoder.encode(titulo, StandardCharsets.UTF_8.toString());
        String url = BASE_URL + "?search=" + encodedTitulo;
        String jsonResponse = fazerRequisicaoHTTP(url);

        // Parse JSON response
        JsonObject jsonObject = JsonParser.parseString(jsonResponse).getAsJsonObject();
        return parseLivrosFromJson(jsonObject);
    }

    /**
     * Lista os top 10 livros mais baixados utilizando a API Gutendex.
     *
     * @return uma lista dos top 10 livros mais baixados
     * @throws Exception se ocorrer um erro na requisição HTTP
     */
    public List<Livro> listarTop10LivrosMaisBaixados() throws Exception {
        String url = BASE_URL + "?sort=downloads&languages=en";
        String jsonResponse = fazerRequisicaoHTTP(url);

        // Parse JSON response
        JsonObject jsonObject = JsonParser.parseString(jsonResponse).getAsJsonObject();
        return parseLivrosFromJson(jsonObject).subList(0, 10);
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

        for (var item : jsonObject.getAsJsonArray("results")) {
            JsonObject livroJson = item.getAsJsonObject();

            // Extrair informações do livro
            String titulo = livroJson.get("title").getAsString();
            String idioma = livroJson.get("languages").getAsJsonArray().get(0).getAsString();
            int downloads = livroJson.get("download_count").getAsInt();

            // Extrair informações do autor
            JsonObject autorJson = livroJson.getAsJsonArray("authors").get(0).getAsJsonObject();
            String nomeAutor = autorJson.get("name").getAsString();
            Autor autor = new Autor(nomeAutor, null, null);

            // Criar objeto Livro e adicionar à lista
            Livro livro = new Livro(titulo, autor, 0, idioma, downloads);
            livros.add(livro);
        }

        return livros;
    }
}
