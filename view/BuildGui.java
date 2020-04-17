package view;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import controller.*;
import model.Model;

import static java.lang.Math.E;

/**
 * @author Murray Wood Demonstration of MVC and MIT Physics Collisions 2014
 */

public class BuildGui {

	private Model model;
	private JFrame frame;
	private ActionListener listener;
	private Board board;
	private ArrayList<AbstractButton> boardClickableButtons;
	private JSlider velo;
	private JTextField textField;

	static int x;

	static int y;


	public BuildGui(Model m) {
		model = m;
		boardClickableButtons = new ArrayList<>();
		// RunListener catches all GUI events. In reality might have many listeners.
		//TODO check if broken
		listener = new buildModeListener(model, this);


	}



	public void createAndShowGUI() {

		frame = new JFrame("Build Mode");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Build Mode");
		//frame.setResizable(false);



		JMenuBar menuB = new JMenuBar();
		JMenu menu = new JMenu("MENU");
		JMenuItem openMenu = new JMenuItem("Open");
		JMenuItem saveMenu = new JMenuItem("Save");
		JMenuItem exitMenu = new JMenuItem("Exit");
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
		board.createGridLines();



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
		paneGrid.setBorder(new EmptyBorder(30, 200, 5, 5));
		paneGrid.add(board);

		JPanel buttons = new JPanel();
		panel.add(buttons, BorderLayout.SOUTH);

		Font gf = new Font("Arial", Font.BOLD, 12);


//		JButton button1 = new JButton("Start");
//		button1.setFont(gf);
//		button1.addActionListener(listener);
//		button1.setPreferredSize(new Dimension(100,50));
//		buttons.add(button1);
//
//		JButton button2 = new JButton("Pause");
//		button2.setFont(gf);
//		button2.addActionListener(listener);
//		button2.setPreferredSize(new Dimension(100,50));
//		buttons.add(button2);
//
//		JButton button4 = new JButton("Restart");
//		button4.setFont(gf);
//		button4.addActionListener(listener);
//		button4.setPreferredSize(new Dimension(100,50));
//		buttons.add(button4);
//
//		JButton button5 = new JButton("End Game");
//		button5.setFont(gf);
//		button5.addActionListener(listener);
//		button5.setPreferredSize(new Dimension(100,50));
//		buttons.add(button5);

		JButton button3 = new JButton("Quit");
		button3.setFont(gf);
		button3.addActionListener(new exitListener());
		button3.setPreferredSize(new Dimension(100,50));
		buttons.add(button3);

		textField = new JTextField();
		textField.setColumns(50);
		textField.setEditable(false);
		buttons.add(textField, BorderLayout.WEST);


		/*JButton button6 = new JButton("Build Mode");
		button6.setFont(gf);				//NOT NEEDED IN MY FAKE BUILD
		button6.addActionListener(new buildModeListener(board) );
		button6.setPreferredSize(new Dimension(100,50));
		
		paneBuild.add(button6, BorderLayout.EAST); */

		//CODE ADDED FROM BUILD
		ButtonGroup gizmoChoiceGroup = new ButtonGroup();
		JPanel gizmobuttons = new JPanel();
		panel.add(gizmobuttons, BorderLayout.NORTH);
		//buttons.setLayout(new GridLayout(4, 1));


		JButton portalButton = new JButton("Portal");
		portalButton.setFont(gf);
		portalButton.addActionListener(new choiceSelectedListener(portalButton, gizmoChoiceGroup , this));
		gizmobuttons.add(portalButton);
		boardClickableButtons.add(portalButton);
		gizmoChoiceGroup.add(portalButton);
		portalButton.setActionCommand("Portal");




		JButton circleButton = new JButton("Circle");
		circleButton.setFont(gf);
		circleButton.addActionListener(new choiceSelectedListener(circleButton, gizmoChoiceGroup , this));
		gizmobuttons.add(circleButton);
        circleButton.setActionCommand("Circle");

		boardClickableButtons.add(circleButton);
		gizmoChoiceGroup.add(circleButton);



		JButton squareButton = new JButton("Square");
		squareButton.setFont(gf);
		squareButton.addActionListener(new choiceSelectedListener(squareButton, gizmoChoiceGroup , this));
		gizmobuttons.add(squareButton);
        squareButton.setActionCommand("Square");

		boardClickableButtons.add(squareButton);
		gizmoChoiceGroup.add(squareButton);


		JButton triangleButton = new JButton("Triangle");
		triangleButton.setFont(gf);
		triangleButton.addActionListener(new choiceSelectedListener(triangleButton, gizmoChoiceGroup , this));
		gizmobuttons.add(triangleButton);
        triangleButton.setActionCommand("Triangle");

		boardClickableButtons.add(triangleButton);
		gizmoChoiceGroup.add(triangleButton);


		JButton absorberButton = new JButton("Absorber");
		absorberButton.setFont(gf);
		absorberButton.addActionListener(new choiceSelectedListener(absorberButton, gizmoChoiceGroup , this));
		gizmobuttons.add(absorberButton);
        absorberButton.setActionCommand("Absorber");

		boardClickableButtons.add(absorberButton);
		gizmoChoiceGroup.add(absorberButton);




		JButton addballButton = new JButton("Add Ball");
		addballButton.setFont(gf);
		addballButton.addActionListener(new choiceSelectedListener(addballButton, gizmoChoiceGroup , this));
		gizmobuttons.add(addballButton);
		addballButton.setActionCommand("Ball");

		boardClickableButtons.add(addballButton);
		gizmoChoiceGroup.add(addballButton);

		JButton switchButton = new JButton("Run Mode");
		switchButton.setFont(gf);
		gizmobuttons.add(switchButton);
		switchButton.addActionListener(listener);


		JPanel westPanel = new JPanel(new GridLayout(0,1));
		panel.add(westPanel, BorderLayout.WEST);
		JButton rotateButton = new JButton("Rotate");
        JButton deleteButton = new JButton("Delete");
		JButton connectButton = new JButton("Connect");
		JButton disconnectButton = new JButton("Disconnect");
		JButton moveButton = new JButton("Move");
		JButton clearAllButton = new JButton("Clear");

		clearAllButton.addActionListener(new clearBoardListener(model));

		// add choiceSelectedListener to know its is selected
        rotateButton.addActionListener(new choiceSelectedListener(rotateButton, gizmoChoiceGroup , this));
        deleteButton.addActionListener(new choiceSelectedListener(deleteButton, gizmoChoiceGroup , this));
        connectButton.addActionListener(new choiceSelectedListener(connectButton, gizmoChoiceGroup , this));
        disconnectButton.addActionListener(new choiceSelectedListener(disconnectButton, gizmoChoiceGroup , this));
        moveButton.addActionListener(new choiceSelectedListener(moveButton, gizmoChoiceGroup , this));



        // set action commands
        rotateButton.setActionCommand("Rotate");
        deleteButton.setActionCommand("Delete");
        connectButton.setActionCommand("Connect");
        disconnectButton.setActionCommand("Disconnect");
        moveButton.setActionCommand("Move");
        clearAllButton.setActionCommand("Clear All");

        // add them to board clickable buttons
        boardClickableButtons.add(rotateButton);
        boardClickableButtons.add(deleteButton);
        boardClickableButtons.add(connectButton);
        boardClickableButtons.add(disconnectButton);
        boardClickableButtons.add(moveButton);
        boardClickableButtons.add(clearAllButton);

		// add them to the button group
		gizmoChoiceGroup.add(rotateButton);
		gizmoChoiceGroup.add(deleteButton);
		gizmoChoiceGroup.add(connectButton);
		gizmoChoiceGroup.add(disconnectButton);
		gizmoChoiceGroup.add(moveButton);
		gizmoChoiceGroup.add(clearAllButton);

		westPanel.add(rotateButton);
		westPanel.add(deleteButton);
		westPanel.add(connectButton);
		westPanel.add(disconnectButton);
		westPanel.add(moveButton);
		westPanel.add(clearAllButton);
		ButtonGroup group = new ButtonGroup();
		group.add(rotateButton);
		group.add(deleteButton);
		group.add(connectButton);
		group.add(disconnectButton);
		group.add(moveButton);
		group.add(clearAllButton);



	    int velo_min = 10;
		int velo_max = 100;
		int velo_ave = 50;
		JLabel veloSliderLabel = new JLabel("Ball Velo");
		veloSliderLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		velo = new JSlider(JSlider.HORIZONTAL,velo_min, velo_max, velo_ave);
		velo.setMajorTickSpacing(10);
		velo.setMinorTickSpacing(1);
		velo.setPaintTicks(true);
		velo.setPaintLabels(true);
		if(model.getBall()!= null) {
			int ballSpeed = (int) model.getBall().getVelo().x() / 10;
			if (ballSpeed < 0) {
				ballSpeed *= -1;
			}

			velo.setValue(ballSpeed);
		}else{
			velo.setValue(50);
		}
		JPanel velo1 = new JPanel();

		JPanel eastPanel = new JPanel();

		eastPanel.add(velo, BorderLayout.EAST);
		eastPanel.add(veloSliderLabel);
		eastPanel.add(velo1);
		panel.add(eastPanel,BorderLayout.EAST);
//		panel.add(buttons, BorderLayout.LINE_START);
//		panel.add(board, BorderLayout.CENTER);v
		board.addMouseListener(new boardClickedListener(this,model,board));


		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	public String getSelectedButton(){
		for(AbstractButton button : boardClickableButtons){
		    			if(button.isSelected()){
				return button.getActionCommand();
			}
		}
		return null;
	}
	
	public void getRidofGUI() {
			frame.dispose();
		}

    public int getSliderValue() {
	    x = velo.getValue();
	   y = velo.getValue();
	   return velo.getValue();
    }
    

    public static int getVelX() {

    	return x;


    }

    public static int getVelY() {

    	return y;

    	

    }

	public void setTextField(String s) {
		textField.setText(s);
	}
}
