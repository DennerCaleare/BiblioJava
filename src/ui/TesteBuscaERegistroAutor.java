package ui;

import service.AutorService;

/**
 * Classe para testar a busca e registro de autores.
 */
public class TesteBuscaERegistroAutor {
    public static void main(String[] args) {
        AutorService autorService = new AutorService();

        try {
            // Buscar e registrar autor
            String nomeAutor = "Machado de Assis";
            autorService.buscarERegistrarAutor(nomeAutor);
            System.out.println("Autor registrado: " + nomeAutor);

            // Verificar se o autor foi registrado
            autorService.listarTodosOsAutores().forEach(autor -> {
                System.out.println("Nome: " + autor.getNome());
                System.out.println("Nacionalidade: " + autor.getNacionalidade());
                System.out.println("------");
            });

            // Verificar se os livros do autor foram registrados
            autorService.listarTodosOsAutores().forEach(autor -> {
                System.out.println("Nome: " + autor.getNome());
                System.out.println("Nacionalidade: " + autor.getNacionalidade());
                System.out.println("------");
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
