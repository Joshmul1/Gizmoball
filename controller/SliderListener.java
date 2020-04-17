package controller;

import javax.swing.JSlider;
import model.Model;
import view.BuildGui;
import view.RunGui;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class SliderListener implements ChangeListener {
	
	private Model model;
	
	public void SliderListen(Model m) {
		model  = m;
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		
	}

}
