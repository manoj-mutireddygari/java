package ccc;
import java.util.*;

public class Daytwo {
    public static void main(String[] args) {
        Daytwo op=new Daytwo();
        //op.Train();
        //op.Whileloop();
        op.Forloop();
        
    }
    // java code to print the first n natural number using for loop
    public void Forloop(){
        Scanner sc= new Scanner(System.in);
        System.out.print("Enter the natural number: ");
        int n=sc.nextInt();
        for(int i=1;i<=n;i++){
            System.out.println(i);
        }
    }

    // java code to print the first n natural number using while loop
    public void Whileloop(){
        Scanner sc= new Scanner(System.in);
        System.out.print("Enter the natural number: ");
        int n=sc.nextInt();
        int i=1;
        while(i<=n){
            System.out.println(i);
            i++;
        }
    }

    //java code to check the breth using the seat number
    public void Train(){
        Scanner sc= new Scanner(System.in);
        System.out.println("Enter the Seat number: ");
        int seatNumber=sc.nextInt();
        if(seatNumber<=0){
            System.out.println("Enter the valid seat number");
            return;
        }else{
            int remainder = seatNumber % 8;
            switch (remainder) {
                case 1:
                case 4:
                    System.out.println("Seat Number " + seatNumber + " is a Lower berth.");
                    break;
                case 2:
                case 5:
                    System.out.println("Seat Number " + seatNumber + " is a Middle berth.");
                    break;
                case 3:
                case 6:
                    System.out.println("Seat Number " + seatNumber + " is a Upper berth.");
                    break;
                case 7:
                    System.out.println("Seat Number " + seatNumber + " is a Side Lower berth.");
                    break;
                case 0:
                    System.out.println("Seat Number " + seatNumber + " is a Side Upper berth.");
                    break;
                default:
                    System.out.println("Seat Number " + seatNumber + " is a Unknown berth.");
                    break;
            }
        }
        sc.close();
    }
}
