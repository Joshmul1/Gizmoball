package model;

import java.awt.Color;
import java.util.ArrayList;

import physics.Circle;

public class CircleGiz implements Gizmos {
    private int xpos;
    private int ypos;
    private int diameter;
    private int width;
    private int height;
    double radius;
    private Color colour;
    private Circle circa;
    private ArrayList<Gizmos> connections;


    public CircleGiz(int x, int y) {
        xpos = x;
        ypos = y;
        diameter = 25;
        radius = (double)diameter/2;

        width = 2 * diameter;
        height = 2 * diameter;
        circa = new Circle(x+ radius, y+ radius, radius);
        setColour(Color.GREEN);
        connections = new ArrayList<>();

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

    @Override
    public void setX(int x) {
        xpos = x;
        setCollisionThings();
    }

    @Override
    public void setY(int y) {
        ypos = y;
        setCollisionThings();

    }

    private void setCollisionThings() {
        circa = new Circle(xpos+ radius, ypos+ radius, radius);

    }

    public Color getColour() {
    	return colour; 
    	}
    
    public void setColour(Color c) {
		colour = c;		
	}


    @Override
    public void rotate() {

    }

    public int getDiameter() {
        return diameter;
    }

    public void setR() {

    }

    public Circle getCircle() {
        return circa;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

	@Override
	public void trigger() {
		Color newCol = RandomiseColour.rndCol();
		while (newCol == getColour()){
			newCol = RandomiseColour.rndCol();
		}
		setColour(newCol);
				
	}

	public void triggerMyTriggers(){
        for(Gizmos giz : connections){
            giz.trigger();
        }
    }
    public void addConnection(Gizmos gizmo) {
        connections.add(gizmo);
    }


    public void removeConnection(Gizmos gizmo) {
        connections.remove(gizmo);
    }

    public ArrayList<Gizmos> getConnections(){
        return connections;
    }

}

