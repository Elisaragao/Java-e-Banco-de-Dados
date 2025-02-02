import java.util.ArrayList;
import java.util.Scanner;

public class ConsultarStatus {
    static void consulta(Scanner leitura) {
        System.out.println("Digite o título do livro para consulta:");
        String titulo = leitura.nextLine();
        ArrayList<Livro> livros = Conexao.listarLivros();
        boolean livroEncontrado = false;

        for (Livro livro : livros) {
            if (livro.titulo.equalsIgnoreCase(titulo)) {
                livroEncontrado = true;
                System.out.println("Status do livro '" + titulo + "' é " + livro.status);
                return;
            }
        }

        if (!livroEncontrado) {
            System.out.println("Livro não encontrado!");
        }
    }
}
