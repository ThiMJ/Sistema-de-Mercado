package controller;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import model.Produtos;
import java.util.ArrayList;
import model.ItensVenda;

/**
 *
 * @author thiago
 */
public class ProdutoCRUD {

    Conexao con = new Conexao();

    public void cadastrarprod(Produtos prod) {

        try {
            con.conectar();

            String sql = "insert into produtos(idProd, codProd, nomeProd, descProd, vlrUnVenda, "
                    + "vlrUnCompra, qtdEstoque) values (?,?,?,?,?,?,?)";

            PreparedStatement stmt = con.conex.prepareStatement(sql);
            stmt.setInt(1, prod.getIdProd());
            stmt.setString(2, prod.getCodProd());
            stmt.setString(3, prod.getNomeProd());
            stmt.setString(4, prod.getDescProd());
            stmt.setDouble(5, prod.getVlrUnVenda());
            stmt.setDouble(6, prod.getVlrUnCompra());
            stmt.setInt(7, prod.getQtdEstoque());

            stmt.execute();
            stmt.close();
            con.desconectar();

            JOptionPane.showMessageDialog(null, "Produto Cadastrado com Sucesso");

        } catch (SQLException erro) {
            System.out.println("Erro de Cadastramento de Produto: " + erro);
        }
    }

    public void atualizarProd(Produtos prod) {

        try {
            con.conectar();

            String sql = "update produtos set codProd = ?, nomeProd = ?, descProd = ?, vlrUnVenda = ?, "
                    + "vlrUnCompra = ?, qtdEstoque = ? where idProd = ?";

            PreparedStatement stmt = con.conex.prepareStatement(sql);
            stmt.setString(1, prod.getCodProd());
            stmt.setString(2, prod.getNomeProd());
            stmt.setString(3, prod.getDescProd());
            stmt.setDouble(4, prod.getVlrUnVenda());
            stmt.setDouble(5, prod.getVlrUnCompra());
            stmt.setInt(6, prod.getQtdEstoque());
            stmt.setInt(7, prod.getIdProd());

            stmt.execute();
            stmt.close();
            con.desconectar();

            JOptionPane.showMessageDialog(null, "Produto Atualizado com Sucesso");

        } catch (SQLException erro) {
            System.out.println("Erro de Atualização de Produto: " + erro);
        }
    }

    public void excluirProd(int id) {

        try {
            con.conectar();

            String sql = "delete from produtos where idProd = ?";

            PreparedStatement stmt = con.conex.prepareStatement(sql);
            stmt.setInt(1, id);

            stmt.execute();
            stmt.close();
            con.desconectar();

            JOptionPane.showMessageDialog(null, "Produto Excluído com Sucesso");

        } catch (SQLException erro) {
            System.out.println("Erro de exclusão de produto: " + erro);
        }
    }

    public ArrayList listarProds() { //lista todos os produtos
        ArrayList<Produtos> prods = new ArrayList<>();

        try {
            con.conectar();

            String sql = "select * from produtos";

            PreparedStatement stmt = con.conex.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Produtos prod = new Produtos();

                prod.setCodProd(rs.getString("codProd"));
                prod.setDescProd(rs.getString("descProd"));
                prod.setIdProd(rs.getInt("idProd"));
                prod.setNomeProd(rs.getString("nomeProd"));
                prod.setQtdEstoque(rs.getInt("qtdEstoque"));
                prod.setVlrUnCompra(rs.getDouble("vlrUnCompra"));
                prod.setVlrUnVenda(rs.getDouble("vlrUnVenda"));

                prods.add(prod);
            }

            stmt.close();
            rs.close();
            con.desconectar();

        } catch (SQLException erro) {
            System.out.println("Erro de Pesquisar Produtos: " + erro);
        }

        return prods;
    }

    public Produtos pesquisarProd(int id) { //pesquisa por um produto específico pelo id

        Produtos prod = new Produtos();

        try {
            con.conectar();

            String sql = "select * from produtos where idProd = ?";

            PreparedStatement stmt = con.conex.prepareStatement(sql);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                prod.setCodProd(rs.getString("codProd"));
                prod.setDescProd(rs.getString("descProd"));
                prod.setIdProd(rs.getInt("idProd"));
                prod.setNomeProd(rs.getString("nomeProd"));
                prod.setQtdEstoque(rs.getInt("qtdEstoque"));
                prod.setVlrUnCompra(rs.getDouble("vlrUnCompra"));
                prod.setVlrUnVenda(rs.getDouble("vlrUnVenda"));
            }

            stmt.close();
            rs.close();
            con.desconectar();

        } catch (SQLException erro) {
            System.out.println("Erro de busca de produto: " + erro);
        }

        return prod;
    }

    public Produtos pesquisarCodProd(String cod) { //pesquisa um produto específico pelo código
        Produtos prod = new Produtos();

        try {
            con.conectar();

            String sql = "select * from produtos where codProd = ?";

            PreparedStatement stmt = con.conex.prepareStatement(sql);
            stmt.setString(1, cod);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                prod.setCodProd(rs.getString("codProd"));
                prod.setDescProd(rs.getString("descProd"));
                prod.setIdProd(rs.getInt("idProd"));
                prod.setNomeProd(rs.getString("nomeProd"));
                prod.setQtdEstoque(rs.getInt("qtdEstoque"));
                prod.setVlrUnCompra(rs.getDouble("vlrUnCompra"));
                prod.setVlrUnVenda(rs.getDouble("vlrUnVenda"));
            }

            stmt.close();
            rs.close();
            con.desconectar();

        } catch (SQLException erro) {
            System.out.println("Erro de busca de produto por código: " + erro);
        }

        return prod;
    }

    public void baixarEstoqueVenda(ArrayList<ItensVenda> itens) { //da baixa no estoque dos produtos da venda

        try {
            con.conectar();
            double qtdEstoque = 0;
            
            for (int i = 0; i < itens.size(); i++) {
                //pegando a quantidade que ja tem em estoque
                String sql = "select qtdEstoque from produtos where idProd = ?";
                
                PreparedStatement stmt = con.conex.prepareStatement(sql);
                stmt.setInt(1, itens.get(i).getIdProd());
                
                ResultSet rs = stmt.executeQuery();
                
                while(rs.next()){
                    qtdEstoque = rs.getDouble("qtdEstoque"); //armazena a quantidade que ja tem em estoque
                }
                
                //dando baixa no estoque
                sql = "update produtos set qtdEstoque = ? where idProd = ?";
                stmt = con.conex.prepareStatement(sql);
                stmt.setDouble(1, (qtdEstoque - itens.get(i).getQtd()));
                stmt.setInt(2, itens.get(i).getIdProd());
                
                stmt.execute();
                
                stmt.close();
                rs.close();
            }
            
            con.desconectar();
            
        } catch (SQLException erro) {
            System.out.println("Erro de Baixa de Estoque de Venda: " + erro);
        }
    }

    public int buscarIdMax() { //busca o último id
        int id = 0;

        try {
            con.conectar();

            String sql = "select max(idProd) from produtos";

            PreparedStatement stmt = con.conex.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                id = rs.getInt(1);
            }

            stmt.close();
            rs.close();
            con.desconectar();

        } catch (SQLException erro) {
            System.out.println("Erro de busca de Máximo Id: " + erro);
        }

        return id;
    }

    public boolean buscarId(int id) { //verifica se o id já está cadastrado ou não
        //para ver se vai atualizar ou cadastrar
        boolean achou = false;

        try {
            con.conectar();

            String sql = "select idProd from produtos";

            PreparedStatement stmt = con.conex.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                if (id == rs.getInt("idProd")) {
                    achou = true;
                }
            }

            stmt.close();
            rs.close();
            con.desconectar();

        } catch (SQLException erro) {
            System.out.println("Erro de Busca de Id: " + erro);
        }

        return achou;
    }
}
