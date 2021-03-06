package view;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import model.Clientes;
import model.Enderecos;
import controller.ClienteCRUD;
import controller.EnderecosCRUD;
/**
 *
 * @author thiago
 */
public class TelaListarClientes extends javax.swing.JFrame {
    
    DefaultTableModel table = new DefaultTableModel();
    ArrayList<Clientes> clientes = new ArrayList<>();
    ArrayList<Enderecos> enderecos = new ArrayList<>();
    ClienteCRUD cliAcao = new ClienteCRUD();
    EnderecosCRUD endAcao = new EnderecosCRUD();
    /**
     * Creates new form TelaListarClientes
     */
    public TelaListarClientes() {
        initComponents();
        this.setExtendedState(MAXIMIZED_BOTH);
        
        listarClientes();
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
        tableListarClientes = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tableListarClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Id", "Nome", "RG", "CPF", "Data Nascimento", "E-mail", "Status", "Logradouro", "Numero", "Complemento", "Bairro", "Cidade", "CEP"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tableListarClientes);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1117, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 469, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void listarClientes(){
        table = (DefaultTableModel) tableListarClientes.getModel();
        table.setNumRows(0);
        
        clientes = cliAcao.listarClientes();
        
        for(int i=0; i<clientes.size(); i++){
            Enderecos end = new Enderecos();
            end = endAcao.pesquisarEnd(clientes.get(i).getIdEndereco());
            enderecos.add(end);
        }
        
        for(int i=0; i<clientes.size(); i++){
            table.addRow(new Object[]{
                clientes.get(i).getId(),
                clientes.get(i).getNome(),
                clientes.get(i).getRg(),
                clientes.get(i).getCpf(),
                clientes.get(i).getDataNasc(),
                clientes.get(i).getEmail(),
                clientes.get(i).getStatus(),
                enderecos.get(i).getLogradouro(),
                enderecos.get(i).getNum(),
                enderecos.get(i).getComplemento(),
                enderecos.get(i).getBairro(),
                enderecos.get(i).getCidade(),
                enderecos.get(i).getCep()
            });
        }
        
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
            java.util.logging.Logger.getLogger(TelaListarClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaListarClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaListarClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaListarClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaListarClientes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableListarClientes;
    // End of variables declaration//GEN-END:variables
}
