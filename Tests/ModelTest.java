package Tests;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import model.*;
import physics.Circle;
import view.BuildGui;

import java.util.ArrayList;


public class ModelTest {

    @Test
    public void testremoveBall() {
        Model m = new Model();
        Ball b = new Ball(25,25,0,0);
        m.setBall(b);
        assertFalse( b == null);
        m.removeBall();
        //System.out.println(m.getBall());
        assertTrue(m.getBall() == null);
}

    @Test
    public void testaddSquare() {
        Model m = new Model();
        Square s = new Square(25,25);
        m.addSquare(s);
        assertFalse(m.getSquares() == null);
        assertTrue(m.getSquares().size() == 1);
    }

    @Test
    public void testaddAbsorber() {
        Model m = new Model();
        Absorber a = new Absorber(10,10,10,10);
        m.addAbsorber(a);
        assertFalse(m.getAbsorbers() == null);
        assertTrue(m.getAbsorbers().size() == 1);
    }

    @Test
    public void testaddTriangle() {
        Model m = new Model();
        Triangle tr = new Triangle(20,10);
        m.addTriangle(tr);
        assertFalse(m.getTriangle() == null);
        assertTrue(m.getTriangle().size() == 1);
    }

    @Test
    public void testaddCircle() {
        Model m = new Model();
        CircleGiz c = new CircleGiz(10,10);
        m.addCircle(c);
        assertFalse(m.getCircle() == null);
        assertTrue(m.getCircle().size() == 1);
    }

    @Test
    public void testaddLine() {
        Model m = new Model();
        Line l = new Line(10);
        m.addLine(l);
        assertFalse(m.getLines() == null);
        assertTrue(m.getLines().size() == 1);

    }

    @Test
    public void testremoveLines(){
        Model m = new Model();
        Line l = new Line(10);
        m.addLine(l);
        assertFalse(l == null);
        m.removeLines();
        ArrayList<String> lines = new ArrayList<>();
        System.out.println(m.getLines());
        assertEquals (m.getLines(), lines);
        assertTrue(m.getLines().size() == 0 );
    }

    @Test
    public void testdeleteGizmo() {
        Model m = new Model();
        Triangle tr = new Triangle(10,10);
        m.addTriangle(tr);
        m.deleteGizmo(10,10);
        assertTrue( m.getGizmo(10,10)== null);
        CircleGiz c = new CircleGiz(12,12);
        m.addCircle(c);
        m.deleteGizmo(12,12);
        assertTrue(m.getGizmo(12,12) == null);

    }
    
       @Test
        public void  testmoveBall() {
        Model m = new Model();
        Ball b = new Ball(25,25, 10, 10);
        assertFalse(b == null);
        m.moveBall();
        int oldx = 25;
        int oldy = 25;
        System.out.println(b.getX());
        System.out.println(b.getY());
        assertNotEquals( b.getX(), oldx);
        assertNotEquals(b.getY(), oldy);


    }

    @Test
    public void testresetBall() {
        Model m = new Model();
        Ball b = new Ball(25, 25, 10, 10);
        m.setBall(b);
        assertFalse(m.getBall() == null);
        m.resetBall();
        assertEquals(b.getX(), 0);
        assertEquals(b.getY(), 0);
        System.out.println(b.getVelo());
        b.start();
        System.out.println(b.getVelo());
    }

    @Test
    public void testaddPortalOne() {
        Model m = new Model();
        Portal p1 = new Portal(10,10);
        m.addPortalOne(p1);
        assertFalse(m.getPortalOne() == null);
        assertTrue(m.getPortalOne().size() == 1);

    }

    @Test
    public void testisOccupied() {
        Model m = new Model();
        Square s = new Square(10,10);
        m.addSquare(s);
        assertTrue(m.isOccupied(10,10) == false);

    }

    @Test
    public void testdeleteAllcontnet() {
        Model m = new Model();
        Portal p1 = new Portal(10,10);
        m.addPortalOne(p1);
        Square s = new Square(10,10);
        m.addSquare(s);
        CircleGiz c =  new CircleGiz(10,10);
        m.addCircle(c);
        Absorber a = new Absorber(10,10,10,10);
        m.addAbsorber(a);
        Triangle tr = new Triangle(19,10);
        m.addTriangle(tr);
        assertTrue(m.getPortalOne().size() == 1);
        assertTrue(m.getSquares().size() == 1);
        assertTrue(m.getCircle().size() == 1);
        assertTrue(m.getAbsorbers().size() == 1);
        assertTrue(m.getTriangle().size() == 1);
        m.deleteAllcontent();
        assertTrue(m.getPortalOne().size() == 0);
        assertTrue(m.getSquares().size() == 0);
        assertTrue(m.getCircle().size() == 0);
        assertTrue(m.getAbsorbers().size() == 0);
        assertTrue(m.getTriangle().size() == 0);

    }


}
