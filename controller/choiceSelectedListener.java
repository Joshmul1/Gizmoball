package controller;

import view.BuildGui;
import view.RunGui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class choiceSelectedListener implements ActionListener {
    private AbstractButton button;
    private BuildGui gui;
    private ButtonGroup bg;
    public choiceSelectedListener(AbstractButton button, ButtonGroup bg, BuildGui gui){
        this.bg = bg;
        this.button = button;
        this.gui = gui;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(!button.getModel().isSelected()) {
            bg.setSelected(button.getModel(), true);
                changeInstructions(button.getActionCommand());
        }




    }

    private void changeInstructions(String actionCommand) {
        switch(actionCommand) {
            case "Square":
                setText("Click a box to create a square");
                break;

            case "Triangle":
                setText("Click a box to create a triangle");
                break;

            case "Circle":
                setText("Click a box to create a circle");
                break;

            case "Ball":
                setText("Click a box to create or move the ball");
                break;

            case "Absorber":
                setText("Select the top left most position you want the absorber to be in");
                break;

            case "Move":
                setText("Select the gizmo you would like to move");
                break;

            case "Rotate":
                setText("Select the gizmo you would like to rotate. TIP: absorbers and portals cannot be rotated");                break;

            case "Delete":
                setText("Select the gizmo you would like to delete TIP: Does not work on ball");
                break;

            case "Connect":
                setText("Select the gizmo you would like to connect");
                 break;
            case "Disconnect":
                setText("Select the gizmo you would like to disconnect");
                break;

            case "Portal":
                setText("Click a box to add a portal");
                break;


            default:
                 setText("Error : No such changeInstruction command for "+ actionCommand);
                 break;
        }

    }
    private void setText(String s){
        gui.setTextField(s);
    }
}
