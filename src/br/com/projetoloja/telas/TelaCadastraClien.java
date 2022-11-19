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

public class TelaCadastraClien extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    ResultSet rs2 = null;
    private String cpf_logado;

    /**
     * Creates new form TelaFuncionario
     */
    public TelaCadastraClien() {
        initComponents();
    }

    public TelaCadastraClien(String cpf_logado) {
        this.cpf_logado = cpf_logado;
        System.out.println(cpf_logado);
        initComponents();
        conexao = ModuloConexao.conector();
    }

    private void adicionarClien() {
        String sql = "insert into tbpessoa(cpf, primeiro_nome, sobrenome, "
                + "telefone) values (?,?,?,?)";

        String sql2 = "insert into tbcliente(cpf) values (?)";

        String sql3 = "insert into tbendereco (logradouro,estado,cidade,bairro,numero,cep,complemento) values\n"
                + "	(?, ?, ?, ?, ?, ?, ?);";

        String sql4 = "insert into tbreside (id_endereco, cpf) values (?,?)";
        
        String sql5 = "select id_endereco from tbendereco where logradouro=? and estado=? "
                + "and cidade=? and bairro=? and numero=? and cep=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtCPFCadClien.getText());
            pst.setString(2, txtPrimeiroNomeCadClien.getText());
            pst.setString(3, txtSobrenomeCadClien.getText());
            pst.setString(4, txtTelCadClien.getText());
            if (txtCPFCadClien.getText().isEmpty() || txtPrimeiroNomeCadClien.getText().isEmpty()
                    || txtSobrenomeCadClien.getText().isEmpty() || txtLograCadClien.getText().isEmpty()
                    || txtEstadoCadClien.getText().isEmpty() || txtCidadeCadClien.getText().isEmpty()
                    || txtBairroCadClien.getText().isEmpty() || txtNumeroCadClien.getText().isEmpty()
                    || txtCepCadClien.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios!");
            } else {
                //cadastra pessoa
                pst.executeUpdate();

                //cadastra cliente
                PreparedStatement pst2 = conexao.prepareStatement(sql2);
                pst2.setString(1, txtCPFCadClien.getText());
                pst2.executeUpdate();

                //cadastra endereço
                PreparedStatement pst3 = conexao.prepareStatement(sql3);
                pst3.setString(1, txtLograCadClien.getText());
                pst3.setString(2, txtEstadoCadClien.getText());
                pst3.setString(3, txtCidadeCadClien.getText());
                pst3.setString(4, txtBairroCadClien.getText());
                pst3.setString(5, txtNumeroCadClien.getText());
                pst3.setString(6, txtCepCadClien.getText());
                pst3.setString(7, txtComplementoCadClien.getText());
                pst3.executeUpdate();

                //Consulta ID do endereço registrado
                PreparedStatement pst4 = conexao.prepareStatement(sql5);
                pst4.setString(1, txtLograCadClien.getText());
                pst4.setString(2, txtEstadoCadClien.getText());
                pst4.setString(3, txtCidadeCadClien.getText());
                pst4.setString(4, txtBairroCadClien.getText());
                pst4.setString(5, txtNumeroCadClien.getText());
                pst4.setString(6, txtCepCadClien.getText());
                rs2 = pst4.executeQuery();

                if (rs2.next()) {
                    //Update da tabela reside
                    PreparedStatement pst5 = conexao.prepareStatement(sql4);
                    pst5.setString(1, rs2.getString(1));
                    pst5.setString(2, txtCPFCadClien.getText());
                    pst5.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!");
                }

                //Limpa os campos
                txtCPFCadClien.setText(null);
                txtPrimeiroNomeCadClien.setText(null);
                txtSobrenomeCadClien.setText(null);
                txtTelCadClien.setText(null);
                txtLograCadClien.setText(null);
                txtEstadoCadClien.setText(null);
                txtCidadeCadClien.setText(null);
                txtBairroCadClien.setText(null);
                txtNumeroCadClien.setText(null);
                txtCepCadClien.setText(null);
                txtComplementoCadClien.setText(null);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            txtCPFCadClien.setText(null);
            txtPrimeiroNomeCadClien.setText(null);
            txtSobrenomeCadClien.setText(null);
            txtTelCadClien.setText(null);
            txtLograCadClien.setText(null);
            txtEstadoCadClien.setText(null);
            txtCidadeCadClien.setText(null);
            txtBairroCadClien.setText(null);
            txtNumeroCadClien.setText(null);
            txtCepCadClien.setText(null);
            txtComplementoCadClien.setText(null);
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
        txtPrimeiroNomeCadClien = new javax.swing.JTextField();
        txtCPFCadClien = new javax.swing.JTextField();
        txtSobrenomeCadClien = new javax.swing.JTextField();
        txtTelCadClien = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtLograCadClien = new javax.swing.JTextField();
        txtEstadoCadClien = new javax.swing.JTextField();
        btnCadClien = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtCidadeCadClien = new javax.swing.JTextField();
        txtBairroCadClien = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtNumeroCadClien = new javax.swing.JTextField();
        txtCepCadClien = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtComplementoCadClien = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Cadastro Cliente");
        setPreferredSize(new java.awt.Dimension(770, 500));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("*CPF:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("*Primeiro Nome:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("*Sobrenome:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Telefone:");

        txtPrimeiroNomeCadClien.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtPrimeiroNomeCadClien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPrimeiroNomeCadClienActionPerformed(evt);
            }
        });

        txtCPFCadClien.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtCPFCadClien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCPFCadClienActionPerformed(evt);
            }
        });

        txtSobrenomeCadClien.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtSobrenomeCadClien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSobrenomeCadClienActionPerformed(evt);
            }
        });

        txtTelCadClien.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtTelCadClien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTelCadClienActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("*Logradouro:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("*Estado:");

        txtLograCadClien.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtLograCadClien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLograCadClienActionPerformed(evt);
            }
        });

        txtEstadoCadClien.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtEstadoCadClien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEstadoCadClienActionPerformed(evt);
            }
        });

        btnCadClien.setText("Cadastrar");
        btnCadClien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadClienActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("*Cidade:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("*Bairro: ");

        txtCidadeCadClien.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtCidadeCadClien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCidadeCadClienActionPerformed(evt);
            }
        });

        txtBairroCadClien.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtBairroCadClien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBairroCadClienActionPerformed(evt);
            }
        });

        jLabel9.setText("* Campos Obrigatórios");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("*Numero:");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("*CEP:");

        txtNumeroCadClien.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtNumeroCadClien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNumeroCadClienActionPerformed(evt);
            }
        });

        txtCepCadClien.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtCepCadClien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCepCadClienActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setText("Complemento:");

        txtComplementoCadClien.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtComplementoCadClien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtComplementoCadClienActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(290, 290, 290)
                .addComponent(btnCadClien, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                                    .addComponent(txtLograCadClien, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtEstadoCadClien, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel7))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtCidadeCadClien, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtBairroCadClien, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtTelCadClien, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSobrenomeCadClien, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPrimeiroNomeCadClien, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCPFCadClien, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                                    .addComponent(txtNumeroCadClien, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtCepCadClien, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())
                            .addComponent(txtComplementoCadClien)))))
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
                            .addComponent(txtLograCadClien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtEstadoCadClien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(txtCidadeCadClien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtBairroCadClien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(txtNumeroCadClien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtCPFCadClien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtPrimeiroNomeCadClien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtSobrenomeCadClien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtTelCadClien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtCepCadClien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtComplementoCadClien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addComponent(btnCadClien)
                .addContainerGap())
        );

        setBounds(0, 0, 770, 500);
    }// </editor-fold>//GEN-END:initComponents

    private void txtPrimeiroNomeCadClienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPrimeiroNomeCadClienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrimeiroNomeCadClienActionPerformed

    private void txtCPFCadClienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCPFCadClienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCPFCadClienActionPerformed

    private void txtSobrenomeCadClienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSobrenomeCadClienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSobrenomeCadClienActionPerformed

    private void txtTelCadClienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTelCadClienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelCadClienActionPerformed

    private void btnCadClienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadClienActionPerformed
        // TODO add your handling code here:
        adicionarClien();
    }//GEN-LAST:event_btnCadClienActionPerformed

    private void txtEstadoCadClienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEstadoCadClienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEstadoCadClienActionPerformed

    private void txtCepCadClienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCepCadClienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCepCadClienActionPerformed

    private void txtLograCadClienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLograCadClienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLograCadClienActionPerformed

    private void txtNumeroCadClienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNumeroCadClienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNumeroCadClienActionPerformed

    private void txtBairroCadClienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBairroCadClienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBairroCadClienActionPerformed

    private void txtCidadeCadClienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCidadeCadClienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCidadeCadClienActionPerformed

    private void txtComplementoCadClienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtComplementoCadClienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtComplementoCadClienActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCadClien;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField txtBairroCadClien;
    private javax.swing.JTextField txtCPFCadClien;
    private javax.swing.JTextField txtCepCadClien;
    private javax.swing.JTextField txtCidadeCadClien;
    private javax.swing.JTextField txtComplementoCadClien;
    private javax.swing.JTextField txtEstadoCadClien;
    private javax.swing.JTextField txtLograCadClien;
    private javax.swing.JTextField txtNumeroCadClien;
    private javax.swing.JTextField txtPrimeiroNomeCadClien;
    private javax.swing.JTextField txtSobrenomeCadClien;
    private javax.swing.JTextField txtTelCadClien;
    // End of variables declaration//GEN-END:variables
}
