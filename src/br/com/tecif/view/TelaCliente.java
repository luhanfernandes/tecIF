package br.com.tecif.view;

import java.sql.*;
import br.com.tecif.model.Conexao;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

//Classe da Tela do Cliente, com Swing
public class TelaCliente extends javax.swing.JInternalFrame {
    //Variaveis e comandos para conectar ao BD
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    //Iniciar tela do Cliente e a conexao
    public TelaCliente() {
        initComponents();
        conexao = Conexao.conector();
    }
    //Metodo para adicionar clientes
    private void adicionar_cliente() {
        //String com o comando sql para adicionar
        String sql = "insert into tbCliente(nomeCliente,cpfCliente,enderecoCliente,numeroCliente,emailCliente) values(?,?,?,?,?)";
        //Tentando passar os parametros e valores para o BD
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtClienteNome.getText());
            pst.setString(2, txtClienteCPF.getText());
            pst.setString(3, txtClienteEndereco.getText());
            pst.setString(4, txtCliNumero.getText());
            pst.setString(5, txtClienteEmail.getText());
            //Verificando se não tem campo vazio
            if ((txtClienteNome.getText().isEmpty()) || (txtClienteCPF.getText().isEmpty())
                    || txtClienteEndereco.getText().isEmpty() || txtCliNumero.getText().isEmpty()
                    || txtClienteEmail.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Prencha os campos");
            } else {
                //Passando os valores para o BD e limpando os campos
                int adicionado = pst.executeUpdate();
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Cliente adicionado");
                    txtClienteNome.setText(null);
                    txtClienteCPF.setText(null);
                    txtClienteEndereco.setText(null);
                    txtCliNumero.setText(null);
                    txtClienteEmail.setText(null);
                }
            } //Retorno de erro
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
        }
    }
    //Metodo de pesquisa de cliente
    public void pesquisar_cliente() {
        //String de comando para pesquisar clientes 
        String sql = "select idCliente as id, nomeCliente as Nome, enderecoCliente as Endereoço, cpfCliente as CPF, numeroCliente as Numero, emailCliente as Email from tbCliente where nomeCliente like ?";
        //Tentando conectar ao BD e passando a query
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtClientePesquisa.getText() + "%");
            rs = pst.executeQuery();

            tblCliente.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) { //Retorno de erro
            JOptionPane.showConfirmDialog(null, e);
        }
    }
    //Metodo quando cliente selecionado na tabela
    public void setar_cliente() {
        //Passando os valores do BD para preencher a tabela
        int setar = tblCliente.getSelectedRow();
        txtClienteNome.setText(tblCliente.getModel().getValueAt(setar, 1).toString());
        txtClienteCPF.setText(tblCliente.getModel().getValueAt(setar, 3).toString());
        txtClienteEndereco.setText(tblCliente.getModel().getValueAt(setar, 2).toString());
        txtCliNumero.setText(tblCliente.getModel().getValueAt(setar, 4).toString());
        txtClienteEmail.setText(tblCliente.getModel().getValueAt(setar, 5).toString());

        btnAdicionar.setEnabled(false);
    }
    //Metodo para alterar valores do cliente
    private void alterar_cliente() {
        //String para passar o comando sql
        String sql = "update tbCliente set nomeCliente=?,cpfCliente=?,enderecoCliente=?,numeroCliente=?,emailCliente=? where cpfCliente=?";
        //Tentando conectar ao BD e passar valores
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtClienteNome.getText());
            pst.setString(2, txtClienteCPF.getText());
            pst.setString(3, txtClienteEndereco.getText());
            pst.setString(4, txtCliNumero.getText());
            pst.setString(5, txtClienteEmail.getText());
            pst.setString(6, txtClienteCPF.getText());
            //Verificando se todos os campos estão preenchidos
            if ((txtClienteNome.getText().isEmpty()) || (txtClienteCPF.getText().isEmpty())
                    || txtClienteEndereco.getText().isEmpty() || txtCliNumero.getText().isEmpty()
                    || txtClienteEmail.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Prencha os campos");
            } else {
                //Passando os valores para o BD e limpando os campos
                int adicionado = pst.executeUpdate();
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Cliente modificado");
                    txtClienteNome.setText(null);
                    txtClienteCPF.setText(null);
                    txtClienteEndereco.setText(null);
                    txtCliNumero.setText(null);
                    txtClienteEmail.setText(null);

                    btnAdicionar.setEnabled(true);
                }
            }   //Retorno de erro
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
        }
    }
    //Metodo para remover cliente
    public void remover_cliente() {
        //Confirmação se deseja excluir cliente
        int confirmar = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover o cliente?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirmar == JOptionPane.YES_OPTION) {
            //Passando o comando para o SQL e limpando os campos
            try {
                String sql = "delete from tbcliente where cpfCliente=?";
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtClienteCPF.getText());

                int apagar = pst.executeUpdate();
                if (apagar > 0) {
                    JOptionPane.showMessageDialog(null, "Cliente removido");
                    txtClienteNome.setText(null);
                    txtClienteCPF.setText(null);
                    txtClienteEndereco.setText(null);
                    txtCliNumero.setText(null);
                    txtClienteEmail.setText(null);

                    btnAdicionar.setEnabled(true);
                }
            } catch (Exception e) { //Retorno de erro
                JOptionPane.showConfirmDialog(null, e);
            }
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtClientePesquisa = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCliente = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtClienteEmail = new javax.swing.JTextField();
        txtClienteNome = new javax.swing.JTextField();
        txtClienteCPF = new javax.swing.JTextField();
        txtClienteEndereco = new javax.swing.JTextField();
        txtCliNumero = new javax.swing.JTextField();
        btnAdicionar = new javax.swing.JButton();
        btnRemover = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Cliente");
        setPreferredSize(new java.awt.Dimension(520, 380));

        txtClientePesquisa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtClientePesquisaActionPerformed(evt);
            }
        });
        txtClientePesquisa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtClientePesquisaKeyReleased(evt);
            }
        });

        tblCliente = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        tblCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "id", "Nome", "CPF", "Endereco", "Numero", "Email"
            }
        ));
        tblCliente.setFocusable(false);
        tblCliente.getTableHeader().setReorderingAllowed(false);
        tblCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblClienteMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblCliente);

        jLabel1.setText("Nome");

        jLabel2.setText("CPF");

        jLabel3.setText("Numero");

        jLabel4.setText("Endereço");

        jLabel5.setText("Email");

        btnAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/tecif/icon/add.png"))); // NOI18N
        btnAdicionar.setToolTipText("Adicionar");
        btnAdicionar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdicionar.setPreferredSize(new java.awt.Dimension(60, 60));
        btnAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarActionPerformed(evt);
            }
        });

        btnRemover.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/tecif/icon/remove.png"))); // NOI18N
        btnRemover.setToolTipText("Remover");
        btnRemover.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRemover.setPreferredSize(new java.awt.Dimension(60, 60));
        btnRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverActionPerformed(evt);
            }
        });

        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/tecif/icon/edit.png"))); // NOI18N
        btnEditar.setToolTipText("Editar");
        btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEditar.setPreferredSize(new java.awt.Dimension(60, 60));
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addGap(164, 164, 164)
                            .addComponent(jLabel2))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addGap(63, 63, 63)
                            .addComponent(jLabel5))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(txtClienteNome, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(txtClienteCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(txtCliNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtClienteEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel4)
                        .addComponent(txtClienteEndereco))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtClientePesquisa))
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAdicionar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEditar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRemover, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(btnAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(btnRemover, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtClientePesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtClienteNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtClienteCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCliNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtClienteEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtClienteEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        setBounds(25, 50, 520, 380);
    }// </editor-fold>//GEN-END:initComponents

    private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarActionPerformed
        // Chamando comando para adicionar
        adicionar_cliente();
    }//GEN-LAST:event_btnAdicionarActionPerformed

    private void txtClientePesquisaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtClientePesquisaKeyReleased
        // // Chamando comando para pesquisar
        pesquisar_cliente();

    }//GEN-LAST:event_txtClientePesquisaKeyReleased

    private void tblClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblClienteMouseClicked
        // // Chamando comando para setar os campos quando selecionado
        setar_cliente();
    }//GEN-LAST:event_tblClienteMouseClicked

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        // // Chamando comando para alterar os valores do cliente
        alterar_cliente();
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverActionPerformed
        // // Chamando comando para remover cliente
        remover_cliente();
    }//GEN-LAST:event_btnRemoverActionPerformed

    private void txtClientePesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtClientePesquisaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtClientePesquisaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnRemover;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblCliente;
    private javax.swing.JTextField txtCliNumero;
    private javax.swing.JTextField txtClienteCPF;
    private javax.swing.JTextField txtClienteEmail;
    private javax.swing.JTextField txtClienteEndereco;
    private javax.swing.JTextField txtClienteNome;
    private javax.swing.JTextField txtClientePesquisa;
    // End of variables declaration//GEN-END:variables
}
