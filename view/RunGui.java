package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import controller.*;
import model.Model;

/**
 * @author Murray Wood Demonstration of MVC and MIT Physics Collisions 2014
 */

public class RunGui {

	private Model model;
	private JFrame frame;
	private ActionListener listener;
	private Board board;
	private ButtonGroup gizmoChoiceGroup;
	private ArrayList<JButton> boardClickableButtons;

	public RunGui(Model m) {
		model = m;

		// RunListener catches all GUI events. In reality might have many listeners.
		listener = new RunListener(m, this);
		boardClickableButtons = new ArrayList<>();
		
	
	}
	
	

	public void createAndShowGUI() {

		frame = new JFrame("Run Mode");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Run Mode");
		frame.setResizable(false);
		frame.addKeyListener(new absorberTriggerListener(model));
		
		
		JMenuBar menuB = new JMenuBar();
		JMenu menu = new JMenu("MENU");
		JMenuItem openMenu = new JMenuItem("Open",null);
		JMenuItem saveMenu = new JMenuItem("Save", null);
		JMenuItem exitMenu = new JMenuItem("Exit", null);
		openMenu.addActionListener(new openListener(model));
		saveMenu.addActionListener(new saveListener(model));
		exitMenu.addActionListener(new exitListener());
		menu.add(openMenu);
		menu.add(saveMenu);
		menu.add(exitMenu);
		menuB.add(menu);
		frame.setJMenuBar(menuB);
		
		// Board is passed the Model so it can act as Observer
		board = new Board(500, 500, model);
		
		

//		Container cp = frame.getContentPane();
		
		//panels
		
		//main panel
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		frame.getContentPane().add(panel);
		

		
		JPanel paneBuild = new JPanel(new BorderLayout());
		panel.add(paneBuild, BorderLayout.NORTH);
		
		JPanel paneGrid = new JPanel(new BorderLayout());
		panel.add(paneGrid, BorderLayout.CENTER);
		paneGrid.setBorder(new EmptyBorder(5, 200, 5, 200));
		paneGrid.add(board);
		
		JPanel buttons = new JPanel();
		panel.add(buttons, BorderLayout.SOUTH);
		



		Font gf = new Font("Arial", Font.BOLD, 12);


		JButton button1 = new JButton("Start");
		button1.setFont(gf);
		button1.addActionListener(listener);
		button1.setPreferredSize(new Dimension(100,50));
		buttons.add(button1);

		JButton button2 = new JButton("Pause");
		button2.setFont(gf);
		button2.addActionListener(listener);
		button2.setPreferredSize(new Dimension(100,50));
		buttons.add(button2);

		JButton button4 = new JButton("Restart");
		button4.setFont(gf);
		button4.addActionListener(listener);
		button4.setPreferredSize(new Dimension(100,50));
		buttons.add(button4);
		
		JButton button5 = new JButton("End Game");
		button5.setFont(gf);
		button5.addActionListener(listener);
		button5.setPreferredSize(new Dimension(100,50));
		buttons.add(button5);

		JButton button3 = new JButton("Quit");
		button3.setFont(gf);
		button3.addActionListener(listener);
		button3.setPreferredSize(new Dimension(100,50));
		buttons.add(button3);

		JButton button6 = new JButton("Build Mode");
		button6.setFont(gf);
		button6.addActionListener(listener);
		button6.setPreferredSize(new Dimension(100,50));
		
		paneBuild.add(button6, BorderLayout.EAST);

		button1.setFocusable(false);
		button2.setFocusable(false);
		button3.setFocusable(false);
		button4.setFocusable(false);
		button5.setFocusable(false);
		button6.setFocusable(false);

		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
    public void getRidofGUI() {
		frame.dispose();
	}

}
