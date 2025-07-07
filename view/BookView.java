package view;

import model.Book;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BookView extends JFrame {
    private JTable tb_bookTable;
    private DefaultTableModel tableModel;
    private JTextField txt_titleField, txt_authorField, txt_quantityField, txt_priceField;
    private JButton btn_add, btn_update, btn_delete, btn_clear, btn_searchButton;
    private JTextField txt_searchField;
    
    public BookView() {
        initializeComponents();
        setupLayout();
        setupTable();
    }
    
    private void initializeComponents() {
        setTitle("Book Inventory Manager");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        
        // Input fields
        txt_titleField = new JTextField(20);
        txt_authorField = new JTextField(20);
        txt_quantityField = new JTextField(10);
        txt_priceField = new JTextField(10);
        txt_searchField = new JTextField(20);
        
        // Buttons
        btn_add = new JButton("Add Book");
        btn_update = new JButton("Update Book");
        btn_delete = new JButton("Delete Book");
        btn_clear = new JButton("Clear Fields");
        btn_searchButton = new JButton("Search");
        
        // Table
        String[] columnNames = {"ID", "Title", "Author", "Quantity", "Price"};
        tableModel = new DefaultTableModel(columnNames, 0) { // create a default empty table so it can be loaded from the file
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make table non-editable so they use update button
            }
        };
        tb_bookTable = new JTable(tableModel);
        tb_bookTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }
    
    private void setupLayout() {
        setLayout(new BorderLayout());
        
        // North panel - Search
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.setBorder(BorderFactory.createTitledBorder("Search Books"));
        searchPanel.add(new JLabel("Search:"));
        searchPanel.add(txt_searchField);
        searchPanel.add(btn_searchButton);
        
        // Center panel - Table
        JScrollPane scrollPane = new JScrollPane(tb_bookTable);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Book Inventory"));
        
        // South panel - User Input
        JPanel inputPanel = createInputPanel();
        
        add(searchPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(inputPanel, BorderLayout.SOUTH);
    }
    
    private JPanel createInputPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 4));
        panel.setBorder(BorderFactory.createTitledBorder("Book Information"));
        
        // Row 1: Title and Author
        panel.add(new JLabel("Title:"));
        panel.add(txt_titleField);
        panel.add(new JLabel("Author:"));
        panel.add(txt_authorField);
        
        // Row 2: Quantity and Price
        panel.add(new JLabel("Quantity:"));
        panel.add(txt_quantityField);
        panel.add(new JLabel("Price:"));
        panel.add(txt_priceField);
        
        // Row 3: Buttons
        panel.add(btn_add);
        panel.add(btn_update);
        panel.add(btn_delete);
        panel.add(btn_clear);
        
        return panel;
    }
    
    private void setupTable() {
        tb_bookTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = tb_bookTable.getSelectedRow();
                if (selectedRow >= 0) {
                    getSelectedRow(selectedRow);
                }
            }
        });
    }
    
    private void getSelectedRow(int row) {
        txt_titleField.setText(tableModel.getValueAt(row, 1).toString());
        txt_authorField.setText(tableModel.getValueAt(row, 2).toString());
        txt_quantityField.setText(tableModel.getValueAt(row, 3).toString());
        txt_priceField.setText(tableModel.getValueAt(row, 4).toString());
    }
    
    public void updateTable(ArrayList<Book> books) {
        tableModel.setRowCount(0);
        for (Book book : books) {
            Object[] row = {
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getQuantity(),
                String.format("$%.2f", book.getPrice())
            };
            tableModel.addRow(row);
        }
    }
    
    public void clearFields() {
        txt_titleField.setText("");
        txt_authorField.setText("");
        txt_quantityField.setText("");
        txt_priceField.setText("");
        txt_searchField.setText("");
        tb_bookTable.clearSelection();
    }
    
    public void showMessage(String message, String title, int messageType) {
        JOptionPane.showMessageDialog(this, message, title, messageType);
    }
    
    public boolean showConfirmDialog(String message, String title) {
        int result = JOptionPane.showConfirmDialog(this, message, title, JOptionPane.YES_NO_OPTION);
        return result == JOptionPane.YES_OPTION;
    }
    
    // Getters for input fields
    public String getTitleInput() { return txt_titleField.getText().trim(); }
    public String getAuthorInput() { return txt_authorField.getText().trim(); }
    public String getQuantityInput() { return txt_quantityField.getText().trim(); }
    public String getPriceInput() { return txt_priceField.getText().trim().replaceFirst("^\\$", ""); }
    public String getSearchInput() { return txt_searchField.getText().trim(); }
    
    public int getSelectedBookId() {
        int selectedRow = tb_bookTable.getSelectedRow();
        if (selectedRow >= 0) {
            return (Integer) tableModel.getValueAt(selectedRow, 0);
        }
        return -1;
    }
    
    // Event listener setters
    public void setAddButtonListener(ActionListener listener) {
        btn_add.addActionListener(listener);
    }
    
    public void setUpdateButtonListener(ActionListener listener) {
        btn_update.addActionListener(listener);
    }
    
    public void setDeleteButtonListener(ActionListener listener) {
        btn_delete.addActionListener(listener);
    }
    
    public void setClearButtonListener(ActionListener listener) {
        btn_clear.addActionListener(listener);
    }
    
    public void setSearchButtonListener(ActionListener listener) {
        btn_searchButton.addActionListener(listener);
    }
}