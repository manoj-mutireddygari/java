package basic;

import java.util.Scanner;

public class p4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("enter the number: ");
        int a = sc.nextInt();
        sc.nextLine();
        while(a>0){
            System.out.print("enter your name: ");
            String b = sc.nextLine();
            System.out.print("enter your age: ");
            int c = sc.nextInt();
            sc.nextLine();
            System.out.println("My name is " + b + " and my age is " + c );
            a--;
        }
    }
}
