package ru.stqa.geometry;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangleTests {

    @Test
    void CanCalculatePerimeter() {
        var t = new Triangle(9.0, 8.0, 7.5);
        var result = t.trPerimeter();
        Assertions.assertEquals(24.5, result);
    }

    @Test
    void CanCalculateArea() {
        var t = new Triangle(3.0, 4.0, 5.0);
        var result = t.trArea();
        Assertions.assertEquals(6, result);
    }
}