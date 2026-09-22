package basic;

public class p3 {
    import java.util.Scanner;

class Student {
    String name;
    int id;
    double[] grades;

    public Student(String name, int id, double[] grades) {
        this.name = name;
        this.id = id;
        this.grades = grades;
    }

    double getAvg() {
        double sum = 0;

        for (int i = 0; i < grades.length; i++) {
            sum += grades[i];
        }
        return sum / grades.length;
    }
}
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        Student[] students = new Student[n];

        for (int i = 0; i < n; i++) {

            String name = sc.nextLine();
            int id = Integer.parseInt(sc.nextLine());
            int gc = Integer.parseInt(sc.nextLine());
            double[] grades = new double[gc];
            for (int k = 0; k < gc; k++) {
                grades[k] = Double.parseDouble(sc.nextLine());
            }

            students[i] = new Student(name, id, grades);
        }
        for (int j = 0; j < n; j++) {

            double avg = students[j].getAvg();

            System.out.println("ID: " + students[j].id);
            System.out.println("Name: " + students[j].name);
            System.out.printf("Average Grade: %.2f\n", avg);
            if (avg >= 50) {
                System.out.println("Status: Passed");
            } else {
                System.out.println("Status: Failed");
            }
        }

        sc.close();
    }
}
