package org.wildcards;
import java.util.List;
import java.util.ArrayList;

public class MainShapes {
    public static double calculateTotalArea (List<? extends Shape> shapes){
        double totalArea = 0;
        for (Shape shape : shapes){
            totalArea+= shape.getArea();
        }
        return totalArea;
    }

    public static void main (String[] args){
        List<Circle> circles = new ArrayList<>();
        circles.add(new Circle(5));
        circles.add(new Circle(3));
        circles.add(new Circle(2));

        List<Rectangle> rectangles = new ArrayList<>();
        rectangles.add(new Rectangle(2, 3));
        rectangles.add(new Rectangle(3, 4));

        List<Shape> mixedShapes = new ArrayList<>();
        mixedShapes.add(new Rectangle(5, 6));
        mixedShapes.add(new Circle(4));

        System.out.println("Total area of circles: " + calculateTotalArea(circles));
        System.out.println("Total area of rectangles: " + calculateTotalArea(rectangles));
        System.out.println("Total area of mixed shapes: " + calculateTotalArea(mixedShapes));


    }
}
