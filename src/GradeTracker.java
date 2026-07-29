import java.util.ArrayList;
import java.util.Scanner;

public class GradeTracker {

    // Part A: grading scale — cutoffs and letters line up by index
    static double[] cutoffs = {90, 80, 70, 60};
    static char[] letters = {'A', 'B', 'C', 'D'};

    // Turns a number grade into a letter grade using the cutoffs array
    static char letterFor(double grade) {
        for (int i = 0; i < cutoffs.length; i++) {
            if (grade >= cutoffs[i]) {
                return letters[i];
            }
        }
        return 'F';
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Student> roster = new ArrayList<>();
        int choice = -1;

        while (choice != 4) {
            System.out.println("1. Add student  2. View all  3. Class average  4. Exit");
            System.out.print("Choice: ");
            choice = sc.nextInt();

            if (choice == 1) {
                System.out.print("Name: ");
                String name = sc.next();
                System.out.print("Grade: ");
                double grade = sc.nextDouble();
                roster.add(new Student(name, grade));
                System.out.println("Added.");

            } else if (choice == 2) {
                for (Student s : roster) {
                    System.out.printf("%s: %.2f (%c)%n", s.name, s.grade, letterFor(s.grade));
                }

            } else if (choice == 3) {
                if (roster.isEmpty()) {
                    System.out.println("No students yet.");
                } else {
                    double total = 0;
                    for (Student s : roster) {
                        total += s.grade;
                    }
                    System.out.printf("Class average: %.2f%n", total / roster.size());
                }

            } else if (choice == 4) {
                System.out.println("Goodbye!");

            } else {
                System.out.println("Invalid choice.");
            }
        }
        sc.close();
    }
}

// Part B: a single student's name and grade
class Student {
    String name;
    double grade;

    Student(String name, double grade) {
        this.name = name;
        this.grade = grade;
    }
}