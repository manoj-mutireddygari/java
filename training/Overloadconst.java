package training;

public class Overloadconst{
    public Overloadconst(){
        this(77.80f);
    }
    public Overloadconst(float marks){
        this(1);
        System.out.print(marks);
    }
    public Overloadconst(int rollno){
        System.out.print(rollno+" ");
    }
    public static void main(String[] args) {
        Overloadconst ob=new Overloadconst();
    }
}
