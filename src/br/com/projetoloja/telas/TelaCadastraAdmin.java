/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projetoloja.telas;

/**
 *
 * @author Usuário
 */
import java.sql.*;
import br.com.projetoloja.dal.ModuloConexao;
import javax.swing.JOptionPane;

public class TelaCadastraAdmin extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    ResultSet rs2 = null;
    private String cpf_logado;

    /**
     * Creates new form TelaFuncionario
     */
    public TelaCadastraAdmin() {
        initComponents();
    }

    public TelaCadastraAdmin(String cpf_logado) {
        this.cpf_logado = cpf_logado;
        System.out.println(cpf_logado);
        initComponents();
        conexao = ModuloConexao.conector();
    }

    private void adicionarAdmin() {
        String sql = "insert into tbpessoa(cpf, primeiro_nome, sobrenome, "
                + "telefone) values (?,?,?,?)";

        String sql2 = "insert into tbadministrador (cpf, login, senha) values (?,?,?)";

        String sql3 = "insert into tbendereco (logradouro,estado,cidade,bairro,numero,cep,complemento) values\n"
                + "	(?, ?, ?, ?, ?, ?, ?);";

        String sql4 = "insert into tbreside (id_endereco, cpf) values (?,?)";
        
        String sql5 = "select id_endereco from tbendereco where logradouro=? and estado=? "
                + "and cidade=? and bairro=? and numero=? and cep=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtCPFCadAdm.getText());
            pst.setString(2, txtPrimeiroNomeCadAdm.getText());
            pst.setString(3, txtSobrenomeCadAdm.getText());
            pst.setString(4, txtTelCadAdm.getText());
            if (txtCPFCadAdm.getText().isEmpty() || txtPrimeiroNomeCadAdm.getText().isEmpty()
                    || txtSobrenomeCadAdm.getText().isEmpty() || txtLograCadAdm.getText().isEmpty()
                    || txtEstadoCadAdm.getText().isEmpty() || txtCidadeCadAdm.getText().isEmpty()
                    || txtBairroCadAdm.getText().isEmpty() || txtNumeroCadAdm.getText().isEmpty()
                    || txtCepCadAdm.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios!");
            } else {
                //cadastra pessoa
                pst.executeUpdate();

                //cadastra administrador
                PreparedStatement pst2 = conexao.prepareStatement(sql2);
                pst2.setString(1, txtCPFCadAdm.getText());
                pst2.setString(2, txtLoginCadAdm.getText());
                pst2.setString(3, txtSenhaCadAdm.getText());
                pst2.executeUpdate();

                //cadstra endereço
                PreparedStatement pst3 = conexao.prepareStatement(sql3);
                pst3.setString(1, txtLograCadAdm.getText());
                pst3.setString(2, txtEstadoCadAdm.getText());
                pst3.setString(3, txtCidadeCadAdm.getText());
                pst3.setString(4, txtBairroCadAdm.getText());
                pst3.setString(5, txtNumeroCadAdm.getText());
                pst3.setString(6, txtCepCadAdm.getText());
                pst3.setString(7, txtComplementoCadAdm.getText());
                pst3.executeUpdate();

                //Consulta ID do endereço registrado
                PreparedStatement pst4 = conexao.prepareStatement(sql5);
                pst4.setString(1, txtLograCadAdm.getText());
                pst4.setString(2, txtEstadoCadAdm.getText());
                pst4.setString(3, txtCidadeCadAdm.getText());
                pst4.setString(4, txtBairroCadAdm.getText());
                pst4.setString(5, txtNumeroCadAdm.getText());
                pst4.setString(6, txtCepCadAdm.getText());
                rs2 = pst4.executeQuery();

                if (rs2.next()) {
                    //Update da tabela reside
                    PreparedStatement pst5 = conexao.prepareStatement(sql4);
                    pst5.setString(1, rs2.getString(1));
                    pst5.setString(2, txtCPFCadAdm.getText());
                    pst5.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Funcionário cadastrado com sucesso!");
                }

                //Limpa os campos
                txtCPFCadAdm.setText(null);
                txtPrimeiroNomeCadAdm.setText(null);
                txtSobrenomeCadAdm.setText(null);
                txtLoginCadAdm.setText(null);
                txtSenhaCadAdm.setText(null);
                txtTelCadAdm.setText(null);
                txtLograCadAdm.setText(null);
                txtEstadoCadAdm.setText(null);
                txtCidadeCadAdm.setText(null);
                txtBairroCadAdm.setText(null);
                txtNumeroCadAdm.setText(null);
                txtCepCadAdm.setText(null);
                txtComplementoCadAdm.setText(null);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            txtCPFCadAdm.setText(null);
            txtPrimeiroNomeCadAdm.setText(null);
            txtSobrenomeCadAdm.setText(null);
            txtLoginCadAdm.setText(null);
            txtSenhaCadAdm.setText(null);
            txtTelCadAdm.setText(null);
            txtLograCadAdm.setText(null);
            txtEstadoCadAdm.setText(null);
            txtCidadeCadAdm.setText(null);
            txtBairroCadAdm.setText(null);
            txtNumeroCadAdm.setText(null);
            txtCepCadAdm.setText(null);
            txtComplementoCadAdm.setText(null);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtPrimeiroNomeCadAdm = new javax.swing.JTextField();
        txtCPFCadAdm = new javax.swing.JTextField();
        txtSobrenomeCadAdm = new javax.swing.JTextField();
        txtTelCadAdm = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtLograCadAdm = new javax.swing.JTextField();
        txtEstadoCadAdm = new javax.swing.JTextField();
        btnCadAdm = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtCidadeCadAdm = new javax.swing.JTextField();
        txtBairroCadAdm = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtNumeroCadAdm = new javax.swing.JTextField();
        txtCepCadAdm = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtComplementoCadAdm = new javax.swing.JTextField();
        txtLoginCadAdm = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtSenhaCadAdm = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Cadastro Funcionario");
        setPreferredSize(new java.awt.Dimension(770, 500));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("*CPF:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("*Primeiro Nome:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("*Sobrenome:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Telefone:");

        txtPrimeiroNomeCadAdm.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtPrimeiroNomeCadAdm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPrimeiroNomeCadAdmActionPerformed(evt);
            }
        });

        txtCPFCadAdm.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtCPFCadAdm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCPFCadAdmActionPerformed(evt);
            }
        });

        txtSobrenomeCadAdm.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtSobrenomeCadAdm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSobrenomeCadAdmActionPerformed(evt);
            }
        });

        txtTelCadAdm.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtTelCadAdm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTelCadAdmActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("*Logradouro:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("*Estado:");

        txtLograCadAdm.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtLograCadAdm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLograCadAdmActionPerformed(evt);
            }
        });

        txtEstadoCadAdm.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtEstadoCadAdm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEstadoCadAdmActionPerformed(evt);
            }
        });

        btnCadAdm.setText("Cadastrar");
        btnCadAdm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadAdmActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("*Cidade:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("*Bairro: ");

        txtCidadeCadAdm.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtCidadeCadAdm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCidadeCadAdmActionPerformed(evt);
            }
        });

        txtBairroCadAdm.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtBairroCadAdm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBairroCadAdmActionPerformed(evt);
            }
        });

        jLabel9.setText("* Campos Obrigatórios");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("*Numero:");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("*CEP:");

        txtNumeroCadAdm.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtNumeroCadAdm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNumeroCadAdmActionPerformed(evt);
            }
        });

        txtCepCadAdm.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtCepCadAdm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCepCadAdmActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setText("Complemento:");

        txtComplementoCadAdm.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtComplementoCadAdm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtComplementoCadAdmActionPerformed(evt);
            }
        });

        txtLoginCadAdm.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtLoginCadAdm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLoginCadAdmActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setText("Login:");

        txtSenhaCadAdm.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtSenhaCadAdm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSenhaCadAdmActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel14.setText("Senha");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(290, 290, 290)
                .addComponent(btnCadAdm, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addGap(191, 191, 191))
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtLograCadAdm, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtEstadoCadAdm, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel7))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtCidadeCadAdm, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtBairroCadAdm, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtTelCadAdm, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSobrenomeCadAdm, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPrimeiroNomeCadAdm, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCPFCadAdm, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel10)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtNumeroCadAdm, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtCepCadAdm, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(130, 130, 130)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel14))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtSenhaCadAdm)
                                    .addComponent(txtLoginCadAdm))
                                .addContainerGap())
                            .addComponent(txtComplementoCadAdm)))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(txtLograCadAdm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtEstadoCadAdm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(txtCidadeCadAdm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtBairroCadAdm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtNumeroCadAdm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel13)
                                .addComponent(txtLoginCadAdm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtCPFCadAdm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtPrimeiroNomeCadAdm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtSobrenomeCadAdm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtTelCadAdm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(txtCepCadAdm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(txtSenhaCadAdm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtComplementoCadAdm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addComponent(btnCadAdm)
                .addContainerGap())
        );

        setBounds(0, 0, 770, 500);
    }// </editor-fold>//GEN-END:initComponents

    private void txtPrimeiroNomeCadAdmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPrimeiroNomeCadAdmActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrimeiroNomeCadAdmActionPerformed

    private void txtCPFCadAdmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCPFCadAdmActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCPFCadAdmActionPerformed

    private void txtSobrenomeCadAdmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSobrenomeCadAdmActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSobrenomeCadAdmActionPerformed

    private void txtTelCadAdmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTelCadAdmActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelCadAdmActionPerformed

    private void btnCadAdmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadAdmActionPerformed
        // TODO add your handling code here:
        adicionarAdmin();
    }//GEN-LAST:event_btnCadAdmActionPerformed

    private void txtEstadoCadAdmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEstadoCadAdmActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEstadoCadAdmActionPerformed

    private void txtCepCadAdmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCepCadAdmActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCepCadAdmActionPerformed

    private void txtLograCadAdmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLograCadAdmActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLograCadAdmActionPerformed

    private void txtNumeroCadAdmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNumeroCadAdmActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNumeroCadAdmActionPerformed

    private void txtBairroCadAdmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBairroCadAdmActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBairroCadAdmActionPerformed

    private void txtCidadeCadAdmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCidadeCadAdmActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCidadeCadAdmActionPerformed

    private void txtComplementoCadAdmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtComplementoCadAdmActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtComplementoCadAdmActionPerformed

    private void txtLoginCadAdmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLoginCadAdmActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLoginCadAdmActionPerformed

    private void txtSenhaCadAdmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSenhaCadAdmActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSenhaCadAdmActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCadAdm;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField txtBairroCadAdm;
    private javax.swing.JTextField txtCPFCadAdm;
    private javax.swing.JTextField txtCepCadAdm;
    private javax.swing.JTextField txtCidadeCadAdm;
    private javax.swing.JTextField txtComplementoCadAdm;
    private javax.swing.JTextField txtEstadoCadAdm;
    private javax.swing.JTextField txtLoginCadAdm;
    private javax.swing.JTextField txtLograCadAdm;
    private javax.swing.JTextField txtNumeroCadAdm;
    private javax.swing.JTextField txtPrimeiroNomeCadAdm;
    private javax.swing.JTextField txtSenhaCadAdm;
    private javax.swing.JTextField txtSobrenomeCadAdm;
    private javax.swing.JTextField txtTelCadAdm;
    // End of variables declaration//GEN-END:variables
}
