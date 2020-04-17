package controller;

import model.*;
import view.Board;
import view.BuildGui;
import view.RunGui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class boardClickedListener implements MouseListener {
    private BuildGui gui;
    private Model model;
    private Board board;
    private boolean absorberActive, moveActive,connectActive, disconnectActive;
    private int absorberX1, absorberY1, moveX1, Y1;
    private Gizmos moveGizmo,connectGizmo, disconnectedGizmo;



    public boardClickedListener(BuildGui gui, Model model, Board board ) {
        this.gui = gui;
        this.model = model;
        this.board = board;
        absorberActive = false;
        moveActive = false;
        connectActive = false;

        gui.setTextField("Select a button");


    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX() / 25;
        int y = e.getY() / 25;
        if (gui.getSelectedButton() == null) {
        } else {
            if (gui.getSelectedButton().equals("Square")) {
                setBooleansFalse();
                if (!model.isOccupied(x, y)) {
                    model.addSquare(new Square(x * 25, y * 25));
                } else {
                }
            } else if (gui.getSelectedButton().equals("Triangle")) {
                setBooleansFalse();
                if (!model.isOccupied(x, y)) {
                    model.addTriangle(new Triangle(x * 25, y * 25));
                } else {
                }
            } else if (gui.getSelectedButton().equals("Circle")) {
                setBooleansFalse();
                if (!model.isOccupied(x, y)) {
                    model.addCircle(new CircleGiz(x * 25, y * 25));
                } else {
                }
            } else if (gui.getSelectedButton().equals("Ball")) {
                setBooleansFalse();
                if (!model.isOccupied(x, y)) {
                    Ball oldBall = model.getBall();
                    if (oldBall != null) {
                        model.setBall(new Ball((x * 25) + oldBall.getRadius(), (y * 25) + oldBall.getRadius(), oldBall.getVelo().x(), oldBall.getVelo().y()));
                    }else{
                        model.setBall(new Ball((x * 25) + 6.25, (y * 25) +6.25, 500, 500));

                    }
                }else {
                    // todo error message: location is occupied
                }
            } else if (gui.getSelectedButton().equals("Absorber")) {
                moveActive = false;
                connectActive = false;
                if (!model.isOccupied(x, y)) {
                    if (!absorberActive) {
                        absorberX1 = x;
                        absorberY1 = y;
                        absorberActive = true;
                    } else {
                        if (!model.isOccupied(absorberX1*25, absorberY1*25, x*25, y*25)) {
                            model.addAbsorber(new Absorber(absorberX1 * 25, absorberY1 * 25, (x * 25) + 25, (y * 25) + 25));
                            absorberActive = false;
                            gui.setTextField("Select the top left most postion you want the absorber to cover");

                        }else{
                            // TODO: 18/03/2019  error message : area occupied
                            absorberActive = false;
                            gui.setTextField("Select the top left most postion you want the absorber to cover");

                        }
                    }
                }
            } else if (gui.getSelectedButton().equals("Rotate")) {
                setBooleansFalse();
                Gizmos gizmo;
                if ((gizmo = model.getGizmo(x, y)) != null) {
                    gizmo.rotate();
                    board.repaint();

                } else {
                    // TODO: 18/03/2019 error message: nothing in spot
                }

            } else if (gui.getSelectedButton().equals("Delete")) {
                setBooleansFalse();
                model.deleteGizmo(x, y);
                board.repaint();

            }
            else if(gui.getSelectedButton().equals("Move")){
                absorberActive = false;
                connectActive = false;
               if(!moveActive) {
                   moveGizmo = model.getGizmo(x, y);

                   if (moveGizmo != null) {
                       moveActive = true;

                   }
               }else {
                   if (moveGizmo instanceof Absorber) {
                       int x12 = x*25;
                       int y12 = y*25;
                       int x21 = (((Absorber) moveGizmo).getWidth()) + (x*25);
                       int y21 = (((Absorber) moveGizmo).getHeight())+(y*25);
                       if(!model.isOccupied(x12, y12,x21-25,y21-25)){
                           int width = ((Absorber) moveGizmo).getWidth();
                           int height = ((Absorber) moveGizmo).getHeight();
                           moveGizmo.setX(x * 25);
                           moveGizmo.setY(y * 25);

                           int x2 = (x*25) + width;
                           int y2 = (y*25) + height;

                           ((Absorber) moveGizmo).setX2(x2);
                           ((Absorber) moveGizmo).setY2(y2);

                           model.redraw();
                           moveActive = false;
                           gui.setTextField("Select a gizmo you would like to move");
                       }else{
                           moveActive = false;
                           gui.setTextField("Select a gizmo you would like to move");
                       }
                   } else if(moveGizmo instanceof Portal){{
                       if(!model.isOccupied(x*25,y*25,x*25,((y*25)+100))){
                           moveGizmo.setX(x*25);
                           moveGizmo.setY(y*25);
                           model.redraw();
                           moveActive = false;
                           gui.setTextField("Select a gizmo you would like to move");

                       }else{
                           moveActive = false;
                           gui.setTextField("Select a gizmo you would like to move");

                       }
                   }

                   }else {
                       if (!model.isOccupied(x, y)) {

                           moveGizmo.setX(x * 25);
                           moveGizmo.setY(y * 25);


                           model.redraw();
                           moveActive = false;
                           gui.setTextField("Select a gizmo you would like to move");

                       } else {
                           moveActive = false;
                           gui.setTextField("Select a gizmo you would like to move");
                       }
                   }
               }
               }else if(gui.getSelectedButton().equals("Connect")){
                moveActive = false;
                absorberActive = false;
                disconnectActive = false;
                if(!connectActive){
                    connectGizmo = model.getGizmo(x,y);
                    if(!(connectGizmo instanceof Absorber)&& connectGizmo != null){
                        connectActive = true;
                    }
                }else{
                    Gizmos connectTo = model.getGizmo(x,y);
                    if(connectTo != null){
                        connectGizmo.addConnection(connectTo);

                    }
                    connectActive = false;
                    gui.setTextField("Select the gizmo you would like to connect");

                }

             }
             else if(gui.getSelectedButton().equals("Disconnect")){
                 moveActive = false;
                 absorberActive = false;
                 connectActive = false;

                 if(!disconnectActive){
                     disconnectedGizmo = model.getGizmo(x,y);
                     if(!(disconnectedGizmo instanceof  Absorber) && disconnectedGizmo != null ){
                         disconnectActive = true;
                     }

                 }else{
                     Gizmos disconnectFrom = model.getGizmo(x,y);
                     if(disconnectFrom != null){
                         disconnectedGizmo.removeConnection(disconnectFrom);
                     }
                     disconnectActive = false;
                     gui.setTextField("Select the gizmo you would like to disconnect");


                 }
            }else if (gui.getSelectedButton().equals("Portal")) {
                setBooleansFalse();
                if (!model.isOccupied(x*25, y*25,x*25,((y*25)+75))){
                    model.addPortalOne(new Portal(x * 25, y * 25));
                } else {
                    // TODO: 18/03/2019 error message : area occupied is already there
                }
            }
            }
            if(moveActive){
                gui.setTextField("Now press where you want to move it");
            }
            if(absorberActive){
                gui.setTextField("Now press the bottom right most box you would like the absorber to cover");
            }
            if(connectActive){
                gui.setTextField("Now press the gizmo you would like to it to connect to");

            }
            if(disconnectActive){
                gui.setTextField("Now press the gizmo you would like to disconnect from it");

            }
        }

        public void setBooleansFalse(){
            absorberActive = false;
            moveActive = false;
            connectActive = false;
            disconnectActive = false;


        }








    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
