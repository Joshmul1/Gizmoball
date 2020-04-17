package controller;


import model.FileFormat;
import model.Model;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class openListener implements ActionListener {
    FileFormat ff;
    Model model;
    public openListener(Model m){
        model = m;

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        ff = new FileFormat();
        try {

            File file = chooseFile();
            ff.openFile2(file);
            model.setTriangles(ff.getTriangles());
            model.setAbsorbers(ff.getAbsorbers());
            model.setBall(ff.getBall());
            model.setCircles(ff.getCircles());
            model.setSquares(ff.getSquares());
            model.setPortalOne(ff.getPortals());




        } catch (IOException e1) {
            e1.printStackTrace();
        }




    }
    private File chooseFile() throws IOException {
        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        int returnValue = jfc.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            return jfc.getSelectedFile();
        }
        else{
            throw new IOException("THIS MEANS THE FILE SOMEHOW DOESN'T EXIST BUT YOU CLICKED IT HOW IN THE NAME OF????");
        }
    }
}
