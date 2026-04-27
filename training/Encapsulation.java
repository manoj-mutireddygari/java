package training;
import training.Dayfive;
class Balance{
    private int balance;
    public void setBalance(int balance){
        this.balance=balance;
    }
    public int getBalance(){
        return balance;
    }
}
class Name{
    private String name;
    public void SetName(String name){
        this.name=name;
    }
    public String getName(){
        return name;
    }
}

public class Encapsulation {
    public static void main(String[] args) {
        /*Balance ob=new Balance();
        ob.setBalance(500);
        System.out.println(ob.getBalance());*/

        /*Name ob=new Name();
        ob.SetName("manoj");
        System.out.println(ob.getName());*/
        Dayfive.Maxarray();

    }
}
