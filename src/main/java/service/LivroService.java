package service;

import dao.FileUtil;
import dao.GutendexAPI;
import model.Autor;
import model.Livro;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Classe de serviço para manipulação de livros.
 */
public class LivroService {

    private GutendexAPI gutendexAPI;

    public LivroService() {
        this.gutendexAPI = new GutendexAPI();
    }

    /**
     * Lista todos os livros registrados.
     *
     * @return uma lista de todos os livros registrados
     * @throws IOException se ocorrer um erro ao carregar os livros
     */
    public List<Livro> listarTodosOsLivros() throws IOException {
        return FileUtil.carregarLivros();
    }

    /**
     * Lista livros por idioma.
     *
     * @param idioma o idioma para filtrar os livros
     * @return uma lista de livros no idioma especificado
     * @throws IOException se ocorrer um erro ao carregar os livros
     */
    public List<Livro> listarLivrosPorIdioma(String idioma) throws IOException {
        List<Livro> livros = listarTodosOsLivros();
        return livros.stream()
                .filter(livro -> livro.getIdioma().equalsIgnoreCase(idioma))
                .collect(Collectors.toList());
    }

    /**
     * Calcula a média de downloads por autor.
     *
     * @return um mapa contendo autores e suas médias de downloads
     * @throws IOException se ocorrer um erro ao carregar os livros
     */
    public Map<Autor, Double> calcularMediaDownloadsPorAutor() throws IOException {
        List<Livro> livros = listarTodosOsLivros();
        Map<Autor, Integer> totalDownloadsPorAutor = new HashMap<>();
        Map<Autor, Integer> quantidadeLivrosPorAutor = new HashMap<>();

        for (Livro livro : livros) {
            Autor autor = livro.getAutor();
            totalDownloadsPorAutor.put(autor, totalDownloadsPorAutor.getOrDefault(autor, 0) + livro.getDownloads());
            quantidadeLivrosPorAutor.put(autor, quantidadeLivrosPorAutor.getOrDefault(autor, 0) + 1);
        }

        Map<Autor, Double> mediaDownloadsPorAutor = new HashMap<>();
        for (Map.Entry<Autor, Integer> entry : totalDownloadsPorAutor.entrySet()) {
            Autor autor = entry.getKey();
            int totalDownloads = entry.getValue();
            int quantidadeLivros = quantidadeLivrosPorAutor.get(autor);
            mediaDownloadsPorAutor.put(autor, (double) totalDownloads / quantidadeLivros);
        }

        return mediaDownloadsPorAutor;
    }

    /**
     * Busca livros pelo título utilizando a API Gutendex.
     *
     * @param titulo o título do livro a ser buscado
     * @return uma lista de livros correspondentes ao título
     * @throws Exception se ocorrer um erro na requisição HTTP
     */
    public List<Livro> buscarLivrosPorTitulo(String titulo) throws Exception {
        return gutendexAPI.buscarLivrosPorTitulo(titulo);
    }

    /**
     * Lista os top 10 livros mais baixados utilizando a API Gutendex.
     *
     * @return uma lista dos top 10 livros mais baixados
     * @throws Exception se ocorrer um erro na requisição HTTP
     */
    public List<Livro> listarTop10LivrosMaisBaixados() throws Exception {
        return gutendexAPI.listarTop10LivrosMaisBaixados();
    }

    /**
     * Busca detalhes de um livro pelo título utilizando a API Gutendex.
     *
     * @param titulo o título do livro a ser buscado
     * @return o livro detalhado ou null se não encontrado
     * @throws Exception se ocorrer um erro na requisição HTTP
     */
    public Livro buscarLivroDetalhado(String titulo) throws Exception {
        List<Livro> livros = gutendexAPI.buscarLivrosPorTitulo(titulo);
        return livros.isEmpty() ? null : livros.get(0);
    }
}
