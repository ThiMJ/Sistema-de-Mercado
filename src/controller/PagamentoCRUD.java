package controller;

import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import model.Pagamentos;
import java.util.ArrayList;
/**
 *
 * @author thiago
 */
public class PagamentoCRUD {
    
    Conexao con = new Conexao();
    
    public void cadastrarPag(Pagamentos pag){
        
        try{
            con.conectar();
            
            String sql = "insert into pagamentos(idPag, formaPag) values(?,?)";
            
            PreparedStatement stmt = con.conex.prepareStatement(sql);
            stmt.setInt(1, pag.getIdPag());
            stmt.setString(2, pag.getFormaPag());
            
            stmt.execute();
            stmt.close();
            con.desconectar();
            
            JOptionPane.showMessageDialog(null, "Forma de Pagamento Cadastrada com Sucesso");
            
        }catch(SQLException erro){
            System.out.println("Erro de Cadastramento de Forma de Pagamento: " + erro);
        }
    }
    
    public void atualizarPag(Pagamentos pag){
        
        try{
            con.conectar();
            
            String sql = "update pagamentos set formaPag = ? where idPag = ?";
            
            PreparedStatement stmt = con.conex.prepareStatement(sql);
            stmt.setString(1, pag.getFormaPag());
            stmt.setInt(2, pag.getIdPag());
            
            stmt.execute();
            stmt.close();
            con.desconectar();
            
            JOptionPane.showMessageDialog(null, "Forma de Pagamento Atualizada com Sucesso");
            
        }catch(SQLException erro){
            System.out.println("Erro de atualização de forma de pagamento: " + erro);
        }
    }
    
    public void excluirPag(int id){
        
        try{
            con.conectar();
            
            String sql = "delete from pagamentos where idPag = ?";
            
            PreparedStatement stmt = con.conex.prepareStatement(sql);
            stmt.setInt(1, id);
            
            stmt.execute();
            stmt.close();
            con.desconectar();
            
            JOptionPane.showMessageDialog(null, "Forma de Pagamento Excluída com Sucesso");
            
        }catch(SQLException erro){
            System.out.println("Erro de Exclusão de Forma de Pagamento: " + erro);
        }
        
    }
    
    public ArrayList listarPags(){ //lista todas as formas de pagamento
        ArrayList<Pagamentos> pags = new ArrayList<>();
        
        try{
            con.conectar();
            
            String sql = "select * from pagamentos";
            
            PreparedStatement stmt = con.conex.prepareStatement(sql);
            
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Pagamentos pag = new Pagamentos();
                
                pag.setIdPag(rs.getInt("idPag"));
                pag.setFormaPag(rs.getString("formaPag"));
                
                pags.add(pag);
            }
            
            stmt.close();
            rs.close();
            con.desconectar();
            
        }catch(SQLException erro){
            System.out.println("Erro de Listar Formas de Pagamento: " + erro);
        }
        
        return pags;
    }
    
    public Pagamentos pesquisarPag(int id){ //pesquisa por uma forma de pagamento específica
        Pagamentos pag = new Pagamentos();
        
        try{
            con.conectar();
            
            String sql = "select * from pagamentos where idPag = ?";
            
            PreparedStatement stmt = con.conex.prepareStatement(sql);
            stmt.setInt(1, id);
            
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                pag.setIdPag(rs.getInt("idPag"));
                pag.setFormaPag(rs.getString("formaPag"));
            }
            
            stmt.close();
            rs.close();
            con.desconectar();
            
        }catch(SQLException erro){
            System.out.println("Erro de busca de forma de pagamento: " + erro);
        }

        return pag;
    }
    
    public int buscarIdMax(){ //busca o último id
        int id = 0;
        
        try{
            con.conectar();
            
            String sql = "select max(idPag) from pagamentos";
            
            PreparedStatement stmt = con.conex.prepareStatement(sql);
            
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                id = rs.getInt(1);
            }
            
            stmt.close();
            rs.close();
            con.desconectar();
            
        }catch(SQLException erro){
            System.out.println("Erro de busca de Máximo Id: " + erro);
        }
        
        return id;
    }
    
    public boolean buscarId(int id){ //verifica se o id já está cadastrado ou não
                                     //para atualizar ou cadastrar
        boolean achou = false;
        
        try{
            con.conectar();
            
            String sql = "select idPag from pagamentos";
            
            PreparedStatement stmt = con.conex.prepareStatement(sql);
            
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                if(id == rs.getInt("idPag")){
                    achou = true;
                }
            }
            
            stmt.close();
            rs.close();
            con.desconectar();
            
        }catch(SQLException erro){
            System.out.println("Erro de Busca de Id: " + erro);
        }
        
        return achou;
    }
}
