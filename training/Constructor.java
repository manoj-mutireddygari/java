package training;

public class Constructor {
    int rollno;
    float marks;
    static int count=0;
    public Constructor(int rollno,float marks){  //constructor
        this.rollno=rollno;
        this.marks=marks;
        count++;
    }
    public void display(){
        System.out.println(rollno+" "+marks);
    }
    public static void main(String[] args) {
        Constructor s1=new Constructor(1,77.80f); //constructor call
        Constructor s2=new Constructor(2,71.88f);
        Constructor s3=new Constructor(3,75.54f);
        s1.display();
        s2.display();
        s3.display();
        System.out.println("Number of objects: "+count);
    }
}
