package training;

import java.util.Scanner;

public class Dayseven {
    public static void main(String[] args) {
        Dayseven op=new Dayseven();
        //op.Countvowels();
        //op.Removeduplicates();
    }
    //functions
    public void Commonelements(){
        int num1[]={1,2,3,4};
        int num2[]={3,4,5,6};
        
    }
    public void Removeduplicates(){
        int nums[]={1,2,2,3,3,4,5,5};
        int l=nums.length;
        int j=0;
        for(int i=0;i<l;i++){
            if(nums[i]!=nums[j]){
                j++;
                nums[j]=nums[i];
            }

        }
        for (int i = 0; i < j+1; i++) {
            System.out.print(nums[i] + " ");
        }
        

    }

    //program to count vowels
    public void Countvowels(){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the string to count vowels: ");
        String s=sc.nextLine();

        int count=0;
        int l=s.length();

        for(int i=0;i<l;i++){
            if((s.charAt(i)=='a')||(s.charAt(i)=='e')||(s.charAt(i)=='i')||(s.charAt(i)=='o')||(s.charAt(i)=='u')){
                count++;
            }
        }
        System.out.println(count);

    }

}
