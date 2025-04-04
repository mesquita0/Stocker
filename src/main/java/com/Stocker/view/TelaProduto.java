/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package com.Stocker.view;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.Stocker.services.ProductService;
import com.Stocker.entity.Product;
import com.Stocker.entity.User;
import com.Stocker.dto.CreateProductDTO;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Tacio
 */
public class TelaProduto extends javax.swing.JInternalFrame {

    /**
     * Creates new form TelaProduto
     */
    User usuario;
    
    private ProductService productService;
    private List<Product> produtos;
    
    public void cadastrarProduto(){
        try{
           String nome = txt_nome.getText();
           Long barcode = Long.parseLong(txt_codigodebarras.getText());
           Double precoCompra = Double.parseDouble(txt_valorentrada.getText());
           Double precoVenda = Double.parseDouble(txt_valorsaida.getText());
           int quantidade = Integer.parseInt(txt_estoque.getText());
           
           SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
           Date validade = sdf.parse(txt_validade.getText());
           
           CreateProductDTO produto = new CreateProductDTO(barcode, nome, precoCompra, precoVenda, quantidade, validade);
           
       
           Product novoProduto = productService.createProduct(produto);
           
           if(novoProduto != null){
               JOptionPane.showMessageDialog(this, "Produto cadastrado!");
               txt_nome.setText(null);
               txt_codigodebarras.setText(null);
               txt_valorentrada.setText(null);
               txt_valorsaida.setText(null);
               txt_estoque.setText(null);
               txt_validade.setText(null);
           } else {
               JOptionPane.showMessageDialog(this, "Preencha os campos corretamente!", "erro",JOptionPane.ERROR_MESSAGE );
               
           }
                 
        } catch(Exception e){
             JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void pesquisar_produtos() {
        String nomePesquisa = txt_pesquisa.getText(); 
        ProductService productService = new ProductService(usuario);  
        produtos = productService.listProducts(nomePesquisa);    
        
        // Configura a tabela com o modelo personalizado
        jTable1.setModel(new ProductTableModel(produtos));
    }   
    
    public void editar_produtos(){
        int linhaSelecionada = jTable1.getSelectedRow(); 

        if (linhaSelecionada != -1) { // Verifica se alguma linha foi selecionada
            Product produtoSelecionado = produtos.get(linhaSelecionada);
            produtoSelecionado.setName(txt_nome.getText());
            produtoSelecionado.setBarcode(Long.parseLong(txt_codigodebarras.getText()));
            produtoSelecionado.setPurchasePrice(Double.parseDouble(txt_valorentrada.getText()));
            produtoSelecionado.setSellingPrice(Double.parseDouble(txt_valorsaida.getText()));
            produtoSelecionado.setAmount(Integer.parseInt(txt_estoque.getText()));
           
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            
            try {
                produtoSelecionado.setExpiryDate(sdf.parse(txt_validade.getText()));
            } catch (ParseException ex) {
                Logger.getLogger(TelaProduto.class.getName()).log(Level.SEVERE, null, ex);
            }
            
           
            if(produtoSelecionado != null){
               JOptionPane.showMessageDialog(this, "Produto editado!");
               txt_nome.setText(null);
               txt_codigodebarras.setText(null);
               txt_valorentrada.setText(null);
               txt_valorsaida.setText(null);
               txt_estoque.setText(null);
               txt_validade.setText(null);
           } else {
               JOptionPane.showMessageDialog(this, "Preencha os campos corretamente!", "erro",JOptionPane.ERROR_MESSAGE );
               
           }
            
            productService.updateProduct(produtoSelecionado);
        } 
    }

    public void setar_campos() {
        int linhaSelecionada = jTable1.getSelectedRow(); 

        if (linhaSelecionada != -1) { // Verifica se alguma linha foi selecionada
            Product produtoSelecionado = produtos.get(linhaSelecionada);
            txt_nome.setText(produtoSelecionado.getName());
            txt_codigodebarras.setText(produtoSelecionado.getBarcode().toString());
            txt_valorentrada.setText(produtoSelecionado.getPurchasePrice().toString());
            txt_valorsaida.setText(produtoSelecionado.getSellingPrice().toString());
            txt_estoque.setText(produtoSelecionado.getAmount().toString());
            txt_validade.setText(produtoSelecionado.getExpiryDate().toString());
        } 
    }

    public void resetar_campos() {
        txt_nome.setText("");
        txt_codigodebarras.setText("");
        txt_valorentrada.setText("");
        txt_valorsaida.setText("");
        txt_estoque.setText("");
        txt_validade.setText("");
    }

    public void remover_produto() {
        int linhaSelecionada = jTable1.getSelectedRow(); 

        if (linhaSelecionada != -1) {
            productService.deleteProduct(produtos.get(linhaSelecionada));
        } 
    }
    
    public TelaProduto(User usuario) {
        this.usuario = usuario;
        productService = new ProductService(usuario);
        initComponents();
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
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txt_nome = new javax.swing.JTextField();
        txt_valorentrada = new javax.swing.JTextField();
        txt_estoque = new javax.swing.JTextField();
        bnt_adicionar = new javax.swing.JButton();
        bnt_editar = new javax.swing.JButton();
        bnt_remover = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txt_valorsaida = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txt_codigodebarras = new javax.swing.JTextField();
        txt_validade = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        getContentPane().setLayout(null);

        txt_pesquisa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_pesquisaActionPerformed(evt);
            }
        });
        txt_pesquisa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_pesquisaKeyReleased(evt);
            }
        });
        getContentPane().add(txt_pesquisa);
        txt_pesquisa.setBounds(25, 30, 415, 22);

        jButton1.setText("Pesquisar");
        getContentPane().add(jButton1);
        jButton1.setBounds(446, 30, 90, 23);

        pesquisar_produtos();
        
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(25, 70, 1230, 300);

        jLabel1.setText("Nome");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(100, 410, 33, 16);

        jLabel3.setText("Valor Entrada");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(60, 460, 70, 16);

        jLabel4.setText("Validade");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(620, 410, 45, 16);

        jLabel5.setText("Estoque");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(620, 460, 42, 20);
        getContentPane().add(txt_nome);
        txt_nome.setBounds(140, 410, 350, 22);
        getContentPane().add(txt_valorentrada);
        txt_valorentrada.setBounds(140, 460, 172, 22);
        getContentPane().add(txt_estoque);
        txt_estoque.setBounds(680, 460, 64, 22);

        bnt_adicionar.setText("Adicionar");
        bnt_adicionar.setToolTipText("");
        bnt_adicionar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bnt_adicionar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bnt_adicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bnt_adicionarActionPerformed(evt);
            }
        });
        getContentPane().add(bnt_adicionar);
        bnt_adicionar.setBounds(170, 580, 81, 23);

        bnt_editar.setText("Editar");
        bnt_editar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bnt_editar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bnt_editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bnt_editarActionPerformed(evt);
            }
        });
        getContentPane().add(bnt_editar);
        bnt_editar.setBounds(600, 580, 72, 23);

        bnt_remover.setText("Remover");
        bnt_remover.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bnt_remover.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bnt_remover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bnt_removerActionPerformed(evt);
            }
        });
        getContentPane().add(bnt_remover);
        bnt_remover.setBounds(980, 570, 77, 23);

        jLabel2.setText("Valor Saida");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(70, 510, 60, 16);

        txt_valorsaida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_valorsaidaActionPerformed(evt);
            }
        });
        getContentPane().add(txt_valorsaida);
        txt_valorsaida.setBounds(140, 510, 180, 22);

        jLabel7.setText("Codigo de Barras");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(570, 510, 110, 16);
        getContentPane().add(txt_codigodebarras);
        txt_codigodebarras.setBounds(680, 510, 120, 22);

        txt_validade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_validadeActionPerformed(evt);
            }
        });
        getContentPane().add(txt_validade);
        txt_validade.setBounds(680, 410, 190, 22);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_pesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_pesquisaActionPerformed
        // TODO add your handling code here:
        pesquisar_produtos();
    }//GEN-LAST:event_txt_pesquisaActionPerformed

    private void bnt_removerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bnt_removerActionPerformed
        // TODO add your handling code here:
        remover_produto();
        pesquisar_produtos();
        resetar_campos();
    }//GEN-LAST:event_bnt_removerActionPerformed

    private void bnt_adicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bnt_adicionarActionPerformed
        // TODO add your handling code here:
        cadastrarProduto();
        pesquisar_produtos();
    }//GEN-LAST:event_bnt_adicionarActionPerformed

    private void txt_valorsaidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_valorsaidaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_valorsaidaActionPerformed

    private void txt_validadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_validadeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_validadeActionPerformed

    private void txt_pesquisaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_pesquisaKeyReleased
        // TODO add your handling code here:
        pesquisar_produtos();
    }//GEN-LAST:event_txt_pesquisaKeyReleased

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        setar_campos();
    }//GEN-LAST:event_jTable1MouseClicked

    private void bnt_editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bnt_editarActionPerformed
        // TODO add your handling code here:
        editar_produtos();
        pesquisar_produtos();
    }//GEN-LAST:event_bnt_editarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bnt_adicionar;
    private javax.swing.JButton bnt_editar;
    private javax.swing.JButton bnt_remover;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txt_codigodebarras;
    private javax.swing.JTextField txt_estoque;
    private javax.swing.JTextField txt_nome;
    private javax.swing.JTextField txt_pesquisa;
    private javax.swing.JTextField txt_validade;
    private javax.swing.JTextField txt_valorentrada;
    private javax.swing.JTextField txt_valorsaida;
    // End of variables declaration//GEN-END:variables
}
