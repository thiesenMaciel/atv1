
import java.sql.Connection;
import java.sql.DriverManager;

public class conectaDAO {

    public Connection connectDB() {
        try {
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost/ uc11 ", // linha de conexao
                    "root", // usuario do mysql
                    "Isis2022"// senha do mysql
            );
            return conn;

        } catch (Exception e) {
            System.out.println("Erro ao conectar: " + e.getMessage());
            return null;
        }

    }

}
