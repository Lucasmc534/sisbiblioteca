package com.lab.jpa.sisbiblioteca.config;

import com.lab.jpa.sisbiblioteca.model.Autor; 
import com.lab.jpa.sisbiblioteca.model.Livro; 
import com.lab.jpa.sisbiblioteca.repository.AutorRepository; 
import com.lab.jpa.sisbiblioteca.repository.LivroRepository; 
import org.springframework.boot.CommandLineRunner; 
import org.springframework.stereotype.Component;

import java.util.Optional; 
import java.util.Scanner;

@Component
public class DataInitializer implements CommandLineRunner {

    private final AutorRepository autorRepository; 
    private final LivroRepository livroRepository;

        public DataInitializer(AutorRepository autorRepository, LivroRepository livroRepository) {
            this.autorRepository = autorRepository; 
            this.livroRepository = livroRepository;
        }

        @Override
        public void run(String... args) throws Exception {
            var scanner = new Scanner(System.in); 
            var continuar = true;

            System.out.println("==========================================");
            System.out.println("   SISTEMA DE GESTÃO DE BIBLIOTECA JPA    ");
            System.out.println("==========================================");

            // Mantem o menu ativo ate o usuario escolher a opcao 0.
            while (continuar) {
                System.out.println("\nMENU DE OPÇÕES:"); 
                System.out.println("1 - Cadastrar Autor"); 
                System.out.println("2 - Listar Autores"); 
                System.out.println("3 - Cadastrar Livro"); 
                System.out.println("4 - Listar Livros");
                System.out.println("5 - Pesquisar Autores pelo nome");
                System.out.println("6 - Pesquisar Livros pelo título");
                System.out.println("7 - Listar Livros de uma Autor específico");
                System.out.println("8 - Excluir Autor");
                System.out.println("9 - Excluir livro");
                System.out.println("0 - Sair"); 
                System.out.print("Escolha uma opção: ");

                var opcao = scanner.nextLine();

                switch (opcao) {
                    case "1" -> {
                        cadastrarAutor(scanner);
                    }
                    case "2" -> {
                        listarAutores();
                    }
                    case "3" -> {
                        cadastrarLivro(scanner);
                    }
                    case "4" -> {
                        listarLivros();
                    }
                    case "5" -> {
                        pesquisarAutores(scanner);
                    }
                    case "6" -> {
                        pesquisarLivros(scanner);
                    }
                    case "7" -> {
                        listarLivrosPorAutor(scanner);
                    }
                    case "8" -> {
                        excluirAutor(scanner);
                    }
                    case "9" -> {
                        excluirLivro(scanner);
                    }
                    case "0" -> {
                        System.out.println("Saindo do sistema...");
                        continuar = false;
                    }
                    default -> {
                        System.out.println("Opção invalida. Tente novamente.");
                    }
                }
            }
            System.out.println("Aplicação finalizada.");
        }

        // Valida o nome antes de persistir um novo autor.
        private void cadastrarAutor(Scanner scanner) { 
            System.out.print("Digite o nome do autor: ");
            var nome = scanner.nextLine();

            if (nome.isBlank() || nome.length() < 2 || nome.length() > 100) {
                System.out.println("Nome inválido!");
                return;
            }

            var autor = new Autor(nome);
            autorRepository.save(autor);

            System.out.println(">>> Autor: '" + autor.getNome() + "' cadastrado com ID: " + autor.getId());
        }

        private void listarAutores() {
            var autores = autorRepository.findAll();

            if (autores.isEmpty()) {
                System.out.println("Nenhum autor cadastrado. ");
                return;
            }
            System.out.println("\n--- LISTA DE AUTORES ---");
            autores.forEach(a -> System.out.printf("ID: %d | Nome: %s%n", a.getId(), a.getNome()));
            System.out.println("------------------------");
            }

                private void cadastrarLivro(Scanner scanner) {
                    listarAutores();
                    System.out.print("Informe o ID do autor do livro: "); 
                    var idStr = scanner.nextLine();

                    try {
                        var autorId = Long.parseLong(idStr); 
                        Optional<Autor> autorOpt = autorRepository.findById(autorId);

                    if (autorOpt.isEmpty()) { 
                        System.out.println("Autor não encontrado com o ID informado!"); 
                        return;
                }

                System.out.print("Digite o título do livro: "); 
                var titulo = scanner.nextLine();
                if (titulo.isBlank() || titulo.length() > 150) {
                    System.out.println("Título inválido!");
                    return;
                }

                System.out.print("Digite o ano de publicação: "); 
                var ano = Integer.parseInt(scanner.nextLine());

                if (ano < 1000 || ano > 2026) {
                    System.out.println("Ano de publicação inválido!");
                    return;
                }

                var livro = new Livro(titulo, ano, autorOpt.get()); 
                livroRepository.save(livro); 
                System.out.println(">>> Livro '" + livro.getTitulo() + "' cadastrado com sucesso!");
            } catch (NumberFormatException e) { 
                System.out.println("Valor numérico inválido informado.");
            }
        }

        private void listarLivros() {
            var livros = livroRepository.findAll();

            if (livros.isEmpty()) {
                System.out.println("Nenhum livro cadastrado.");
                return;
            }
            System.out.println("\n--- LISTA DE LIVROS ---");
            livros.forEach(l -> System.out.printf("ID: %d | Título: %s | Ano: %d | Autor: %s%n",
            l.getId(), l.getTitulo(), l.getAnoPublicacao(), l.getAutor().getNome()));
            System.out.println("-----------------------");
          }

        // Consulta autores pelo nome informado e exibe os resultados.
        private void pesquisarAutores(Scanner scanner) {
            System.out.println("Digite o nome do autor para pesquisa:");
            var nome = scanner.nextLine();

            var autores = autorRepository.findByNomeContainingIgnoreCase(nome);
            if (autores.isEmpty()) {
                System.out.println("Nenhum autor encontrado com o nome informado.");
                return;
            }
            System.out.println("\n--- Autores encontrados ---");
            autores.forEach(e -> System.out.printf("ID: %d | Nome: %s%n", e.getId(),
             e.getNome()));
            System.out.println("----------------------------");
        }

        // Consulta livros pelo titulo informado.
        private void pesquisarLivros(Scanner scanner) {
            System.out.println("Digite o título do livro para pesquisar:");
            var titulo = scanner.nextLine();

            var livros = livroRepository.findByTituloContainingIgnoreCase(titulo);

            if (livros.isEmpty()) {
                System.out.println("Nenhum livro encontrado com o título informado.");
                return;
            }
            System.out.println("\n--- Livros encontrados ---");
            livros.forEach(e -> System.out.printf("ID: %d | Título: %s | Ano: %d%n", e.getId(),
             e.getTitulo(),
             e.getAnoPublicacao()
            ));

             System.out.println("----------------------------");
        }

        // Lista somente os livros associados ao autor escolhido.
        private void listarLivrosPorAutor(Scanner scanner) {
            listarAutores();

            System.out.println("Digite o ID do autor para listar seus livros:");
            var idstr = scanner.nextLine();

            try {
                var autorId = Long.parseLong(idstr);
                var livros = livroRepository.findByAutorId(autorId);

                if (livros.isEmpty()) {
                    System.out.println("Nenhum livro encontrado para o autor informado.");
                    return;
                }
                System.out.println("\n--- Livros do autor ---");
                livros.forEach(e -> System.out.printf("ID: %d | Titulo: %s | Ano: %d%n",
                    e.getId(),
                    e.getTitulo(),
                    e.getAnoPublicacao()
                ));
            } catch (NumberFormatException e) {
                System.out.println("Valor numérico inválido informado.");
            }
        }

        // Remove o autor encontrado pelo id informado.
        private void excluirAutor(Scanner scanner) {
            listarAutores();

            System.out.println("Digite o ID do autor para excluir:");
            var idstr = scanner.nextLine();

            try {
                var autorId = Long.parseLong(idstr);
                Optional<Autor> autorOpt = autorRepository.findById(autorId);

                if (autorOpt.isEmpty()) {
                    System.out.println("Autor não encontrado com o ID informado.");
                    return;
                }else {
                    autorRepository.delete(autorOpt.get());
                    System.out.println("Autor excluído com sucesso.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Valor numérico inválido informado.");
            }
        }

        // Remove o livro encontrado pelo id informado.
        private void excluirLivro(Scanner scanner) {
            listarLivros();

            System.out.println("Digite o ID do livro para excluir:");
            var idstr = scanner.nextLine();

            try {
                var livroId = Long.parseLong(idstr);
                Optional<Livro> livroOpt = livroRepository.findById(livroId);
                if (livroOpt.isPresent()) {
                    livroRepository.delete(livroOpt.get());
                    System.out.println("Livro excluído com sucesso.");
                } else {
                    System.out.println("Livro não encontrado com o ID informado.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Valor numérico inválido informado.");
            }
        }
    }