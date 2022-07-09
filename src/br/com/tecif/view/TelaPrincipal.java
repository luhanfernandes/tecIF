package br.com.tecif.view;

import br.com.tecif.model.Conexao;
import java.text.DateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import java.sql.*;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;


//Classe da tela principal
public class TelaPrincipal extends javax.swing.JFrame {

      Connection conexao = null;

      //Iniciando a tela Principal e conectando o BD
    public TelaPrincipal() {
        initComponents();
        conexao = Conexao.conector();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        desktop = new javax.swing.JDesktopPane();
        jLabel1 = new javax.swing.JLabel();
        lblUsuario = new javax.swing.JLabel();
        lblData = new javax.swing.JLabel();
        MenuPrincipal = new javax.swing.JMenuBar();
        MenuCadastrar = new javax.swing.JMenu();
        MenuCadastarCliente = new javax.swing.JMenuItem();
        MenuCadastrarOS = new javax.swing.JMenuItem();
        MenuCadastrarUsuario = new javax.swing.JMenuItem();
        MenuRelatorio = new javax.swing.JMenu();
        MenuRelatorioServiço = new javax.swing.JMenuItem();
        MenuRelatorioCliente = new javax.swing.JMenuItem();
        MenuAjuda = new javax.swing.JMenu();
        MenuAjudaSobre = new javax.swing.JMenuItem();
        MenuOpções = new javax.swing.JMenu();
        MenuOpçõesSair = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("TecIF");
        setBackground(new java.awt.Color(0, 153, 0));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        desktop.setBackground(new java.awt.Color(0, 153, 51));
        desktop.setDesktopManager(null);
        desktop.setPreferredSize(new java.awt.Dimension(700, 510));

        javax.swing.GroupLayout desktopLayout = new javax.swing.GroupLayout(desktop);
        desktop.setLayout(desktopLayout);
        desktopLayout.setHorizontalGroup(
            desktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 570, Short.MAX_VALUE)
        );
        desktopLayout.setVerticalGroup(
            desktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/tecif/icon/ifpeIcon.png"))); // NOI18N

        lblUsuario.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblUsuario.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUsuario.setText("Usuário");
        lblUsuario.setToolTipText("");
        lblUsuario.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        lblData.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        lblData.setText("Data");
        lblData.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        lblData.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        MenuPrincipal.setBorder(null);

        MenuCadastrar.setText("Cadastrar");

        MenuCadastarCliente.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.SHIFT_DOWN_MASK));
        MenuCadastarCliente.setText("Cliente");
        MenuCadastarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuCadastarClienteActionPerformed(evt);
            }
        });
        MenuCadastrar.add(MenuCadastarCliente);

        MenuCadastrarOS.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.SHIFT_DOWN_MASK));
        MenuCadastrarOS.setText("OrdemServiço");
        MenuCadastrarOS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuCadastrarOSActionPerformed(evt);
            }
        });
        MenuCadastrar.add(MenuCadastrarOS);

        MenuCadastrarUsuario.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.SHIFT_DOWN_MASK));
        MenuCadastrarUsuario.setText("Usuário");
        MenuCadastrarUsuario.setEnabled(false);
        MenuCadastrarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuCadastrarUsuarioActionPerformed(evt);
            }
        });
        MenuCadastrar.add(MenuCadastrarUsuario);

        MenuPrincipal.add(MenuCadastrar);

        MenuRelatorio.setText("Relatório");
        MenuRelatorio.setEnabled(false);

        MenuRelatorioServiço.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.SHIFT_DOWN_MASK));
        MenuRelatorioServiço.setText("Serviço");
        MenuRelatorioServiço.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuRelatorioServiçoActionPerformed(evt);
            }
        });
        MenuRelatorio.add(MenuRelatorioServiço);

        MenuRelatorioCliente.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.SHIFT_DOWN_MASK));
        MenuRelatorioCliente.setText("Cliente");
        MenuRelatorioCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuRelatorioClienteActionPerformed(evt);
            }
        });
        MenuRelatorio.add(MenuRelatorioCliente);

        MenuPrincipal.add(MenuRelatorio);

        MenuAjuda.setText("Ajuda");

        MenuAjudaSobre.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, java.awt.event.InputEvent.SHIFT_DOWN_MASK));
        MenuAjudaSobre.setText("Sobre");
        MenuAjudaSobre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuAjudaSobreActionPerformed(evt);
            }
        });
        MenuAjuda.add(MenuAjudaSobre);

        MenuPrincipal.add(MenuAjuda);

        MenuOpções.setText("Opções");
        MenuOpções.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuOpçõesActionPerformed(evt);
            }
        });

        MenuOpçõesSair.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_DOWN_MASK));
        MenuOpçõesSair.setText("Sair");
        MenuOpçõesSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuOpçõesSairActionPerformed(evt);
            }
        });
        MenuOpções.add(MenuOpçõesSair);

        MenuPrincipal.add(MenuOpções);

        setJMenuBar(MenuPrincipal);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(desktop, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(lblUsuario))
                        .addGap(25, 25, 25))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblData)
                        .addGap(45, 45, 45))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel1)
                .addGap(45, 45, 45)
                .addComponent(lblUsuario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblData)
                .addGap(41, 41, 41))
            .addComponent(desktop, javax.swing.GroupLayout.DEFAULT_SIZE, 518, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(736, 548));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void MenuCadastarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuCadastarClienteActionPerformed
        // Chamando comando para aparecer a tela principal
        TelaCliente cliente = new TelaCliente();
        cliente.setVisible(true);
        desktop.add(cliente);
    }//GEN-LAST:event_MenuCadastarClienteActionPerformed

    private void MenuAjudaSobreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuAjudaSobreActionPerformed
        // Chamando comando para aparecer a tela sobre
        TelaSobre sobre = new TelaSobre();
        sobre.setVisible(true);
    }//GEN-LAST:event_MenuAjudaSobreActionPerformed

    private void MenuCadastrarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuCadastrarUsuarioActionPerformed
        // Chamando comando para aparecer a tela do usuario
        TelaUsuario usuario = new TelaUsuario();
        usuario.setVisible(true);
        desktop.add(usuario);
    }//GEN-LAST:event_MenuCadastrarUsuarioActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // Chamando comando para modificar a aparecer a data
        Date data = new Date();
        DateFormat formatador = DateFormat.getDateInstance(DateFormat.SHORT);
        lblData.setText(formatador.format(data));
    }//GEN-LAST:event_formWindowActivated

    private void MenuOpçõesSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuOpçõesSairActionPerformed
        // Chamando comando para encerrar a aplicação
        int sair = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja encerrar a aplicação?","TecIF",JOptionPane.YES_NO_OPTION);
        if (sair == JOptionPane.YES_OPTION){
            System.exit(0);
        }
    }//GEN-LAST:event_MenuOpçõesSairActionPerformed

    private void MenuOpçõesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuOpçõesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MenuOpçõesActionPerformed

    private void MenuCadastrarOSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuCadastrarOSActionPerformed
        // Chamando comando para aparecer a tela OS
        TelaOS os = new TelaOS();
        os.setVisible(true);
        desktop.add(os);
    }//GEN-LAST:event_MenuCadastrarOSActionPerformed
        // Chamando comando para imprimir relatorio dos clientes
    private void MenuRelatorioClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuRelatorioClienteActionPerformed
        //Tela de confirmação para imprimri o relatorio
        int confirma = JOptionPane.showConfirmDialog(null, "Deseja imprimir o relatorio?", "Atenção", JOptionPane.YES_NO_OPTION);
        //Comando sql para imprimrir a OS, utilizando a ferramento Jasper
        if (confirma == JOptionPane.YES_OPTION){
            try {
                JasperPrint imprimir = JasperFillManager.fillReport("C:/Users/Luhan/Desktop/ProjetoLPOO/reports/cliente.jasper", null,conexao);
                JasperViewer.viewReport(imprimir,false);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }//GEN-LAST:event_MenuRelatorioClienteActionPerformed
        // Chamando comando para imprimir relatorio dos Serviços
    private void MenuRelatorioServiçoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuRelatorioServiçoActionPerformed
        //Tela de confirmação para imprimri o relatorio
        int confirma = JOptionPane.showConfirmDialog(null, "Deseja imprimir o relatorio?", "Atenção", JOptionPane.YES_NO_OPTION);
        //Comando sql para imprimrir a OS, utilizando a ferramento Jasper        
        if (confirma == JOptionPane.YES_OPTION){
            try {
                JasperPrint imprimir = JasperFillManager.fillReport("C:/Users/Luhan/Desktop/ProjetoLPOO/reports/servico.jasper", null,conexao);
                JasperViewer.viewReport(imprimir,false);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }//GEN-LAST:event_MenuRelatorioServiçoActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu MenuAjuda;
    private javax.swing.JMenuItem MenuAjudaSobre;
    private javax.swing.JMenuItem MenuCadastarCliente;
    private javax.swing.JMenu MenuCadastrar;
    private javax.swing.JMenuItem MenuCadastrarOS;
    public static javax.swing.JMenuItem MenuCadastrarUsuario;
    private javax.swing.JMenu MenuOpções;
    private javax.swing.JMenuItem MenuOpçõesSair;
    private javax.swing.JMenuBar MenuPrincipal;
    public static javax.swing.JMenu MenuRelatorio;
    private javax.swing.JMenuItem MenuRelatorioCliente;
    private javax.swing.JMenuItem MenuRelatorioServiço;
    private javax.swing.JDesktopPane desktop;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblData;
    public static javax.swing.JLabel lblUsuario;
    // End of variables declaration//GEN-END:variables
}
