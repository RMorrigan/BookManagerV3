
import controller.BookController;
import model.BookModel;
import view.BookView;

public class Main {
    public static void main(String[] args) {
        // Initialize MVC components
        BookModel model = new BookModel();
        BookView view = new BookView();
        BookController controller = new BookController(model, view);
        
        // Make the GUI visible
        view.setVisible(true);
    }
}
