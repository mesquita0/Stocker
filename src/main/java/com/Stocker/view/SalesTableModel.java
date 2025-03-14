package com.Stocker.view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.Stocker.dto.Notification;
import com.Stocker.entity.Product;
import com.Stocker.entity.Sale;

public class SalesTableModel extends AbstractTableModel {
    private List<Sale> vendas;
    private String[] colunas = {"Produto", "Preço unidade", "Quantidade", "Total", "Data"};

    public SalesTableModel(List<Sale> vendas) {
        this.vendas = vendas;
    }

    @Override
    public int getRowCount() {
        return vendas.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Sale venda = vendas.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return venda.getProduct().getName();
            case 1:
                return venda.getPrice();
            case 2:
                return venda.getAmount();
            case 3:
                return venda.getPrice() * venda.getAmount();
            case 4:
                return venda.getDate().toString();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }
}
