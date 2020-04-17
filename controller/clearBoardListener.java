package controller;

import model.Model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class clearBoardListener implements ActionListener {

    private Model model;

    public clearBoardListener(Model m){
        this.model = m;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        model.deleteAllcontent();
    }
}
