package ccc2;

import java.util.Scanner;

public class linearSearch {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        linearSearch op=new linearSearch();
        System.out.print("Enter the no of elements in the array: ");
        int len=sc.nextInt();
        int [] arr=new int[len];
        System.out.print("Enter the values in the Array with the space: ");
        for(int i=0;i<len;i++){
            arr[i]=sc.nextInt();
        }
        System.out.print("Enter the target variabe: ");
        int t=sc.nextInt();
        int result=op.linearsearch(arr,len,t);

        if (result != -1) {
            System.out.println("Element found at index: " + result);
        } else {
            System.out.println("Element not found in the array.");
        }

    }
    public int linearsearch(int[]arr,int len,int t){
        for(int i=0;i<len;i++){
            if(arr[i]==t){
                return i; 
            }
        }
        return -1;
    }
}
