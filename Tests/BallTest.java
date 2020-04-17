package Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

import org.junit.jupiter.api.Test;

import model.*;
import physics.Circle;
import physics.Vect;

class BallTest {
	
	@Test
	void getVelo() {
		Ball ball = new Ball(10.0, 0.0, 20.0, 20.0);
			Ball.setVelo(new Vect(25.0,25.0));
			Vect temp = new Vect(25.0, 25.0);
			assertEquals(ball.getVelo() ,temp);

	}
	
	@Test
	void setVelo() {
		Ball ball = new Ball(10.0, 0.0, 0.0, 0.0);
		Vect newVelo = new Vect(20,20);
		assertNotEquals(ball.getVelo(), newVelo);
		Ball.setVelo(newVelo);
		assertSame(ball.getVelo(), newVelo);

	}

	@Test
	void getRadius() {
		Ball ball = new Ball(10.0, 0.0, 0.0, 0.0);
		assertEquals(5, ball.getRadius(), 0.0);

	}
	
	@Test
	void getExactX() {
		Ball ball = new Ball(10.0, 0.0, 0.0, 0.0);
			assertNotEquals(20.0, ball.getExactX(), 0.0);
			assertEquals(10.0, ball.getExactX(), 0.0);
	}
	
	
	@Test
	void getExactY() {
		Ball ball = new Ball(0.0, 20.0, 0.0, 0.0);
			assertNotEquals(30.0, ball.getExactY(), 0.0);
			assertEquals(20.0, ball.getExactY(), 0.0);
	}
	
	@Test
	void setExactX() {
		Ball ball = new Ball(0.0, 0.0, 0.0, 0.0);
		ball.setExactX(20.0);
			assertNotEquals(30.0, ball.getExactX(), 0.0);
			assertEquals(20.0, ball.getExactX(), 0.0);
	}
	
	@Test
	void setExactY() {
		Ball ball = new Ball(0.0, 0.0, 0.0, 0.0);
		ball.setExactY(20.0);
			assertNotEquals(30.0, ball.getExactY(), 0.0);
			assertEquals(20.0, ball.getExactY(), 0.0);
	}
	
	@Test
	void stop() {
		Ball ball = new Ball(0.0, 0.0, 0.0, 0.0);
		ball.stop();
		assertEquals (ball.stopped(), true);


	}
	
	@Test
	void start() {
		Ball ball = new Ball(0.0, 0.0, 0.0, 0.0);
		ball.start();
        assertNotSame(ball.getVelo(), new Vect(0, 0));


	}
	
	@Test
	void stopped() {
		Ball ball = new Ball(0.0, 0.0, 0.0, 0.0);
		assertEquals (ball.stopped(), false);


	}
	
	@Test
	void getColor() {
		Ball ball = new Ball(0.0, 0.0, 0.0, 0.0);
		ball.getColour();
		assertEquals (Color.BLUE, ball.getColour());



	}
	@Test
	void setColor() {
		Ball ball = new Ball(0.0, 0.0, 0.0, 0.0);
		ball.setColour(Color.BLUE);
        assertSame(ball.getColour(), Color.BLUE);

	}
	@Test
	void getDiameter() {
		Ball ball = new Ball(0.0, 0.0, 0.0, 0.0);
        ball.getDiameter();
    	assertEquals(ball.getDiameter(), ball.getRadius() * 2, 0.0);

	}

	@Test
	void getCenter() {
		Ball ball = new Ball(20.0, 20.0, 0, 0.0);
		ball.getCenter();
		Vect vect = new Vect(20.0, 20.0);
		assertEquals (ball.getCenter(), vect);
	}
	
	
}
