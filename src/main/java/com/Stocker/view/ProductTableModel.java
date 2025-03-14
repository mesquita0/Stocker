package com.Stocker.view;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.Stocker.entity.Product;

public class ProductTableModel extends AbstractTableModel {
    private List<Product> produtos;
    private String[] colunas = {"Nome", "Codigo de barra", "Data de validade", "Quantidade"};

    public ProductTableModel(List<Product> produtos) {
        this.produtos = produtos;
    }

    @Override
    public int getRowCount() {
        return produtos.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Product produto = produtos.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return produto.getName();
            case 1:
                return produto.getBarcode();
            case 2:
                return produto.getExpiryDate();
            case 3:
                return produto.getAmount();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }
}
