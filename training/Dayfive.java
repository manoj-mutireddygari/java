package training;

import java.util.Arrays;
import java.util.Scanner;

public class Dayfive {
    //main
    //array
    public static void main(String[] args) {
        Dayfive op=new Dayfive();
        //op.Indexes();
        //op.Createarray();
        //op.Printingarray();
        //op.Assingingvalues();
        //op.Sumarray();
        //op.Maxarray();
        //op.Secondlargestelement();
        //op.Numinarray();
        //op.Removeelement();
        op.Samechar();
    }

    //functions
    public void Samechar(){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the forst word: ");
        String str1 =sc.nextLine();
        System.out.println("Enter the second word: ");
        String str2 =sc.nextLine();
        int l=Math.min(str1.length(),str2.length());
        for (int i=0;i<l;i++){
            if (str1.charAt(i)!=str2.charAt(i)){
                System.out.println(str1.charAt(i)+" "+str2.charAt(i));
            }
        }
    }
    public void Removeelement(){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the number to be deleted: ");
        int target= sc.nextInt();
        int arr[]={10,20,30,40};
        for(int i=0;i<arr.length;i++){
            if(arr[i]==target){
                continue;
            }else{
                System.out.print(arr[i]+" ");
            }
        }


    }
    //finding the number in the array
    public void Numinarray(){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the target: ");
        int target= sc.nextInt();
        int arr[]={10,20,30,40};
        for(int i=0;i<arr.length;i++){
            if(arr[i]==target){
                System.out.println(target+" found at index: "+i);
            }
        }

    }

    //printing second largest number in the array
    public void Secondlargestelement(){
        int arr[]={10,20,30,40};
        int max=0;
        Arrays.sort(arr);
        System.out.println(arr[arr.length-2]);
    }

    //printing the max elemnt in the array
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

    //printing the sum of the array
    public void Sumarray(){
        int arr[]={10,20,30,40};
        int sum=0;
        for(int i=0;i<arr.length;i++){
            sum += arr[i];
        }
        System.out.println(sum);
    }

    //Assigning the numbers to array using the for loop 
    public void Assingingvalues(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the length of the array: ");
        int length=sc.nextInt();
        int[] numbers = new int[length];
        for(int i = 0; i < length; ++i) {
            System.out.println("Enter the value of index:"+i);
            numbers[i] = sc.nextInt();
        }
        System.out.println(Arrays.toString(numbers));
    }

    //prinitng elements in the array using for loop
    public void Printingarray(){
        int arr[]=new int[5];
        for(int i=0;i<arr.length;i++){
            System.out.print(arr[i]);
        }
    }

    //printing the elements using particular index value
    public void Indexes(){
        int a[]={1,2,3,4,5,6,7};
        System.out.println(a[2]);

    }
    
    //creating the array
    public void Createarray(){
        int arr[]=new int[5];
        System.out.println(arr.length);
    }
}
