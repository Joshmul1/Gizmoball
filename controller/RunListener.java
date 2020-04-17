package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import model.Model;
import view.BuildGui;
import view.RunGui;

/**
 * @author Murray Wood Demonstration of MVC and MIT Physics Collisions 2014
 */

public class RunListener implements ActionListener {

	private Timer timer;
	private Model model;
	private RunGui rGUI;

	public RunListener(Model m, RunGui rg) {
		model = m;
		timer = new Timer(50, this);
		rGUI = rg;
	}

	@Override
	public final void actionPerformed(final ActionEvent e) {

		if (e.getSource() == timer) {
			model.moveBall();
		} else
			switch (e.getActionCommand()) {
			case "Start":
				timer.start();
				break;
			case "Pause":
				timer.stop();
				break;
			case "Restart":
				model.resetBall();
				timer.start();
				break;
			case "End Game":
				model.removeBall();
				timer.stop();
				break;	
			case "Quit":
			System.exit(0);
				break;
			case "Build Mode":
				BuildGui gui = new BuildGui(model);
				timer.stop();
				gui.createAndShowGUI();
				rGUI.getRidofGUI();
				break;
			}
	}
}
