/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package com.Stocker.view;

import java.util.List;

import com.Stocker.entity.Sale;
import com.Stocker.entity.User;
import com.Stocker.repository.SaleRepository;
import com.Stocker.services.ProductService;
import com.Stocker.services.SaleService;

/**
 *
 * @author Tacio
 */
public class TelaHistorico extends javax.swing.JInternalFrame {

    /**
     * Creates new form TelaHistorico
     */
    private User usuario;
    private SaleService saleService;
    private List<Sale> vendas;

    public TelaHistorico(User usuario) {
        this.usuario = usuario;
        saleService = new SaleService(usuario);
        initComponents();
    }

    public void listar_vendas() {
        vendas = saleService.listSales();    
        
        // Configura a tabela com o modelo personalizado
        tbl_historico.setModel(new SalesTableModel(vendas));
    }  

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_historico = new javax.swing.JTable();

        jTextField1.setText("jTextField1");

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        getContentPane().setLayout(null);

        listar_vendas();
        
        jScrollPane1.setViewportView(tbl_historico);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(30, 80, 1110, 402);

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTable tbl_historico;
    // End of variables declaration//GEN-END:variables
}
