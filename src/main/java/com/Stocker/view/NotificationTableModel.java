package com.Stocker.view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.Stocker.dto.Notification;

public class NotificationTableModel extends AbstractTableModel {
    private List<String> notificacoes;
    private String[] colunas = {"Notificações"};

    public NotificationTableModel(List<Notification> notificacoes) {
        this.notificacoes = new ArrayList<>();
        for (Notification notificacao : notificacoes) {
            this.notificacoes.add(notificacao.toString());
        }
    }

    @Override
    public int getRowCount() {
        return notificacoes.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        String notificacao = notificacoes.get(rowIndex);
        return notificacao.toString();
    }

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }
}
