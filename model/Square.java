package model;

import java.awt.Color;
import java.util.ArrayList;

import physics.Circle;
import physics.LineSegment;

public class Square implements Gizmos {

	private int xpos;
	private int ypos;
	private int width;
	private int height;
	private Color colour;
	private LineSegment ls1;
	private LineSegment ls2;
	private LineSegment ls3;
	private LineSegment ls4;
	private Circle c1;
	private Circle c2;
	private Circle c3;
	private Circle c4;
	private ArrayList<Gizmos> connections;

	public Square(int x, int y) {
		xpos = x;
		ypos = y;
		width = 25;
		height= 25;
		connections = new ArrayList<>();
		setCollisionThings();


	}

	private void setCollisionThings() {
		ls1 = new LineSegment(xpos, ypos, xpos + width, ypos);
		ls2 = new LineSegment(xpos + width, ypos, xpos + width, ypos + height);
		ls3 = new LineSegment(xpos, ypos + height, xpos + width, ypos + height);
		ls4 = new LineSegment(xpos, ypos, xpos, ypos + height);

		c1 = new Circle(xpos , ypos, 0);
		c2 = new Circle(xpos + width, ypos, 0);
		c3 = new Circle(xpos, ypos + height, 0);
		c4 = new Circle(xpos + width, ypos + height, 0);
        setColour(Color.ORANGE);

	}

	@Override
	public int getX() {
		// TODO Auto-generated method stub
		return xpos;
	}

	@Override
	public int getY() {
		return ypos;
	}

	@Override
	public void setX(int x) {
		xpos= x;
		setCollisionThings();

	}

	@Override
	public void setY(int y) {
		ypos = y;
		setCollisionThings();
	}

	 public Color getColour() {
	    	return colour; 
	    	}
	    
	    public void setColour(Color c) {
			colour = c;		
		}

	public void triggerMyTriggers(){
		for(Gizmos giz : connections){
			giz.trigger();
		}
	}

	@Override
	public void rotate() {

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

	public void addConnection(Gizmos giz){
		connections.add(giz);
	}

	public void removeConnection(Gizmos giz){
		connections.remove(giz);
	}


	@Override
	public void trigger() {
		Color newCol = RandomiseColour.rndCol();
		while (newCol == getColour()){
			newCol = RandomiseColour.rndCol();
		}
		setColour(newCol);
				
	}
	public ArrayList<Gizmos> getConnections(){
		return connections;
	}







}

