package model;

import physics.Circle;
import physics.LineSegment;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
public class Triangle implements Gizmos{

    private int xpos;
    int xcoord[] = {xpos};
    private int ypos;
    int ycoord[] = {ypos};
    int npoints[];
    private int width;
    private int height;
    private Color colour;
    private LineSegment ls1;
    private LineSegment ls2;
    private LineSegment ls3;
    private Circle c1;
    private Circle c2;
    private Circle c3;
    private int rotation;



    public Triangle(int x, int y) {
        xpos = x;
        ypos = y;
        width = 25;
        height =25;
        rotation = 0;
//        ls1 = new LineSegment(x, y, x, y + h);
//        ls2 = new LineSegment(x, y + h, x + w, y + h);
//        ls3 = new LineSegment(x, y, x + w, y + h);

        ls1 = new LineSegment(x, y, x+width, y);
        ls2 = new LineSegment(x, y, x, y + height);
        ls3 = new LineSegment(x, y+height, x + width, y);


        c1 = new Circle(x, y, 0);
        c2 = new Circle(x , y + height, 0);
        c3 = new Circle(x + width,y, 0);
        
        setColour(Color.MAGENTA);


    }

    public int getX() {
        return xpos;

    }

    public int getY() {
        return ypos;

    }




    public void setX(int x) {
        xpos = x;
        setCollisionThings();
    }

    public void setY(int y) {
        ypos =y;
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
    private ArrayList<Gizmos> connections = new ArrayList<>();
    public void addConnection(Gizmos gizmo) {


        connections.add(gizmo);
    }

    public void removeConnection(Gizmos gizmo) {
        connections.remove(gizmo);
    }


    public int getWidth() {return width; }

    public int getHeight() {return height; }

    @Override
    public String toString() {
        return String.format("Triangle %s %s %s",xcoord, ycoord,npoints);
    }

    public ArrayList<LineSegment> getLineSeg() {
    ArrayList<LineSegment> ls = new ArrayList<LineSegment>();
    ls.add(ls1);
    ls.add(ls2);
    ls.add(ls3);
    return ls;

    }

    public ArrayList<Circle> getCircles() {
        ArrayList<Circle> cr = new ArrayList<Circle>();
        cr.add(c1);
        cr.add(c2);
        cr.add(c3);
        return cr;

    }

    public void rotate(){
        rotation++;
        setCollisionThings();

    }

    private void  setCollisionThings(){
        if (rotation > 3){
            rotation = 0;
        }
        if(rotation == 0){
            ls1 = new LineSegment(xpos, ypos, xpos+width, ypos);
            ls2 = new LineSegment(xpos, ypos, xpos, ypos + height);
            ls3 = new LineSegment(xpos, ypos+height, xpos + width, ypos);

            c1 = new Circle(xpos, ypos, 0);
            c2 = new Circle(xpos , ypos + height, 0);
            c3 = new Circle(xpos + width,ypos, 0);

        }

        else if(rotation == 1){
            ls1 = new LineSegment(xpos,ypos,xpos + width,ypos);
            ls2 = new LineSegment(xpos + width , ypos, xpos + width, ypos+ height);
            ls3 = new LineSegment(xpos, ypos, xpos + width, ypos + height);

            c1 = new Circle(xpos, ypos,0);
            c2 = new Circle(xpos + width, ypos, 0 );
            c3 = new Circle(xpos + width, ypos + height, 0);
        }
        else if(rotation == 2){
            ls1 = new LineSegment(xpos + width, ypos, xpos+width, ypos+ height);
            ls2 = new LineSegment(xpos, ypos+height,xpos+width, ypos+ height);
            ls3 = new LineSegment(xpos, ypos + height, xpos + width, ypos);

            c1 = new Circle(xpos + width, ypos, 0 );
            c2 = new Circle(xpos + width, ypos + height, 0 );
            c3 = new Circle(xpos, ypos + height, 0);
        }
        else if(rotation == 3){
            ls1 = new LineSegment(xpos, ypos, xpos + height, ypos+ width);
            ls2 = new LineSegment(xpos, ypos, xpos, ypos+ height);
            ls3 = new LineSegment(xpos ,ypos + height, xpos+width, ypos+ height);

            c1 = new Circle(xpos, ypos, 0 );
            c2 = new Circle(xpos, ypos + height, 0);
            c3 = new Circle(xpos + width, ypos + height, 0);
        }

    }
    public int getRotation(){
        return rotation;
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


