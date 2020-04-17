package main;

import javax.swing.UIManager;

import model.*;
import view.BuildGui;
import view.RunGui;

/**
 * @author Murray Wood Demonstration of MVC and MIT Physics Collisions 2014
 */

public class Main {

	public static void main(String[] args) {
		try {
			// Use the platform look and feel
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
		}

		Model model = new Model();


		BuildGui gui = new BuildGui(model);
		gui.createAndShowGUI();
	}


}
