package com.Stocker.view;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.Stocker.entity.SupplierProduct;

public class SupplierProductTableModel extends AbstractTableModel {
    private List<SupplierProduct> produtos;
    private String[] colunas = {"Nome", "Tempo de entrega", "Preço"};

    public SupplierProductTableModel(List<SupplierProduct> produtos) {
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
        SupplierProduct produto = produtos.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return produto.getProduct().getName();
            case 1:
                return produto.getDeliveryTimeDays();
            case 2:
                return produto.getPrice();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }
}
