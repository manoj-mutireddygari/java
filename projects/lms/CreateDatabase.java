import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateDatabase {

    public static void main(String[] args) {

        // Create Users Table
        String usersTable = "CREATE TABLE IF NOT EXISTS users ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "username TEXT UNIQUE NOT NULL,"
                + "password TEXT NOT NULL"
                + ");";

        // Create Books Table
        String booksTable = "CREATE TABLE IF NOT EXISTS books ("
                + "book_id INTEGER PRIMARY KEY,"
                + "book_name TEXT NOT NULL,"
                + "author TEXT NOT NULL,"
                + "status TEXT DEFAULT 'Available'"
                + ");";

        // Create Issued Books Table
        String issuedBooksTable = "CREATE TABLE IF NOT EXISTS issued_books ("
                + "issue_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "student_name TEXT NOT NULL,"
                + "book_id INTEGER,"
                + "issue_date TEXT,"
                + "return_date TEXT,"
                + "FOREIGN KEY(book_id) REFERENCES books(book_id)"
                + ");";

        try (
            Connection con = DriverManager.getConnection("jdbc:sqlite:library.db");
            Statement stmt = con.createStatement()
        ) {

            stmt.execute(usersTable);
            stmt.execute(booksTable);
            stmt.execute(issuedBooksTable);

            System.out.println("-----------------------------------");
            System.out.println("Library Database Created Successfully!");
            System.out.println("Database Name : library.db");
            System.out.println("Tables Created:");
            System.out.println("1. users");
            System.out.println("2. books");
            System.out.println("3. issued_books");
            System.out.println("-----------------------------------");

        } catch (SQLException e) {
            System.out.println("Database Creation Failed!");
            e.printStackTrace();
        }

    }
}
