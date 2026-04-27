package training;

public class Dayeight {
    //main
    public static void main(String[] args) {
        Dayeight op=new Dayeight();
        //op.Removeduplicates();
        //op.Movezeros();
        //System.out.println(op.a);
        //System.out.println(a);
        //Addition(1, 2);
    }
    //functions

    
    //static to static addition function calling without operartor
    public static void Addition(int a ,int b){
        System.out.println(a+b);
    }

    //initializing the global variable of both static and the non static
    //public int a=20;
    //public static int a=20;

    //move all zeros in the array to end of the array
    public void Movezeros(){
        int nums[]={1,0,0,3,15,0,20};
        int left=0;
        for(int right=0;right<nums.length;right++){
            if(nums[right]!=0){
                int temp = nums[right];
                nums[right] = nums[left];
                nums[left] = temp;
                left+=1;
            }
        }
        for (int num:nums) {
            System.out.print(num + " ");
        }
    }

    //remove dupliactes from the sorted array
    public void Removeduplicates(){
        int nums[]={1,2,2,3,3,4,4,5,5};
        int left=0;
        for(int right=1;right<nums.length;right++){
            if(nums[left]!=nums[right]){
                left+=1;
                nums[left]=nums[right];
            }
        }
        for (int i = 0; i < left+1; i++) {
            System.out.print(nums[i] + " ");
        }

    }
}
