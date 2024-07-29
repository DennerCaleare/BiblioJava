package service;

import dao.FileUtil;
import model.Livro;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Classe de serviço para manipulação de livros.
 */
public class LivroService {

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
}
