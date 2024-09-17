package ru.stqa.geometry;

public class Triangle {
    public double a, b, c;

    public Triangle(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;

        if ( a < 0 || b < 0 || c < 0) {
            throw new IllegalArgumentException("Triangle side should be non-negative");
        }
        if ( a + b < c || b + c < a || c + a < b) {
            throw new IllegalArgumentException("This is wrong triangle");
        }
    }
    public static void main(String[] args) {
        printTrianglePerimeter (new Triangle(2.0, 3.0, 4.0));
        printTriangleArea (new Triangle(4.0,5.0,7.0));
    }
    public static void printTrianglePerimeter(Triangle t){
        String text = String.format("Периметр треугольника со сторонами %f, %f, %f = %f", t.a, t.b, t.c, t.trPerimeter());
        System.out.println(text);
    }
    public double trPerimeter() {
        return this.a + this.b + this.c;
    }

    public static void printTriangleArea(Triangle t){
        String text = String.format("Площадь треугольника со сторонами %f, %f, %f = %f", t.a, t.b, t.c, t.trArea());
        System.out.println(text);
    }
    public  double trArea() {
        double p = (this.a + this.b + this.c) / 2;
        return Math.sqrt(p * (p - this.a) * (p - this.b) * (p - this.c));
    }
}