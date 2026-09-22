package ccc;
import java.util.*;
import java.lang.*;

public class Dayfive {
    public static void main(String[] args) {
        Dayfive op=new Dayfive();
        //op.anagram();
        //op.anagramchecker();
        op.pangram();
    }

    public void pangram(){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the String:");
        String s=sc.nextLine().toLowerCase();

        HashMap<Character,Integer>fm=new HashMap<>();
        for(int i=0;i<s.length();i++){
            char ch=s.charAt(i);
            if (ch >= 'a' && ch <= 'z') {
                fm.put(ch,fm.getOrDefault(ch,0)+1);
            }
        }
        
        if (fm.keySet().size() == 26) {
            System.out.println("pangram");
        } else {
            System.out.println("Not pangram");
        }
    }




    public void anagram(){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the First String:");
        String x=sc.nextLine();
        System.out.println("Enter the Second String:");
        String y=sc.nextLine();

        char []s=x.toCharArray();
        char []t=y.toCharArray();

        int []arr=new int[26];
        for(int i=0;i<s.length;i++){
            arr[s[i]-'a']++;
        }
        for(int i=0;i<t.length;i++){
            arr[t[i]-'a']--;
        }
        int count=0;
        for(int i=0;i<26;i++){
            if(arr[i]!=0){
                count++;
            }
        }
        if(count==0){
            System.out.println("The String is anagram");
        }else{
            System.out.println("The String is not an anagram");
        }
    }
    

    public void anagramchecker(){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the First String:");
        String s1=sc.nextLine();
        System.out.println("Enter the Second String:");
        String s2=sc.nextLine();

        HashMap<Character,Integer>fm=new HashMap<>();
        for(int i=0;i<s1.length();i++){
            char ch=s1.charAt(i);
            fm.put(ch,fm.getOrDefault(ch,0)+1);
        }
        for(int i=0;i<s2.length();i++){
            char ch=s2.charAt(i);
            fm.put(ch,fm.getOrDefault(ch,0)-1);
        }
        for(Integer c:fm.values()){
            if(c!=0){
                System.out.println("Not anagram");
                System.exit(0);
            }
        }
        System.out.println("Anagram");
    }


    
}
