package com.todo;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SwingTodoList {

    private TodoList todoList;
    private DefaultTableModel tableModel;
    private JTable table;
    private JFrame frame = new JFrame("SwingTodoList");
    private JPanel controlPanel = new JPanel(new BorderLayout());
    private JButton addButton = new JButton("Add");
    private JTextField textField = new JTextField(40);
    private JPanel tablePanel = new JPanel();

    public SwingTodoList() {
        todoList = new TodoList();
        tableModel = new TodoListModel(todoList);
        table = new JTable(tableModel);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                int r = table.rowAtPoint(e.getPoint());
                if (r >= 0 && r < table.getRowCount()) {
                    table.setRowSelectionInterval(r, r);
                } else {
                    table.clearSelection();
                }

                final int rowindex = table.getSelectedRow();
                if (rowindex < 0)
                    return;
                if (e.isPopupTrigger() && e.getComponent() instanceof JTable ) {
                    JPopupMenu menuPopup = new JPopupMenu();
                    JMenuItem removeMenuItem = new JMenuItem("Remove");
                    JMenuItem doneMenuItem = new JMenuItem("Done");
                    removeMenuItem.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            todoList.remove(rowindex);
                            table.repaint();
                        }
                    });

                    doneMenuItem.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            todoList.setStatus(rowindex, Status.DONE);
                            table.repaint();
                        }
                    });
                    menuPopup.add(removeMenuItem);
                    menuPopup.add(doneMenuItem);

                    menuPopup.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });

        controlPanel.setLayout(new BorderLayout());
        controlPanel.add(addButton, BorderLayout.EAST);
        controlPanel.add(textField, BorderLayout.CENTER);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = textField.getText();
                if (text != "") {
                    todoList.addItem(text);
                    table.repaint();
                }
            }
        });

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        tablePanel.setLayout(new BorderLayout());
        tablePanel.add(table.getTableHeader(), BorderLayout.PAGE_START);
        tablePanel.add(table, BorderLayout.CENTER);
        tablePanel.add(controlPanel, BorderLayout.SOUTH);
        frame.getContentPane().add(tablePanel, BorderLayout.CENTER);

        frame.pack();
        frame.setVisible(true);

        frame.setSize(600, 300);
        frame.setLocation(200, 200);
    }

    public static void main(String[] args) {
        SwingTodoList swingUrlCrawler = new SwingTodoList();
    }
}
