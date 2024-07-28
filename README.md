# BiblioJava

## Descrição

BiblioJava é um sistema desenvolvido em Java que permite a busca e gerenciamento de livros utilizando a API externa Gutendex, que fornece informações do catálogo de livros do Project Gutenberg, uma biblioteca online de e-books gratuitos. O sistema permite que os usuários busquem títulos, registrem livros e autores favoritos em um banco de dados local, e consultem diversas informações literárias.

## Funcionalidades

- **Busca de Livros pelo Título**: Permite ao usuário buscar livros pelo título utilizando a API Gutendex e armazenar os resultados no banco de dados local.
- **Listagem de Livros Registrados**: Exibe uma lista de todos os livros registrados no banco de dados local.
- **Listagem de Autores Registrados**: Exibe uma lista de todos os autores registrados no banco de dados local.
- **Listagem de Autores Vivos em um Determinado Ano**: Permite ao usuário especificar um ano e listar autores que estavam vivos naquele ano.
- **Listagem de Livros por Idioma**: Filtra e exibe livros por idioma.
- **Listagem dos Top 10 Livros Mais Baixados**: Mostra os 10 livros mais baixados no catálogo da Gutendex.
- **Busca de Autor**: Permite ao usuário buscar informações sobre um autor específico e registrar novas obras.
- **Cálculo da Média de Downloads por Autor**: Calcula e exibe a média de downloads por autor.

## Estrutura do Projeto

O projeto está organizado nos seguintes pacotes:

- **model**: Contém as classes que representam os dados do sistema, como `Livro` e `Autor`.
- **dao**: Contém as classes para acesso a dados (Data Access Objects), responsáveis pela persistência e recuperação dos dados.
- **service**: Contém as classes de lógica de negócios, como `LivroService` e `AutorService`.
- **ui**: Contém as classes relacionadas à interface do usuário (inicialmente terminal, futuramente gráfica).

## Requisitos

- **Java 11** ou superior
- **IntelliJ IDEA** (ou outra IDE de sua preferência)
- **Git** para controle de versão

## Configuração do Ambiente

1. **Clonar o repositório:**
   ```bash
   git clone https://github.com/<USERNAME>/BiblioJava.git
   cd BiblioJava

2. **Abrir o projeto no IntelliJ IDEA:**
    ```bash
    Selecione "Open" e escolha a pasta do projeto clonado.

3. **Configurar o JDK:**
    ```bash
   Vá para "File" > "Project Structure" > "Project" e configure o JDK para Java 11 ou superior.

## Contribuição

Contribuições são bem-vindas! Siga os passos abaixo para contribuir com o projeto:

1. **Faça um Fork do projeto**
2. **Crie uma branch para sua feature** (`git checkout -b feature/AmazingFeature`)
3. **Commit suas mudanças** (`git commit -m 'Add some AmazingFeature'`)
4. **Push para a branch** (`git push origin feature/AmazingFeature`)
5. **Abra um Pull Request**

## Licença

Distribuído sob a licença MIT. Veja `LICENSE` para mais informações.

## Contato

Para mais informações, entre em contato com o desenvolvedor:

- GitHub: [Denner Caleare](https://github.com/DennerCaleare)
- LinkedIn: [Denner Caleare](https://www.linkedin.com/in/dennercaleare/)
- Instagram: [Denner Caleare](https://www.instagram.com/dennercaleare/)