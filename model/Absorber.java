package model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

import physics.Circle;
import physics.LineSegment;

public class Absorber implements Gizmos {

    private int xpos;
    private int ypos;
    private int width;
    private int height;
    private int x2,y2;
    private Color colour;
    private LineSegment ls1;
    private LineSegment ls2;
    private LineSegment ls3;
    private LineSegment ls4;
    private Circle c1;
    private Circle c2;
    private Circle c3;
    private Circle c4;
    private Queue<Ball> BallsQueue;

    public Absorber(int x, int y, int x2, int y2) {
        xpos = x;
        ypos = y;
        this.x2 = x2;
        this.y2 = y2;
        changeCollisionThings();
        setColour(Color.RED);

    }

    private void changeCollisionThings() {
        width = x2 - xpos;
        height = y2 - ypos;
        ls1 = new LineSegment(xpos, ypos, xpos + width, ypos);
        ls2 = new LineSegment(xpos + width, ypos, xpos + width, ypos + height);
        ls3 = new LineSegment(xpos, ypos + height, xpos + width, ypos + height);
        ls4 = new LineSegment(xpos, ypos, xpos, ypos + height);

        c1 = new Circle(xpos , ypos, 0);
        c2 = new Circle(xpos + width, ypos, 0);
        c3 = new Circle(xpos, ypos + height, 0);
        c4 = new Circle(xpos + width, ypos + height, 0);
    }

    @Override
    public int getX() {
        // TODO Auto-generated method stub
        return xpos;
    }

    @Override
    public int getY() {
        // TODO Auto-generated method stub
        return ypos;
    }

    public int getX2(){
        return x2;
    }

    public int getY2() {
        return y2;
    }

    @Override
    public void setX(int x) {
            xpos = x;
        changeCollisionThings();

    }

    @Override
    public void setY(int y) {
            ypos = y;
        changeCollisionThings();

    }

    public void setX2(int x2){
        this.x2 = x2;
        changeCollisionThings();

    }
    public void setY2(int y2){
        this.y2 = y2;
        changeCollisionThings();

    }

    public Color getColour() {
    	return colour; 
    	}
    
    public void setColour(Color c) {
		colour = c;		
	}

    @Override
    public void triggerMyTriggers() {

    }

    @Override
    public void addConnection(Gizmos connectTo) {

    }

    @Override
    public void removeConnection(Gizmos gizmo) {

    }

    @Override
    public void rotate() {

    }

    public Ball getHead() {
        return BallsQueue.poll();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public ArrayList<LineSegment> getLineSeg() {
        ArrayList<LineSegment> ls = new ArrayList<LineSegment>();
        ls.add(ls1);
        ls.add(ls2);
        ls.add(ls3);
        ls.add(ls4);
        return ls;
    }

    public ArrayList<Circle> getCircles() {
        ArrayList<Circle> cr = new ArrayList<Circle>();
        cr.add(c1);
        cr.add(c2);
        cr.add(c3);
        cr.add(c4);
        return cr;

    }

    public void addBall(Ball ball) {
        BallsQueue.add(ball);
    }

    public Queue<Ball> getBallsQueue() {
        return BallsQueue;
    }

    public void shoot() {
        if(!BallsQueue.isEmpty()){
            Ball shoot = BallsQueue.poll();
            shoot.start();
        }
    }

	@Override
	public void trigger() {
		// TODO Auto-generated method stub
		
	}

	public ArrayList<Gizmos> getConnections(){
        return null;
    }
}

