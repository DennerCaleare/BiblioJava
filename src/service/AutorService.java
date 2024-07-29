package service;

import dao.FileUtil;
import model.Autor;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Classe de serviço para manipulação de autores.
 */
public class AutorService {

    /**
     * Lista todos os autores registrados.
     *
     * @return uma lista de todos os autores registrados
     * @throws IOException se ocorrer um erro ao carregar os autores
     */
    public List<Autor> listarTodosOsAutores() throws IOException {
        return FileUtil.carregarAutores();
    }

    /**
     * Lista autores que estavam vivos em um determinado ano.
     *
     * @param ano o ano para verificar os autores vivos
     * @return uma lista de autores que estavam vivos no ano especificado
     * @throws IOException se ocorrer um erro ao carregar os autores
     */
    public List<Autor> listarAutoresVivosEmAno(int ano) throws IOException {
        List<Autor> autores = listarTodosOsAutores();
        return autores.stream()
                .filter(autor -> autor.getDataNascimento() != null && autor.getDataNascimento().getYear() <= ano)
                .collect(Collectors.toList());
    }
}
