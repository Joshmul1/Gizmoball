package model;

import java.awt.Color;
import java.util.ArrayList;

import physics.Circle;
import physics.Vect;


public class Ball implements Gizmos{

	private static Vect velocity;
	private Vect center;
	private double radius;
	private double xpos;
	private double ypos;
	private Color colour;

	private boolean stopped;

	// x, y coordinates and x,y velocity
	public Ball(double x, double y, double xv, double yv) {
		xpos = x; // Centre coordinates
		ypos = y;
		colour = Color.BLUE;
		velocity = new Vect(xv, yv);
		center = new Vect(x,y);
		radius = 5;
		stopped = false;
	}

	public Vect getVelo() {
		return velocity;
	}

	public static void setVelo(Vect v) {
		velocity = v;
	}

	public double getRadius() {
		return radius;
	}

	public Circle getCircle() {
		return new Circle(xpos, ypos, radius);

	}

	// Ball specific methods that deal with double precision.
	public double getExactX() {
		return xpos;
	}

	public double getExactY() {
		return ypos;
	}

	public void setExactX(double x) {
		xpos = x;
	}

	public void setExactY(double y) {
		ypos = y;
	}

	public void stop() {
		stopped = true;
	}

	public void start() {
		stopped = false;
	}

	public boolean stopped() {
		return stopped;
	}

	@Override
	public int getX() {
return 0;
	}

	@Override
	public int getY() {

		return 0;
	}

	@Override
	public void setX(int x) {

	}

	@Override
	public void setY(int y) {

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
	public ArrayList<Gizmos> getConnections() {
		return null;
	}

	@Override
	public void rotate() {

	}

	public double getDiameter(){
		return radius*2;
	}

	public Vect getCenter() {
		// TODO Auto-generated method stub
		return new Vect(xpos,ypos);

	}

	@Override
	public void trigger() {
		// TODO Auto-generated method stub
		
	}

	public Ball copy(){
		return new Ball(this.getExactX(),this.getExactY(),this.getVelo().x(),this.getVelo().y());
	}

}