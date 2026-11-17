/**
 * Experiment 2: Constructor overloading with an Employee class.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Employee 1 (default constructor)");
        new Employee().display();

        System.out.println("Employee 2 (name only)");
        new Employee("Amit").display();

        System.out.println("Employee 3 (name and salary)");
        new Employee("Neha", 25000).display();

        System.out.println("Employee 4 (all details)");
        new Employee(101, "Rahul", 30000).display();
    }
}

class Employee {
    private int id;
    private String name;
    private double salary;   // monthly salary

    // Default constructor
    Employee() {
        this.id = 0;
        this.name = "Not Assigned";
        this.salary = 0.0;
    }

    // Constructor with name only
    Employee(String name) {
        this();
        this.name = name;
    }

    // Constructor with name and salary
    Employee(String name, double salary) {
        this(name);
        this.salary = salary;
    }

    // Constructor with all details
    Employee(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    double calculateAnnualSalary() {
        return salary * 12;
    }

    void display() {
        System.out.println("ID            : " + id);
        System.out.println("Name          : " + name);
        System.out.printf("Monthly Salary: Rs. %.2f%n", salary);
        System.out.printf("Annual Salary : Rs. %.2f%n", calculateAnnualSalary());
        System.out.println("-----------------------------------");
    }
}
