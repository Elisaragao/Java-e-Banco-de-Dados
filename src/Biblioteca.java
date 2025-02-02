import java.util.Scanner;

public class Biblioteca {
    public static void main(String[] args) {
        Scanner leitura = new Scanner(System.in);   
        int opcao;
        do {
            System.out.println("Escolha uma opção: ");
            System.out.println("1. Cadastrar livro");
            System.out.println("2. Listar livros");
            System.out.println("3. Empréstimo de livro");
            System.out.println("4. Devolução de livro");
            System.out.println("5. Consultar status dos livros");
            System.out.println("6. Sair");
         
            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println("Digite o título do livro:");
                    String titulo = leitura.nextLine();
                    System.out.println("Digite o autor do livro:");
                    String autor = leitura.nextLine();
                    System.out.println("Digite o gênero do livro:");
                    String genero = leitura.nextLine();
                    System.out.println("Digite o ISBN do livro:");
                    String isbn = leitura.nextLine();
                    
                    Livro livro = new Livro(titulo, autor, genero, isbn);
                    Conexao.inserirLivro(livro);
                    System.out.println("Livro inserido com sucesso!");
                    break;

                case 2:
                    LivroListar.listarLivros();
                    break;

                case 3:
                    LivroEmprestar.emprestar(leitura);
                    break;

                case 4:
                    LivroDevolver.devolver(leitura);
                    break;

                case 5:
                    ConsultarStatus.consulta(leitura);
                    break;

                case 6:
                    System.out.println("Até mais");
                    break;

                default:
                    System.out.println("Número inválido!");
                    break;
            }
        } while (opcao != 6);
    }
}
