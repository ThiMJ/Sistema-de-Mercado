package model;

/**
 *
 * @author thiago
 */
public class Produtos {
    
    private int idProd;
    private String codProd;
    private String nomeProd;
    private String descProd;
    private double vlrUnVenda;
    private double vlrUnCompra;
    private int qtdEstoque;

    public int getIdProd() {
        return idProd;
    }

    public void setIdProd(int idProd) {
        this.idProd = idProd;
    }

    public String getCodProd() {
        return codProd;
    }

    public void setCodProd(String codProd) {
        this.codProd = codProd;
    }

    public String getNomeProd() {
        return nomeProd;
    }

    public void setNomeProd(String nomeProd) {
        this.nomeProd = nomeProd;
    }

    public String getDescProd() {
        return descProd;
    }

    public void setDescProd(String descProd) {
        this.descProd = descProd;
    }

    public double getVlrUnVenda() {
        return vlrUnVenda;
    }

    public void setVlrUnVenda(double vlrUnVenda) {
        this.vlrUnVenda = vlrUnVenda;
    }

    public double getVlrUnCompra() {
        return vlrUnCompra;
    }

    public void setVlrUnCompra(double vlrUnCompra) {
        this.vlrUnCompra = vlrUnCompra;
    }

    public int getQtdEstoque() {
        return qtdEstoque;
    }

    public void setQtdEstoque(int qtdEstoque) {
        this.qtdEstoque = qtdEstoque;
    }
     
}
