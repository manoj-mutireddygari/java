package training;
import java.util.Scanner;

public class operation {
    public void add(int a, int b){
        int c=a+b;
        System.out.println("a + b = "+c);
    }
    public void sub(int a, int b){
        int c=a-b;
        System.out.println("a - b = "+c);
    }

    //using static keyword
    public static void mul(int a, int b){
        int c=a*b;
        System.out.println("a * b = "+c);
    }

    public void div(int a, int b){
        int c=a/b;
        System.out.println("a / b = "+c);
    }
    public void mod(int a, int b){
        int c=a%b;
        System.out.println("a % b = "+c);
    }


    public static void main(String [] args){
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Enter First Number: ");
        int a = sc.nextInt(); 

        System.out.println("Enter Second Number: ");
        int b = sc.nextInt();

        operation op=new operation();
        op.add(a,b);
        op.sub(a,b);
        op.div(a,b);
        op.mod(a,b);

        //static function calling
        mul(a, b);
    }
}
