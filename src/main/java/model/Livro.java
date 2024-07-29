package model;

/**
 * Representa um livro no sistema.
 */
public class Livro {
    private String titulo; // Título do livro
    private Autor autor; // Autor do livro
    private int anoPublicacao; // Ano de publicação do livro
    private String idioma; // Idioma do livro
    private int downloads; // Número de downloads do livro

    /**
     * Construtor para inicializar um livro com os detalhes fornecidos.
     *
     * @param titulo        o título do livro
     * @param autor         o autor do livro
     * @param anoPublicacao o ano de publicação do livro
     * @param idioma        o idioma do livro
     * @param downloads     o número de downloads do livro
     */
    public Livro(String titulo, Autor autor, int anoPublicacao, String idioma, int downloads) {
        this.titulo = titulo;
        this.autor = autor;
        this.anoPublicacao = anoPublicacao;
        this.idioma = idioma;
        this.downloads = downloads;
    }

    // Getters e Setters para acessar e modificar os atributos

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public int getAnoPublicacao() {
        return anoPublicacao;
    }

    public void setAnoPublicacao(int anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public int getDownloads() {
        return downloads;
    }

    public void setDownloads(int downloads) {
        this.downloads = downloads;
    }
}
