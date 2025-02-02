import java.util.ArrayList;
import java.util.Scanner;

//ESSA CLASSE EXECUTA  O CADASTRO DE LIVROS
//PERMITE QUE O USUÁRIO INSIRA AS INFORMAÇÕES
//E ARMAZENE NO ARRAY livros
class LivroCadastro
{

static ArrayList<Livro> livros = new ArrayList<>();   

Scanner leitura = new Scanner(System.in);

static void cadastrarLivro(Scanner leitura) 
{ 
    System.out.print("Título: "); 
    String titulo = leitura.nextLine();
    //AQUI EU VERIFIQUEI SE O LIVRO JÁ FOI CADASTRADO ANTES  
    for (Livro livro : livros)
    {
        if(livro.titulo.equals(titulo)){
        System.out.println("Livro já cadastrado!");
        return;
        }
    }
    System.out.print("Autor: ");
    String autor = leitura.nextLine(); 
    System.out.print("Gênero: "); 
    String genero = leitura.nextLine(); 
    System.out.print("Código ISBN: "); 
    String isbn = leitura.nextLine(); 
    
    //DESSA FORMA, QUANDO EU CRIEI O OBJETO LIVRO, OS PARÂMETROS FORAM
    //PASSADOS COMO DEVERIAM SER. LEMBRA DO CONSTRUTOR DE Livro.java? AQUI ELE FOI ÚTIL
    livros.add(new Livro(titulo, autor, genero, isbn)); 
    System.out.print("Livro cadastrado com sucesso!"); 
    }
}
