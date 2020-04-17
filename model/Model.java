package model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

import physics.Circle;
import physics.Geometry;
import physics.LineSegment;
import physics.Vect;
import view.BuildGui;

/**
 * @author Murray Wood Demonstration of MVC and MIT Physics Collisions 2014
 */

public class Model extends Observable {
	private Gizmos collidedGizmo;
	private ArrayList<Line> lines;
	private ArrayList<Square> squares;
	private ArrayList<Triangle> triangles;
	private ArrayList<CircleGiz> circles;
	private ArrayList<Absorber> absorbers;
	private ArrayList<Portal> portal;
	private Ball ball;
	private Walls gws;
	private int gravity;
	private boolean isHIT, isInAbsorber, isInPortal;
	private Vect ballBeforeHitVelocity;
	private Ball originalBall;


	public Model() {

		// Ball position (25, 25) in pixels. Ball velocity (100, 100) pixels per tick
		ball = new Ball(25, 25, BuildGui.getVelX(), BuildGui.getVelY());
		isInAbsorber = false;
		isInPortal = false;

		// Wall size 500 x 500 pixels
		gws = new Walls(0, 0, 500, 500);

		// Lines added in Main
		lines = new ArrayList<Line>();
		squares = new ArrayList<Square>();
		triangles = new ArrayList<Triangle>();
		circles = new ArrayList<CircleGiz>();
		absorbers = new ArrayList<Absorber>();
		portal = new ArrayList<>();
		isHIT = false;


	}
	public void setOriginalBall(Ball b){
	    originalBall = b;
    }

    public Ball getOriginalBall(){
	    return originalBall;
    }

	public Portal getRandomPortal(){
		Random rnd = new Random();
		//	int random = rnd.nextInt((portal.size() - 1) + 1) + 1;
		int random = rnd.nextInt(portal.size());
		Portal ptl = portal.get(random);
		return ptl;
	}

	public void moveBall() {

		double moveTime = 0.05; // 0.05 = 20 times per second as per Gizmoball

		if (ball != null && !ball.stopped()) {

			CollisionDetails cd = timeUntilCollision();
			double tuc = cd.getTuc();
			if (tuc > moveTime) {
				// No collision ...
				ball = movelBallForTime(ball, moveTime);


			}  else {
				// We've got a collision in tuc
				if(isHIT){
					if(collidedGizmo instanceof Absorber) {
						ballBeforeHitVelocity = ball.getVelo();
						ball = movelBallForTime(ball, tuc);

						ball.setExactX((((Absorber) collidedGizmo).getX2()) - ball.getRadius());
						ball.setExactY((((Absorber) collidedGizmo).getY2()) - ball.getRadius());
						ball.setVelo(new Vect(0, 0));
						isInAbsorber = true;
						isHIT = false;
						redraw();
					}
					else if(collidedGizmo instanceof Portal){
						ball = movelBallForTime(ball, tuc);

						Portal randomP = getRandomPortal();
						if(randomP.getX() == 0){
						    ball.setExactX(25+(ball.getRadius()*2));
						    ball.setExactY(randomP.getY());
						    if(ball.getVelo().x()<0){
						        ball.setVelo(new Vect(ball.getVelo().x()*-1,ball.getVelo().y()));
                            }
                        }else{
                            ball.setExactX((randomP.getX()-(ball.getRadius()*2)));
                            ball.setExactY((randomP.getY()));
                            if(ball.getVelo().x()>0){

                                ball.setVelo(new Vect(ball.getVelo().x()*-1,ball.getVelo().y()));

                            }

                        }


						ball.setVelo(cd.getVelo());
						isInPortal = true;
						isHIT = false;
						redraw();
					}
					else{
						collidedGizmo.trigger();
						collidedGizmo.triggerMyTriggers();
						isHIT = false;
						ball = movelBallForTime(ball, tuc);
						// Post collision velocity ...
						ball.setVelo(cd.getVelo());

					}
				}
				else {
					ball = movelBallForTime(ball, tuc);
					// Post collision velocity ...
					ball.setVelo(cd.getVelo());
				}




			}

			// Notify observers ... redraw updated view
			this.setChanged();
			this.notifyObservers();
		}

	}

	private Ball movelBallForTime(Ball ball, double time) {

		double newX = 0.0;
		double newY = 0.0;
		double xVel = ball.getVelo().x();
		double yVel = ball.getVelo().y();
		newX = ball.getExactX() + (xVel * time);
		newY = ball.getExactY() + (yVel * time);
		ball.setExactX(newX);
		ball.setExactY(newY);
		return ball;
	}
	public void resetBall() {
	    if(ball != null) {
            ball = originalBall.copy();
            setChanged();
            notifyObservers();
        }
		//ball = new Ball(25, 25, BuildGui.getVelX()*10, BuildGui.getVelY()*10);

	}

	public void removeBall() {
		ball = null;
		setChanged();
		notifyObservers();
	}

	private CollisionDetails timeUntilCollision() {
		// Find Time Until Collision and also, if there is a collision, the new speed vector.
		// Create a physics.Circle from Ball
		Circle ballCircle = ball.getCircle();
		Vect ballVelocity = ball.getVelo();
		Vect newVelo = new Vect(0, 0);

		// Now find shortest time to hit a vertical line or a wall line
		double shortestTime = Double.MAX_VALUE;
		double time = 0.0;

		// Time to collide with 4 walls
		ArrayList<LineSegment> lss = gws.getLineSegments();
		for (LineSegment line : lss) {
			time = Geometry.timeUntilWallCollision(line, ballCircle, ballVelocity);
			if (time < shortestTime) {
				shortestTime = time;
				newVelo = Geometry.reflectWall(line, ball.getVelo(), 1.0);
			}
		}

		for(CircleGiz circle : circles) {
			Circle circ = circle.getCircle();

			time = Geometry.timeUntilCircleCollision(circ, ballCircle, ballVelocity);
			if (time < shortestTime) {
				collidedGizmo = circle;
				isHIT = true;
				shortestTime = time;
				newVelo = Geometry.reflectCircle(circ.getCenter(),ball.getCenter(), ball.getVelo(), 1.0);
			}
		}



		for(Square square : squares) {
			ArrayList<LineSegment> lsq = square.getLineSeg();
			ArrayList<Circle> c = square.getCircles();
			for(LineSegment line: lsq) {
				time = Geometry.timeUntilWallCollision(line, ballCircle, ballVelocity);
				if (time < shortestTime) {
					collidedGizmo = square;
					isHIT = true;
					shortestTime = time;
					newVelo = Geometry.reflectWall(line, ball.getVelo(), 1.0);
				}
			}

			for(Circle circ : c) {
				time = Geometry.timeUntilCircleCollision(circ, ballCircle, ballVelocity);
				if (time < shortestTime) {
					collidedGizmo = square;
					isHIT = true;
					shortestTime = time;
					newVelo = Geometry.reflectCircle(circ.getCenter(),ball.getCenter(), ball.getVelo(), 1.0);
				}

			}
		}

		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		for(Portal p : portal) {
			ArrayList<LineSegment> lsq = p.getLineSeg();
			ArrayList<Circle> c = p.getCircles();
			for(LineSegment line: lsq) {
				time = Geometry.timeUntilWallCollision(line, ballCircle, ballVelocity);
				if (time < shortestTime) {
					collidedGizmo = p;
					isHIT = true;
					shortestTime = time;
					newVelo = Geometry.reflectWall(line, ball.getVelo(), 1.0);
				}
			}

			for(Circle circ : c) {
				time = Geometry.timeUntilCircleCollision(circ, ballCircle, ballVelocity);
				if (time < shortestTime) {
					collidedGizmo = p;
					isHIT = true;
					shortestTime = time;
					newVelo = Geometry.reflectCircle(circ.getCenter(),ball.getCenter(), ball.getVelo(), 1.0);
				}

			}
		}

		for(Triangle triangle : triangles) {
			ArrayList<LineSegment> ltr = triangle.getLineSeg();
			ArrayList<Circle> c = triangle.getCircles();
			for (LineSegment line : ltr) {
				time = Geometry.timeUntilWallCollision(line, ballCircle, ballVelocity);
				if (time < shortestTime) {
					collidedGizmo = triangle;
					isHIT = true;
					shortestTime = time;
					newVelo = Geometry.reflectWall(line, ball.getVelo(), 1.0);
				}
			}

			for (Circle circ : c) {
				time = Geometry.timeUntilCircleCollision(circ, ballCircle, ballVelocity);
				if (time < shortestTime) {
					collidedGizmo = triangle;
					isHIT = true;
					shortestTime = time;
					newVelo = Geometry.reflectCircle(circ.getCenter(), ball.getCenter(), ball.getVelo(), 1.0);

				}
			}

		}
		for(Absorber absorber : absorbers) {
			ArrayList<LineSegment> lsq = absorber.getLineSeg();
			ArrayList<Circle> c = absorber.getCircles();
			for(LineSegment line: lsq) {
				time = Geometry.timeUntilWallCollision(line, ballCircle, ballVelocity);
				if (time < shortestTime) {
					collidedGizmo = absorber;
					isHIT = true;
					shortestTime = time;
					newVelo = Geometry.reflectWall(line, ball.getVelo(), 1.0);
				}
			}

			for(Circle circ : c) {
				time = Geometry.timeUntilCircleCollision(circ, ballCircle, ballVelocity);
				if (time < shortestTime) {
					collidedGizmo = absorber;
					isHIT = true;
					shortestTime = time;
					newVelo = Geometry.reflectCircle(circ.getCenter(),ball.getCenter(), ball.getVelo(), 1.0);
				}

			}
		}


		return new CollisionDetails(shortestTime, newVelo);
	}

	public Ball getBall() {
		return ball;
	}

	public ArrayList<Line> getLines() {
		return lines;
	}

	public ArrayList<Square> getSquares() {
		return squares;
	}

	public ArrayList<Portal> getPortalOne() {
		return portal;
	}

	public void addSquare(Square s) {
		squares.add(s);
		setChanged();
		notifyObservers();
	}

	public void addPortalOne(Portal p1) {
		portal.add(p1);
		setChanged();
		notifyObservers();
	}

	public void redraw(){
		setChanged();
		notifyObservers();
	}
	public ArrayList<Absorber> getAbsorbers(){
		return absorbers;
	}

	public void addAbsorber(Absorber a) {
		absorbers.add(a);
		setChanged();
		notifyObservers();
	}

	public ArrayList<Triangle> getTriangle() {
		return triangles;
	}

	public void addTriangle(Triangle tr) {
		triangles.add(tr);
		setChanged();
		notifyObservers();
	}


	public void addLine(Line l) {
		lines.add(l);
		setChanged();
		notifyObservers();

	}




	public void setBallSpeed(int x, int y) {
		ball.setVelo(new Vect(x, y));
	}

	public ArrayList<CircleGiz> getCircle(){
		return circles;
	}

	public void addCircle(CircleGiz c) {
		circles.add(c);
		setChanged();
		notifyObservers();
	}

	public void setTriangles(ArrayList<Triangle> triangles) {
		this.triangles = triangles;
		setChanged();
		notifyObservers();
	}

	public void setAbsorbers(ArrayList<Absorber> absorbers) {
		this.absorbers = absorbers;
		setChanged();
		notifyObservers();
	}

	public void setBall(Ball ball) {
		this.ball = ball;
		setChanged();
		notifyObservers();
	}

	public void setCircles(ArrayList<CircleGiz> circles) {
		this.circles = circles;
		setChanged();
		notifyObservers();
	}

//	public void setLeftFlippers(ArrayList<PortalOne> leftFlippers) {
//		this.leftFlippers = leftFlippers;
//		setChanged();
//		notifyObservers();
//	}

//	public void setRightFlippers(ArrayList<RightFlipper> rightFlippers) {
//		this.rightFlippers = rightFlippers;
//		setChanged();
//		notifyObservers();
//	}

	public void setPortalOne(ArrayList<Portal> portalOne) {
		this.portal = portalOne;
		setChanged();
		notifyObservers();
	}

	public void setSquares(ArrayList<Square> squares) {
		this.squares = squares;
		setChanged();
		notifyObservers();
	}

	public void removeLines(){
		lines.clear();
	}
	public boolean isOccupied(int x, int y) {
	    if(x > 19 || y > 19){
	        return true;
        }
		x= x*25;
		y=y*25;
		for(Triangle t: triangles){
			if(t.getX() == x && t.getY() == y){
				return true;
			}
		}
		for(CircleGiz c : circles){
			if(c.getX() == x && c.getY() == y){
				return true;
			}
		}
		for(Square sq : squares){

			if(sq.getX() == x && sq.getY() == y){
                redraw();

                return true;
			}
		}
		for(Portal p1 : portal){

		    if(x >= p1.getX() && x <= p1.getX() && ( y>= p1.getY() && y<= p1.getY()+75)){


                return true;
			}
		}

		for(Absorber ab : absorbers){

			if((x >= ab.getX() && x <= ab.getX2()-25) && (y>= ab.getY() && y<= ab.getY2()-25) ){

                return true;
			}

		}

		return false;
	}

	public Gizmos getGizmo(int x, int y) {
		x= x*25;
		y=y*25;
		for(Triangle t: triangles){
			if(t.getX() == x && t.getY() == y){
				return t;
			}
		}
		for(CircleGiz c : circles){
			if(c.getX() == x && c.getY() == y){
				return c;
			}
		}
		for(Square sq : squares){
			if(sq.getX() == x && sq.getY() == y){
				return sq;
			}
		}

		for(Portal p1 : portal){
			if((p1.getX() <= x && p1.getX()+25 >= x) && (p1.getY()<= y && p1.getY()+100 >=y)){
				return p1;
			}
		}
		for(Absorber ab : absorbers){
			if((ab.getX() <= x && ab.getX2() >= x) && (ab.getY()<= y && ab.getY2() >=y)){
				return ab;
			}
		}

		return null;
	}


	public void deleteAllcontent(){
		squares.clear();
		triangles.clear();
		circles.clear();
		absorbers.clear();
		portal.clear();
		ball = null;
		setChanged();
		notifyObservers();
	}

	public boolean isOccupied(int x1, int y1, int x2, int y2) {
		if(x1 <= x2 && y1 <= y2){

			x1 /=25;
			y1/=25;
			x2/=25;
			y2/=25;

			// then all is good its top right to bottom left
			int deltaX = x2-x1;
			int deltaY = y2-y1;
			for(int i =x1 ; i <= x2 ; i ++ ){

				for(int y = y1 ; y <= y2; y ++){
					if(isOccupied(i,y)){
						return true;
					}
				}

			}
		}
		else{
		}


		return false;
	}

	public void deleteGizmo(int x, int y){
		x= x*25;
		y=y*25;
		for(Triangle t: triangles){
			if(t.getX() == x && t.getY() == y){
				triangles.remove(t);
				return;
			}
		}
		for(CircleGiz c : circles){
			if(c.getX() == x && c.getY() == y){
				circles.remove(c);
				return;
			}
		}
		for(Square sq : squares){
			if(sq.getX() == x && sq.getY() == y){
				squares.remove(sq);
				return;
			}
		}
		for(Portal p : portal){
			if((p.getX() <= x && p.getX()+25 >= x) && (p.getY()<= y && p.getY()+100 >=y)){
				portal.remove(p);
				return;
			}
		}

		for(Absorber ab : absorbers){
			if((ab.getX() <= x && ab.getX2() >= x) && (ab.getY()<= y && ab.getY2() >=y)){
				absorbers.remove(ab);
				return;
			}


		}

	}

	public void setGravity(int g){
		gravity = g;
	}
	public boolean isBallInAbsorber(){
		return isInAbsorber;
	}

	public boolean isBallInPortal(){
		return isInPortal;
	}

	public Gizmos getCollidedGizmo() {
		return collidedGizmo;
	}

	public void setInAbsorber(boolean b){
		isInAbsorber = b;
	}
	public void setInPortal(boolean b){
		isInPortal = b;
	}

	public Vect getBellBeforeHitVelocity(){
		return ballBeforeHitVelocity;
	}
}

