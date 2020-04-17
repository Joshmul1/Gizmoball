package Tests;
import model.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import model.Square;

import java.awt.*;

public class SquareTest {
    @Test
    public void getX(){
        Square squares = new Square(10, 0);
        assertFalse(squares.getX()==5);
//        Square squares = new Square(10,0);
        assertTrue (squares.getX() == 10);
    }

    @Test
    public void getY(){
        Square squares = new Square(0,10);
        assertTrue(squares.getY() == 10 );
    }

    @Test
    public void setX(){
        Square squares = new Square(0, 0);
        squares.setX(5);
        assertTrue(squares.getX() == 5);
    }

    @Test
    public void setY(){
        Square squares = new Square(0, 0);
        squares.setY(2);
        assertTrue(squares.getX() == 2);
    }

    @Test
    public void getColour(){
        Square squares = new Square(0, 0);
        squares.setColour(Color.ORANGE);
        assertTrue(squares.getColour() == Color.ORANGE);
    }

    public void setColour(){
        Square squares = new Square(0, 0);
        squares.setColour(Color.GREEN);
        assertTrue(squares.getColour() == Color.GREEN);
    }
}