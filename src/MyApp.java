import java.util.InputMismatchException;
import java.util.Scanner;
import controller.UserController;

public class MyApp {
    private int user_id = 1;
    private final UserController controller;
    private final Scanner in;

    public MyApp(UserController controller){
        this.controller = controller;
        in = new Scanner(System.in);
    }

    // Start application ( Project )
    public void start() {
        System.out.println("Hello, my friend! This is online library system!");
        System.out.println("1. Log in");       // If user has account, choose log in
        System.out.println("2. Registration"); // First time, choose Registration
        System.out.println("0. Exit");         // Else, exit, ( break or stop application )
        while (true) {
            try {
                int option = in.nextInt();
                if(option==1){
                    String nickname,password;
                    System.out.print("Nickname: ");
                    nickname = in.next();
                    System.out.print("Password: ");
                    password = in.next();
                    boolean rep = controller.checkUser(nickname,password);
                    if(rep == true) {
                        System.out.println("You have entered");
                        user_id = controller.getIdByNickName(nickname);
                        if(user_id == 1) {
                            adminOptions();   // If user id is equal to 1, ( 1 it is admin ), so auto it shows ADMIN panel
                        }
                        else{
                            userOptions();   // Else, it shows USER panel
                        }
                    }
                    else{
                        System.out.println("Name or password is wrong!\nFor registration, write 2");
                    }
                }
                else if(option == 2){
                    regisUser();          // Calls registration method
                }
                else{
                    break;
                }
            }catch (InputMismatchException e) {
                // Input must be integer
                System.out.println("Input must be integer");
                in.nextLine();
            } catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }


    // ADMIN OPTIONS ( PANEL )
    private void adminOptions() {
        while (true) {
            System.out.println();
            System.out.println("1. Add book");
            System.out.println("2. Remove book by name");
            System.out.println("3. Show users");
            System.out.println("0. Exit");
            try {
                int option = in.nextInt();
                if(option==1){
                    addBook();
                }
                else if(option == 2){
                    removeBookByName();
                }
                else if(option == 3){
                    showUsers();
                }
               else {
                   break;
                }
        }catch (InputMismatchException e) {
                // Input must be integer
                System.out.println("Input must be integer");
                in.nextLine();
            } catch (Exception e){
                System.out.println(e.getMessage());
            }

            System.out.println("*******************************");
        }
    }
    // ADMIN PANEL METHOD
    // Add book method to add books to DBMS
    public void addBook() {
        String t,a,s,l;
        System.out.print("Title");
        t = in.next();
        System.out.print("Author:");
        a = in.next();
        System.out.print("Subject: ");
        s= in.next();
        System.out.print("Language: ");
        l = in.next();
        String res = controller.addBook(t,a,s,l);
        System.out.println(res);
    }
    // Remove book by name, from DBMS
    public void removeBookByName() {
        String n;
        System.out.print("Name of book: ");
        n = in.next();
        String res = controller.removeBookByName(n);
        System.out.println(res);
    }
    // Show users in DBMS
    public void showUsers(){
        System.out.println("All users:");
        //System.out.println(" Name               | Author         | Genre           | Language");
        String responce = controller.showUsers();
        System.out.println(" "+responce);
    }

    // USER OPTION ( PANEL )
    private void userOptions() {
        while (true) {
            System.out.println("1. Show all books in library");
            System.out.println("2. Show all books by genre");
            System.out.println("3. Show all books by author");
            System.out.println("4. Show all books by language");
            System.out.println("5. Select book");
            System.out.println("6. Show read books");
            System.out.println("0. Exit");
            try {
                int option = in.nextInt();
                if(option==1){
                    showAllBooks();
                }
                else if(option == 2){
                    showBooksBySubject();
                }
                else if(option == 3){
                    showBooksByAuthor();
                }
                else if(option == 4){
                    showBooksByLanguage();
                }
                else if(option == 5){
                    selectBook();
                }
                else if(option == 6){
                    getSelectedBooks();
                }
                else {
                    break;
                }
            }catch (InputMismatchException e) {
                // Input must be integer
                System.out.println("Input must be integer");
                in.nextLine();
            } catch (Exception e){
                System.out.println(e.getMessage());
            }

            System.out.println("*******************************");
        }
    }
    // Show all books from DBMS
    public void showAllBooks(){
        System.out.println("All books at library:");
        System.out.println(" Name               | Author         | Genre           | Language");
        String responce = controller.showAllBooks();
        System.out.println(" "+responce);
    }
    // Show books by subject, which will be writen by user
    public void showBooksBySubject(){
        System.out.println("Choose subject(genre): ");
        System.out.println("Fantasy,Mystery,Thriller,Romance etc.");
        String subject = in.next();
        System.out.println(" Name               | Author          | Language");
        String responce = controller.showBooksBySubject(subject);
        System.out.println(" "+responce);
    }
    // Show books by author, which will be writen by user
    public void showBooksByAuthor(){
        System.out.println("Write name of author: ");
        String author = in.next();
        System.out.println(" Name               | Genre           | Language");
        String responce = controller.showBooksByAuthor(author);
        System.out.println(" "+responce);
    }
    // Show books by language, which will be writen by user
    public void showBooksByLanguage(){
        System.out.println("Write language of book: ");
        String language = in.next();
        System.out.println(" Name               | Author          |Genre         ");
        String responce = controller.showBooksByLanguage(language);
        System.out.println(" "+responce);
    }
    // Registration USER
    public void regisUser(){
        String n,s,nick,pass,mail;
        int a;
        System.out.print("Name: ");
        n = in.next();
        System.out.print("Surname: ");
        s = in.next();
        System.out.print("Age: ");
        a = in.nextInt();
        System.out.print("Nickname: ");
        nick = in.next();
        System.out.print("Password: ");
        pass = in.next();
        System.out.print("Email: ");
        mail = in.next();
        String rep = controller.regisUser(n,s,a,nick,pass,mail);
        System.out.println(rep);
    }
    // Method where user can select books, by inputting book_id
    public void selectBook() {
        System.out.println("Book id: ");
        int book_id = in.nextInt();
        String res = controller.selectBook(user_id, book_id);
        System.out.println(res);
    }
    // Show user's selected books, you need to input user_id
    public void getSelectedBooks(){
        String res = controller.getSelectedBooks(user_id);
        System.out.println(res);
    }
}
