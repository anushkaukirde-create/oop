import java.util.Scanner;

/**
 * Experiment 6: Multiple inheritance using interfaces.
 * Student implements both Academic and Sports.
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Student s = new Student(sc);

        System.out.println("\n--- Enter student details ---");
        s.setAcademicDetails();
        s.setSportsDetails();

        System.out.println("\n===== COMBINED STUDENT INFORMATION =====");
        s.displayAcademicDetails();
        s.displaySportsDetails();
        sc.close();
    }
}

interface Academic {
    void setAcademicDetails();
    void displayAcademicDetails();
}

interface Sports {
    void setSportsDetails();
    void displaySportsDetails();
}

class Student implements Academic, Sports {
    private final Scanner sc;
    private String name, course;
    private double marks;
    private String sport, level;
    private int achievements;

    Student(Scanner sc) {
        this.sc = sc;
    }

    @Override
    public void setAcademicDetails() {
        System.out.print("Student name: ");
        name = sc.nextLine();
        System.out.print("Course: ");
        course = sc.nextLine();
        System.out.print("Marks (%): ");
        marks = Double.parseDouble(sc.nextLine().trim());
    }

    @Override
    public void displayAcademicDetails() {
        System.out.println("[Academic]");
        System.out.println("Name   : " + name);
        System.out.println("Course : " + course);
        System.out.println("Marks  : " + marks + "%");
    }

    @Override
    public void setSportsDetails() {
        System.out.print("Sport played: ");
        sport = sc.nextLine();
        System.out.print("Level (College/State/National): ");
        level = sc.nextLine();
        System.out.print("Number of achievements: ");
        achievements = Integer.parseInt(sc.nextLine().trim());
    }

    @Override
    public void displaySportsDetails() {
        System.out.println("[Sports]");
        System.out.println("Sport        : " + sport);
        System.out.println("Level        : " + level);
        System.out.println("Achievements : " + achievements);
    }
}
