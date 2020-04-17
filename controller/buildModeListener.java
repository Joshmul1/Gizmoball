package controller;

import view.Board;
import view.BuildGui;
import view.RunGui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.Ball;
import model.Model;
import physics.Vect;

public class buildModeListener implements ActionListener, ChangeListener {

    private Model model;
    private BuildGui bGUI;
    private int x = BuildGui.getVelX();
    private int y = BuildGui.getVelY();

    public buildModeListener(Model m, BuildGui bg) {
        model = m;
        bGUI = bg;

    }



    @Override
    public void actionPerformed(ActionEvent e) {


    	switch (e.getActionCommand()) {

    	case "Run Mode":

    		RunGui gui = new RunGui(model);

			x = bGUI.getSliderValue()* 10;

			y = bGUI.getSliderValue()*10;

			Vect velocity = new Vect(x, y);
			if(model.getBall() != null) {
                model.getBall().setVelo(velocity);
                model.setOriginalBall(model.getBall().copy());
            }
//			Ball.setVelo(velocity);

			gui.createAndShowGUI();

			bGUI.getRidofGUI();
			model.removeLines();

			break;
    	}

    		

    	}

    @Override
    public void stateChanged(ChangeEvent e) {

    }
}