package com.Stocker.view;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.Stocker.entity.Supplier;

public class SupplierTableModel extends AbstractTableModel {
    private List<Supplier> fornecedores;
    private String[] colunas = {"Nome", "CNPJ"};

    public SupplierTableModel(List<Supplier> fornecedores) {
        this.fornecedores = fornecedores;
    }

    @Override
    public int getRowCount() {
        return fornecedores.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Supplier produto = fornecedores.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return produto.getName();
            case 1:
                return produto.getCnpj();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }
}
