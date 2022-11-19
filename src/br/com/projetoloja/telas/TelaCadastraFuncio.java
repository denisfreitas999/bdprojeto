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

public class TelaCadastraFuncio extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    ResultSet rs2 = null;
    private String cpf_logado;

    /**
     * Creates new form TelaFuncionario
     */
    public TelaCadastraFuncio() {
        initComponents();
    }

    public TelaCadastraFuncio(String cpf_logado) {
        this.cpf_logado = cpf_logado;
        System.out.println(cpf_logado);
        initComponents();
        conexao = ModuloConexao.conector();
    }

    private void adicionarFuncio() {
        String sql = "insert into tbpessoa(cpf, primeiro_nome, sobrenome, "
                + "telefone) values (?,?,?,?)";

        String sql2 = "insert into tbfuncionario(cpf, login, senha, adm_cpf_cadastro) values (?,?,?,?)";

        String sql3 = "insert into tbendereco (logradouro,estado,cidade,bairro,numero,cep,complemento) values\n"
                + "	(?, ?, ?, ?, ?, ?, ?);";

        String sql4 = "insert into tbreside (id_endereco, cpf) values (?,?)";
        
        String sql5 = "select id_endereco from tbendereco where logradouro=? and estado=? "
                + "and cidade=? and bairro=? and numero=? and cep=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtCPFCadFunc.getText());
            pst.setString(2, txtPrimeiroNomeCadFunc.getText());
            pst.setString(3, txtSobrenomeCadFunc.getText());
            pst.setString(4, txtTelCadFunc.getText());
            if (txtCPFCadFunc.getText().isEmpty() || txtPrimeiroNomeCadFunc.getText().isEmpty()
                    || txtSobrenomeCadFunc.getText().isEmpty() || txtLograCadFunc.getText().isEmpty()
                    || txtEstadoCadFunc.getText().isEmpty() || txtCidadeCadFunc.getText().isEmpty()
                    || txtBairroCadFunc.getText().isEmpty() || txtNumeroCadFunc.getText().isEmpty()
                    || txtCepCadFunc.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios!");
            } else {
                //cadastra pessoa
                pst.executeUpdate();

                //cadastra funcionario
                PreparedStatement pst2 = conexao.prepareStatement(sql2);
                pst2.setString(1, txtCPFCadFunc.getText());
                pst2.setString(2, txtLoginCadFunc.getText());
                pst2.setString(3, txtSenhaCadFunc.getText());
                pst2.setString(4, cpf_logado);
                pst2.executeUpdate();

                //cadstra endereço
                PreparedStatement pst3 = conexao.prepareStatement(sql3);
                pst3.setString(1, txtLograCadFunc.getText());
                pst3.setString(2, txtEstadoCadFunc.getText());
                pst3.setString(3, txtCidadeCadFunc.getText());
                pst3.setString(4, txtBairroCadFunc.getText());
                pst3.setString(5, txtNumeroCadFunc.getText());
                pst3.setString(6, txtCepCadFunc.getText());
                pst3.setString(7, txtComplementoCadFunc.getText());
                pst3.executeUpdate();

                //Consulta ID do endereço registrado
                PreparedStatement pst4 = conexao.prepareStatement(sql5);
                pst4.setString(1, txtLograCadFunc.getText());
                pst4.setString(2, txtEstadoCadFunc.getText());
                pst4.setString(3, txtCidadeCadFunc.getText());
                pst4.setString(4, txtBairroCadFunc.getText());
                pst4.setString(5, txtNumeroCadFunc.getText());
                pst4.setString(6, txtCepCadFunc.getText());
                rs2 = pst4.executeQuery();

                if (rs2.next()) {
                    //Update da tabela reside
                    PreparedStatement pst5 = conexao.prepareStatement(sql4);
                    pst5.setString(1, rs2.getString(1));
                    pst5.setString(2, txtCPFCadFunc.getText());
                    pst5.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Funcionário cadastrado com sucesso!");
                }

                //Limpa os campos
                txtCPFCadFunc.setText(null);
                txtPrimeiroNomeCadFunc.setText(null);
                txtSobrenomeCadFunc.setText(null);
                txtTelCadFunc.setText(null);
                txtLograCadFunc.setText(null);
                txtEstadoCadFunc.setText(null);
                txtCidadeCadFunc.setText(null);
                txtBairroCadFunc.setText(null);
                txtNumeroCadFunc.setText(null);
                txtCepCadFunc.setText(null);
                txtComplementoCadFunc.setText(null);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            txtCPFCadFunc.setText(null);
            txtPrimeiroNomeCadFunc.setText(null);
            txtSobrenomeCadFunc.setText(null);
            txtTelCadFunc.setText(null);
            txtLograCadFunc.setText(null);
            txtEstadoCadFunc.setText(null);
            txtCidadeCadFunc.setText(null);
            txtBairroCadFunc.setText(null);
            txtNumeroCadFunc.setText(null);
            txtCepCadFunc.setText(null);
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
        txtPrimeiroNomeCadFunc = new javax.swing.JTextField();
        txtCPFCadFunc = new javax.swing.JTextField();
        txtSobrenomeCadFunc = new javax.swing.JTextField();
        txtTelCadFunc = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtLograCadFunc = new javax.swing.JTextField();
        txtEstadoCadFunc = new javax.swing.JTextField();
        btnCadFunc = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtCidadeCadFunc = new javax.swing.JTextField();
        txtBairroCadFunc = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtNumeroCadFunc = new javax.swing.JTextField();
        txtCepCadFunc = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtComplementoCadFunc = new javax.swing.JTextField();
        txtLoginCadFunc = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtSenhaCadFunc = new javax.swing.JTextField();
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

        txtPrimeiroNomeCadFunc.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtPrimeiroNomeCadFunc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPrimeiroNomeCadFuncActionPerformed(evt);
            }
        });

        txtCPFCadFunc.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtCPFCadFunc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCPFCadFuncActionPerformed(evt);
            }
        });

        txtSobrenomeCadFunc.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtSobrenomeCadFunc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSobrenomeCadFuncActionPerformed(evt);
            }
        });

        txtTelCadFunc.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtTelCadFunc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTelCadFuncActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("*Logradouro:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("*Estado:");

        txtLograCadFunc.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtLograCadFunc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLograCadFuncActionPerformed(evt);
            }
        });

        txtEstadoCadFunc.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtEstadoCadFunc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEstadoCadFuncActionPerformed(evt);
            }
        });

        btnCadFunc.setText("Cadastrar");
        btnCadFunc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadFuncActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("*Cidade:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("*Bairro: ");

        txtCidadeCadFunc.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtCidadeCadFunc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCidadeCadFuncActionPerformed(evt);
            }
        });

        txtBairroCadFunc.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtBairroCadFunc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBairroCadFuncActionPerformed(evt);
            }
        });

        jLabel9.setText("* Campos Obrigatórios");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("*Numero:");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("*CEP:");

        txtNumeroCadFunc.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtNumeroCadFunc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNumeroCadFuncActionPerformed(evt);
            }
        });

        txtCepCadFunc.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtCepCadFunc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCepCadFuncActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setText("Complemento:");

        txtComplementoCadFunc.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtComplementoCadFunc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtComplementoCadFuncActionPerformed(evt);
            }
        });

        txtLoginCadFunc.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtLoginCadFunc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLoginCadFuncActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setText("Login:");

        txtSenhaCadFunc.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtSenhaCadFunc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSenhaCadFuncActionPerformed(evt);
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
                .addComponent(btnCadFunc, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                                    .addComponent(txtLograCadFunc, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtEstadoCadFunc, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel7))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtCidadeCadFunc, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtBairroCadFunc, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtTelCadFunc, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSobrenomeCadFunc, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPrimeiroNomeCadFunc, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCPFCadFunc, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                                    .addComponent(txtNumeroCadFunc, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtCepCadFunc, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(130, 130, 130)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel14))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtSenhaCadFunc)
                                    .addComponent(txtLoginCadFunc))
                                .addContainerGap())
                            .addComponent(txtComplementoCadFunc)))))
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
                            .addComponent(txtLograCadFunc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtEstadoCadFunc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(txtCidadeCadFunc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtBairroCadFunc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtNumeroCadFunc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel13)
                                .addComponent(txtLoginCadFunc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtCPFCadFunc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtPrimeiroNomeCadFunc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtSobrenomeCadFunc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtTelCadFunc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(txtCepCadFunc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(txtSenhaCadFunc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtComplementoCadFunc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addComponent(btnCadFunc)
                .addContainerGap())
        );

        setBounds(0, 0, 770, 500);
    }// </editor-fold>//GEN-END:initComponents

    private void txtPrimeiroNomeCadFuncActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPrimeiroNomeCadFuncActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrimeiroNomeCadFuncActionPerformed

    private void txtCPFCadFuncActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCPFCadFuncActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCPFCadFuncActionPerformed

    private void txtSobrenomeCadFuncActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSobrenomeCadFuncActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSobrenomeCadFuncActionPerformed

    private void txtTelCadFuncActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTelCadFuncActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelCadFuncActionPerformed

    private void btnCadFuncActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadFuncActionPerformed
        // TODO add your handling code here:
        adicionarFuncio();
    }//GEN-LAST:event_btnCadFuncActionPerformed

    private void txtEstadoCadFuncActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEstadoCadFuncActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEstadoCadFuncActionPerformed

    private void txtCepCadFuncActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCepCadFuncActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCepCadFuncActionPerformed

    private void txtLograCadFuncActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLograCadFuncActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLograCadFuncActionPerformed

    private void txtNumeroCadFuncActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNumeroCadFuncActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNumeroCadFuncActionPerformed

    private void txtBairroCadFuncActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBairroCadFuncActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBairroCadFuncActionPerformed

    private void txtCidadeCadFuncActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCidadeCadFuncActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCidadeCadFuncActionPerformed

    private void txtComplementoCadFuncActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtComplementoCadFuncActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtComplementoCadFuncActionPerformed

    private void txtLoginCadFuncActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLoginCadFuncActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLoginCadFuncActionPerformed

    private void txtSenhaCadFuncActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSenhaCadFuncActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSenhaCadFuncActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCadFunc;
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
    private javax.swing.JTextField txtBairroCadFunc;
    private javax.swing.JTextField txtCPFCadFunc;
    private javax.swing.JTextField txtCepCadFunc;
    private javax.swing.JTextField txtCidadeCadFunc;
    private javax.swing.JTextField txtComplementoCadFunc;
    private javax.swing.JTextField txtEstadoCadFunc;
    private javax.swing.JTextField txtLoginCadFunc;
    private javax.swing.JTextField txtLograCadFunc;
    private javax.swing.JTextField txtNumeroCadFunc;
    private javax.swing.JTextField txtPrimeiroNomeCadFunc;
    private javax.swing.JTextField txtSenhaCadFunc;
    private javax.swing.JTextField txtSobrenomeCadFunc;
    private javax.swing.JTextField txtTelCadFunc;
    // End of variables declaration//GEN-END:variables
}
