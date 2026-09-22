package ccc;
import java.util.*;
public class Dayfour {
    public static void main(String[] args) {
        Dayfour op=new Dayfour();
        //op.arrayExchange();
        //op.arraySwap();
        //op.matrix();
        //op.addmatrix();
        //op.transposematrix();
        //op.matrixsumdiagonal();

    }

    public void matrixsumdiagonal(){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the value of m: ");
        int m=sc.nextInt();
        System.out.println("Enter the value of n: ");
        int n=sc.nextInt();

        int [][]mat=new int[m][n];
        System.out.println("Enter the matrix elements:");

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                mat[i][j] = sc.nextInt();
            }
        }
        int ds=0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(i==j||i+j==(n-1)){
                    ds+=mat[i][j];
                }
            }
        }
        System.out.println(ds);
        
    }


    public void transposematrix(){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the value of m: ");
        int m=sc.nextInt();
        System.out.println("Enter the value of n: ");
        int n=sc.nextInt();

        int [][]mat=new int[m][n];
        System.out.println("Enter the matrix elements:");

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                mat[i][j] = sc.nextInt();
            }
        }
        int [][]matrix=new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = mat[j][i];
            }
        }
        
        System.out.println("Transposed matrix:");
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }



    public void addmatrix(){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the value of m: ");
        int m=sc.nextInt();
        System.out.println("Enter the value of n: ");
        int n=sc.nextInt();
        int [][]mat1=new int[m][n];
        System.out.println("Enter the matrix 1 elements:");
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                mat1[i][j] = sc.nextInt();
            }
        }

        System.out.println("Enter the value of p: ");
        int p=sc.nextInt();
        System.out.println("Enter the value of q: ");
        int q=sc.nextInt();
        System.out.println("Enter the matrix 2 elements:");
        int [][]mat2=new int[p][q];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                mat2[i][j] = sc.nextInt();
            }
        }

        int [][]matrix=new int[m][n];
        if(m==p&&n==q){
            for(int i=0;i<m;i++){
                for(int j=0;j<n;j++){
                    matrix[i][j]=mat1[i][j]+mat2[i][j];
                }
            }
        }else{
            System.out.println("Addition not possible");
        }


        System.out.println("Matrix after Addition");
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }


    public void matrix(){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the value of m: ");
        int m=sc.nextInt();
        System.out.println("Enter the value of n: ");
        int n=sc.nextInt();

        int [][]mat=new int[m][n];
        System.out.println("Enter the matrix elements:");

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                mat[i][j] = sc.nextInt();
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(mat[i][j] + " ");
            }
            System.out.println();
        }
    }


    public void arraySwap(){
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
    public void arrayExchange(){
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter the length of the array: ");
        int len=sc.nextInt();
        System.out.print("Enter the number by space: ");
        int[]numbers=new int[len];
        for(int i=0;i<len;i++){
            int n=sc.nextInt();
            numbers[i]=n;
        }
        System.out.println("Enter the number of Roatations: ");
        int r=sc.nextInt();
        while(r-->0){
            int left=0;
            int right=left+1;
            int temp=numbers[0];
            for(int j=0;j<len-1;j++){
                numbers[left]=numbers[right];
                left++;
                right++;
            }
            numbers[len-1]=temp;
        }
        System.out.println(Arrays.toString(numbers));
    }
}
