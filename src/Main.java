import repository.IBookRep;
import repository.IUserRep;
import repository.BookRep;
import repository.UserRep;
import repository.IAdminRep;
import repository.AdminRep;
import controller.UserController;


public class Main {

    public static void main(String[] args) {
        data.IDB db = new data.SQL();
        // User methods as 'u' from IUserRep
        IUserRep u = new UserRep(db);
        // Admin methods as 'a' for IAdminRep
        IAdminRep a = new AdminRep(db);
        // Book methods as 'b' from IBookRep
        IBookRep b = new BookRep(db);
        // User Controller, where work with all methods
        UserController controller = new UserController(u,b,a);
        // Application works with console, calling methods
        MyApp app = new MyApp(controller);
        // Start the method
        app.start();
    }
}
