package training;
abstract class Area{
    public void circle(int radius){
        System.out.println(Math.PI*radius*radius);
    }
    public abstract void rectangle(int l,int b);
}
public class shape extends Area{
    public void rectangle(int l,int b){
        System.out.println(l*b);
    }
    public static void main(String[] args) {
        shape ob=new shape();
        ob.circle(4);
        ob.rectangle(2,7);
    }
    
}
