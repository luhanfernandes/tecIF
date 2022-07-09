package br.com.tecif.view;

import java.sql.*;
import br.com.tecif.model.Conexao;
import javax.swing.JOptionPane;

//Classe de criação da tela de Login
public class TelaLogin extends javax.swing.JFrame {
    //Variaveis para conectar ao BD
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    //Metodo para realizar login
    public void logar() {
        //Comando sql para verificar se o usuario é cadastrado
        String sql = "select * from tbusuario where login=? and senha =?";
        //Tentando conexao do BD passando login e senha
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtUsuario.getText());
            pst.setString(2, txtSenhaUsuario.getText());

            rs = pst.executeQuery();
            //Verificando o acesso e tipo de perfil
            if (rs.next()) {
                //Variavel para armazenar o perfil
                String perfil = rs.getString(6);
                //Se perfil igual adm libera todas as areas
                if (perfil.equals("adm")) {

                    TelaPrincipal principal = new TelaPrincipal();
                    principal.setVisible(true);
                    TelaPrincipal.MenuRelatorio.setEnabled(true);
                    TelaPrincipal.MenuCadastrarUsuario.setEnabled(true);
                    TelaPrincipal.lblUsuario.setText(rs.getString(2));
                    this.dispose();
                } else { //Senao, não ativa determinadas areas
                    TelaPrincipal principal = new TelaPrincipal();
                    principal.setVisible(true);
                    TelaPrincipal.lblUsuario.setText(rs.getString(2));
                    this.dispose();
                }
            } else { //Retorno de senha invalida
                JOptionPane.showMessageDialog(null, "usuario ou senha invalida!");
            }
        } catch (Exception e) { //Retorno de erro
            JOptionPane.showMessageDialog(null, e);
        }
    }
    //Criando tela de login e conectando ao BD
    public TelaLogin() {
        initComponents();
        conexao = Conexao.conector();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        btnLogin = new javax.swing.JButton();
        txtSenhaUsuario = new javax.swing.JPasswordField();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Tec IF (Login)");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);

        jLabel1.setText("Senha");

        jLabel2.setText("Usuário");

        btnLogin.setText("Login");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        txtSenhaUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSenhaUsuarioActionPerformed(evt);
            }
        });

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/tecif/icon/ifpeIcon.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(161, 161, 161)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(182, 182, 182))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(102, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSenhaUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(98, 98, 98))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnLogin)
                        .addGap(162, 162, 162))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(177, 177, 177))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel3)
                .addGap(27, 27, 27)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtSenhaUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(btnLogin)
                .addGap(73, 73, 73))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtSenhaUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSenhaUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSenhaUsuarioActionPerformed
  
    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        // // Chamando comando para inciizar o login
        logar();
    }//GEN-LAST:event_btnLoginActionPerformed

    
    public static void main(String args[]) {
 
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPasswordField txtSenhaUsuario;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables

}
