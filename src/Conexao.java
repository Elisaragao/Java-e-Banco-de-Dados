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
                conn.setAutoCommit(false);
            }
            return conn;
        } catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }
    public static void tentarRollback() {
        try {
            Conexao.getConexao().rollback();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}

