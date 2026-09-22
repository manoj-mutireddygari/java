import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            System.out.println("SQLite JDBC Driver not found!");
            return;
        }

        while (true) {
            System.out.println("==================================");
            System.out.println("LIBRARY MANAGEMENT SYSTEM");
            System.out.println("==================================");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    registerUser();
                    break;
                case 2:
                    loginUser();
                    break;
                case 3:
                    System.out.println("Thank you for using Library Management System!");
                    return;
                default:
                    System.out.println("Invalid Choice! Please try again.");
            }
        }
    }

    private static Connection getConnection() throws SQLException {

        String url = "jdbc:sqlite:library.db?busy_timeout=5000";

        return DriverManager.getConnection(url);
    }

    private static void registerUser() {
        System.out.print("Enter Username: ");
        String username = scanner.nextLine();

        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        // Step 1: Check if username already exists
        String checkQuery = "SELECT * FROM users WHERE username = ?";
        boolean userExists = false;

        try (
            Connection con = getConnection();
            PreparedStatement pstmt = con.prepareStatement(checkQuery)
        ) {
            pstmt.setString(1, username);
            try (ResultSet rs = pstmt.executeQuery()) {
                userExists = rs.next();
            }
        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
            e.printStackTrace();
            return;
        }

        if (userExists) {
            System.out.println("Username already exists! Please try with a different username.");
            return;
        }

        // Step 2: Insert new user
        String insertQuery = "INSERT INTO users (username, password) VALUES (?, ?)";
        try (
            Connection con = getConnection();
            PreparedStatement pstmt = con.prepareStatement(insertQuery)
        ) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.executeUpdate();
            System.out.println("Registration Successful! You can now login.");
        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void loginUser() {

        System.out.print("Enter Username: ");
        String username = scanner.nextLine();

        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        String query = "SELECT * FROM users WHERE username=? AND password=?";

        boolean loginSuccess = false;

        try (
            Connection con = getConnection();
            PreparedStatement pstmt = con.prepareStatement(query)
        ) {

            pstmt.setString(1, username);
            pstmt.setString(2, password);

            try (ResultSet rs = pstmt.executeQuery()) {

                if (rs.next()) {
                    loginSuccess = true;
                }

            }

        } catch (SQLException e) {

            System.out.println(e.getMessage());
            return;

        }

        if (loginSuccess) {
            System.out.println("Login Successful! Welcome " + username + "!");
            showDashboard();        // Connection is already closed here
        } else {
            System.out.println("Invalid Username or Password");
        }
    }

    private static void showDashboard() {
        while (true) {
            System.out.println("==================================");
            System.out.println("LIBRARIAN DASHBOARD");
            System.out.println("==================================");
            System.out.println("1. Add Book");
            System.out.println("2. Delete Book");
            System.out.println("3. View Books");
            System.out.println("4. Search Book");
            System.out.println("5. Issue Book");
            System.out.println("6. Return Book");
            System.out.println("7. View Issued Books");
            System.out.println("8. Logout");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    deleteBook();
                    break;
                case 3:
                    viewBooks();
                    break;
                case 4:
                    searchBook();
                    break;
                case 5:
                    issueBook();
                    break;
                case 6:
                    returnBook();
                    break;
                case 7:
                    viewIssuedBooks();
                    break;
                case 8:
                    System.out.println("Logged out successfully!");
                    return;
                default:
                    System.out.println("Invalid Choice! Please try again.");
            }
        }
    }

    private static void addBook() {
        System.out.print("Enter Book ID: ");
        int bookId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Book Name: ");
        String bookName = scanner.nextLine();

        System.out.print("Enter Author: ");
        String author = scanner.nextLine();

        String query = "INSERT INTO books (book_id, book_name, author, status) VALUES (?, ?, ?, 'Available')";
        try (
            Connection con = getConnection();
            PreparedStatement pstmt = con.prepareStatement(query)
        ) {
            pstmt.setInt(1, bookId);
            pstmt.setString(2, bookName);
            pstmt.setString(3, author);
            pstmt.executeUpdate();
            System.out.println("Book Added Successfully!");
        } catch (SQLException e) {
            if (e.getMessage().contains("UNIQUE") || e.getMessage().contains("PRIMARY KEY")) {
                System.out.println("Duplicate Book ID! Please use a different Book ID.");
            } else {
                System.err.println("SQL Error: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    private static void deleteBook() {
        System.out.print("Enter Book ID to delete: ");
        int bookId = scanner.nextInt();
        scanner.nextLine();

        // Step 1: Check if book exists
        String checkQuery = "SELECT * FROM books WHERE book_id = ?";
        boolean bookExists = false;

        try (
            Connection con = getConnection();
            PreparedStatement pstmt = con.prepareStatement(checkQuery)
        ) {
            pstmt.setInt(1, bookId);
            try (ResultSet rs = pstmt.executeQuery()) {
                bookExists = rs.next();
            }
        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
            e.printStackTrace();
            return;
        }

        if (!bookExists) {
            System.out.println("Book Not Found");
            return;
        }

        // Step 2: Delete the book
        String deleteQuery = "DELETE FROM books WHERE book_id = ?";
        try (
            Connection con = getConnection();
            PreparedStatement pstmt = con.prepareStatement(deleteQuery)
        ) {
            pstmt.setInt(1, bookId);
            pstmt.executeUpdate();
            System.out.println("Book Deleted");
        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void viewBooks() {
        String query = "SELECT * FROM books";
        try (
            Connection con = getConnection();
            PreparedStatement pstmt = con.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery()
        ) {
            System.out.println("==================================");
            System.out.println("BOOKS LIST");
            System.out.println("==================================");
            System.out.println("Book ID\t\tBook Name\t\tAuthor\t\t\tStatus");
            System.out.println("------------------------------------------------------------");

            boolean hasBooks = false;
            while (rs.next()) {
                hasBooks = true;
                int bookId = rs.getInt("book_id");
                String bookName = rs.getString("book_name");
                String author = rs.getString("author");
                String status = rs.getString("status");
                System.out.println(bookId + "\t\t" + bookName + "\t\t" + author + "\t\t" + status);
            }

            if (!hasBooks) {
                System.out.println("No books found in the library.");
            }
        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void searchBook() {
        System.out.print("Enter Book ID to search: ");
        int bookId = scanner.nextInt();
        scanner.nextLine();

        String query = "SELECT * FROM books WHERE book_id = ?";
        try (
            Connection con = getConnection();
            PreparedStatement pstmt = con.prepareStatement(query)
        ) {
            pstmt.setInt(1, bookId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String bookName = rs.getString("book_name");
                    String author = rs.getString("author");
                    String status = rs.getString("status");

                    System.out.println("==================================");
                    System.out.println("BOOK DETAILS");
                    System.out.println("==================================");
                    System.out.println("Book Name: " + bookName);
                    System.out.println("Author: " + author);
                    System.out.println("Status: " + status);
                } else {
                    System.out.println("Book Not Found");
                }
            }
        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void issueBook() {
        System.out.print("Enter Student Name: ");
        String studentName = scanner.nextLine();

        System.out.print("Enter Book ID: ");
        int bookId = scanner.nextInt();
        scanner.nextLine();

        // Step 1: Check if book exists and is available
        String checkQuery = "SELECT * FROM books WHERE book_id = ?";
        boolean bookFound = false;
        String bookStatus = null;

        try (
            Connection con = getConnection();
            PreparedStatement pstmt = con.prepareStatement(checkQuery)
        ) {
            pstmt.setInt(1, bookId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    bookFound = true;
                    bookStatus = rs.getString("status");
                }
            }
        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
            e.printStackTrace();
            return;
        }

        if (!bookFound) {
            System.out.println("Book Not Found");
            return;
        }

        if (!"Available".equals(bookStatus)) {
            System.out.println("Book Not Available");
            return;
        }

        // Step 2: Update book status to Issued
        String updateQuery = "UPDATE books SET status = 'Issued' WHERE book_id = ?";
        try (
            Connection con = getConnection();
            PreparedStatement pstmt = con.prepareStatement(updateQuery)
        ) {
            pstmt.setInt(1, bookId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
            e.printStackTrace();
            return;
        }

        // Step 3: Insert into issued_books
        String issueDate = LocalDate.now().toString();
        String insertQuery = "INSERT INTO issued_books (student_name, book_id, issue_date) VALUES (?, ?, ?)";
        try (
            Connection con = getConnection();
            PreparedStatement pstmt = con.prepareStatement(insertQuery)
        ) {
            pstmt.setString(1, studentName);
            pstmt.setInt(2, bookId);
            pstmt.setString(3, issueDate);
            pstmt.executeUpdate();
            System.out.println("Book Issued Successfully to " + studentName + "!");
        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void returnBook() {
        System.out.print("Enter Book ID to return: ");
        int bookId = scanner.nextInt();
        scanner.nextLine();

        // Step 1: Check if book exists
        String checkQuery = "SELECT * FROM books WHERE book_id = ?";
        boolean bookExists = false;

        try (
            Connection con = getConnection();
            PreparedStatement pstmt = con.prepareStatement(checkQuery)
        ) {
            pstmt.setInt(1, bookId);
            try (ResultSet rs = pstmt.executeQuery()) {
                bookExists = rs.next();
            }
        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
            e.printStackTrace();
            return;
        }

        if (!bookExists) {
            System.out.println("Book Not Found");
            return;
        }

        // Step 2: Update book status to Available
        String updateBookQuery = "UPDATE books SET status = 'Available' WHERE book_id = ?";
        try (
            Connection con = getConnection();
            PreparedStatement pstmt = con.prepareStatement(updateBookQuery)
        ) {
            pstmt.setInt(1, bookId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
            e.printStackTrace();
            return;
        }

        // Step 3: Update return date in issued_books
        String returnDate = LocalDate.now().toString();
        String updateIssueQuery = "UPDATE issued_books SET return_date = ? WHERE book_id = ? AND return_date IS NULL";
        try (
            Connection con = getConnection();
            PreparedStatement pstmt = con.prepareStatement(updateIssueQuery)
        ) {
            pstmt.setString(1, returnDate);
            pstmt.setInt(2, bookId);
            pstmt.executeUpdate();
            System.out.println("Book Returned Successfully");
        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void viewIssuedBooks() {
        String query = "SELECT * FROM issued_books";
        try (
            Connection con = getConnection();
            PreparedStatement pstmt = con.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery()
        ) {
            System.out.println("==================================");
            System.out.println("ISSUED BOOKS LIST");
            System.out.println("==================================");
            System.out.println("Student Name\t\tBook ID\t\tIssue Date\t\tReturn Date");
            System.out.println("------------------------------------------------------------");

            boolean hasIssuedBooks = false;
            while (rs.next()) {
                hasIssuedBooks = true;
                String studentName = rs.getString("student_name");
                int bookId = rs.getInt("book_id");
                String issueDate = rs.getString("issue_date");
                String returnDate = rs.getString("return_date");
                if (returnDate == null) {
                    returnDate = "Not Returned";
                }
                System.out.println(studentName + "\t\t" + bookId + "\t\t" + issueDate + "\t\t" + returnDate);
            }

            if (!hasIssuedBooks) {
                System.out.println("No books have been issued.");
            }
        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

