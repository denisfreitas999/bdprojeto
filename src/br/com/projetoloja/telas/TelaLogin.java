/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projetoloja.telas;
import java.sql.*;
import br.com.projetoloja.dal.ModuloConexao;
import java.awt.Color;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuário
 */
public class TelaLogin extends javax.swing.JFrame {
    //Variáveis de conexão
    Connection conexao = null;
    PreparedStatement pst = null;
    PreparedStatement pst2 = null;
    ResultSet rs = null;
    ResultSet rs2 = null;

    
    //Método para efetuar login
    public void logar(){
        //String sql = "select * from tbadministrador where login=? and senha=?";
         String sql = "select ad.login, ad.senha, pe.primeiro_nome, pe.sobrenome, pe.cpf "
                 + "from tbadministrador ad join tbpessoa pe on (ad.cpf = pe.cpf) "
                 + "where ad.login =? and ad.senha=?";
        //String sql2 = "select * from tbfuncionario where login=? and senha=?";
        String sql2 = "select fu.login, fu.senha, pe.primeiro_nome, pe.sobrenome, pe.cpf "
                + "from tbfuncionario fu join tbpessoa pe on (pe.cpf = fu.cpf) "
                + "where fu.login =? and fu.senha=?";
       
        try {
            //Captura das informações das labels de texto para verificação
            //no banco de dados.
            
            //Administrador
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtUsuario.getText());
            String captura = new String(txtSenha.getPassword());
            pst.setString(2, captura);
            
            //Funcionário   
            pst2 = conexao.prepareStatement(sql2);
            pst2.setString(1, txtUsuario.getText());
            String captura2 = new String(txtSenha.getPassword());
            pst2.setString(2, captura2);
            
            
            //Execução da consulta ao banco de dados
            rs = pst.executeQuery();
            rs2 = pst2.executeQuery();
            
            //Se existir usuáro e senha correspondente no banco temos:

            if (rs.next()){
//                String sql3 = "select * from tbpessoa pe join tbadministrador ad on (pe.cpf = ad.cpf) where pe.cpf =?";
//                PreparedStatement pst3 = conexao.prepareStatement(sql3);
//                pst3.setString(1, rs.getString(1));
//                ResultSet rs3 = pst3.executeQuery();
//                String nomeCompleto = new String();
//                while(rs3.next()){
//                    //System.out.println(rs3.getString(1));
//                    //System.out.println(rs3.getString(2));
//                    //System.out.println(rs3.getString(3));
//                    //System.out.println(rs3.getString(4));
//                    //System.out.println(rs3.getString(5));
//                    //System.out.println(rs3.getString(6));
//                    //System.out.println(rs3.getString(7));
//                    nomeCompleto = rs3.getString(2) + " " + rs3.getString(3);
//                }
                String nomeCompleto =  rs.getString(3) + " " + rs.getString(4);
                //Instância e abertura da tela principal
                TelaPrincipal principal = new TelaPrincipal(rs.getString(5));
                principal.setVisible(true);
                TelaPrincipal.menAdm.setEnabled(true);
                TelaPrincipal.lblUsuario.setText(nomeCompleto);
                TelaPrincipal.lblUsuario.setForeground(Color.red);
                //TelaPrincipal.lblUsuario.setText(rs.getString());
                this.dispose();
                conexao.close();
            }else if (rs2.next()){
//                String sql4 = "select * from tbpessoa pe join tbfuncionario fu on (pe.cpf = fu.cpf) where pe.cpf =?";
//                PreparedStatement pst4 = conexao.prepareStatement(sql4);
//                pst4.setString(1, rs2.getString(1));
//                ResultSet rs4 = pst4.executeQuery();
//                String nomeCompleto2 = new String();
//                while(rs4.next()){
//                    nomeCompleto2 = rs4.getString(2) + " " + rs4.getString(3);
//                }
                String nomeCompleto2 =  rs2.getString(3) + " " + rs2.getString(4);
                //Instância e abertura da tela principal
                TelaPrincipal principal = new TelaPrincipal(rs2.getString(5));
                principal.setVisible(true);
                TelaPrincipal.lblUsuario.setText(nomeCompleto2);
                this.dispose();
                conexao.close();
            }else{
                JOptionPane.showMessageDialog(null, "Usuario e/ou senha inválidos.");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    /**
     * Creates new form TelaLogin
     */
    public TelaLogin() {
        initComponents();
        conexao = ModuloConexao.conector();
        System.out.println(conexao);
        if (conexao != null){
            lblStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/projetoloja/icones/dbok.png")));
        }else{
            lblStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/projetoloja/icones/dberror.png")));
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
        txtUsuario = new javax.swing.JTextField();
        btnLogin = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtSenha = new javax.swing.JPasswordField();
        lblStatus = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("BD Loja - Login");
        setResizable(false);

        jLabel1.setText("Usuário");

        jLabel2.setText("Senha");

        btnLogin.setText("Login");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("Bem-vindo! Efetue seu login:");

        lblStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/projetoloja/icones/dberror.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtUsuario)
                                .addComponent(txtSenha, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)))
                        .addContainerGap(69, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47)
                        .addComponent(lblStatus)
                        .addGap(45, 45, 45))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(btnLogin)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                        .addComponent(lblStatus)
                        .addGap(24, 24, 24))))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        // TODO add your handling code here:
        //Executando o método logar()
        logar();
    }//GEN-LAST:event_btnLoginActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
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
    private javax.swing.JLabel lblStatus;
    private javax.swing.JPasswordField txtSenha;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
