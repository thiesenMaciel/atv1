
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProdutosDAO {

    private conectaDAO conecta;
    private Connection conn;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();

    public ProdutosDAO() {
        this.conecta = new conectaDAO();
        this.conn = this.conecta.connectDB();
    }

    public void cadastrarProduto(ProdutosDTO p) {
        conn = new conectaDAO().connectDB();

        String sql = "INSERT INTO produtos ( nome, valor, status)VALUES(?,?,?)";
        try {
            PreparedStatement prep = this.conn.prepareStatement(sql);
            //prep.setInt(1, p.getId());
            prep.setString(1, p.getNome());
            prep.setInt(2, p.getValor());
            prep.setString(3, p.getStatus());
            prep.executeUpdate();
        } catch (Exception e) {
            System.out.println("Erro ao salvar produto: " + e.getMessage());
        }

    }

    public ArrayList<ProdutosDTO> listarProdutos() {

        return listagem;
    }

}
