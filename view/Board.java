package view;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import model.*;


public  class Board extends JPanel implements Observer{

	private static final long serialVersionUID = 1L;
	protected int width;
	protected int height;
	protected Model gm;

	public Board(int w, int h, Model m) {
		// Observe changes in Model
		m.addObserver(this);
		width = w;
		height = h;
		gm = m;
		this.setBorder(BorderFactory.createLineBorder(Color.black));
	}

	// Fix onscreen size
	public Dimension getPreferredSize() {
		return new Dimension(width, height);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;


		for(Line vl : gm.getLines()){
			g2.drawLine(vl.getPos(),0 , vl.getPos(),500);
			g2.drawLine(0,vl.getPos(), 500, vl.getPos());
		}
		for (Square sq : gm.getSquares()) {

			g2.setColor(sq.getColour());
			g2.fillRect(sq.getX(), sq.getY(), 25, 25);

		}

		for (Triangle tri : gm.getTriangle()) {
			Polygon poly = new Polygon();
			int rotation = tri.getRotation();
			int width = 25;
			if(rotation == 0) {
				poly.addPoint(tri.getX(), tri.getY());
				poly.addPoint(tri.getX() + width, tri.getY());
				poly.addPoint(tri.getX(), tri.getY() + width);
			}else if(rotation == 1){
				poly.addPoint(tri.getX() + width , tri.getY());
				poly.addPoint(tri.getX() + width, tri.getY()+width);
				poly.addPoint(tri.getX(), tri.getY());
			}else if(rotation == 2){
				poly.addPoint(tri.getX() + width , tri.getY() + width);
				poly.addPoint(tri.getX(), tri.getY()+width);
				poly.addPoint(tri.getX() + width, tri.getY());
			}
			else if(rotation == 3){
				poly.addPoint(tri.getX(), tri.getY() + width);
				poly.addPoint(tri.getX(), tri.getY());
				poly.addPoint(tri.getX() + width, tri.getY()+ width);
			}
			g2.setColor(tri.getColour());
			g2.fillPolygon(poly);
			g2.drawPolygon(poly);

		}


		for (CircleGiz cir : gm.getCircle()) {
			g2.setColor(cir.getColour());
			g2.fillOval(cir.getX(), cir.getY(), cir.getDiameter(),cir.getDiameter());

		}

		for (Absorber ab : gm.getAbsorbers()) {
			g2.setColor(ab.getColour());
			g2.fillRect(ab.getX(), ab.getY(), ab.getWidth(), ab.getHeight());

		}
		for(Portal p : gm.getPortalOne()){

			g2.setColor(p.getColour());
			g2.fillRect(p.getX(), p.getY(), 25, 100);


		}


		Ball b = gm.getBall();
		if (b != null) {
			g2.setColor(b.getColour());
			int x = (int) (b.getExactX() - b.getRadius());
			int y = (int) (b.getExactY() - b.getRadius());
			int width = (int) (2 * b.getRadius());
			g2.fillOval(x, y, width, width);
		}
	}

	@Override
	public void update(Observable arg0, Object arg1) {
			repaint();
		}

	public void createGridLines() {
		for (int i = 25; i <= 475; i += 25) {
			gm.addLine(new Line(i));
		}


	}
		public void removeGridLines(){
		gm.removeLines();
		}
}
