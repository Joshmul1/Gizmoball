package Tests;

import model.*;
import java.awt.*;
import java.util.Queue;

import org.junit.jupiter.api.Test;


public class AbsorberTest {

	@Test
	void testGetX() {
		Absorber absorber = new Absorber(10, 0, 0, 0);
		absorber.getX();
		assertEquals (absorber.getX(), 10);
	}
	
	@Test
	void getY() {
		Absorber absorber = new Absorber(0, 20, 0, 0);
		assertEquals (absorber.getY(), 20);
	}

	@Test
	void getX2() {
		Absorber absorber = new Absorber(0, 0, 30, 0);
		assertEquals (absorber.getX2(), 30);
	}

	@Test
	void getY2() {
		Absorber absorber = new Absorber(0, 0, 0, 40);
		assertEquals (absorber.getY2(), 40);
	}
	
	@Test
	void setX() {
		Absorber absorber = new Absorber(0, 0, 0, 0);
		absorber.setX(10);
		assertEquals (absorber.getX(), 10);
	


	}
	
	@Test
	void setY() {
		Absorber absorber = new Absorber(0, 0, 0, 0);
		absorber.setY(20);
		assertEquals (absorber.getY(), 20);

	}
	
	@Test
	void setX2() {
		Absorber absorber = new Absorber(0, 0, 0, 0);
		absorber.setX2(30);
		assertEquals (absorber.getX2(), 30);
		
	}
	
	@Test
	void setY2() {
		Absorber absorber = new Absorber(0, 0, 0, 0);
		absorber.setY2(40);
		assertEquals (absorber.getY2(), 40);

	}
	
	@Test
	void getColor() {
		Absorber absorber = new Absorber(0, 0, 0, 0);
		assertEquals (absorber.getColour(), Color.RED);


	}
	
	
	@Test
	void setColor() {
		Absorber absorber = new Absorber(0, 0, 0, 0);
        absorber.setColour(Color.BLUE);
        assertEquals (absorber.getColour(), Color.BLUE);
	}
	
//	@Test
//	void addBall() {
//		Absorber absorber = new Absorber(0, 0, 0, 0);
//		Ball ball = new Ball(25.0, 25.0, 0.0, 0.0);
//		Queue<Ball> BallsQueue;
//		absorber.getBallsQueue().add(ball);
//		assertEquals (absorber.getBallsQueue(), ball);
//	}
//	
//	@Test
//	void getBallsQueue() {
//		Absorber absorber = new Absorber(0, 0, 0, 0);
//		
//		
//	}
	
//	@Test
//	void shoot() {
//		Absorber absorber = new Absorber(0, 0, 0, 0);
//		absorber.getBallsQueue().isEmpty();
//		
//		
//		
//		
//	}
	


	

}
