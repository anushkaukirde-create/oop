import java.util.Scanner;

/**
 * Experiment 5: Inheritance and method overriding with shapes.
 * Demonstrates runtime polymorphism (Shape reference -> subclass object).
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter radius of circle: ");
        double r = sc.nextDouble();

        System.out.print("Enter length and breadth of rectangle: ");
        double l = sc.nextDouble();
        double b = sc.nextDouble();

        System.out.print("Enter base and height of triangle: ");
        double bs = sc.nextDouble();
        double h = sc.nextDouble();

        Shape[] shapes = { new Circle(r), new Rectangle(l, b), new Triangle(bs, h) };

        System.out.println("\n===== AREAS (runtime polymorphism) =====");
        for (Shape s : shapes) {
            s.calculateArea();
        }
        sc.close();
    }
}

class Shape {
    void calculateArea() {
        System.out.println("Area of a generic shape is undefined.");
    }
}

class Circle extends Shape {
    private double radius;
    Circle(double radius) { this.radius = radius; }

    @Override
    void calculateArea() {
        System.out.printf("Area of Circle    = %.2f%n", Math.PI * radius * radius);
    }
}

class Rectangle extends Shape {
    private double length, breadth;
    Rectangle(double length, double breadth) {
        this.length = length;
        this.breadth = breadth;
    }

    @Override
    void calculateArea() {
        System.out.printf("Area of Rectangle = %.2f%n", length * breadth);
    }
}

class Triangle extends Shape {
    private double base, height;
    Triangle(double base, double height) {
        this.base = base;
        this.height = height;
    }

    @Override
    void calculateArea() {
        System.out.printf("Area of Triangle  = %.2f%n", 0.5 * base * height);
    }
}
