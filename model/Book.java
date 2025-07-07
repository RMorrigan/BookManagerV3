// Represent a single book in the library
package model;

public class Book {

	// fields
	private int id, quantity;
	private String title;
	private String author;
	private double price;

	// constructor to initialize book details
	public Book(int id, String title, String author, int quantity, double price) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.quantity = quantity;
		this.price = price;
	}

	// Getters for returning book details
	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public int getId() {
		return id;
	}

	public int getQuantity() {
		return quantity;
	}

	public double getPrice() {
		return price;
	}

	// Setters
	public void setTitle(String title) {
		this.title = title;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	// Validation logic

	public static boolean isValidString(String input) {
		return input != null && !input.trim().isEmpty();
	}

	public static boolean isValidQuantity(int quantity) {
		return quantity >= 0;
	}

	public static boolean isValidPrice(double price) {
		return price > 0;
	}

	// Format book information to display information
	@Override
	public String toString() {
		return String.format("ID: %d | Title: %s | Author: %s | Quantity: %d | Price: $%.2f", id, title, author,
				quantity, price);
	}

	public String toFile() {
		return id + "," + title + "," + author + "," + quantity + "," + price;
	}
}
