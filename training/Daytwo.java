package training;

import java.util.Scanner;

public class Daytwo {
    public static void main(String[] args) {
        Daytwo op=new Daytwo();
        //op.Pattern();
        //op.righttriangle();
        //op.Patternnumber();
        //op.Samenumber();
        //op.Numbertriangle();
        //op.Rowcolumntriangle();
        //op.Oddeventriangle();
        //op.Starsquare();
        //op.Startriangle();
        //op.Starwrong();
        //op.Starz();
        //op.Stard();
        //op.StarP();
        //op.StarK();
        //op.StarM();
        //op.StarY();
        //op.StarG();
        //op.Nameinx();
        //op.Nameinz();
    }

    //functions
    //printing the name of the company
    public void Nameinz(){
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter the name: ");
        String name=sc.nextLine();
        sc.close();
        int index=0;
        int n=7;
        int length=name.length();
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if((i==0)||(i+j==n-1)||(i==n-1)){
                    System.out.print(name.charAt(index)+" ");
                    index++;
                }
                else{System.out.print("  ");}
            }
            System.out.println();
        }

    }

    //prinitng the name in the x form
    /*M       M
        A   A  
          N    
        O   O  
      J       J */
    public void Nameinx(){
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter the name: ");
        String a=sc.nextLine();
        sc.close();
        int n=a.length();
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if((i==j)||(i+j==n-1)){
                    System.out.print(a.charAt(i));
                    //System.out.print(a.charAt(j));
                }
                else{System.out.print("  ");}
            }
            System.out.println();
        }

    }
    //pattern alphabet G
    /** * * * * * 
      *           
      *           
      *   * * * * 
      *         * 
      * * * * * *  */
    public void StarG(){
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter the number: ");
        int n=sc.nextInt();
        sc.close();
        for(int i=1;i<=n;i++){
            for(int j=1;j<=n;j++){
                if((i==1)||(j==1)||(i==n)||((j==n)&&(i>n/2+1))||((i==n/2+1)&&(j>=n/2))){
                    System.out.print("* ");
                }
                else{System.out.print("  ");}
            }
            System.out.println();
        }
    }

    //pattern apphabet Y
    /**           * 
       *        *   
          *   *     
            *       
            *       
            *       
            *  */
    public void StarY(){
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter the number: ");
        int n=sc.nextInt();
        sc.close();
        for(int i=1;i<=n;i++){
            for(int j=1;j<=n;j++){
                if(((i+j==n+1)&&(j>n/2))||((i==j)&&(j<=n/2))||((j==n/2+1)&&(i>n/2))){
                    System.out.print("* ");
                }
                else{System.out.print("  ");}
            }
            System.out.println();
        }

    }

    //pattern alphabet M
    /* *       * 
       * *   * * 
       *   *   * 
       *       * 
       *       *  */
    public void StarM(){
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter the number: ");
        int n=sc.nextInt();
        sc.close();
        for(int i=1;i<=n;i++){
            for(int j=1;j<=n;j++){
                if((j==1)||(j==n)||((i+j==n+1)&&(j>n/2))||((i==j)&&(j<=n/2))){
                    System.out.print("* ");
                }
                else{System.out.print("  ");}
            }
            System.out.println();
        }

    }

    //pattern of alphabetK
    /**     * 
      *   *   
      * *     
      * *     
      *   *   
      *     *  */

    public void StarK(){
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter the number: ");
        int n=sc.nextInt();
        sc.close();
        for(int i=1;i<=n;i++){
            for(int j=1;j<=n;j++){
                if((j==n/2)||((i+j==n+1)&&(j>n/2))||((i==j)&&j>n/2)){
                    System.out.print("* ");
                }
                else{System.out.print("  ");}
            }
            System.out.println();
        }

    }
    //pattern of alphabetP
    /** * * * * * 
      *         * 
      * * * * * * 
      *           
      *           
      *             */

    public void StarP(){
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter the number: ");
        int n=sc.nextInt();
        sc.close();
        for(int i=1;i<=n;i++){
            for(int j=1;j<=n;j++){
                if((j==1)||((i==1))||((j==n)&&(i<=n/2))||(i==n/2)){
                    System.out.print("* ");
                }
                else{System.out.print("  ");}
            }
            System.out.println();
        }

    }

    // pattern of alphabetD
    /*  * * * *   
        *       * 
        *       * 
        *       * 
        * * * *   */
    public void Stard(){
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter the number: ");
        int n=sc.nextInt();
        sc.close();
        for(int i=1;i<=n;i++){
            for(int j=1;j<=n;j++){
                if(j==1 || (i==1 && j<n) || (i==n && j<n) || (j==n && i!=1 && i!=n)){
                    System.out.print("* ");
                }
                else{System.out.print("  ");}
            }
            System.out.println();
        }

    }

    // pattern of right angled traingle with numbers odd zeros and even ones
    /* * * * * * 
             *   
           *     
         *       
       * * * * * */
    public void Starz(){
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter the number: ");
        int n=sc.nextInt();
        sc.close();
        for(int i=1;i<=n;i++){
            for(int j=1;j<=n;j++){
                if((i==1)||(i+j==n+1)||(i==n)){
                    System.out.print("* ");
                }
                else{System.out.print("  ");}
            }
            System.out.println();
        }

    }

    // pattern of right angled traingle with numbers odd zeros and even ones
      /* *              *
            *       *
                *
            *      *
         *               * n*/
    public void Starwrong(){
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter the number: ");
        int n=sc.nextInt();
        sc.close();
        for(int i=1;i<=n;i++){
            for(int j=1;j<=n;j++){
                if((i==j)||(i+j==n+1)){
                    System.out.print("* ");
                }
                else{System.out.print("  ");}
            }
            System.out.println();
        }

    }

    // pattern of right angled traingle with *
      /* *
         * *
         *   *
         *     * 
         * *  *  * */
    public void Startriangle(){
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter the number: ");
        int n=sc.nextInt();
        sc.close();
        for(int i=1;i<=n;i++){
            for(int j=1;j<=i;j++){
                if((i==1)||(j==i)||(i==n)||(j==1)){
                    System.out.print("* ");
                }
                else{System.out.print("  ");}
            }
            System.out.println();
        }

    }

    // pattern of square with *
      /* ****
         *. *
         *. *
         **** */
    public void Starsquare(){
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter the number: ");
        int n=sc.nextInt();
        sc.close();
        for(int i=1;i<=n;i++){
            for(int j=1;j<=n;j++){
                if((i==1)||(j==1)||(i==n)||(j==n)){
                    System.out.print("* ");
                }
                else{System.out.print("  ");}
            }
            System.out.println();
        }

    }

    // pattern of right angled traingle with numbers odd zeros and even ones
      /* 1
         10
         101*/
    public void Oddeventriangle(){
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter the number: ");
        int n=sc.nextInt();
        sc.close();
        for(int i=1;i<=n;i++){
            for(int j=1;j<=i;j++){
                if(j%2==0){
                    System.out.print("0");
                }
                else{
                    System.out.print("1");
                }
            }
            System.out.println();
        }

    }

    // pattern of right angled traingle with numbers of i*j
      /* 1
         24
         369*/
    public void Rowcolumntriangle(){
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter the number: ");
        int n=sc.nextInt();
        sc.close();
        for(int i=1;i<=n;i++){
            for(int j=1;j<=i;j++){
                System.out.print(i*j);
            }
            System.out.println();
        }

    }

    // pattern of right angled traingle with numbers of i
      /* 1
         22
         333
         4444 */
    public void Numbertriangle(){
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter the number: ");
        int n=sc.nextInt();
        sc.close();
        for(int i=1;i<=n;i++){
            for(int j=1;j<=i;j++){
                System.out.print(i);
            }
            System.out.println();
        }

    }

    // pattern of right angled traingle
      /* *
         **
         ***
         **** */
    public void righttriangle(){
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter the number: ");
        int n=sc.nextInt();
        sc.close();
        for(int i=1;i<=n;i++){
            for(int j=1;j<=i;j++){
                System.out.print("*");
            }
            System.out.println();
        }

    }

    //printing the box full of sequence of numbers
      /*111
        222
        333*/
    public void Samenumber(){
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter the number: ");
        int n=sc.nextInt();
        sc.close();
        for(int i=1;i<=n;i++){
            for(int j=1;j<=n;j++){
                System.out.print(i);
            }
            System.out.println();
        }
    }

    //printing the box full of sequence of numbers
      /*123
        123
        123*/
    public void Patternnumber(){
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter the number: ");
        int n=sc.nextInt();
        sc.close();
        for(int i=1;i<=n;i++){
            for(int j=1;j<=n;j++){
                System.out.print(j);
            }
            System.out.println();
        }
    }

    //printing the pattern of the square full of stars 
      /* ***
         ***
         *** */
    public void Pattern(){
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter the number: ");
        int n=sc.nextInt();
        sc.close();
        for(int i=1;i<=n;i++){
            for(int j=1;j<=n;j++){
                System.out.print("*");
            }
            System.out.println();
        }
    }


}
