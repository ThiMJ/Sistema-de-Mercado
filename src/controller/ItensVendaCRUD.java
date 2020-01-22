package controller;

import model.ItensVenda;
import model.Produtos;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.text.DecimalFormat;

/**
 *
 * @author thiago
 */
public class ItensVendaCRUD {

    Conexao con = new Conexao();

    ArrayList<ItensVenda> itens = new ArrayList<>(); //array com os itens
    ArrayList<Produtos> prods = new ArrayList<>(); //array com os produtos
    
    DecimalFormat df = new DecimalFormat("0.00");

    public void adicionarItem(Produtos prod, int qtd, int idVenda) { //adiciona os itens em um Array
        ItensVenda item = new ItensVenda();

        item.setIdProd(prod.getIdProd());
        item.setIdVenda(idVenda);
        item.setQtd(qtd);
        item.setVlrUn(prod.getVlrUnVenda());
        item.setVlrTotal(Double.parseDouble(df.format(qtd * prod.getVlrUnVenda()).replace(",", ".")));

        itens.add(item);
        prods.add(prod);
    }

    public void excluirItem(int ind) { //exclui um item do array
        itens.remove(ind);
        prods.remove(ind);
    }

    public void excluirTodosItens() {
        itens.clear();
        prods.clear();
    }

    public void salvarItens(ArrayList<ItensVenda> itens) { //salva no BD os itens da venda
        try {
            con.conectar();

            String sql = "insert into itens_venda(qtd, vlrUnt, vlrTotal, idProd, idVenda) values(?,?,?,?,?)";

            PreparedStatement stmt = con.conex.prepareStatement(sql);

            for (int i = 0; i < itens.size(); i++) {
                stmt.setDouble(1, itens.get(i).getQtd());
                stmt.setDouble(2, itens.get(i).getVlrUn());
                stmt.setDouble(3, itens.get(i).getVlrTotal());
                stmt.setInt(4, itens.get(i).getIdProd());
                stmt.setInt(5, itens.get(i).getIdVenda());

                stmt.execute();
            }

            stmt.close();
            con.desconectar();

            JOptionPane.showMessageDialog(null, "Venda realizada com sucesso!!!");

        } catch (SQLException erro) {
            System.out.println("Erro de salvamento de Itens: " + erro);
        }
    }

    public ArrayList listarItensVenda(int id) { //lista todos os itens de uma venda específica
        ArrayList<ItensVenda> itensVenda = new ArrayList<>();

        try {
            con.conectar();

            String sql = "select * from itens_venda where idVenda = ?";

            PreparedStatement stmt = con.conex.prepareStatement(sql);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ItensVenda item = new ItensVenda();

                item.setIdProd(rs.getInt("idProd"));
                item.setIdVenda(rs.getInt("idVenda"));
                item.setQtd(rs.getDouble("qtd"));
                item.setVlrTotal(rs.getDouble("vlrTotal"));
                item.setVlrUn(rs.getDouble("vlrUnt"));

                itensVenda.add(item);
            }

            stmt.close();
            rs.close();
            con.desconectar();

        } catch (SQLException erro) {
            System.out.println("Erro de busca de itens de uma venda: " + erro);
        }

        return itensVenda;
    }

    public ArrayList produtosItens() { //retorna o array de produtos adicionados como itens
        return this.prods;
    }

    public ArrayList itens() { //retorna o array dos itens
        return this.itens;
    }

    public double valorTotalItens() { //calcula o valor total dos itens
        double vlrTotal = 0;

        for (int i = 0; i < itens.size(); i++) {
            vlrTotal += itens.get(i).getVlrTotal();
        }

        return vlrTotal;
    }
}
