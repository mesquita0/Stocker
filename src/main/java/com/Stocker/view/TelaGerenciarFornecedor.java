/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package com.Stocker.view;

import com.Stocker.services.SupplierService;
import com.Stocker.entity.Supplier;
import javax.swing.JOptionPane;
import com.Stocker.entity.User; 
import java.util.List;

/**
 *
 * @author Tacio
 */
public class TelaGerenciarFornecedor extends javax.swing.JInternalFrame {

    /**
     * Creates new form TelaGerenciarFornecedor
     */
    User usuario; 
    
    private SupplierService supplierService;  
    private List<Supplier> fornecedores;
     
    public TelaGerenciarFornecedor(User usuario) {
        this.usuario = usuario;   
        supplierService = new SupplierService(usuario);     
        initComponents();   
    }
    
    public void pesquisar_fornecedores(){ 
        int linhaSelecionada = tbl_gerenciarfornecedores.getSelectedRow(); 

        if (linhaSelecionada != -1) { // Verifica se alguma linha foi selecionada
            Supplier fornecedor = fornecedores.get(linhaSelecionada);
            txt_nome.setText(fornecedor.getName());
            txt_cnpj.setText(fornecedor.getCnpj());
        } 
    }
    
    public void setar_campos(){ 
        int linhaSelecionada = tbl_gerenciarfornecedores.getSelectedRow(); 

        if (linhaSelecionada != -1) { // Verifica se alguma linha foi selecionada
            Supplier fornecedor = fornecedores.get(linhaSelecionada);
            txt_nome.setText(fornecedor.getName());
            txt_cnpj.setText(fornecedor.getCnpj());
        } 
    }

    public void resetar_campos() {
        txt_nome.setText("");
        txt_cnpj.setText("");
    }
    
    public void adicionar_fornecedor(){
        try{
           String nome = txt_nome.getText();
           String cnpj = txt_cnpj.getText();
           
           Supplier novoSupplier = supplierService.createSupplier(nome, cnpj);       
           
           if(novoSupplier != null){ 
               JOptionPane.showMessageDialog(this, "Fornecedor cadastrado!");
               txt_nome.setText(null);
               txt_cnpj.setText(null);

           } else {
               JOptionPane.showMessageDialog(this, "Preencha os campos corretamente!", "erro",JOptionPane.ERROR_MESSAGE );
               
           }
                 
        } catch(Exception e){
             JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void remover_fornecedor(){
        int linhaSelecionada = tbl_gerenciarfornecedores.getSelectedRow(); 

        if (linhaSelecionada != -1) {
            supplierService.deleteSupplier(fornecedores.get(linhaSelecionada));
        } 
    }

    public void editar_fornecedor(){
        int linhaSelecionada = tbl_gerenciarfornecedores.getSelectedRow();
        
        if (linhaSelecionada != -1){
            Supplier fornecedorSelecionado = fornecedores.get(linhaSelecionada);
            fornecedorSelecionado.setName(txt_nome.getText());
            fornecedorSelecionado.setCnpj(txt_cnpj.getText());
             supplierService.updateSupplier(fornecedorSelecionado);
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

        txt_pesquisa = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_gerenciarfornecedores = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txt_nome = new javax.swing.JTextField();
        txt_cnpj = new javax.swing.JTextField();
        txt_id = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        getContentPane().setLayout(null);

        txt_pesquisa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_pesquisaKeyReleased(evt);
            }
        });
        getContentPane().add(txt_pesquisa);
        txt_pesquisa.setBounds(70, 40, 330, 26);

        jButton1.setText("Pesquisar");
        getContentPane().add(jButton1);
        jButton1.setBounds(410, 40, 90, 27);

        tbl_gerenciarfornecedores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_gerenciarfornecedoresMouseClicked(evt);
            }
        });

        pesquisar_fornecedores();

        jScrollPane1.setViewportView(tbl_gerenciarfornecedores);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(70, 80, 1020, 270);

        jLabel1.setText("Nome");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(80, 390, 33, 16);

        jLabel2.setText("CNPJ");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(80, 450, 41, 16);

        jLabel3.setText("Id");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(950, 410, 10, 16);
        getContentPane().add(txt_nome);
        txt_nome.setBounds(130, 390, 400, 26);
        getContentPane().add(txt_cnpj);
        txt_cnpj.setBounds(130, 450, 400, 26);
        getContentPane().add(txt_id);
        txt_id.setBounds(970, 400, 68, 26);

        jButton2.setText("Adicionar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2);
        jButton2.setBounds(180, 560, 110, 27);

        jButton3.setText("Editar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3);
        jButton3.setBounds(450, 560, 76, 27);

        jButton4.setText("Remover");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4);
        jButton4.setBounds(710, 560, 90, 27);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here: 
        remover_fornecedor();
        pesquisar_fornecedores();
        resetar_campos();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        editar_fornecedor();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void txt_pesquisaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_pesquisaKeyReleased
        // TODO add your handling code here: 
        pesquisar_fornecedores();      
    }//GEN-LAST:event_txt_pesquisaKeyReleased

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        adicionar_fornecedor(); 
    }//GEN-LAST:event_jButton2ActionPerformed

    private void tbl_gerenciarfornecedoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_gerenciarfornecedoresMouseClicked
        // TODO add your handling code here:
        setar_campos();
    }//GEN-LAST:event_tbl_gerenciarfornecedoresMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbl_gerenciarfornecedores;
    private javax.swing.JTextField txt_cnpj;
    private javax.swing.JTextField txt_id;
    private javax.swing.JTextField txt_nome;
    private javax.swing.JTextField txt_pesquisa;
    // End of variables declaration//GEN-END:variables
}
