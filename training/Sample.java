package training;

import java.util.Scanner;

public class Sample {
    public static void main(String[] args) {
        System.out.println("Enter the String: ");
        Scanner sc=new Scanner(System.in);
        String s=sc.nextLine();
        int len=s.length();
        char[]chars=s.toCharArray();
        int front=0;
        int back=len-1;
        for(int i=0;i<len;i++){
            for(int j=i+1;j<len;j++){
                if(chars[i]==chars[j]){
                    char temp=chars[len-1];
                    chars[len-1]=chars[j];
                    chars[j]=temp;
                    front++;
                    back--;
                    break;
                }
            }
        }
        System.out.println(chars);
    }
}
