package controller;

import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.Vendas;
import java.util.ArrayList;

/**
 *
 * @author thiago
 */
public class VendaCRUD {
    
    Conexao con = new Conexao();
    
    public int buscarIdMax(){ //busca o último id
        int id = 0;
        
        try{
            con.conectar();
            
            String sql = "select max(idVenda) from vendas";
            
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
    
    public void salvarVenda(Vendas venda){
        
        try{
            con.conectar();
            
            String sql = "insert into vendas(idVenda, data, valorTotal, idFunc, idPag, idCli) values(?,?,?,?,?,?)";
            
            PreparedStatement stmt = con.conex.prepareStatement(sql);
            stmt.setInt(1, venda.getId());
            stmt.setString(2, venda.getData());
            stmt.setDouble(3, venda.getValorTotal());
            stmt.setInt(4, venda.getIdFunc());
            stmt.setInt(5, venda.getIdPag());
            stmt.setInt(6, venda.getIdCli());
            
            stmt.execute();
            stmt.close();
            con.desconectar();
            
        }catch(SQLException erro){
            System.out.println("Erro de salvamento de venda: " + erro);
        }
    }
    
    public ArrayList listarVendas(int formaPag, String dataInicial, String dataFinal){
        ArrayList<Vendas> vendas = new ArrayList<>();
        
        try{
            con.conectar();
            String sql;
            PreparedStatement stmt;
            
            if(formaPag == 0){// 0 é igual a todas
                sql = "select * from vendas where data between ? and ?";
                
                stmt = con.conex.prepareStatement(sql);
                stmt.setString(1, dataInicial);
                stmt.setString(2, dataFinal);
                
            }else{
                sql = "select * from vendas where idPag = ? and data between ? and ?";
                
                stmt = con.conex.prepareStatement(sql);
                stmt.setInt(1, formaPag);
                stmt.setString(2, dataInicial);
                stmt.setString(3, dataFinal);
            }
                       
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Vendas venda = new Vendas();
                
                venda.setData(rs.getString("data"));
                venda.setId(rs.getInt("idVenda"));
                venda.setIdCli(rs.getInt("idCli"));
                venda.setIdFunc(rs.getInt("idFunc"));
                venda.setIdPag(rs.getInt("idPag"));
                venda.setValorTotal(rs.getDouble("valorTotal"));
                
                vendas.add(venda);
            }
            
            stmt.close();
            rs.close();
            con.desconectar();
            
        }catch(SQLException erro){
            System.out.println("Erro de listar Vendas: " + erro);
        }
        
        return vendas;
    }
}
