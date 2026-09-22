import java.util.Scanner;
import java.util.Date;
public class Functions {
    static Scanner sc= new Scanner(System.in);
    static Backend Backend=new Backend();

    public static void register(){
        try{
            System.out.print("Enter the username: ");
            String userName=sc.nextLine().trim();
            if (userName.isEmpty()) {
                throw new IllegalArgumentException("Username cannot be empty.");
            }
            if(Backend.checkUser(userName)){
                System.out.println("Username already exists! Try logging in.");
                register();
                return;
            }
            System.out.print("Enter the passw1ord: ");
            String password=sc.nextLine().trim();
            if (password.isEmpty()) {
                throw new IllegalArgumentException("Password cannot be empty.");
            }
            if(Backend.createUser(userName,password)){
                System.out.println("User Registered successfully");
            }
        }catch (IllegalArgumentException e) {
            System.out.println("Registration Error: " + e.getMessage());
        }
    }

    public static void login(){
        try{
            System.out.print("Enter the username: ");
            String userName=sc.nextLine().trim();
            if (userName.isEmpty()) {
                    throw new IllegalArgumentException("Username cannot be empty.");
                }
            System.out.print("Enter the password: ");
            String password=sc.nextLine().trim();
            if (password.isEmpty()) {
                    throw new IllegalArgumentException("Password cannot be empty.");
                }
            if(Backend.checkLogin(userName,password)){
                System.out.println("Login Successful");
                Main.Dashboard();
            } else{
                System.out.println("Invalid username or password");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Login Error: " + e.getMessage());
        }
    }


    public static void viewBooks(){
        System.out.println("View Books");
        boolean hasBooks = Backend.passBooks();
        if (!hasBooks) {
            System.out.println("No books available in the database to display.");
        }
    }
    
    public static void addBook(){
        System.out.println("Add Book");
        try{
            System.out.print("Enter the book ID: ");
            String id =sc.nextLine().trim();
            System.out.print("Enter the book Title: ");
            String title =sc.nextLine().trim();
            System.out.print("Enter the book Author: ");
            String author =sc.nextLine().trim();
            System.out.print("Enter the book Quantity: ");
            String quantity =sc.nextLine().trim();
            if(Backend.storeBook(id,title,author,quantity)){
                System.out.println("Book added successfully");
                return;
            }
        }catch (IllegalArgumentException e){
            System.out.println("Error in Adding Book:"+e.getMessage());
        }
    }


    public static void deleteBook(){
        System.out.println("Delete Book");
        try{
            System.out.print("Enter the Book id: ");
            String id=sc.nextLine();
            boolean dataconfirm=Backend.passBook(id);
            if(dataconfirm){
                System.out.print("Are you sure you want to delete this book (yes/no): ");
                String confirm =sc.nextLine().trim().toLowerCase();
                if(confirm.equals("yes")){
                    if(Backend.deleteBook(id)){
                        System.out.println("Book has been deleted successfully!!");
                    }else{
                        System.out.println("Error in deleting the Book");
                    }
                }else{
                    System.out.println("Deletion Cancelled");
                }
            }else{
                System.out.println("Book not Found");
            }
        }catch (Exception e){
            System.out.println("Error in Deleting Book:"+e.getMessage());
        }
    }

    public static void searchBook(){
        System.out.println("Search Book");
        try{
            System.out.print("Enter the book ID:");
            String id=sc.nextLine();
            if(Backend.passBook(id)){
                System.out.println("Book fetched Successfully");
            }else{
                System.out.println("Book not found in the database");
            }

        }catch(Exception e){
            System.out.println("Error in Searching the Book:"+e.getMessage());
        }
    }

    public static void issueBook(){
        System.out.println("Issue Book");
        try{
            System.out.print("Enter the book Id:");
            String id=sc.nextLine();
            if(Backend.passBook(id)){
                System.out.print("Weather you want to Issue this Book (yes/no): ");
                String choice =sc.nextLine().trim().toLowerCase();
                if(choice.equals("yes")){
                    System.out.print("Enter the reciever's name: ");
                    String name=sc.nextLine().trim();
                    System.out.print("Enter the reciever mobile number: ");
                    String phone=sc.nextLine();
                    System.out.print("Enter the quantity of books issuing: ");
                    String quantity=sc.nextLine();
                    String date=new Date().toString();
                    if(Backend.storeLog(id, name, phone, quantity, date)){
                        System.out.println("Successfully Issued the Book!");
                    }
                }else{
                    System.out.println("Canceled Issuing the Book");
                }
            }else{
                System.out.println("Book not Found");
            }
        }catch(Exception e){
            System.out.println("Error in Issuing the Book:"+e.getMessage());
        }

    }
    public static void returnBook(){
        System.out.println("Return Book");
        try{


        }catch(Exception e){
            System.out.println("Error in Returning the Book:"+e.getMessage());
        }
    }

    public static void viewLogs(){
        boolean haslogs = Backend.passLogs();
        if (!haslogs) {
            System.out.println("No logs available in the database to display.");
        }
    }

}
