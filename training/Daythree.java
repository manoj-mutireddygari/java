package training;
import java.util.*;

public class Daythree {
    //main
    //strings
    public static void main(String[] args) {
        Daythree op=new Daythree();
        //op.Lengthofstring();
        //op.Wordsinstring();
        //op.Extractnumbers();
        //op.Extractcharacters();
        //op.Extractspecialcharacters();
        //op.Numtospecial();
        //op.Voweltospecial();
        //op.Removevowel();
        //op.Reversevowel();
        //op.ReverseString();
        //op.Countchar();
        //op.Spaceremove();

        
    }

    //functions
    //remove space in the string
    public void Spaceremove(){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the string: ");
        String s=sc.nextLine();
        char b[]=s.toCharArray();
        int n=s.length();
        int i=0;
    }

    //highest count of char in string
    public void Countchar(){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the string: ");
        String s=sc.nextLine();
        System.out.println("Enter the string: ");
        char f=sc.next().charAt(0);
        char b[]=s.toCharArray();
        int l=s.length();
        int count=0;
        for(int i=0;i<l;i++){
            if(b[i]==f){
                count++;
            }
        }
        System.out.println(count);
    }

    //reverse the string
    public void ReverseString(){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the string: ");
        String s=sc.nextLine();
        char b[]=s.toCharArray();
        int l=s.length();
        int i=0;
        int j=l-1;
        while(i<j){
            char temp=b[i];
            b[i]=b[j];
            b[j]=temp;
            i++;
            j--;
        }
        System.out.println(b);
    }

    //reverse the vowels
    public void Reversevowel(){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the string: ");
        String s=sc.nextLine();
        char b[]=s.toCharArray();
        int l=s.length();
        for(int i=0;i<l;i++){
            if((b[i]=='A')||(b[i]=='E')||(b[i]=='I')||(b[i]=='O')||(b[i]=='U')
                ||(b[i]=='a')||(b[i]=='e')||(b[i]=='i')||(b[i]=='o')||(b[i]=='u')){
                for(int j=i+1;j<l;j++){
                    if((b[j]=='A')||(b[j]=='E')||(b[j]=='I')||(b[j]=='O')||(b[j]=='U')
                      ||(b[j]=='a')||(b[j]=='e')||(b[j]=='i')||(b[j]=='o')||(b[j]=='u')){
                        char temp=b[i];
                        b[i]=b[j];
                        b[j]=temp;

                    }
                }
            }
        }
        System.out.println(b);
    }
    
    //delete voels int he sstring
    public void Removevowel(){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the string: ");
        String s=sc.nextLine();
        char b[]=s.toCharArray();
        int l=s.length();
        for(int i=0;i<l;i++){
            if((b[i]=='A')||(b[i]=='E')||(b[i]=='I')||(b[i]=='O')||(b[i]=='U')
                ||(b[i]=='a')||(b[i]=='e')||(b[i]=='i')||(b[i]=='o')||(b[i]=='u')){
                continue;
            }
            else{
                System.out.print(b[i]);
            }
        }
    }


    //replce vowels with $
    public void Voweltospecial(){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the string: ");
        String s=sc.nextLine();
        char b[]=s.toCharArray();
        int l=s.length();
        for(int i=0;i<l;i++){
            if(((b[i]=='A')||(b[i]=='E')||(b[i]=='I')||(b[i]=='O')||(b[i]=='U'))
                ||(b[i]=='a')||(b[i]=='e')||(b[i]=='i')||(b[i]=='o')||(b[i]=='u')){
                b[i]='$';
            }
        }
        System.out.println(b);
    }

    //REPLACE THE NUMBER WITH SPEACIAL CHARACTERS
    public void Numtospecial(){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the string: ");
        String s=sc.nextLine();
        char b[]=s.toCharArray();
        int l=s.length();
        for(int i=0;i<l;i++){
            if((b[i]>='0')&&(b[i]<='9')){
                b[i]='@';
            }
        }
        System.out.println(b);

    }

    //extarct special characters
    public void Extractspecialcharacters(){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the string: ");
        String s=sc.nextLine();
        char b[]=s.toCharArray();
        int l=s.length();
        for(int i=0;i<l;i++){
            if(!(((b[i]>='A')&&(b[i]<='Z'))||((b[i]>='a')&&(b[i]<='z'))||((b[i]>='0')&&(b[i]<='9'))||(b[i]==' '))){
                System.out.print(b[i]);
            }
        }

    }

    //extract characters
    public void Extractcharacters(){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the string: ");
        String s=sc.nextLine();
        char b[]=s.toCharArray();
        int l=s.length();
        for(int i=0;i<l;i++){
            if(((b[i]>='A')&&(b[i]<='Z'))||((b[i]>='a')&&(b[i]<='z'))){
                System.out.print(b[i]);
            }
        }

    }

    //extract the number
    public void Extractnumbers(){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the string: ");
        String s=sc.nextLine();
        char b[]=s.toCharArray();
        int l=s.length();
        for(int i=0;i<l;i++){
            if((b[i]>='0')&&(b[i]<='9')){
                System.out.print(b[i]);
            }
        }

    }

    //no if words in the string
    public void Wordsinstring(){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the string: ");
        String s=sc.nextLine();
        char b[]=s.toCharArray();
        int l=s.length();
        int count=0;
        for(int i=0;i<l;i++){
            if(b[i]==' '){
                count++;
            }
        }
        System.out.println(count+1);
    }

    //length of the total string with the spaces
    public void Lengthofstring(){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the string: ");
        String s=sc.nextLine();
        int length = s.length();
        System.out.println(length);
    }


}
