import java.util.ArrayList;
public class LivroListar {
    static void listarLivros() {
        ArrayList<Livro> livros = Livro.listar();
        for (Livro livro : livros) {
            System.out.println(
                "Título: " + livro.titulo
                + ", Autor: " + livro.autor
                + ", Gênero: " + livro.genero
                + ", ISBN: " + livro.isbn
                + ", Status: " + livro.status
            );
        }
    }
}
