// Base class
class Shape {
    String name;

    // Default constructor
    Shape() {
        this.name = "Unknown Shape";
    }

    // Parameterized constructor
    Shape(String name) {
        this.name = name;
    }

    double area() {
        return 0;
    }

    double perimeter() {
        return 0;
    }

    void displayInfo() {
        System.out.println("Shape: " + name);
    }
}

// Rectangle class extending Shape
class Rectangle extends Shape {
    double length;
    double width;

    // Constructor 1: takes name + dimensions
    Rectangle(String name, double length, double width) {
        super(name); // calls parent constructor
        this.length = length;
        this.width = width;
    }

    // Constructor 2: takes only dimensions
    Rectangle(double length, double width) {
        super("Rectangle"); // default name
        this.length = length;
        this.width = width;
    }

    @Override
    double area() {
        return length * width;
    }

    @Override
    double perimeter() {
        return 2 * (length + width);
    }
}

// Circle class extending Shape
class Circle extends Shape {
    double radius;

    // Constructor 1: with name
    Circle(String name, double radius) {
        super(name);
        this.radius = radius;
    }

    // Constructor 2: without name
    Circle(double radius) {
        super("Circle");
        this.radius = radius;
    }

    @Override
    double area() {
        return Math.PI * radius * radius;
    }

    @Override
    double perimeter() {
        return 2 * Math.PI * radius;
    }
}

// Main class
public class MainConstructor {
    public static void main(String[] args) {
        Shape generic = new Shape();
        Rectangle rect1 = new Rectangle("Custom Rectangle", 5, 3);
        Rectangle rect2 = new Rectangle(4, 2);
        Circle circle = new Circle(3.5);

        generic.displayInfo();
        rect1.displayInfo();
        System.out.println("Area: " + rect1.area() + ", Perimeter: " + rect1.perimeter());

        rect2.displayInfo();
        System.out.println("Area: " + rect2.area() + ", Perimeter: " + rect2.perimeter());

        circle.displayInfo();
        System.out.println("Area: " + circle.area() + ", Perimeter: " + circle.perimeter());
    }
}
