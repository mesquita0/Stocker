/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package com.Stocker.view;

import java.util.List;

import com.Stocker.entity.Product;
import com.Stocker.entity.User;
import com.Stocker.services.ProductService;
import com.Stocker.services.SaleService;

/**
 *
 * @author Tacio
 */
public class TelaPedido extends javax.swing.JInternalFrame {

    /**
     * Creates new form TelaPedido
     */
    User usuario;
    
    private ProductService productService;
    private SaleService saleService;
    private List<Product> produtos;
  
    public TelaPedido(User usuario) {
        this.usuario = usuario;
        productService = new ProductService(usuario);
        saleService = new SaleService(usuario);
        initComponents();
    }

    public void cadastrar_venda() {
        int linhaSelecionada = tbl_pedidos.getSelectedRow(); 

        if (linhaSelecionada != -1) { // Verifica se alguma linha foi selecionada
            Product produtoSelecionado = produtos.get(linhaSelecionada);
            int quantidade = Integer.parseInt(jTextField5.getText()); 

            saleService.addToCart(produtoSelecionado, quantidade);
            saleService.confirmSale();
        } 
    }

    public void pesquisar_produtos() {
        String nomePesquisa = txt_pesquisa.getText(); 
        ProductService productService = new ProductService(usuario);  
        produtos = productService.listProducts(nomePesquisa);    
        
        // Configura a tabela com o modelo personalizado
        tbl_pedidos.setModel(new ProductTableModel(produtos));
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
        tbl_pedidos = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        getContentPane().setLayout(null);
        getContentPane().add(txt_pesquisa);
        txt_pesquisa.setBounds(60, 40, 290, 22);

        txt_pesquisa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_pesquisaKeyReleased(evt);
            }
        });

        jButton1.setText("Pesquisar");
        getContentPane().add(jButton1);
        jButton1.setBounds(360, 40, 90, 23);

        pesquisar_produtos();

        jScrollPane1.setViewportView(tbl_pedidos);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(60, 90, 1000, 240);

        jLabel4.setText("Quantidade");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(60, 370, 70, 16);
        getContentPane().add(jTextField5);
        jTextField5.setBounds(140, 370, 310, 22);

        jButton2.setText("Cadastrar venda");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2);
        jButton2.setBounds(530, 370, 144, 30);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        cadastrar_venda();
        pesquisar_produtos();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txt_pesquisaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_pesquisaKeyReleased
        // TODO add your handling code here:
        pesquisar_produtos();
    }//GEN-LAST:event_txt_pesquisaKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTable tbl_pedidos;
    private javax.swing.JTextField txt_pesquisa;
    // End of variables declaration//GEN-END:variables
}
