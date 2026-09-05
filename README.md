📚 Sisbiblioteca

Sistema de Gestão de Biblioteca desenvolvido como atividade prática da disciplina, utilizando Java, Spring Boot, Spring Data JPA e banco de dados H2.

O sistema permite realizar o cadastro, consulta e gerenciamento de autores e livros, utilizando relacionamento entre as entidades e persistência de dados com JPA.

«Projeto desenvolvido em dupla, em colaboração entre Lucas Kauan e Lucas Cordeiro.»

---

🎯 Objetivo

O objetivo do projeto é desenvolver um sistema simples de gerenciamento de biblioteca, colocando em prática conceitos de:

- Programação em Java;
- Spring Boot;
- Spring Data JPA;
- Mapeamento Objeto-Relacional (ORM);
- Relacionamentos entre entidades;
- Repositórios JPA;
- Banco de dados H2;
- "CommandLineRunner";
- Interface de menu pelo terminal;
- Validação de dados;
- Consultas e gerenciamento de registros.

O projeto trabalha principalmente com o relacionamento entre Autores e Livros, utilizando "@OneToMany" e "@ManyToOne".

---

🛠️ Tecnologias utilizadas

- Java 17+
- Spring Boot
- Spring Data JPA
- H2 Database
- Spring Web
- Lombok
- Maven
- Git / GitHub
- Visual Studio Code

---

📂 Estrutura do projeto

sisbiblioteca/
├── pom.xml
└── src/
    └── main/
        ├── java/
        │   └── com/lab/jpa/sisbiblioteca/
        │       ├── SisbibliotecaApplication.java
        │       ├── config/
        │       │   └── DataInitializer.java
        │       ├── model/
        │       │   ├── Autor.java
        │       │   └── Livro.java
        │       └── repository/
        │           ├── AutorRepository.java
        │           └── LivroRepository.java
        └── resources/
            └── application.properties

---

⚙️ Funcionalidades principais

O sistema possui funcionalidades para gerenciamento de autores e livros.

👤 Autores

- Cadastrar autor;
- Listar autores;
- Buscar autores pelo nome;
- Excluir autor;
- Validar informações durante o cadastro.

📖 Livros

- Cadastrar livro;
- Listar livros;
- Buscar livros pelo título;
- Listar livros associados a determinado autor;
- Excluir livro;
- Validar informações durante o cadastro.

🔗 Relacionamento

Cada livro possui um autor associado.

O relacionamento entre as entidades é representado por:

Autor 1 ─────────── N Livro

Um autor pode possuir vários livros, enquanto cada livro pertence a um autor.

---

🔄 Principais modificações realizadas

Além da implementação inicial proposta na atividade, foram realizadas algumas modificações para ampliar as funcionalidades e melhorar a utilização do sistema.

1. 🔎 Pesquisa de autores

Foi adicionada a possibilidade de pesquisar autores pelo nome.

A pesquisa permite encontrar autores mesmo quando o usuário informa apenas parte do nome, utilizando uma consulta que ignora diferenças entre letras maiúsculas e minúsculas.

---

2. 🔍 Pesquisa de livros

Foi adicionada uma funcionalidade para pesquisar livros pelo título.

Assim, o usuário não precisa necessariamente listar todos os livros cadastrados para encontrar uma obra específica.

---

3. 📚 Listagem de livros por autor

Foi adicionada uma funcionalidade para consultar os livros pertencentes a um determinado autor.

Essa modificação utiliza o relacionamento entre as entidades "Autor" e "Livro", permitindo consultar as obras associadas ao ID de um autor.

---

4. 🗑️ Exclusão de autores e livros

Foram adicionadas funcionalidades para exclusão de registros.

O sistema passou a permitir:

- Excluir autores;
- Excluir livros;
- Verificar a existência do registro antes da exclusão;
- Informar ao usuário quando o registro solicitado não foi encontrado.

---

5. ✅ Validação dos cadastros

Foram adicionadas validações principalmente nos processos de cadastro de autores e livros.

Entre as validações implementadas estão:

- Impedir o cadastro de autor sem nome;
- Impedir o cadastro de livro sem título;
- Verificar se o autor informado realmente existe;
- Impedir valores inválidos para o ano de publicação da obra;
- Tratar entradas numéricas inválidas;
- Exibir mensagens informativas ao usuário quando os dados fornecidos não são válidos.

A validação do ano de publicação foi adicionada para evitar valores incoerentes, como anos muito pequenos ou valores excessivamente grandes.

---

6. 🖥️ Pequenas alterações no "case" do menu

Foram realizadas pequenas alterações na estrutura dos "case" responsáveis pelo menu interativo.

Essas alterações tiveram como objetivo:

- Organizar melhor as opções;
- Adicionar as novas funcionalidades;
- Melhorar o fluxo de navegação;
- Manter o menu mais simples e intuitivo;
- Ajustar o comportamento de retorno de cada operação.

---

🗄️ Banco de dados

O projeto utiliza o H2 Database como banco de dados em memória.

As principais tabelas são:

AUTORES
    ↓
LIVROS

A tabela "LIVROS" possui uma chave estrangeira que referencia o autor responsável pela obra.

O projeto também disponibiliza o H2 Console para visualização das tabelas e dos dados persistidos durante a execução da aplicação.

---

▶️ Como executar

1. Clonar o repositório

git clone https://github.com/SEU-USUARIO/sisbiblioteca.git

2. Entrar na pasta

cd sisbiblioteca

3. Executar com Maven

mvn spring-boot:run

Ou executar a classe:

SisbibliotecaApplication.java

---

🖥️ Utilização

Após iniciar a aplicação, o sistema apresenta um menu interativo no terminal.

O usuário pode escolher as operações disponíveis e inserir os dados solicitados.

Exemplo:

==========================================
       SISTEMA DE GESTÃO DE BIBLIOTECA
==========================================

MENU DE OPÇÕES:

1 - Cadastrar Autor
2 - Listar Autores
3 - Cadastrar Livro
4 - Listar Livros
5 - Pesquisar Autores pelo nome
6 - Pesquisar Livros pelo título
7 - Listar Livros de uma Autor específico
8 - Excluir Autor
9 - Excluir Livro
0 - Sair

Escolha uma opção:

---

🧪 H2 Console

Durante a execução da aplicação, o banco H2 pode ser acessado pelo navegador através de:

http://localhost:8080/h2-console

Configuração:

JDBC URL: jdbc:h2:mem:testdb
User Name: sa
Password:

Através do console é possível visualizar as tabelas e verificar os registros armazenados.

---

👥 Autores do projeto

Lucas Kauan
Lucas Cordeiro

Projeto desenvolvido em dupla/colaboração, com participação conjunta na implementação, testes, correções e melhorias do sistema.

---

📌 Considerações finais

O projeto foi desenvolvido com o objetivo de aplicar na prática os conceitos apresentados durante a disciplina, partindo de um sistema básico de cadastro de autores e livros e adicionando funcionalidades para tornar a aplicação mais completa.

As modificações realizadas também contribuíram para melhorar a experiência de utilização do sistema, principalmente através das funcionalidades de pesquisa, exclusão e validação dos dados inseridos.

Sisbiblioteca — Sistema de Gestão de Biblioteca 📚
