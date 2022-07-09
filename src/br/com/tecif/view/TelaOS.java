package br.com.tecif.view;

import java.sql.*;
import br.com.tecif.model.Conexao;
import java.util.HashMap;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import java.awt.Toolkit;

//Classe da tela de ordem de serviço
public class TelaOS extends javax.swing.JInternalFrame {
    //Variaveis para o BD
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    //String para armazenar o tipo de os
    private String tipo;
    //Iniciando tela de OS e conectando ao BD
    public TelaOS() {
        initComponents();
        conexao = Conexao.conector();
    }
    //Modulo para pesquisar clientes na tabela
    private void pesquisarCliente() {
        //Comando sql para pesquisar o cliente na tabela pelo nome e setar seu ID
        String sql = "select idCliente as ID, nomeCliente as nome, numeroCliente as numero from tbCliente where nomeCliente like ?";
        //Tentando conectar ao BD e passar os valores
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtClientePesquisar.getText() + "%");
            rs = pst.executeQuery();
            tblCliente.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) { //Retorno de erro
            JOptionPane.showMessageDialog(null, e);
        }
    }
    //Modulo para setar os valores do ID no seu respectivo campo
    private void setar() {
        int setar = tblCliente.getSelectedRow();
        txtClienteID.setText(tblCliente.getModel().getValueAt(setar, 0).toString());
    }
    //Modulo para realizar impressão da OS
    private void emitir() {
        //Comando sql para juntar dados da tabela e realizar a impressão
        String sql = "insert into tbos(tipo,situacao,equipamento,defeito,servico,tecnico,valor,idCliente) values(?,?,?,?,?,?,?,?)";
        //Tentando conectar ao BD e passando valores
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, tipo);
            pst.setString(2, cboOsSit.getSelectedItem().toString());
            pst.setString(3, txtOSEquipamento.getText());
            pst.setString(4, txtOSDefeito.getText());
            pst.setString(5, txtOSServico.getText());
            pst.setString(6, txtOSTecnico.getText());
            pst.setString(7, txtOSValor.getText());
            pst.setString(8, txtClienteID.getText());
            //Verificação para que não tenha campos em brancos
            if ((txtClienteID.getText().isEmpty()) || (txtOSEquipamento.getText().isEmpty()) || (txtOSDefeito.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos");
            } else { 
                //Executando comando sql e limpando os campos
                int adicionado = pst.executeUpdate();

                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "OS Emitida");

                    txtClienteID.setText(null);
                    txtOSEquipamento.setText(null);
                    txtOSDefeito.setText(null);
                    txtOSServico.setText(null);
                    txtOSTecnico.setText(null);
                    txtOSValor.setText(null);

                }
            }

        } catch (Exception e) { //Retorno de erro
            JOptionPane.showMessageDialog(null, e);
        }
    }
    //Metodo para realizar pesquisa da OS de acordo com seu id
    private void pesquisar() {
        //Campo para perguntar o id da os
        String num_os = JOptionPane.showInputDialog("Numero da Ordem");
        //Comando sql para setar o valor do id
        String sql = "select * from tbos where os= " + num_os;
        //Tentando conectar ao BD e passando valores
        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();

            if (rs.next()) {
                txtOS.setText(rs.getString(1));
                txtData.setText(rs.getString(2));
                String rbtTipo = rs.getString(4);
                //Se o campo OS tiver selecioado vai receber o valor OS
                if (rbtTipo.equals("OS")) {
                    rbtOS.setSelected(true);
                    tipo = "OS";
                } else { //Se o campo Orçamento tiver selecioando vai receber o valor Orçamento
                    rbtOc.setSelected(true);
                    tipo = "Orçamento";
                }

                cboOsSit.setSelectedItem(rs.getString(4));
                txtOSEquipamento.setText(rs.getString(5));
                txtOSDefeito.setText(rs.getString(6));
                txtOSServico.setText(rs.getString(7));
                txtOSTecnico.setText(rs.getString(8));
                txtOSValor.setText(rs.getString(9));
                txtClienteID.setText(rs.getString(10));

                btnOSAdicionar.setEnabled(false);
                txtClientePesquisar.setEnabled(false);
                tblCliente.setVisible(false);

            } else { //Retorno de OS que não existe
                JOptionPane.showMessageDialog(null, "OS não existente");
            }

        } catch (SQLSyntaxErrorException e) { //Retorno de digitação errada
            JOptionPane.showMessageDialog(null, "OS invalida");
        } catch (Exception erro) { //Retorno de erro
            JOptionPane.showMessageDialog(null, erro);
        }
    }
    //Metodo para modificar dados da os
    private void alterar() {
        //Comando sql para realizar alteração na os
        String sql = "update tbos set tipo=?,situacao=?,equipamento=?,defeito=?,servico=?,tecnico=?,valor=? where os=?";
        //Tentando conectar ao BD e setando valores
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, tipo);
            pst.setString(2, cboOsSit.getSelectedItem().toString());
            pst.setString(3, txtOSEquipamento.getText());
            pst.setString(4, txtOSDefeito.getText());
            pst.setString(5, txtOSServico.getText());
            pst.setString(6, txtOSTecnico.getText());
            pst.setString(7, txtOSValor.getText());
            pst.setString(8, txtOS.getText());
            //Verificando se não há campos em branco
            if ((txtClienteID.getText().isEmpty()) || (txtOSEquipamento.getText().isEmpty()) || (txtOSDefeito.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos");
            } else {
                int adicionado = pst.executeUpdate();
                //Realizando alterações e limpando os campos
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "OS Alterada");

                    txtClienteID.setText(null);
                    txtOSEquipamento.setText(null);
                    txtOSDefeito.setText(null);
                    txtOSServico.setText(null);
                    txtOSTecnico.setText(null);
                    txtOSValor.setText(null);
                    txtOS.setText(null);
                    txtData.setText(null);

                    btnOSAdicionar.setEnabled(true);
                    txtClientePesquisar.setEnabled(true);
                    tblCliente.setVisible(true);

                }
            }

        } catch (Exception e) { //Retorno de erro
            JOptionPane.showMessageDialog(null, e);
        }
    }
    //Metodo para exlcuir uma OS
    private void excluir() {
        //Tela de alerta para certeza sobre excluir uma OS
        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir a OS?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            //Comando sql para deletar uma OS
            String sql = "delete from tbos where os=?";
            //Tentando conectar ao BD e setar valores e limpando os campos
            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtOS.getText());
                int apagado = pst.executeUpdate();
                if (apagado > 0) {
                    JOptionPane.showMessageDialog(null, "OS excluida");
                    txtClienteID.setText(null);
                    txtOSEquipamento.setText(null);
                    txtOSDefeito.setText(null);
                    txtOSServico.setText(null);
                    txtOSTecnico.setText(null);
                    txtOSValor.setText(null);
                    txtOS.setText(null);
                    txtData.setText(null);

                    btnOSAdicionar.setEnabled(true);
                    txtClientePesquisar.setEnabled(true);
                    tblCliente.setVisible(true);
                }
            } catch (Exception e) { //Retorno de erro
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }
    //Metodo para imprimir uma OS
    private void imprimir(){
                //Tela de confirmação para imprimir uma OS
                int confirma = JOptionPane.showConfirmDialog(null, "Deseja imprimir a ordem de serviço?", "Atenção", JOptionPane.YES_NO_OPTION);
        //Localiza os valor da OS e passa para a ferramento Jasper realizar a impressão
        if (confirma == JOptionPane.YES_OPTION) {
            try {
                HashMap localizar = new HashMap();
                localizar.put("os", Integer.parseInt(txtOS.getText()));
                
                JasperPrint imprimir = JasperFillManager.fillReport("C:/Users/Luhan/Desktop/ProjetoLPOO/reports/os.jasper", localizar, conexao);
                JasperViewer.viewReport(imprimir, false);
            } catch (Exception e) { //Retorno de erro
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtOS = new javax.swing.JTextField();
        txtData = new javax.swing.JTextField();
        rbtOS = new javax.swing.JRadioButton();
        rbtOc = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        cboOsSit = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtClientePesquisar = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtClienteID = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblCliente = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtOSEquipamento = new javax.swing.JTextField();
        txtOSDefeito = new javax.swing.JTextField();
        txtOSServico = new javax.swing.JTextField();
        txtOSTecnico = new javax.swing.JTextField();
        txtOSValor = new javax.swing.JTextField();
        btnOSAdicionar = new javax.swing.JButton();
        btnOSPesquisar = new javax.swing.JButton();
        btnOSEditar = new javax.swing.JButton();
        btnOSRemover = new javax.swing.JButton();
        btnOSImprimir = new javax.swing.JButton();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Ordem de Serviço");
        setPreferredSize(new java.awt.Dimension(570, 485));
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameOpened(evt);
            }
        });

        jLabel1.setText("N° OS");

        jLabel2.setText("Data");

        txtOS.setEditable(false);

        txtData.setEditable(false);

        buttonGroup1.add(rbtOS);
        rbtOS.setText("OS");
        rbtOS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtOSActionPerformed(evt);
            }
        });

        buttonGroup1.add(rbtOc);
        rbtOc.setText("Orçamento");
        rbtOc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtOcActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addGap(50, 50, 50)
                        .addComponent(jLabel2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(rbtOc)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rbtOS)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(txtOS, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtData, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 17, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtOS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbtOc)
                    .addComponent(rbtOS))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jLabel3.setText("Situação");

        cboOsSit.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Entregue", "Orçamento Negado", "Agurdando Aprovação", "Aguardando Peças", "Abandonado", "Na Banca", "Retorno" }));

        jLabel4.setText("Cliente");

        txtClientePesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtClientePesquisarKeyReleased(evt);
            }
        });

        jLabel5.setText("ID");

        txtClienteID.setEditable(false);

        tblCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Nome", "Numero"
            }
        ));
        tblCliente.setFocusable(false);
        tblCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblClienteMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblCliente);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtClientePesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtClienteID, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtClientePesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtClienteID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel3.setPreferredSize(new java.awt.Dimension(570, 485));

        jLabel6.setText("Equipamento");

        jLabel7.setText("Defeito");

        jLabel8.setText("Serviço");

        jLabel9.setText("Tecnico");

        jLabel10.setText("Valor");

        txtOSValor.setText("0");

        btnOSAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/tecif/icon/add.png"))); // NOI18N
        btnOSAdicionar.setToolTipText("Adicionar");
        btnOSAdicionar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnOSAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOSAdicionarActionPerformed(evt);
            }
        });

        btnOSPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/tecif/icon/about.png"))); // NOI18N
        btnOSPesquisar.setToolTipText("Sobre");
        btnOSPesquisar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnOSPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOSPesquisarActionPerformed(evt);
            }
        });

        btnOSEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/tecif/icon/edit.png"))); // NOI18N
        btnOSEditar.setToolTipText("Editar");
        btnOSEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnOSEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOSEditarActionPerformed(evt);
            }
        });

        btnOSRemover.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/tecif/icon/remove.png"))); // NOI18N
        btnOSRemover.setToolTipText("Remover");
        btnOSRemover.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnOSRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOSRemoverActionPerformed(evt);
            }
        });

        btnOSImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/tecif/icon/imprimir.png"))); // NOI18N
        btnOSImprimir.setToolTipText("Imprimir");
        btnOSImprimir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnOSImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOSImprimirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(txtOSTecnico, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 77, Short.MAX_VALUE)
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtOSValor, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtOSEquipamento)
                            .addComponent(txtOSDefeito)
                            .addComponent(txtOSServico)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnOSAdicionar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnOSPesquisar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnOSEditar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnOSRemover)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnOSImprimir)))
                .addContainerGap(77, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtOSEquipamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtOSDefeito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtOSServico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(txtOSTecnico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtOSValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnOSRemover, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnOSImprimir, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnOSEditar)
                    .addComponent(btnOSPesquisar)
                    .addComponent(btnOSAdicionar))
                .addContainerGap(276, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cboOsSit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(12, 12, 12)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(cboOsSit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        setBounds(0, 0, 570, 485);
    }// </editor-fold>//GEN-END:initComponents

    private void rbtOSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtOSActionPerformed
        //  Chamando comando para selecionar o tipo de OS
        tipo = "OS";
    }//GEN-LAST:event_rbtOSActionPerformed

    private void txtClientePesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtClientePesquisarKeyReleased
        //  Chamando comando para pesquisar um cliente
        pesquisarCliente();
    }//GEN-LAST:event_txtClientePesquisarKeyReleased

    private void tblClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblClienteMouseClicked
        //  Chamando comando para setar os valores selecionados
        setar();
    }//GEN-LAST:event_tblClienteMouseClicked

    private void rbtOcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtOcActionPerformed
        //  Chamando comando para selecionar o tipo de OS
        tipo = "Orçamento";
    }//GEN-LAST:event_rbtOcActionPerformed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        //  Chamando comando quando selecionado o tipo orçamento
        rbtOc.setSelected(true);
        tipo = "Orçamento";
    }//GEN-LAST:event_formInternalFrameOpened

    private void btnOSAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOSAdicionarActionPerformed
        // Chamando comando para emitir uma OS
        emitir();
    }//GEN-LAST:event_btnOSAdicionarActionPerformed

    private void btnOSPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOSPesquisarActionPerformed
        // Chamando comando para pesquisar uma OS
        pesquisar();
    }//GEN-LAST:event_btnOSPesquisarActionPerformed

    private void btnOSEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOSEditarActionPerformed
        // Chamando comando para alterar os valores de uma OS
        alterar();
    }//GEN-LAST:event_btnOSEditarActionPerformed

    private void btnOSRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOSRemoverActionPerformed
        // Chamando comando para excluir uma OS
        excluir();
    }//GEN-LAST:event_btnOSRemoverActionPerformed

    private void btnOSImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOSImprimirActionPerformed
        // Chamando comando para imprimir uma OS
        imprimir();
    }//GEN-LAST:event_btnOSImprimirActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnOSAdicionar;
    private javax.swing.JButton btnOSEditar;
    private javax.swing.JButton btnOSImprimir;
    private javax.swing.JButton btnOSPesquisar;
    private javax.swing.JButton btnOSRemover;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboOsSit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JRadioButton rbtOS;
    private javax.swing.JRadioButton rbtOc;
    private javax.swing.JTable tblCliente;
    private javax.swing.JTextField txtClienteID;
    private javax.swing.JTextField txtClientePesquisar;
    private javax.swing.JTextField txtData;
    private javax.swing.JTextField txtOS;
    private javax.swing.JTextField txtOSDefeito;
    private javax.swing.JTextField txtOSEquipamento;
    private javax.swing.JTextField txtOSServico;
    private javax.swing.JTextField txtOSTecnico;
    private javax.swing.JTextField txtOSValor;
    // End of variables declaration//GEN-END:variables

}
