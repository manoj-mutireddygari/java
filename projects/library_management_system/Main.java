import java.util.Scanner;

public class Main {

    static Scanner sc= new Scanner(System.in);

    public static void main(String[] args) {
        while(true){
            System.out.println("\n===== Library Management System =====");
            System.out.println("1.regitser");
            System.out.println("2.login");
            System.out.println("3.exit");

            System.out.print("Enter the choice: ");
            int choice=sc.nextInt();
            sc.nextLine();

            switch(choice){
                case 1:
                    Functions.register();
                    break;
                case 2:
                    Functions.login();
                    break;
                case 3:
                    System.out.println("System stopped");
                    return;
            }
        }
    }

    public static void Dashboard(){
        while(true){
            System.out.println("\n===== Library Management Dashboard =====");
            System.out.println("1.View Books");
            System.out.println("2.Add Book");
            System.out.println("3.Delete Book");
            System.out.println("4.Issue Book");
            System.out.println("5.Return Book");
            System.out.println("6.Search Book");
            System.out.println("7.View Logs");
            System.out.println("8.exit");

            System.out.print("Enter the choice: ");
            int choice=sc.nextInt();
            sc.nextLine();
            switch(choice){
                case 1:
                    Functions.viewBooks();
                    break;
                case 2:
                    Functions.addBook();
                    break;
                case 3:
                    Functions.deleteBook();
                    break;
                case 4:
                    Functions.issueBook();
                    break;
                case 5:
                    Functions.returnBook();
                    break;
                case 6:
                    Functions.searchBook();
                    break;
                case 7:
                    Functions.viewLogs();
                    break;
                case 8:
                    return;
            }
        }
    }
}
