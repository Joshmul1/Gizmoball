package model;

import java.awt.Color;
import java.util.ArrayList;

public interface Gizmos {

	int getX();
	int getY();
	void setX(int x);
	void setY(int y);
	Color getColour();
	void rotate();
	void trigger();
	void setColour(Color c);

	void triggerMyTriggers();


	void addConnection(Gizmos connectTo);
	void removeConnection(Gizmos gizmo);
	ArrayList<Gizmos> getConnections();
}
