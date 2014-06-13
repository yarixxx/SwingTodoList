package com.todo;

import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class TodoListModel extends DefaultTableModel implements TableModel {
    private TodoList dataSource;
    private String[] columnNames = {"TodoItem", "Status"};

    public TodoListModel(TodoList dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public int getRowCount() {
        if (dataSource != null) {
            return dataSource.getSize();
        }
        return 0;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (columnIndex == 0) {
            return dataSource.getItem(rowIndex);
        }
        if (columnIndex == 1) {
            return dataSource.getItemStatus(rowIndex);
        }
        return null;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        return;
    }

    @Override
    public void addTableModelListener(TableModelListener l) {

    }

    @Override
    public void removeTableModelListener(TableModelListener l) {

    }
}
