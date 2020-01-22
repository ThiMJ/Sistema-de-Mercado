package model;

/**
 *
 * @author thiago
 */
public class ItensVenda{
    
    private int idVenda;
    private double qtd;
    private double vlrUn;
    private double vlrTotal;
    private int idProd;

    public int getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(int idVenda) {
        this.idVenda = idVenda;
    }

    public double getQtd() {
        return qtd;
    }

    public void setQtd(double qtd) {
        this.qtd = qtd;
    }

    public double getVlrUn() {
        return vlrUn;
    }

    public void setVlrUn(double vlrUn) {
        this.vlrUn = vlrUn;
    }

    public double getVlrTotal() {
        return vlrTotal;
    }

    public void setVlrTotal(double vlrTotal) {
        this.vlrTotal = vlrTotal;
    }

    public int getIdProd() {
        return idProd;
    }

    public void setIdProd(int idProd) {
        this.idProd = idProd;
    }


   
}
