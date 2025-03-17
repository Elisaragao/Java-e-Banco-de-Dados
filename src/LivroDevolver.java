import java.util.Scanner;
import java.util.ArrayList;
public class LivroDevolver {
    static void devolver(Scanner leitura) {
        System.out.print("Digite o ISBN do livro que deseja devolver: ");
        String isbn = leitura.nextLine();
        // Verifica se o livro está emprestado
        ArrayList<Livro> livros = Livro.listar();
        boolean livroEncontrado = false;
        for (Livro livro : livros) {
            if (livro.isbn.equals(isbn)) {
                livroEncontrado = true;
                if (livro.status.equals("emprestado")) {
                    // Atualiza o status do livro para "disponível"
                    Livro.buscarIsbn(isbn).atualizarStatus("disponível");
                    System.out.println("Devolução realizada com sucesso!");
                    return;
                } else {
                    System.out.println("O livro já está disponível.");
                    return;
                }
            }
        }
        if (!livroEncontrado) {
            System.out.println("Livro não encontrado!");
        }
    }
}
