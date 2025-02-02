import java.util.ArrayList;
import java.util.Scanner;

public class LivroEmprestar {
    static void emprestar(Scanner leitura) {
        System.out.print("Digite o ISBN do livro: ");
        String isbn = leitura.nextLine();
        ArrayList<Livro> livros = Conexao.listarLivros();
        boolean livroEncontrado = false;

        for (Livro livro : livros) {
            if (livro.isbn.equals(isbn)) {
                livroEncontrado = true;
                if (livro.status.equals("disponível")) {
                    // Atualiza o status do livro para "emprestado"
                    Conexao.atualizarStatusLivro(isbn, "emprestado");
                    System.out.println("Empréstimo realizado com sucesso!");
                    return;
                } else {
                    System.out.println("Livro não disponível para empréstimo.");
                    return;
                }
            }
        }
        if (!livroEncontrado) {
            System.out.println("Livro não encontrado!");
        }
    }
}
