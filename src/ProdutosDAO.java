
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

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
            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao salvar produto: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar:");
        }

    }

    public List<ProdutosDTO> listarProdutos() {
        String sql = "select * from produtos";

        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            List<ProdutosDTO> lista = new ArrayList<>();
            //verificar se a consulta encontrou o funcionário com a matrícula informada
            while (rs.next()) { // se encontrou o funcionário, vamos carregar os dados
                ProdutosDTO p = new ProdutosDTO();
                p.setId(rs.getInt("ID"));
                p.setNome(rs.getString("Nome"));
                p.setValor(rs.getInt("Valor"));
                p.setStatus(rs.getString("Status"));
                lista.add(p);
            }
            return lista;
        } catch (SQLException ex) {
            System.out.println("Erro ao conectar no listarProdutos: " + ex.getMessage());
            return null;
        }
    }

    public void venderProduto(int id) {
        //string sql com o código de update para o banco de dados
        String sql = "UPDATE produtos SET status=? WHERE id=?";
        try {
            //esse trecho é igual ao método inserir
            PreparedStatement stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ProdutosDTO p = new ProdutosDTO();
            //Setando os parâmetros
            stmt.setString(1, "Vendido");
            stmt.setInt(2, id);
            //Executando a query
            stmt.execute();
            //tratando o erro, caso ele ocorra
        } catch (Exception e) {
            System.out.println("erro no vender produto: " + e.getMessage());
        }
    }

    public ProdutosDTO listarProdutosVendidos(String status) {
        String sql = "SELECT * FROM produtos WHERE status LIKE ?";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            status = "vendido";
            stmt.setString(1, status);

            ResultSet rs = stmt.executeQuery();

            ProdutosDTO p = new ProdutosDTO();
            rs.next();

            p.setId(rs.getInt("ID"));
            p.setNome(rs.getString("Nome"));
            p.setValor(rs.getInt("Valor"));
            p.setStatus(rs.getString(status));
            p.setStatus(rs.getString("Status"));

            return p;

            //tratando o erro, caso ele ocorra
        } catch (Exception e) {
            System.out.println("erro: " + e.getMessage());
            return null;
        }
    }

}
