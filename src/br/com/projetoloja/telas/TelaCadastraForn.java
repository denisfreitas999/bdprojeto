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

public class TelaCadastraForn extends javax.swing.JInternalFrame {
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    ResultSet rs2 = null;
    private String cpf_logado;
    /**
     * Creates new form TelaFuncionario
     */
    public TelaCadastraForn() {
        initComponents();
    }
    
     public TelaCadastraForn(String cpf_logado) {
        this.cpf_logado = cpf_logado;
         System.out.println(cpf_logado);
        initComponents();
        conexao = ModuloConexao.conector();
    }
     private void adicionarForn() {
        String sql = "insert into tbfornecedor (cnpj, nome_da_empresa,telefone,email,id_endereco) values \n" +
"	(?, ?, ?, ?, ?)";
        String sql2 = "select id_endereco from tbendereco where logradouro=? and estado=? "
                + "and cidade=? and bairro=? and numero=? and cep=?";
        
        String sql3 = "insert into tbendereco (logradouro,estado,cidade,bairro,numero,cep,complemento) values\n" +
"	(?, ?, ?, ?, ?, ?, ?);";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtCNPJCadForn.getText());
            pst.setString(2, txtNomeEmpreCadForn.getText());
            pst.setString(3, txtTelefoneCadForn.getText());
            pst.setString(4, txtEmailCadForn.getText());
            if (txtCNPJCadForn.getText().isEmpty() || txtNomeEmpreCadForn.getText().isEmpty() ||
                    txtTelefoneCadForn.getText().isEmpty() || txtLograCadForn.getText().isEmpty() ||
                    txtEstadoCadForn.getText().isEmpty() || txtCidadeCadForn.getText().isEmpty() ||
                    txtBairroCadForn.getText().isEmpty() || txtNumeroCadForn.getText().isEmpty() ||
                    txtCepCadForn.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios!");
            } else {
                PreparedStatement pst2 = conexao.prepareStatement(sql2);    
                pst2.setString(1, txtLograCadForn.getText());
                pst2.setString(2, txtEstadoCadForn.getText());
                pst2.setString(3, txtCidadeCadForn.getText());
                pst2.setString(4, txtBairroCadForn.getText());
                pst2.setString(5, txtNumeroCadForn.getText());
                pst2.setString(6, txtCepCadForn.getText()); 
                rs = pst2.executeQuery();
                
                if(rs.next()){
                    //JOptionPane.showMessageDialog(null, "Id do endereço:" + rs.getString(1));
                    pst.setString(5, rs.getString(1));
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Fornecedor cadastrado com sucesso!");
                }else{
                    
                    PreparedStatement pst3 = conexao.prepareStatement(sql3); 
                    pst3.setString(1, txtLograCadForn.getText());
                    pst3.setString(2, txtEstadoCadForn.getText());
                    pst3.setString(3, txtCidadeCadForn.getText());
                    pst3.setString(4, txtBairroCadForn.getText());
                    pst3.setString(5, txtNumeroCadForn.getText()); 
                    pst3.setString(6, txtCepCadForn.getText()); 
                    pst3.setString(7, txtComplementoCadForn.getText()); 
                    pst3.executeUpdate();
                    
                    PreparedStatement pst4 = conexao.prepareStatement(sql2);
                    pst4.setString(1, txtLograCadForn.getText());
                    pst4.setString(2, txtEstadoCadForn.getText());
                    pst4.setString(3, txtCidadeCadForn.getText());
                    pst4.setString(4, txtBairroCadForn.getText());
                    pst4.setString(5, txtNumeroCadForn.getText());
                    pst4.setString(6, txtCepCadForn.getText());
                    rs2 = pst4.executeQuery();

                    if (rs2.next()) {
                        pst.setString(5, rs2.getString(1));
                        pst.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Fornecedor cadastrado com sucesso!");
                    }
                }
                //pst2.executeUpdate();
                //pst.executeUpdate();
                //Atualizar a tabela no banco de dados com o dados do form             
                txtCNPJCadForn.setText(null);
                txtNomeEmpreCadForn.setText(null);
                txtTelefoneCadForn.setText(null);
                txtEmailCadForn.setText(null);
                txtLograCadForn.setText(null);
                txtEstadoCadForn.setText(null);
                txtCidadeCadForn.setText(null);
                txtBairroCadForn.setText(null);
                txtNumeroCadForn.setText(null);
                txtCepCadForn.setText(null);
                txtComplementoCadForn.setText(null);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            txtCNPJCadForn.setText(null);
            txtNomeEmpreCadForn.setText(null);
            txtTelefoneCadForn.setText(null);
            txtEmailCadForn.setText(null);
            txtLograCadForn.setText(null);
            txtEstadoCadForn.setText(null);
            txtCidadeCadForn.setText(null);
            txtBairroCadForn.setText(null);
            txtNumeroCadForn.setText(null);
            txtCepCadForn.setText(null);
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
        txtNomeEmpreCadForn = new javax.swing.JTextField();
        txtCNPJCadForn = new javax.swing.JTextField();
        txtTelefoneCadForn = new javax.swing.JTextField();
        txtEmailCadForn = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtLograCadForn = new javax.swing.JTextField();
        txtEstadoCadForn = new javax.swing.JTextField();
        btnCadForn = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtCidadeCadForn = new javax.swing.JTextField();
        txtBairroCadForn = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtNumeroCadForn = new javax.swing.JTextField();
        txtCepCadForn = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtComplementoCadForn = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Cadastro Fornecedor");
        setPreferredSize(new java.awt.Dimension(770, 500));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("*CNPJ:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("*Nome da Empresa:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("*Telefone:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Email:");

        txtNomeEmpreCadForn.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtNomeEmpreCadForn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomeEmpreCadFornActionPerformed(evt);
            }
        });

        txtCNPJCadForn.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtCNPJCadForn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCNPJCadFornActionPerformed(evt);
            }
        });

        txtTelefoneCadForn.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtTelefoneCadForn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTelefoneCadFornActionPerformed(evt);
            }
        });

        txtEmailCadForn.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtEmailCadForn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailCadFornActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("*Logradouro:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("*Estado:");

        txtLograCadForn.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtLograCadForn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLograCadFornActionPerformed(evt);
            }
        });

        txtEstadoCadForn.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtEstadoCadForn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEstadoCadFornActionPerformed(evt);
            }
        });

        btnCadForn.setText("Cadastrar");
        btnCadForn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadFornActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("*Cidade:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("*Bairro: ");

        txtCidadeCadForn.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtCidadeCadForn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCidadeCadFornActionPerformed(evt);
            }
        });

        txtBairroCadForn.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtBairroCadForn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBairroCadFornActionPerformed(evt);
            }
        });

        jLabel9.setText("* Campos Obrigatórios");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("*Numero:");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("*CEP:");

        txtNumeroCadForn.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtNumeroCadForn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNumeroCadFornActionPerformed(evt);
            }
        });

        txtCepCadForn.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtCepCadForn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCepCadFornActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setText("Complemento:");

        txtComplementoCadForn.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtComplementoCadForn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtComplementoCadFornActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(290, 290, 290)
                .addComponent(btnCadForn, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addGap(191, 191, 191))
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtLograCadForn, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtEstadoCadForn, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel7))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtCidadeCadForn, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtBairroCadForn, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtEmailCadForn, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTelefoneCadForn, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNomeEmpreCadForn, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCNPJCadForn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel10)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtNumeroCadForn, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtCepCadForn, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(txtComplementoCadForn))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtCNPJCadForn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtNomeEmpreCadForn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtTelefoneCadForn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtEmailCadForn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(txtLograCadForn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtEstadoCadForn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(txtCidadeCadForn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtBairroCadForn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(txtNumeroCadForn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(txtCepCadForn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(txtComplementoCadForn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(36, 36, 36)
                .addComponent(btnCadForn)
                .addContainerGap())
        );

        setBounds(0, 0, 770, 500);
    }// </editor-fold>//GEN-END:initComponents

    private void txtNomeEmpreCadFornActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeEmpreCadFornActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomeEmpreCadFornActionPerformed

    private void txtCNPJCadFornActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCNPJCadFornActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCNPJCadFornActionPerformed

    private void txtTelefoneCadFornActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTelefoneCadFornActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefoneCadFornActionPerformed

    private void txtEmailCadFornActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailCadFornActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailCadFornActionPerformed

    private void btnCadFornActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadFornActionPerformed
        // TODO add your handling code here:
        adicionarForn();
    }//GEN-LAST:event_btnCadFornActionPerformed

    private void txtEstadoCadFornActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEstadoCadFornActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEstadoCadFornActionPerformed

    private void txtCepCadFornActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCepCadFornActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCepCadFornActionPerformed

    private void txtLograCadFornActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLograCadFornActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLograCadFornActionPerformed

    private void txtNumeroCadFornActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNumeroCadFornActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNumeroCadFornActionPerformed

    private void txtBairroCadFornActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBairroCadFornActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBairroCadFornActionPerformed

    private void txtCidadeCadFornActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCidadeCadFornActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCidadeCadFornActionPerformed

    private void txtComplementoCadFornActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtComplementoCadFornActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtComplementoCadFornActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCadForn;
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
    private javax.swing.JTextField txtBairroCadForn;
    private javax.swing.JTextField txtCNPJCadForn;
    private javax.swing.JTextField txtCepCadForn;
    private javax.swing.JTextField txtCidadeCadForn;
    private javax.swing.JTextField txtComplementoCadForn;
    private javax.swing.JTextField txtEmailCadForn;
    private javax.swing.JTextField txtEstadoCadForn;
    private javax.swing.JTextField txtLograCadForn;
    private javax.swing.JTextField txtNomeEmpreCadForn;
    private javax.swing.JTextField txtNumeroCadForn;
    private javax.swing.JTextField txtTelefoneCadForn;
    // End of variables declaration//GEN-END:variables
}
