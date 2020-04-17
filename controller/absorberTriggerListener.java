package controller;

import model.Absorber;
import model.Ball;
import model.Model;
import physics.Vect;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class absorberTriggerListener implements KeyListener {
    Model model;
    public absorberTriggerListener(Model m){
        model = m;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == 32){
            if(model.isBallInAbsorber()){
                Vect ballSpeedBefore = model.getBellBeforeHitVelocity();
                Vect newSpeed;

                if(ballSpeedBefore.y() >0 ){
                    newSpeed = new Vect(0,ballSpeedBefore.y()*-1);

                }else{
                    newSpeed = new Vect(0, ballSpeedBefore.y());
                }
                Absorber absorber = (Absorber) model.getCollidedGizmo();
                Ball ball = model.getBall();
                ball.setExactX(absorber.getX2()-ball.getRadius());
                ball.setExactY(absorber.getY()-ball.getRadius());
                ball.setVelo(newSpeed);
                model.setInAbsorber(false);
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
