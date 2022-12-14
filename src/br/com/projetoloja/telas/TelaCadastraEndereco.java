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

public class TelaCadastraEndereco extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    ResultSet rs2 = null;
    ResultSet rs3 = null;
    private String cpf_logado;

    /**
     * Creates new form TelaFuncionario
     */
    public TelaCadastraEndereco() {
        initComponents();
    }

    public TelaCadastraEndereco(String cpf_logado) {
        this.cpf_logado = cpf_logado;
        System.out.println(cpf_logado);
        initComponents();
        conexao = ModuloConexao.conector();
    }

    private void adicionarEnd() {
        String sql = "insert into tbendereco (logradouro,estado,cidade,bairro,numero,cep,complemento) values\n"
                + "	(?, ?, ?, ?, ?, ?, ?);";

        String sql2 = "select cpf from tbpessoa where cpf=?";

        String sql3 = "insert into tbreside (id_endereco, cpf) values (?,?)";

        String sql4 = "select id_endereco from tbendereco where logradouro=? and estado=? "
                + "and cidade=? and bairro=? and numero=? and cep=?";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtLograCadEnd.getText());
            pst.setString(2, txtEstadoCadEnd.getText());
            pst.setString(3, txtCidadeCadEnd.getText());
            pst.setString(4, txtBairroCadEnd.getText());
            pst.setString(5, txtNumeroCadEnd.getText());
            pst.setString(6, txtCepCadEnd.getText());
            pst.setString(7, txtComplementoCadEnd.getText());
            if (txtLograCadEnd.getText().isEmpty() || txtEstadoCadEnd.getText().isEmpty()
                    || txtCidadeCadEnd.getText().isEmpty() || txtBairroCadEnd.getText().isEmpty()
                    || txtNumeroCadEnd.getText().isEmpty() || txtCepCadEnd.getText().isEmpty()
                    || txtCPFCadEnd.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios!");
            } else {
                //Consulta o cpf registrado
                PreparedStatement pst2 = conexao.prepareStatement(sql2);
                pst2.setString(1, txtCPFCadEnd.getText());
                rs = pst2.executeQuery();
                Boolean valida = false;
                if (rs.next()) {
                    valida = true;
                } else {
                    JOptionPane.showMessageDialog(null, "CPF não existe!");
                }
                if (valida) {
                    
                    
                    //Consulta ID do endereço registrado
                    PreparedStatement pst4 = conexao.prepareStatement(sql4);
                    pst4.setString(1, txtLograCadEnd.getText());
                    pst4.setString(2, txtEstadoCadEnd.getText());
                    pst4.setString(3, txtCidadeCadEnd.getText());
                    pst4.setString(4, txtBairroCadEnd.getText());
                    pst4.setString(5, txtNumeroCadEnd.getText());
                    pst4.setString(6, txtCepCadEnd.getText());
                    rs2 = pst4.executeQuery();

                    if (rs2.next()) {
                        //Update da tabela reside
                        JOptionPane.showMessageDialog(null, "Endereço existente, vinculando ao cpf...");
                        PreparedStatement pst3 = conexao.prepareStatement(sql3);
                        pst3.setString(1, rs2.getString(1));
                        pst3.setString(2, txtCPFCadEnd.getText());
                        pst3.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Endereço cadastrado com sucesso!");
                    }else {
                        JOptionPane.showMessageDialog(null, "Endereço não existente, criando endereço e vinculando ao cpf...");
                        //Cria o endereço
                        pst.executeUpdate();
                        
                         //Consulta ID do endereço registrado
                        PreparedStatement pst5 = conexao.prepareStatement(sql4);
                        pst5.setString(1, txtLograCadEnd.getText());
                        pst5.setString(2, txtEstadoCadEnd.getText());
                        pst5.setString(3, txtCidadeCadEnd.getText());
                        pst5.setString(4, txtBairroCadEnd.getText());
                        pst5.setString(5, txtNumeroCadEnd.getText());
                        pst5.setString(6, txtCepCadEnd.getText());
                        rs3 = pst5.executeQuery();
                        
                        if(rs3.next()){
                            //Update da tabela reside
                            PreparedStatement pst3 = conexao.prepareStatement(sql3);
                            pst3.setString(1, rs3.getString(1));
                            pst3.setString(2, txtCPFCadEnd.getText());
                            pst3.executeUpdate();
                            JOptionPane.showMessageDialog(null, "Endereço cadastrado com sucesso!");
                        }
                         
                    }
                }

                txtLograCadEnd.setText(null);
                txtEstadoCadEnd.setText(null);
                txtCidadeCadEnd.setText(null);
                txtBairroCadEnd.setText(null);
                txtNumeroCadEnd.setText(null);
                txtCepCadEnd.setText(null);
                txtComplementoCadEnd.setText(null);
                txtCPFCadEnd.setText(null);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            txtLograCadEnd.setText(null);
            txtEstadoCadEnd.setText(null);
            txtCidadeCadEnd.setText(null);
            txtBairroCadEnd.setText(null);
            txtNumeroCadEnd.setText(null);
            txtCepCadEnd.setText(null);
            txtComplementoCadEnd.setText(null);
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

        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtLograCadEnd = new javax.swing.JTextField();
        txtEstadoCadEnd = new javax.swing.JTextField();
        btnCadEnd = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtCidadeCadEnd = new javax.swing.JTextField();
        txtBairroCadEnd = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtNumeroCadEnd = new javax.swing.JTextField();
        txtCepCadEnd = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtComplementoCadEnd = new javax.swing.JTextField();
        txtCPFCadEnd = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Cadastro Endereço");
        setPreferredSize(new java.awt.Dimension(770, 500));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("*Logradouro:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("*Estado:");

        txtLograCadEnd.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtLograCadEnd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLograCadEndActionPerformed(evt);
            }
        });

        txtEstadoCadEnd.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtEstadoCadEnd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEstadoCadEndActionPerformed(evt);
            }
        });

        btnCadEnd.setText("Cadastrar");
        btnCadEnd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadEndActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("*Cidade:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("*Bairro: ");

        txtCidadeCadEnd.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtCidadeCadEnd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCidadeCadEndActionPerformed(evt);
            }
        });

        txtBairroCadEnd.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtBairroCadEnd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBairroCadEndActionPerformed(evt);
            }
        });

        jLabel9.setText("* Campos Obrigatórios");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("*Numero:");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("*CEP:");

        txtNumeroCadEnd.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtNumeroCadEnd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNumeroCadEndActionPerformed(evt);
            }
        });

        txtCepCadEnd.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtCepCadEnd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCepCadEndActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setText("Complemento:");

        txtComplementoCadEnd.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtComplementoCadEnd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtComplementoCadEndActionPerformed(evt);
            }
        });

        txtCPFCadEnd.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtCPFCadEnd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCPFCadEndActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setText("*CPF:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(290, 290, 290)
                .addComponent(btnCadEnd, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addGap(191, 191, 191))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtComplementoCadEnd)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel10))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtNumeroCadEnd, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtCepCadEnd, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtLograCadEnd, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtEstadoCadEnd, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel7))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtCidadeCadEnd, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtBairroCadEnd, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 128, Short.MAX_VALUE)
                        .addComponent(jLabel13)
                        .addGap(18, 18, 18)
                        .addComponent(txtCPFCadEnd, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtLograCadEnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtCPFCadEnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel13)))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtEstadoCadEnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(txtCidadeCadEnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtBairroCadEnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(txtNumeroCadEnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtCepCadEnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtComplementoCadEnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addComponent(btnCadEnd)
                .addContainerGap())
        );

        setBounds(0, 0, 770, 500);
    }// </editor-fold>//GEN-END:initComponents

    private void txtLograCadEndActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLograCadEndActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLograCadEndActionPerformed

    private void txtEstadoCadEndActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEstadoCadEndActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEstadoCadEndActionPerformed

    private void btnCadEndActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadEndActionPerformed
        // TODO add your handling code here:
        adicionarEnd();
    }//GEN-LAST:event_btnCadEndActionPerformed

    private void txtCidadeCadEndActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCidadeCadEndActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCidadeCadEndActionPerformed

    private void txtBairroCadEndActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBairroCadEndActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBairroCadEndActionPerformed

    private void txtNumeroCadEndActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNumeroCadEndActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNumeroCadEndActionPerformed

    private void txtCepCadEndActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCepCadEndActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCepCadEndActionPerformed

    private void txtComplementoCadEndActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtComplementoCadEndActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtComplementoCadEndActionPerformed

    private void txtCPFCadEndActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCPFCadEndActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCPFCadEndActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCadEnd;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField txtBairroCadEnd;
    private javax.swing.JTextField txtCPFCadEnd;
    private javax.swing.JTextField txtCepCadEnd;
    private javax.swing.JTextField txtCidadeCadEnd;
    private javax.swing.JTextField txtComplementoCadEnd;
    private javax.swing.JTextField txtEstadoCadEnd;
    private javax.swing.JTextField txtLograCadEnd;
    private javax.swing.JTextField txtNumeroCadEnd;
    // End of variables declaration//GEN-END:variables
}
