package Tests;

import model.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


import java.awt.*;

public class TriangleTest {
    @Test
    public void getX(){
        Triangle triangleTest = new Triangle(10,0);
        assertTrue(triangleTest.getX()==10);
    }
    @Test
    public void getY(){
        Triangle triangleTest = new Triangle(0, 10);
        assertTrue(triangleTest.getY()==10);
    }
    @Test
    public void getXs(){

    }
    @Test
    public void getYs() {
        int ycoord[1];
        Triangle triangleTest = new Triangle(10, 10);
        int[] array = triangleTest.getYs();

    }
    @Test
    public void setX(){
        Triangle triangleTest = new Triangle(0,0);
        triangleTest.setX(5);
        assertTrue(triangleTest.getX()==5);
    }
    @Test
    public void setY(){
        Triangle triangleTest = new Triangle(0,0);
        triangleTest.setY(5);
        assertTrue(triangleTest.getY()==5);
    }
    @Test
    public void getColour(){
        Triangle triangleTest= new Triangle(0,0);
        triangleTest.setColour(Color.RED);
        assertTrue(triangleTest.getColour()==Color.RED);
    }
    @Test
    public void setColour(){
        Triangle triangleTest = new Triangle(0,0);
        triangleTest.setColour(Color.MAGENTA);
        assertTrue(triangleTest.getColour()==Color.MAGENTA);
    }
}