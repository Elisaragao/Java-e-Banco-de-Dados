import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;

//ESSA CLASSE REPRESENTA UM LIVRO GENÉRICO
public class Livro {
    //AQUI EU JÁ DEFINI OS ATRIBUTOS DO LIVRO
    String titulo;
    String autor;
    String isbn;
    String genero;
    String status;
    //AQUI EU USEI UM CONSTRUTOR PARA QUE OS ATRIBUTOS SEJAM INICIADOS QUANDO EU CRIAR UM NOVO OBJETO LIVRO
    Livro(String titulo, String autor, String genero, String isbn)
    {
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.isbn = isbn;
        this.status = "disponível";
    }
    // DAO
    public void inserir() {
        String sql = "INSERT INTO livros (titulo, autor, genero, isbn, status) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = Conexao.getConexao();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, titulo);
            pstmt.setString(2, autor);
            pstmt.setString(3, genero);
            pstmt.setString(4, isbn);
            pstmt.setString(5, status);
            pstmt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            Conexao.tentarRollback();
        }
    }
    public static Livro buscarIsbn(String isbn) {
        String sql = "SELECT * FROM livros WHERE isbn = ?";

        try {
            Connection conn = Conexao.getConexao();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, isbn);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            return Livro.fromResultSet(rs);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public void atualizarStatus(String novoStatus) {
        String sql = "UPDATE livros SET status = ? WHERE isbn = ?";
        try (Connection conn = Conexao.getConexao();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, novoStatus);
            pstmt.setString(2, isbn);
            pstmt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            Conexao.tentarRollback();
        }
    }
    public static ArrayList<Livro> listar() {
        ArrayList<Livro> livros = new ArrayList<>();
        String sql = "SELECT * FROM livros";
        try (Connection conn = Conexao.getConexao();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Livro livro = Livro.fromResultSet(rs);
                livro.status = rs.getString("status");
                livros.add(livro);
            }
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            Conexao.tentarRollback();
        }
        return livros;
    }
    private static Livro fromResultSet(ResultSet rs) throws SQLException {
        return new Livro(
            rs.getString("titulo"),
            rs.getString("autor"),
            rs.getString("genero"),
            rs.getString("isbn")
        );
    }
}
