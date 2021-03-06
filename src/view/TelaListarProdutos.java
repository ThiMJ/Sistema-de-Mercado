package view;

import javax.swing.table.DefaultTableModel;
import model.Produtos;
import controller.ProdutoCRUD;
import java.util.ArrayList;
/**
 *
 * @author thiago
 */
public class TelaListarProdutos extends javax.swing.JFrame {
    
    DefaultTableModel table = new DefaultTableModel();
    ProdutoCRUD prodAcao = new ProdutoCRUD();
    ArrayList<Produtos> prods = new ArrayList<>();
    TelaVenda telaVenda; //vai receber a instancia da TelaVenda
    /**
     * Creates new form TelaListarProdutos
     */
    public TelaListarProdutos() {
        initComponents();
        this.setExtendedState(MAXIMIZED_BOTH);
        listarProds();
        
        table = (DefaultTableModel) tableListaProd.getModel();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tableListaProd = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tableListaProd.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Id", "Código de Barras", "Nome", "Descrição", "Valor Un Venda", "Valor Un Compra", "Quantidade Estoque"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableListaProd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableListaProdMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableListaProd);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 727, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 441, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tableListaProdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableListaProdMouseClicked
        try{
        
        if(evt.getClickCount() == 2){ //verifica se clicou 2 vezes
            int linha = tableListaProd.getSelectedRow();
            
            String cod = table.getValueAt(linha, 1).toString(); //pega o código do produto da linha selecionada
            telaVenda.escreverCampoCodProd(cod);
            this.dispose();
        }
        }
        catch(Exception erro){
            System.out.println("Erro duplo clique: " + erro);
        }
    }//GEN-LAST:event_tableListaProdMouseClicked

    public void listarProds(){
        table = (DefaultTableModel) tableListaProd.getModel();
        table.setNumRows(0);
        
        prods = prodAcao.listarProds();
        
        for(int i = 0; i< prods.size(); i++){
            table.addRow(new Object[]{
                prods.get(i).getIdProd(),
                prods.get(i).getCodProd(),
                prods.get(i).getNomeProd(),
                prods.get(i).getDescProd(),
                prods.get(i).getVlrUnVenda(),
                prods.get(i).getVlrUnCompra(),
                prods.get(i).getQtdEstoque()
            });
        }
    }
    
    public void transferirDados(TelaVenda telaVenda){ //usado para transferir dados entre as telas sem o construtor
        this.telaVenda = telaVenda;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaListarProdutos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaListarProdutos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaListarProdutos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaListarProdutos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaListarProdutos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableListaProd;
    // End of variables declaration//GEN-END:variables
}
