package Tests;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import model.*;

import java.awt.*;

public class CircleTest {

    @Test
    public void testGetX() {
        CircleGiz testC = new CircleGiz(5,0);
        testC.getX();
        assertTrue(testC.getX()==5);
    }

    @Test
    public void testGetY() {
        CircleGiz testC = new CircleGiz(0,5);
        testC.getY();
        assertTrue(testC.getY()== 5);
    }

    @Test
    public void testSetX() {
        CircleGiz testC = new CircleGiz(0,0);
        testC.setX(5);
        assertTrue(testC.getX()== 5);
    }

    @Test
    public void testSetY() {
        CircleGiz testC = new CircleGiz(0,0);
        testC.setY(5);
        assertTrue(testC.getY() == 5);
    }
    /*
    //julia thing TODO
    @Test
    public void testSetCollisionThings() {
        CircleGiz testC = new CircleGiz(0,0);

    }
    */

    @Test
    public void testgetColour() {
        CircleGiz testC = new CircleGiz(0,0);
        testC.setColour(Color.GREEN);
        assertTrue(testC.getColour() == Color.GREEN);
    }

    @Test
    public void testsetColour() {
        CircleGiz testC = new CircleGiz(0,0);
        testC.setColour(Color.BLUE);
        assertTrue(testC.getColour() == Color.BLUE);
    }

    @Test
    public void testrotate() {
        CircleGiz testC = new CircleGiz(0,0);
        testC.rotate();
        assertTrue(testC.getX() == 0);
        assertTrue(testC.getY() == 0);
    }

    @Test
    public void testgetDiameter(){
        CircleGiz testC = new CircleGiz(0,0);
        testC.getDiameter();
        assertTrue(testC.getDiameter() == 25);
    }

 }
