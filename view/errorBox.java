package view;

import javax.swing.*;

public class errorBox {
    String message;

    public errorBox(String message){

        this.message = message;

        JFrame frame = new JFrame();
        JOptionPane pane = new JOptionPane();

        pane.showMessageDialog(frame, message,"Error Message",JOptionPane.INFORMATION_MESSAGE);
        frame.add(pane);
    }
}
