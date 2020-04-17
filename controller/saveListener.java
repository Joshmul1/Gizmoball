package controller;


import model.FileFormat;
import model.Model;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class saveListener implements ActionListener {
    FileFormat ff;
    Model model;
    List<Object> allObjects;


    public saveListener(Model m){
        model = m;

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        //allObjects = model.getAllBoardObjects();
        ff = new FileFormat();
        try {
            File fileToSave = saveDirectory();
            if (fileToSave != null) {
                ff.saveToFile(model, fileToSave);


            }

        } catch (IOException e1) {
            e1.printStackTrace();
        }


    }

    private File saveDirectory(){

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Specify a file to save");

        int userSelection = fileChooser.showSaveDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            return fileToSave;
        }
        else{
            return null;
        }

    }
}
