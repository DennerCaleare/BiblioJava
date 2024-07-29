package model;

import java.util.Date;

/**
 * Representa um autor no sistema.
 */
public class Autor {
    private String nome; // Nome do autor
    private Date dataNascimento; // Data de nascimento do autor
    private String nacionalidade; // Nacionalidade do autor

    /**
     * Construtor para inicializar um autor com os detalhes fornecidos.
     *
     * @param nome           o nome do autor
     * @param dataNascimento a data de nascimento do autor
     * @param nacionalidade  a nacionalidade do autor
     */
    public Autor(String nome, Date dataNascimento, String nacionalidade) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.nacionalidade = nacionalidade;
    }

    // Getters e Setters para acessar e modificar os atributos

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }
}
