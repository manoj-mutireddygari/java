package training;
import java.util.*;
import java.math.*;

public class Dayone {
    //main
    public static void main(String[] args) {
        Dayone op=new Dayone();
        //op.Primenumber();
        //op.Lengthofnumbers();
        //op.Factorial();
        //op.Reversedigit();
        //op.Table();
        //op.Sumofnumbers();
        //op.amstrongnumber();
        //op.sumofdigits();
        //op.Strongnumber();
    }


    //Functions
    public void Strongnumber(){
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter the number: ");
        int n=sc.nextInt();
        sc.close();
        int n1=n;
        int x=1;
        int sum=0;
        while(n>0){
            int y=n%10;
            n=n/10;
            x=1;
            for(int i=1;i<=y;i++){
                x=x*i;
            }
            sum+=x;
        }
        if(sum==n1){
            System.out.println("Strong number");
        }
        else{
            System.out.println("Not a Strong Number");
        }
    }

    public void sumofdigits() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number: ");
        int n = sc.nextInt();
        sc.close();

        int sum = n;

        while (sum > 9) {
            int temp = 0;

            while (sum > 0) {
                int a = sum % 10;
                sum = sum / 10;
                temp += a;
            }

            sum = temp;
        }

        System.out.println(sum);
    }

    public void amstrongnumber(){
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter the number: ");
        int n=sc.nextInt();
        sc.close();
        int n1=n;
        int n2=n;
        int count=0;
        int result=0;
        while(n>0){
            n=n/10;
            count++;
        }
        while(n1>0){
            int a=n1%10;
            n1=n1/10;
            result += (int) Math.pow(a,count);

        }
        if(result==n2){
            System.out.println("Amstrong number");
        }
        else{
            System.out.println("Not an Amstrong number");
        }
    }

    public void Sumofnumbers(){
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter the number: ");
        int n=sc.nextInt();
        sc.close();
        int sum=0;
        int i;
        for(i=1;i<n;i++){
            if(n%i==0){
                sum=sum+i;
            }
        }
        if(sum==n){
            System.out.println("perfect number");
        }
        else{
            System.out.println("Not perfect number");
        }
    }

    public void Table(){
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter the number: ");
        int n=sc.nextInt();
        sc.close();
        int a;
        for(int i=1;i<=n;i++){
            a=i*2;
            System.out.println(i+" * 2 = "+a);
        }
    }

    public void Primenumber(){
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter the number: ");
        int n=sc.nextInt();
        sc.close();
        int count=0;
        int i;
        for(i=1;i<=n;i++){
            if(n%i==0){
                count++;
            }
        }
        if(count==2){
            System.out.println(("Prime"));
        }
        else{
            System.out.println("Not Prime");
        }
    }
    
    public void Lengthofnumbers(){
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter the number: ");
        int num=sc.nextInt();
        sc.close();
        int count=0;
        while(num>0){
            int a=num%10;
            num=num/10;
            count++;
        }
        System.out.println(count);
    }

    public void Factorial(){
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter the number: ");
        int n=sc.nextInt();
        sc.close();
        int x=1;
        if(n==0){
            System.out.println("factorail of "+n+" is: 1");
        }
        else{
            for(int i=1;i<=n;i++){
                x=x*i;
            }
            System.out.println("factorail of "+n+" is: "+x);
        }
    }

    public void Reversedigit(){
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter the number: ");
        int n=sc.nextInt();
        sc.close();
        int z=0;
        int y;
        while(n>0){
            y=n%10;
            z=z*10+y;
            n=n/10;
        }
        System.out.println(z);
    }
}
