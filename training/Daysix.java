package training;

import java.util.Arrays;
import java.util.Scanner;

public class Daysix {
    public static void main(String[] args) {
        Daysix op=new Daysix();
        //op.Maxarray();
        op.Solution();
    }
    //functions
    public void Maxarray(){
        int arr[]={10,20,30,40};
        int max=0;
        for(int i=0;i<arr.length;i++){
            if (max<arr[i]){
                max=arr[i];
            }
            
        }
        System.out.println(max);
    }
    
    public void Solution(){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the command string: ");
        String s=sc.nextLine();
        int l=s.length();

        int nums[]=new int[l];
        char ch[]=new char[l];

        int ni=0,chi=0;

        for(int i=0;i<l;i++){
            if((s.charAt(i)>='0')&&(s.charAt(i)<='9')){
                nums[ni]=s.charAt(i)-'0';
                ni++;
            } else{
                ch[chi]=s.charAt(i);
                chi++;
            }
        }
        int result=nums[0];
        for (int i=0;i<chi;i++){
            if(ch[i]=='+'){
                result+=nums[i+1];
            } else if(ch[i]=='-'){
                result-=nums[i+1];
            } else if(ch[i]=='*'){
                result*=nums[i+1];
            } else if(ch[i]=='/'){
                result/=nums[i+1];
            }
        }
        System.out.println(result);
    }
}
