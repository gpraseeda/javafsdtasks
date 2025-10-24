public class Shape {

    // Area of a circle
    double area(double radius) {
        return Math.PI * radius * radius;
    }

    // Area of a rectangle
    double area(double length, double width) {
        return length * width;
    }

    // Area of a triangle
    double area(double base, double height, boolean isTriangle) {
        if (isTriangle) {
            return 0.5 * base * height;
        } else {
            return 0.0;
        }
    }

    public static void main(String[] args) {
        Shape shape = new Shape();

        System.out.println("Area of Circle: " + shape.area(5));              // circle
        System.out.println("Area of Rectangle: " + shape.area(4, 6));       // rectangle
        System.out.println("Area of Triangle: " + shape.area(3, 8, true));  // triangle
    }
}
