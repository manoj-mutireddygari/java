package training;

import java.util.Scanner;

public class Testing {
    public static void main(String[] args) {
        Testing op=new Testing();
        //op.Sumofdigits();
        op.ReverseString();
    }
    public void Sumofdigits(){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the number: ");
        int n=sc.nextInt();
        sc.close();

        while(n>9){
            int temp=0;
            while(n>0){
                int a=n%10;
                n=n/10;
                temp+=a;
            }
            n=temp;
        }
        System.out.println(n);
    }
    public void ReverseString(){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the String: ");
        String s=sc.nextLine();
        sc.close();

        char[] arr=s.toCharArray();
        int left=0;
        int right=s.length()-1;
        while(left<right){
            char temp=arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
        System.out.println(arr);
    }

}
