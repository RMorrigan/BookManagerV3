package model;

import java.io.*;
import java.util.*;

public class BookModel {
    private ArrayList<Book> inventory;
    private static final String FILE_NAME = "books.txt";
    
    public BookModel() {
        inventory = new ArrayList<>();
        loadInventory();
    }
    
    /**
     * Load inventory from file
     */
    public void loadInventory() {
        File file = new File(FILE_NAME);
        
        if (!file.exists()) {
            initializeDefaultData();
            return;
        }
        
        try (Scanner scanner = new Scanner(file)) {
            inventory.clear();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (!line.isEmpty()) {
                    String[] parts = line.split(",");
                    if (parts.length == 5) {
                        try {
                            int id = Integer.parseInt(parts[0]);
                            String title = parts[1];
                            String author = parts[2];
                            int quantity = Integer.parseInt(parts[3]);
                            double price = Double.parseDouble(parts[4]);
                            
                            inventory.add(new Book(id, title, author, quantity, price));
                        } catch (NumberFormatException e) {
                            System.err.println("Invalid data format in line: " + line);
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading inventory: " + e.getMessage());
            initializeDefaultData();
        }
    }
    
    /**
     * Save inventory to file
     */
    public void saveInventory() throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Book book : inventory) {
                writer.println(book.toFile());
            }
        }
    }
    
    /**
     * Initialize default data
     */
    private void initializeDefaultData() {
        inventory.clear();
        inventory.add(new Book(1, "The Hobbit", "J.R.R. Tolkien", 12, 8.99));
        inventory.add(new Book(2, "1984", "George Orwell", 8, 6.50));
        inventory.add(new Book(3, "To Kill a Mockingbird", "Harper Lee", 10, 7.25));
        
        try {
            saveInventory();
        } catch (IOException e) {
            System.err.println("Error saving default data: " + e.getMessage());
        }
    }
    
    /**
     * Get all books
     */
    public ArrayList<Book> getBooks() {
        return new ArrayList<>(inventory);
    }
    
    /**
     * Add a new book
     */
    public void addBook(Book book) {
        inventory.add(book);
    }
    
    /**
     * Update an existing book
     */
    public boolean updateBook(int id, String title, String author, int quantity, double price) {
        Book book = findBookById(id);
        if (book != null) {
            book.setTitle(title);
            book.setAuthor(author);
            book.setQuantity(quantity);
            book.setPrice(price);
            return true;
        }
        return false;
    }
    
    /**
     * Delete a book
     */
    public boolean deleteBook(int id) {
        Book book = findBookById(id);
        if (book != null) {
            inventory.remove(book);
            return true;
        }
        return false;
    }
    
    /**
     * Find book by ID
     */
    public Book findBookById(int id) {
        for (Book book : inventory) {
            if (book.getId() == id) {
                return book;
            }
        }
        return null;
    }
    
    /**
     * Generate unique ID
     */
    public int generateUniqueId() {
        int maxId = 0;
        for (Book book : inventory) {
            if (book.getId() > maxId) {
                maxId = book.getId();
            }
        }
        return maxId + 1;
    }
    
    /**
     * Search books by title or author
     */
    public ArrayList<Book> searchBooks(String keyword) {
        ArrayList<Book> results = new ArrayList<>();
        String searchTerm = keyword.toLowerCase();
        
        for (Book book : inventory) {
            if (book.getTitle().toLowerCase().contains(searchTerm) ||
                book.getAuthor().toLowerCase().contains(searchTerm)) {
                results.add(book);
            }
        }
        return results;
    }
}