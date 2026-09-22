public class Backend {
    private static String[][] userDatabase = new String[100][2];
    private static int userCount=0;

    private static String[][] bookDatabase = new String[100][4];
    private static int bookCount=0;

    private static String[][] logDatabase=new String[10000][7];
    private static int logCount=0;

    public boolean storeLog(String id,String name,String phone,String quantity,String date ){
        try{
            for(int i = 0; i < bookCount; i++){
                if(bookDatabase[i][0].equals(id)){
                    int currentCount = Integer.parseInt(bookDatabase[i][3]);
                    int currentqty =Integer.parseInt(quantity);
                    if(currentCount >= currentqty){
                        bookDatabase[i][3] = String.valueOf(currentCount - currentqty);
                        logDatabase[logCount][0]=id;
                        logDatabase[logCount][1]=name;
                        logDatabase[logCount][2]=phone;
                        logDatabase[logCount][3]=quantity;
                        logDatabase[logCount][4]=date;
                        logCount++;
                        return true;
                    }
                }
            }
        }catch(Exception e){
            System.out.println("Error in Storing the log: "+e.getMessage());
        }
        return false;
    }
    
    public boolean passLogs(){
        try{
            if(logCount==0){
                return false;
            }
            System.out.println("\n=== ALL LOGS ===");
            for(int i = 0; i < logCount; i++){
                String id = logDatabase[i][0];
                String name = logDatabase[i][1];
                String phone = logDatabase[i][2];
                String quantity = logDatabase[i][3];
                String date = logDatabase[i][4];
                System.out.println("Book ID: " + id + " | Reciever's Name: " + name + " | Phone Number: " + phone + " | Qty: " + quantity+ " | Date and Time: " + date);
            }
            System.out.println("=======================\n");
            return true;
        }catch(Exception e){
            System.out.println("Error in Printing the Logs: "+e.getMessage());
        }
        return false;
    }

    public boolean storeBook(String id,String title, String author,String quantity){
        try{
            bookDatabase[bookCount][0]= id;
            bookDatabase[bookCount][1]=title;
            bookDatabase[bookCount][2]=author;
            bookDatabase[bookCount][3]=quantity;
            bookCount++;
            return true;
        }catch(Exception e){
            System.out.println("Error in Storing the Book: "+e.getMessage());
        }
        return false;
    }

    public boolean deleteBook(String id){
        try{
            for(int i = 0; i < bookCount; i++){
                if(bookDatabase[i][0].equals(id)){
                    for(int j=i;j<bookCount-1;j++){
                        bookDatabase[j]=bookDatabase[j+1];
                    }
                    bookDatabase[bookCount - 1] = new String[4];
                    bookCount--;
                    return true;
                }
            }
        }catch(Exception e){
            System.out.println("Error while Deleting the Book: "+e.getMessage());
        }
        return false;
    }

    public boolean passBook(String id){
        try{
            if(bookCount==0){
                return false;
            }
            for(int i = 0; i < bookCount; i++){
                if(bookDatabase[i][0].equals(id)){
                    System.out.println("\n=== BOOK DETAILS ==="); 
                    String Id = bookDatabase[i][0];
                    String title = bookDatabase[i][1];
                    String author = bookDatabase[i][2];
                    String quantity = bookDatabase[i][3];
                    System.out.println("ID: " + Id + " | Title: " + title + " | Author: " + author + " | Qty: " + quantity);
                    return true;
                }
            }
        }catch(Exception e){
            System.out.println("Error while Printing the Book: "+e.getMessage());
        }
        return false;
    }
    
    public boolean passBooks(){
        try{
            if(bookCount==0){
                return false;
            }
            System.out.println("\n=== AVAILABLE BOOKS ===");
            for(int i = 0; i < bookCount; i++){
                String id = bookDatabase[i][0];
                String title = bookDatabase[i][1];
                String author = bookDatabase[i][2];
                String quantity = bookDatabase[i][3];
                System.out.println("ID: " + id + " | Title: " + title + " | Author: " + author + " | Qty: " + quantity);
            }
            System.out.println("=======================\n");
            return true;
        }catch(Exception e){
            System.out.println("Error while Printing the Books: "+e.getMessage());
        }
        return false;
    }


    public boolean createUser(String userName,String Password){
        try{
            userDatabase[userCount][0] = userName;
            userDatabase[userCount][1] = Password;
            userCount++;
            return true;
        }catch(Exception e){
            System.out.println("Error while Creating the user: "+e.getMessage());
        }
        return false;
    }

    public boolean checkLogin(String userName,String Password){
        try{
            for(int i = 0; i < userCount; i++){
                if(userDatabase[i][0].equals(userName) && userDatabase[i][1].equals(Password)){
                    return true;
                }
            }
        }catch(Exception e){
            System.out.println("Error while logging in: "+e.getMessage());
        }
        return false;
    }

    public boolean checkUser(String userName){
        try{
            for(int i = 0; i < userCount; i++) {
                if(userDatabase[i][0].equals(userName)){
                    return true;
                }
            }
        }catch(Exception e){
            System.out.println("Error while confirming the user: "+e.getMessage());
        }
        return false;
    }
    
}