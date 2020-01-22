package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import model.Funcionarios;
import java.util.ArrayList;
/**
 *
 * @author thiago
 */
public class FuncionariosCRUD {
    
    Conexao con = new Conexao();
    
    public void cadastrarFunc(Funcionarios func){
        
        try{
            con.conectar();
            
            String sql = "insert into funcionarios(idFunc, nome, rg, cpf, dataNasc, salario, admissao, login,"
                    + "senha, idEndereco, idCargo) values(?,?,?,?,?,?,?,?,?,?,?)";
            
            PreparedStatement stmt = con.conex.prepareStatement(sql);
            stmt.setInt(1, func.getId());
            stmt.setString(2, func.getNome());
            stmt.setString(3, func.getRg());
            stmt.setString(4, func.getCpf());
            stmt.setString(5, func.getDataNasc());
            stmt.setDouble(6, func.getSalario());
            stmt.setString(7, func.getAdmissao());
            stmt.setString(8, func.getLogin());
            stmt.setString(9, func.getSenha());
            stmt.setInt(10, func.getIdEndereco());
            stmt.setInt(11, func.getIdCargo());
            
            stmt.execute();
            stmt.close();
            con.desconectar();
            
            JOptionPane.showMessageDialog(null, "Funcionário Cadastrado com Sucesso");
            
        }catch(SQLException erro){
            System.out.println("Erro de Cadastramento de Funcionário: " + erro);
        }
        
    }
    
    public void atualizarFunc(Funcionarios func){ //esse método não atualiza a senha. Para a senha há um método a parte 
        
        try{
            con.conectar();
            
            String sql = "update funcionarios set nome = ?, rg = ?, cpf = ?, dataNasc = ?, salario = ?, "
                    + "admissao = ?, login = ?, idCargo = ? where idFunc = ?";
            
            PreparedStatement stmt = con.conex.prepareStatement(sql);
            stmt.setString(1, func.getNome());
            stmt.setString(2, func.getRg());
            stmt.setString(3, func.getCpf());
            stmt.setString(4, func.getDataNasc());
            stmt.setDouble(5, func.getSalario());
            stmt.setString(6, func.getAdmissao());
            stmt.setString(7, func.getLogin());
            stmt.setInt(8, func.getIdCargo());
            stmt.setInt(9, func.getId());
            
            stmt.execute();
            stmt.close();
            con.desconectar();
            
            JOptionPane.showMessageDialog(null, "Cadastro de Funcionário Atualizado com Sucesso");
            
        }catch(SQLException erro){
            System.out.println("Erro de Atualização de Funcionário: " + erro);
        }
    }
    
    public void excluirFunc(int id){
        
        try{
            con.conectar();
            
            String sql = "delete from funcionarios where idFunc = ?";
            
            PreparedStatement stmt = con.conex.prepareStatement(sql);
            stmt.setInt(1, id);
            
            stmt.execute();
            stmt.close();
            con.desconectar();
            
            JOptionPane.showMessageDialog(null, "Cadastro de Funcionário Excluído com Sucesso");
            
        }catch(SQLException erro){
            System.out.println("Erro de Exclusão de Funcionário: " + erro);
        }
    }
    
    public ArrayList listarFuncs(){ //lista todos os funcionários
        ArrayList<Funcionarios> funcs = new ArrayList();
        
        try{
            con.conectar();
            
            String sql = "select * from funcionarios";
            
            PreparedStatement stmt = con.conex.prepareStatement(sql);
            
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Funcionarios func = new Funcionarios();
                
                func.setAdmissao(rs.getString("admissao"));
                func.setCpf(rs.getString("cpf"));
                func.setDataNasc(rs.getString("dataNasc"));
                func.setId(rs.getInt("idFunc"));
                func.setIdCargo(rs.getInt("idCargo"));
                func.setIdEndereco(rs.getInt("idEndereco"));
                func.setLogin(rs.getString("login"));
                func.setNome(rs.getString("nome"));
                func.setRg(rs.getString("rg"));
                func.setSalario(rs.getDouble("salario"));
                
                funcs.add(func);
            }
            
            stmt.close();
            rs.close();
            con.desconectar();
            
        }catch(SQLException erro){
            System.out.println("Erro de Listar Funcionários: " + erro);
        }
        
        return funcs;
    }
    
    public Funcionarios pesquisarFunc(int id){ //pequisa por um funcionário específico por id
        
        Funcionarios func = new Funcionarios();
        
        try{
            con.conectar();
            
            String sql = "select * from funcionarios where idFunc = ?";
            
            PreparedStatement stmt = con.conex.prepareStatement(sql);
            stmt.setInt(1, id);
            
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                func.setAdmissao(rs.getString("admissao"));
                func.setCpf(rs.getString("cpf"));
                func.setDataNasc(rs.getString("dataNasc"));
                func.setId(rs.getInt("idFunc"));
                func.setIdCargo(rs.getInt("idCargo"));
                func.setIdEndereco(rs.getInt("idEndereco"));
                func.setLogin(rs.getString("login"));
                func.setNome(rs.getString("nome"));
                func.setRg(rs.getString("rg"));
                func.setSalario(rs.getDouble("salario"));
            }
            
            stmt.close();
            rs.close();
            con.desconectar();
            
        }catch(SQLException erro){
            System.out.println("Erro de busca de funcionário especifico: " + erro);
        }
        
        return func;        
    }
    
    public void atualizarSenha(int id, String senha){ //atualiza senha do funcionário
        
        try{
            con.conectar();
            
            String sql = "update funcionarios set senha = ? where idFunc = ?";
            
            PreparedStatement stmt = con.conex.prepareStatement(sql);
            
            stmt.setString(1, senha);
            stmt.setInt(2, id);
            
            stmt.execute();
            stmt.close();
            con.desconectar();
            
        }catch(SQLException erro){
            System.out.println("Erro de atualização de senha: " + erro);
        }
    }
    
    public int buscarIdMax(){ //buscar o último id
        int id = 0;
        
        try{
            con.conectar();
            
            String sql = "select max(idFunc) from funcionarios";
            
            PreparedStatement stmt = con.conex.prepareStatement(sql);
            
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                id = rs.getInt(1);
            }
            
            stmt.close();
            rs.close();
            con.desconectar();
            
        }catch(SQLException erro){
            System.out.println("Erro de Busca Máximo Id: " + erro);
        }
        
        return id;
    }
    
    public boolean buscarId(int id){ //verifica se tem um id no db igual o passado
                                    //para ver se vai atualizar ou cadastrar um novo
        boolean achou = false;
        
        try{
            con.conectar();
            
            String sql = "select idFunc from funcionarios";
            
            PreparedStatement stmt = con.conex.prepareStatement(sql);
            
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                if(id == rs.getInt("idFunc")){
                    achou = true;
                }
            }
            
            stmt.close();
            rs.close();
            con.desconectar();
            
        }catch(SQLException erro){
            System.out.println("Erro de busca de id: " + erro);
        }
        
        return achou;
    }
}
