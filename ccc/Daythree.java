package ccc;
import java.util.*;
public class Daythree {
    public static void main(String[] args) {
        Daythree op=new Daythree();
        //op.Primenumber();
        //op.Toplefttraingle();
        //op.Toprighttraingle();
        //op.Numbertraingle();
        //op.Something();
        //op.array();
        //op.arrayproblem();
        //op.arrayReverse();
        //op.arraycompletelyReverse();
        //op.rangeofarray();
        //op.copyarray();
        op.findarray();
    }
    public void findarray(){
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter the length of the array: ");
        int len=sc.nextInt();
        System.out.print("Enter the number by space: ");
        int[]numbers=new int[len];
        for(int i=0;i<len;i++){
            int n=sc.nextInt();
            numbers[i]=n;
        }
        System.out.print("Enter the element to Search");
        int target=sc.nextInt();
        for(int i=0;i<len;i++){
            if(target==numbers[i]){
                System.out.println("Element "+target+" found in the index: "+i);
                continue;
            }
        }
    }


    public void copyarray(){
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter the length of the array: ");
        int len=sc.nextInt();
        System.out.print("Enter the number by space: ");
        int[]numbers=new int[len];
        int[]new_numbers=new int[len];
        for(int i=0;i<len;i++){
            int n=sc.nextInt();
            numbers[i]=n;
        }
        for(int j=0;j<len;j++){
            new_numbers[j]=numbers[j];
        }
        System.out.println(Arrays.toString(numbers));
        System.out.println(Arrays.toString(new_numbers));
    }

    //finding and printing the maximum and minimum and range of the array
    public void rangeofarray(){
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter the length of the array: ");
        int len=sc.nextInt();
        System.out.print("Enter the number by space: ");
        int[]numbers=new int[len];
        for(int i=0;i<len;i++){
            int n=sc.nextInt();
            numbers[i]=n;
        }
        int max=numbers[0];
        int min=numbers[0];
        for(int j=1;j<len;j++){
            if(numbers[j]>max){
                max=numbers[j];
            }
            if(numbers[j]<min){
                min=numbers[j];
            }
        }
        int range=max-min;
        System.out.println("Maximum value in the array: "+max);
        System.out.println("Minimum element in the array: "+min);
        System.out.println("Range of the array is: "+range);
    }



    //completely reversing the array value in the same array
    public void arraycompletelyReverse(){
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter the length of the array: ");
        int len=sc.nextInt();
        System.out.print("Enter the number by space: ");
        int[]numbers=new int[len];
        for(int i=0;i<len;i++){
            int n=sc.nextInt();
            numbers[i]=n;
        }
        int left=0;
        int right=len-1;
        while(left<right){
            int temp=numbers[left];
            numbers[left]=numbers[right];
            numbers[right]=temp;
            left++;
            right--;
        }
        System.out.println(Arrays.toString(numbers));
    }


    //print the elements in the array in reverse
    public void arrayReverse(){
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter the length of the array: ");
        int len=sc.nextInt();
        System.out.print("Enter the number by space: ");
        int[]numbers=new int[len];
        for(int i=0;i<len;i++){
            int n=sc.nextInt();
            numbers[i]=n;
        }
        for(int j=len-1;j>=0;j--){
            System.out.print(numbers[j]+" ");
            
        }
    }

    //printing the even numbers in the array
    public void arrayproblem(){
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter the length of the array: ");
        int len=sc.nextInt();
        System.out.print("Enter the number by space: ");
        int[]numbers=new int[len];
        for(int i=0;i<len;i++){
            int n=sc.nextInt();
            numbers[i]=n;
        }
        for(int j=0;j<len;j++){
            if(numbers[j]%2==0){
                System.out.print(numbers[j]+ " ");
            }
        }
    }

    //creating the array and storing the elements in the array using user input values
    public void array(){
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter the length of the array: ");
        int len=sc.nextInt();
        System.out.print("Enter the number by space: ");
        int[]numbers=new int[len];
        for(int i=1;i<len;i++){
            int n=sc.nextInt();
            numbers[i]=n;
        }
        System.out.println(Arrays.toString(numbers));
    }

    //to find wheather the number is prime number or not
    public void Primenumber(){
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter the number: ");
        int n=sc.nextInt();
        int count=0;
        for(int i=1;i<=n;i++){
            if(n%i==0){
                count++;
            }
        }
        if(count==2){
            System.out.println("Prime Number");
        }else{
            System.out.println("Not Prime Number");
        }
    }


    //creating a * star design top right angled traingle
    /*  ****
         ***
          **
           *  */
    public void Toprighttraingle(){
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter the number: ");
        int n=sc.nextInt();
        sc.close();
        for(int i=1;i<=n;i++){
            for (int k = 1; k <= i; k++) { 
                System.out.print(" ");
            }
            for(int j=1;j<=n-i+1;j++){
                System.out.print("*");
            }
            System.out.println();
        }
    }

    //creating a * star design top left angled traingle
    public void Toplefttraingle(){
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter the number: ");
        int n=sc.nextInt();
        sc.close();
        for(int i=1;i<=n;i++){
            for(int j=1;j<=n-i+1;j++){
                System.out.print("*");
            }
            System.out.println();
        }
    }

    /*  1
        23
        456
        78910 */
    public void Numbertraingle(){
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter the number: ");
        int n=sc.nextInt();
        sc.close();
        int count=0;
        for(int i=1;i<=n;i++){
            for(int j=1;j<=i;j++){
                count++;
                System.out.print(count);
            }
            System.out.println();
        }
    }
    /*  *      *
        **    **
        ***  ***
        ******** */
    public void Something(){
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter the number: ");
        int n=sc.nextInt();
        sc.close();
        for(int i=1;i<=n;i++){
            for(int j=1;j<=n*2;j++){
                if (j <= i || j > n * 2 - i) {
                System.out.print("*");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

}
