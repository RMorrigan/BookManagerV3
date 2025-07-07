package controller;

import model.Book;
import model.BookModel;
import view.BookView;
import javax.swing.*;
import java.io.IOException;

public class BookController {
    private BookModel model;
    private BookView view;
    
    public BookController(BookModel model, BookView view) {
        this.model = model;
        this.view = view;
        
        initializeView();
        setupEventHandlers();
    }
    
    private void initializeView() {
        refreshView();
    }
    
    private void setupEventHandlers() {
        view.setAddButtonListener(e -> addBook());
        view.setUpdateButtonListener(e -> updateBook());
        view.setDeleteButtonListener(e -> deleteBook());
        view.setClearButtonListener(e -> clearFields());
        view.setSearchButtonListener(e -> searchBooks());
    }
    
    private void addBook() {
        try {
            String title = view.getTitleInput();
            String author = view.getAuthorInput();
            String quantityStr = view.getQuantityInput();
            String priceStr = view.getPriceInput();
            
            // Validate inputs
            if (!validateInputs(title, author, quantityStr, priceStr)) {
                return;
            }
            
            int quantity = Integer.parseInt(quantityStr);
            double price = Double.parseDouble(priceStr);
            
            // Create and add book
            int id = model.generateUniqueId();
            Book book = new Book(id, title, author, quantity, price);
            model.addBook(book);
            
            // Save to file
            model.saveInventory();
            
            // Refresh view
            refreshView();
            clearFields();
            
            view.showMessage("Book added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            
        } catch (NumberFormatException e) {
            view.showMessage("Invalid number format!", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
            view.showMessage("Error saving to file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void updateBook() {
        try {
            int selectedId = view.getSelectedBookId();
            if (selectedId == -1) {
                view.showMessage("Please select a book to update.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            String title = view.getTitleInput();
            String author = view.getAuthorInput();
            String quantityStr = view.getQuantityInput();
            String priceStr = view.getPriceInput();
            
            // Validate inputs
            if (!validateInputs(title, author, quantityStr, priceStr)) {
                return;
            }
            
            int quantity = Integer.parseInt(quantityStr);
            double price = Double.parseDouble(priceStr);
            
            // Update book
            if (model.updateBook(selectedId, title, author, quantity, price)) {
                model.saveInventory();
                refreshView();
                clearFields();
                view.showMessage("Book updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                view.showMessage("Error updating book!", "Error", JOptionPane.ERROR_MESSAGE);
            }
            
        } catch (NumberFormatException e) {
            view.showMessage("Invalid number format!", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
            view.showMessage("Error saving to file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void deleteBook() {
        try {
            int selectedId = view.getSelectedBookId();
            if (selectedId == -1) {
                view.showMessage("Please select a book to delete!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            Book book = model.findBookById(selectedId);
            if (book == null) {
                view.showMessage("Book not found!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Confirm deletion
            String message = "Are you sure you want to delete this book?\n" + book.toString();
            if (view.showConfirmDialog(message, "Confirm Deletion")) {
                if (model.deleteBook(selectedId)) {
                    model.saveInventory();
                    refreshView();
                    clearFields();
                    view.showMessage("Book deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    view.showMessage("Error deleting book!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            
        } catch (IOException e) {
            view.showMessage("Error saving to file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void searchBooks() {
        String keyword = view.getSearchInput();
        if (keyword.isEmpty()) {
            refreshView();
            return;
        }
        
        var results = model.searchBooks(keyword);
        view.updateTable(results);
        
        if (results.isEmpty()) {
            view.showMessage("No books found matching: " + keyword, "Search Results", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void clearFields() {
        view.clearFields();
        refreshView();
    }
    
    private void refreshView() {
        view.updateTable(model.getBooks());
    }
    
    private boolean validateInputs(String title, String author, String quantityStr, String priceStr) {
        // Validate title
        if (!Book.isValidString(title)) {
            view.showMessage("Title cannot be empty!", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        // Validate author
        if (!Book.isValidString(author)) {
            view.showMessage("Author cannot be empty!", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        // Validate quantity
        try {
            int quantity = Integer.parseInt(quantityStr);
            if (!Book.isValidQuantity(quantity)) {
                view.showMessage("Quantity must be greater than 0", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (NumberFormatException e) {
            view.showMessage("Quantity must be a valid integer!", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        // Validate price
        try {
            double price = Double.parseDouble(priceStr);
            if (!Book.isValidPrice(price)) {
                view.showMessage("Price must be greater than 0!", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (NumberFormatException e) {
            view.showMessage("Price must be a valid number!", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        return true;
    }
}