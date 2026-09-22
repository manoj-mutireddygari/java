package ccc;
import java.util.*;
public class Dayone {
    public static void main(String[] args) {
        Dayone op=new Dayone();
        //op.Cylinder();
        //op.Voter();
        //op.Arithmetic();
        //op.Largest();
        //op.Triangle();
        
    }
    public void Triangle(){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter three sides of triangle separated by enter:");
        
        // The scanner reads them one by one from the same input stream
        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();

        if (a != 0 && b != 0 && c != 0) {
            if (a == b && b == c) {
                System.out.println("Equilateral triangle");
            } else if (a == b || b == c || a == c) {
                System.out.println("Isosceles");
            } else {
                System.out.println("Scalene triangle");
            }
        } else {
            System.out.println("Sides cannot be zero");
        }
    }
    public void Largest(){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter three numbers (separated by space or enter):");
        
        // The scanner reads them one by one from the same input stream
        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();
        
        if(a>b){
            if(a>c){
                System.out.println("Largest Number is: "+ a);
            }
            else{
                System.out.println("Largest Number is: "+ c);
            }  
        }
        else{
            if(b>c){
                System.out.println("Largest Number is: "+ b);
            }
            else{
                System.out.println("Largest Number is: "+ c);
            }
        }
    }
    public void Arithmetic(){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the first number:");
        int a=sc.nextInt();
        System.out.println("Enter the second number:");
        int b=sc.nextInt();
        
        int sum=a+b;
        int sub=a-b;
        int mul=a*b;
        double div=(double)a/b;
        
        System.out.println("Sum of two numbers: " + a + " + " + b + " = " + sum);
        System.out.println("Sub of two numbers: " + a + " - " + b + " = " + sub);
        System.out.println("Mul of two numbers: " + a + " * " + b + " = " + mul);
        System.out.println("Div of two numbers: " + a + " / " + b + " = " + div);

        sc.close();
    }

    public void Voter(){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter your age:");
        int age=sc.nextInt();
        if(age>=18){    
            System.out.println("You are Eligible to vote");
        }
        else{
            System.out.println("You are not Eligible to vote");
        }
        sc.close();
    }
    public void Cylinder(){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the height of the cylinder:");
        double h=sc.nextDouble();
        System.out.println("Enter the radius of base of the cylinder: ");
        double r=sc.nextDouble();
        
        double lsa=2*(22.0/7)*r*h;
        double tsa=2*(22/7.0)*r*(h+r);
        double vol=(22.0/7)*r*r*h;
        
        System.out.println("Lsa of cylinder:"+ lsa);
        System.out.println("Tsa of cylinder:"+ tsa);
        System.out.println("Volume of the cylinder:"+ vol);
        sc.close();
    }
}
