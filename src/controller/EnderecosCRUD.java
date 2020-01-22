package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Enderecos;
/**
 *
 * @author thiago
 */
public class EnderecosCRUD {
    
    Conexao con = new Conexao();
    
    public void cadastrarEnd(Enderecos end){
        
        try{
            con.conectar();
            
            String sql = "insert into enderecos(idEndereco,logradouro,num,complemento,bairro,cidade,cep) "
                    + "values(?,?,?,?,?,?,?)";
            
            PreparedStatement stmt = con.conex.prepareStatement(sql);
            stmt.setInt(1, end.getIdEndereco());
            stmt.setString(2, end.getLogradouro());
            stmt.setInt(3, end.getNum());
            stmt.setString(4, end.getComplemento());
            stmt.setString(5, end.getBairro());
            stmt.setString(6, end.getCidade());
            stmt.setString(7, end.getCep());
            
            stmt.execute();
            stmt.close();
            con.desconectar();
            
        }catch(SQLException erro){
            System.out.println("Erro de Cadastramento de Endereco: " + erro);
        }
    }
    
    public void atualizarEnd(Enderecos end){
        
        try{
            con.conectar();
            
            String sql = "update enderecos set logradouro = ?, num = ?, complemento = ?, bairro = ?, cidade = ?, "
                    + "cep = ? where idEndereco = ?";
            
            PreparedStatement stmt = con.conex.prepareStatement(sql);
            stmt.setString(1, end.getLogradouro());
            stmt.setInt(2, end.getNum());
            stmt.setString(3, end.getComplemento());
            stmt.setString(4, end.getBairro());
            stmt.setString(5, end.getCidade());
            stmt.setString(6, end.getCep());
            stmt.setInt(7, end.getIdEndereco());
            
            stmt.execute();
            stmt.close();
            con.desconectar();
            
        }catch(SQLException erro){
            System.out.println("Erro de Atualização de Endereço: " + erro);
        }
    }
    
    public void excluirEnd(int id){
        
        try{
            con.conectar();
            
            String sql = "delete from enderecos where idEndereco = ?";
            
            PreparedStatement stmt = con.conex.prepareStatement(sql);
            stmt.setInt(1, id);
            
            stmt.execute();
            stmt.close();
            con.desconectar();
            
        }catch(SQLException erro){
            System.out.println("Erro de Exclusão de Endereço: " + erro);
        }
    }
        
    public Enderecos pesquisarEnd(int id){ //pesquisa um endereco especifico
        String ident = String.valueOf(id);
        
        Enderecos end = new Enderecos();
        
        try{
            con.conectar();
            
            String sql = "select * from enderecos where idEndereco = ?";
            
            PreparedStatement stmt = con.conex.prepareStatement(sql);   
            stmt.setString(1, ident);
            
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                end.setIdEndereco(rs.getInt("idEndereco"));
                end.setCep(rs.getString("cep"));
                end.setCidade(rs.getString("cidade"));
                end.setComplemento(rs.getString("complemento"));
                end.setLogradouro(rs.getString("logradouro"));
                end.setNum(rs.getInt("num"));
                end.setBairro(rs.getString("bairro"));
            }
            
            stmt.close();
            rs.close();
            con.desconectar();
            
        }catch(SQLException erro){
            System.out.println("Erro Pesquisar Endereco: " + erro);
        }
        
        return end;
    }
    
    public int buscarIdMax(){ //busca o valor máximo id do endereco
        int idEnd = 0;
        
        try{
            con.conectar();
            
            String sql = "select max(idEndereco) from enderecos";
            
            PreparedStatement stmt = con.conex.prepareStatement(sql);   

            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                idEnd = rs.getInt(1);
            }
            
            stmt.close();
            rs.close();
            con.desconectar();
            
        }catch(SQLException erro){
            System.out.println("Erro Buscar Maximo Id Endereco: " + erro);
        }
        
        return idEnd;
    }
}
