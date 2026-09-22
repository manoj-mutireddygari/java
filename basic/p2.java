package basic;
import java.util.*;
public class p2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("enter the number: ");       
        int a = sc.nextInt();
        int b = 1;
        while(b<=a){
           if(b%2==0){
            System.out.println(b);
           }
           b++;
        }
    }
}
