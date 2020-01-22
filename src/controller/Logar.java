package controller;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import model.Funcionarios;
import java.sql.ResultSet;

/**
 *
 * @author thiago
 */
public class Logar {

    Conexao con = new Conexao();

    public Funcionarios login(Funcionarios func) {

        Funcionarios fun = new Funcionarios();

        try {
            con.conectar();

            String sql = "SELECT * FROM funcionarios WHERE login = ? AND senha = ?";

            PreparedStatement stmt = con.conex.prepareStatement(sql);

            stmt.setString(1, func.getLogin());
            stmt.setString(2, func.getSenha());

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                fun.setId(rs.getInt("idFunc"));
                fun.setNome(rs.getString("nome"));
                fun.setRg(rs.getString("rg"));
                fun.setCpf(rs.getString("cpf"));
                fun.setDataNasc(rs.getString("dataNasc"));
                fun.setSalario(rs.getDouble("salario"));
                fun.setAdmissao(rs.getString("admissao"));
                fun.setLogin(rs.getString("login"));
                fun.setSenha(rs.getString("senha"));
                fun.setIdEndereco(rs.getInt("idEndereco"));
                fun.setIdCargo(rs.getInt("idCargo"));
            }

            stmt.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro de Login: " + ex.getMessage());
        }

        return fun;
    }
}
