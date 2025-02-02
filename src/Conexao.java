import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Conexao {
    
    private static final String url = "jdbc:mysql://localhost:3306/biblioteca";
    private static final String user = "root";
    private static final String password = "";

    private static Connection conn;

    public static Connection getConexao(){
        try{
            if(conn == null || conn.isClosed()){
                conn = DriverManager.getConnection(url, user, password);
            }
            return conn;
        } catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public static void inserirLivro(Livro livro) {
        String sql = "INSERT INTO livros (titulo, autor, genero, isbn, status) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = getConexao();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, livro.titulo);
            pstmt.setString(2, livro.autor);
            pstmt.setString(3, livro.genero);
            pstmt.setString(4, livro.isbn);
            pstmt.setString(5, livro.status);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Livro> listarLivros() {
        ArrayList<Livro> livros = new ArrayList<>();
        String sql = "SELECT * FROM livros";
        try (Connection conn = getConexao();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Livro livro = new Livro(
                    rs.getString("titulo"),
                    rs.getString("autor"),
                    rs.getString("genero"),
                    rs.getString("isbn")
                );
                livro.status = rs.getString("status");
                livros.add(livro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return livros;
    }

    public static void atualizarStatusLivro(String isbn, String novoStatus) {
        String sql = "UPDATE livros SET status = ? WHERE isbn = ?";
        try (Connection conn = getConexao();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, novoStatus);
            pstmt.setString(2, isbn);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
