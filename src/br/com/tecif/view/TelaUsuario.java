package br.com.tecif.view;


import java.sql.*;
import br.com.tecif.model.Conexao;
import javax.swing.JOptionPane;

//Classe da tela do Usuario
public class TelaUsuario extends javax.swing.JInternalFrame {
    //Variaveis para conectar ao BD
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    //Iniciando a tela do usuario e fazendo conexao ao BD
    public TelaUsuario() {
        initComponents();
        conexao = Conexao.conector();
    }
    //Metodo para consultar os usuario de acordo com seu id
    public void consultar() {
        //String para passar o comando sql de pesquisa
        String sql = "select * from tbusuario where idUsuario =?";
        //Tentando conectar ao BD e passando o comando sql
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtUsuID.getText());
            rs = pst.executeQuery();
            if (rs.next()) {    //Setando os valores achando nos campos
                txtUsuNome.setText(rs.getString(2));
                txtUsuTelefone.setText(rs.getString(3));
                txtUsuLogin.setText(rs.getString(4));
                txtUsuSenha.setText(rs.getString(5));
                cboUsuPerfil.setSelectedItem(rs.getString(6));

            } else { //Retorno de usuario não encontrado
                JOptionPane.showMessageDialog(null, "Nenhum usuário encontrado");
                txtUsuNome.setText(null);
                txtUsuTelefone.setText(null);
                txtUsuLogin.setText(null);
                txtUsuSenha.setText(null);
            }

        } catch (Exception e) { //Retorno de erro
            JOptionPane.showConfirmDialog(null, e);
        }
    }
    //Metodo para adicionar um novo usuario
    private void adicionar() {
        //Comando sql para adicionar usuario
        String sql = "insert into tbUsuario(idUsuario,usuario,numero,login,senha,perfil) values(?,?,?,?,?,?)";
        //Tentando conexao ao BD e passando valores a serem adicionados de acordo com os campos
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtUsuID.getText());
            pst.setString(2, txtUsuNome.getText());
            pst.setString(3, txtUsuTelefone.getText());
            pst.setString(4, txtUsuLogin.getText());
            pst.setString(5, txtUsuSenha.getText());
            pst.setString(6, cboUsuPerfil.getSelectedItem().toString());
            //Verificando se não há campos vazios
            if ((txtUsuID.getText().isEmpty()) || (txtUsuNome.getText().isEmpty()) || 
                    txtUsuTelefone.getText().isEmpty() || txtUsuLogin.getText().isEmpty() ||
                    txtUsuSenha.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Prencha os campos");
            } else {
                //Executando a adição e limpando os campos
                int adicionado = pst.executeUpdate();
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Usuário adicionado");
                    txtUsuID.setText(null);
                    txtUsuNome.setText(null);
                    txtUsuTelefone.setText(null);
                    txtUsuLogin.setText(null);
                    txtUsuSenha.setText(null);
                }
            }
        } catch (Exception e) { //Retorno de erro
            JOptionPane.showConfirmDialog(null, e);
        }
    }
       //Metodo para mudar valores do usuario
    public void alterar(){
        //Comando sql para realizar busca dos dados
        String sql = "update tbUsuario set usuario=?,numero=?,login=?,senha=?,perfil=? where idUsuario=?";
        //Tentando conexao ao BD de acordo com os campos passados
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtUsuNome.getText());
            pst.setString(2, txtUsuTelefone.getText());
            pst.setString(3, txtUsuLogin.getText());
            pst.setString(4, txtUsuSenha.getText());
            pst.setString(5, cboUsuPerfil.getSelectedItem().toString());
            pst.setString(6, txtUsuID.getText());
            //Verificando se não há campos vazios
            if ((txtUsuID.getText().isEmpty()) || (txtUsuNome.getText().isEmpty()) || 
                    txtUsuTelefone.getText().isEmpty() || txtUsuLogin.getText().isEmpty() ||
                    txtUsuSenha.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Prencha os campos");
            } else {
                //Executando atualização e limpandos os campos
                int adicionado = pst.executeUpdate();
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Usuário modificado");
                    txtUsuID.setText(null);
                    txtUsuNome.setText(null);
                    txtUsuTelefone.setText(null);
                    txtUsuLogin.setText(null);
                    txtUsuSenha.setText(null);
                }
            }
        } catch (Exception e) { //Retorno de erro
            JOptionPane.showConfirmDialog(null, e);
        }
    }
    //Metodo para excluir usuario
    public void remover(){
        //Confirmação para excluir usuario
        int confirmar = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover o usuário?", "Atenção", JOptionPane.YES_NO_OPTION);
        
        if (confirmar == JOptionPane.YES_OPTION){
            //Comando sql para excluir
            String sql = "delete from tbUsuario where idUsuario=?";
            //Tentando conexão ao BD e excluindo de acordo com o id
            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtUsuID.getText());

                int apagar = pst.executeUpdate();
                if (apagar > 0){
                    JOptionPane.showMessageDialog(null, "Usuario removido");
                    txtUsuID.setText(null);
                    txtUsuNome.setText(null);
                    txtUsuTelefone.setText(null);
                    txtUsuLogin.setText(null);
                    txtUsuSenha.setText(null);
                }
            } catch (Exception e) { //Retorno de erro
                JOptionPane.showConfirmDialog(null, e);
            }
        }
    
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtUsuNome = new javax.swing.JTextField();
        txtUsuLogin = new javax.swing.JTextField();
        txtUsuSenha = new javax.swing.JTextField();
        cboUsuPerfil = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        txtUsuTelefone = new javax.swing.JTextField();
        btnUsuCreate = new javax.swing.JButton();
        btnUsuRead = new javax.swing.JButton();
        btnUsoDelete = new javax.swing.JButton();
        btnUsuUpdate = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txtUsuID = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Usuario");
        setPreferredSize(new java.awt.Dimension(520, 380));

        jLabel1.setText("Nome");

        jLabel2.setText("Login");

        jLabel3.setText("Senha");

        jLabel4.setText("Perfil");

        cboUsuPerfil.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "usuario", "adm" }));

        jLabel5.setText("Telefone");

        btnUsuCreate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/tecif/icon/add.png"))); // NOI18N
        btnUsuCreate.setToolTipText("Adicionar");
        btnUsuCreate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUsuCreate.setPreferredSize(new java.awt.Dimension(80, 80));
        btnUsuCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuCreateActionPerformed(evt);
            }
        });

        btnUsuRead.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/tecif/icon/about.png"))); // NOI18N
        btnUsuRead.setToolTipText("Consultar");
        btnUsuRead.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUsuRead.setPreferredSize(new java.awt.Dimension(80, 80));
        btnUsuRead.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuReadActionPerformed(evt);
            }
        });

        btnUsoDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/tecif/icon/remove.png"))); // NOI18N
        btnUsoDelete.setToolTipText("Remover");
        btnUsoDelete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUsoDelete.setPreferredSize(new java.awt.Dimension(80, 80));
        btnUsoDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsoDeleteActionPerformed(evt);
            }
        });

        btnUsuUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/tecif/icon/edit.png"))); // NOI18N
        btnUsuUpdate.setToolTipText("Editar");
        btnUsuUpdate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUsuUpdate.setPreferredSize(new java.awt.Dimension(80, 80));
        btnUsuUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuUpdateActionPerformed(evt);
            }
        });

        jLabel6.setText("id");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnUsuCreate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnUsuRead, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel2)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(txtUsuTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(40, 40, 40))
                                .addComponent(txtUsuLogin, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel6)
                            .addComponent(txtUsuID, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(cboUsuPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel3)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnUsuUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnUsoDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtUsuSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtUsuNome, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUsuNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUsuID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUsuTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(cboUsuPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUsuLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUsuSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnUsuRead, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUsuCreate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUsoDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUsuUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(55, 55, 55))
        );

        setBounds(25, 50, 520, 380);
    }// </editor-fold>//GEN-END:initComponents

    private void btnUsuReadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuReadActionPerformed
        // // Chamando comando para consultar usuario
        consultar();
    }//GEN-LAST:event_btnUsuReadActionPerformed

    private void btnUsuCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuCreateActionPerformed
        // // Chamando comando para adicionar novo usuario
        adicionar();
    }//GEN-LAST:event_btnUsuCreateActionPerformed

    private void btnUsuUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuUpdateActionPerformed
        // // Chamando comando para alterar dados do usuario
        alterar();
    }//GEN-LAST:event_btnUsuUpdateActionPerformed

    private void btnUsoDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsoDeleteActionPerformed
        // // Chamando comando para excluir um cliente
        remover();
    }//GEN-LAST:event_btnUsoDeleteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnUsoDelete;
    private javax.swing.JButton btnUsuCreate;
    private javax.swing.JButton btnUsuRead;
    private javax.swing.JButton btnUsuUpdate;
    private javax.swing.JComboBox<String> cboUsuPerfil;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JTextField txtUsuID;
    private javax.swing.JTextField txtUsuLogin;
    private javax.swing.JTextField txtUsuNome;
    private javax.swing.JTextField txtUsuSenha;
    private javax.swing.JTextField txtUsuTelefone;
    // End of variables declaration//GEN-END:variables
}
