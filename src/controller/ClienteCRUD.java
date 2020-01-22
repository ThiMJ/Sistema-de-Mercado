package controller;

import model.Clientes;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.util.ArrayList;

/**
 *
 * @author thiago
 */
public class ClienteCRUD {

    Conexao con = new Conexao();

    public void cadastrarCli(Clientes cli) {

        try {
            con.conectar();

            String sql = "insert into clientes(idCli,nome,cpf,rg,email,dataNasc,status,idEndereco) "
                    + "values(?,?,?,?,?,?,?,?)";

            PreparedStatement stmt = con.conex.prepareStatement(sql);
            stmt.setInt(1, cli.getId());
            stmt.setString(2, cli.getNome());
            stmt.setString(3, cli.getCpf());
            stmt.setString(4, cli.getRg());
            stmt.setString(5, cli.getEmail());
            stmt.setString(6, cli.getDataNasc());
            stmt.setInt(7, cli.getStatus());
            stmt.setInt(8, cli.getIdEndereco());

            stmt.execute();
            stmt.close();
            con.desconectar();

            JOptionPane.showMessageDialog(null, "Cliente Cadastrado com Sucesso");

        } catch (SQLException erro) {
            System.out.println("Erro de Cadastramento de Cliente: " + erro);
        }
    }

    public void atualizarCli(Clientes cli) {

        try {
            con.conectar();

            String sql = "update clientes set cpf = ?, rg = ?, nome = ?, email = ?, dataNasc = ?, status = ? "
                    + "where idCli = ?";

            PreparedStatement stmt = con.conex.prepareStatement(sql);
            stmt.setString(1, cli.getCpf());
            stmt.setString(2, cli.getRg());
            stmt.setString(3, cli.getNome());
            stmt.setString(4, cli.getEmail());
            stmt.setString(5, cli.getDataNasc());
            stmt.setInt(6, cli.getStatus());
            stmt.setInt(7, cli.getId());

            stmt.execute();
            stmt.close();
            con.desconectar();

            JOptionPane.showMessageDialog(null, "Cadastro de Cliente Atualizado com Sucesso");

        } catch (SQLException erro) {
            System.out.println("Erro de Atualização de Cliente: " + erro);
        }
    }

    public void excluirCli(int id) {

        try {
            con.conectar();

            String sql = "delete from clientes where idCli = ?";

            PreparedStatement stmt = con.conex.prepareStatement(sql);
            stmt.setInt(1, id);

            stmt.execute();
            stmt.close();
            con.desconectar();

            JOptionPane.showMessageDialog(null, "Cadastro de Cliente Excluído com Sucesso");

        } catch (SQLException erro) {
            System.out.println("Erro de exclusão de cliente: " + erro);
        }
    }

    public ArrayList listarClientes() { //lista todos os clientes
        ArrayList<Clientes> clientes = new ArrayList<>();

        try {
            con.conectar();

            String sql = "select * from clientes";

            PreparedStatement stmt = con.conex.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Clientes cli = new Clientes();

                cli.setId(rs.getInt("idCli"));
                cli.setCpf(rs.getString("cpf"));
                cli.setRg(rs.getString("rg"));
                cli.setNome(rs.getString("nome"));
                cli.setEmail(rs.getString("email"));
                cli.setDataNasc(rs.getString("dataNasc"));
                cli.setStatus(rs.getInt("status"));
                cli.setIdEndereco(rs.getInt("idEndereco"));

                clientes.add(cli);
            }

            stmt.close();
            rs.close();
            con.desconectar();

        } catch (SQLException erro) {
            System.out.println("Erro de Listar Clientes: " + erro);
        }

        return clientes;
    }

    public Clientes pesquisarCli(int id) { //utilizado para pesquisar um cliente específico

        Clientes cli = new Clientes();

        try {
            con.conectar();

            String sql = "select * from clientes where idCli = ?";

            PreparedStatement stmt = con.conex.prepareStatement(sql);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                cli.setId(rs.getInt("idCli"));
                cli.setNome(rs.getString("nome"));
                cli.setCpf(rs.getString("cpf"));
                cli.setDataNasc(rs.getString("dataNasc"));
                cli.setEmail(rs.getString("email"));
                cli.setIdEndereco(rs.getInt("idEndereco"));
                cli.setRg(rs.getString("rg"));
                cli.setStatus(rs.getInt("status"));
            }

            stmt.close();
            rs.close();
            con.desconectar();

        } catch (SQLException erro) {
            System.out.println("Erro Pesquisar Cliente: " + erro);
        }
        return cli;
    }

    public int buscarIdMax() { //utilizado para buscar qual o id Máximo
        int id = 0;

        try {
            con.conectar();

            String sql = "select max(idCli) from clientes";

            PreparedStatement stmt = con.conex.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                id = rs.getInt(1); //busca na 1 coluna
            }

            stmt.close();
            rs.close();
            con.desconectar();

        } catch (SQLException erro) {
            System.out.println("Erro Buscar Máximo Id: " + erro);
        }

        return id;
    }

    public boolean buscarId(int id) { //utilizado para verificar se tem um id no banco com o mesmo valor do id passado como parâmetro
                                      //utilizado para ver se vai adicionar um novo cliente ou atualizar um existente
        boolean achou = false;

        try {
            con.conectar();

            String sql = "select idCli from clientes";

            PreparedStatement stmt = con.conex.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                if (id == rs.getInt("idCli")) {
                    achou = true;
                }
            }

            stmt.close();
            rs.close();
            con.desconectar();

        } catch (SQLException erro) {
            System.out.println("Erro Buscar Id: " + erro);
        }

        return achou;
    }
}
