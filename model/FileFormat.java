package model;


import physics.Vect;

import javax.swing.*;
import java.io.*;
import java.lang.reflect.Array;
import java.util.*;


public class FileFormat {

    private HashMap<String, Gizmos> map = new HashMap<>();

    private ArrayList<Line> lines;
    private ArrayList<Square> squares;
    private ArrayList<Triangle> triangles;
    private ArrayList<CircleGiz> circles;
    private ArrayList<Absorber> absorbers;
    private ArrayList<Portal> portals;

    private Ball ball;



    public FileFormat(){

        squares = new ArrayList<>();
        triangles = new ArrayList<>();
        circles = new ArrayList<>();
        absorbers = new ArrayList<>();
        portals = new ArrayList<>();




    }

    public boolean isGizmo(String opcode){
        if(opcode.equals("Triangle") || opcode.equals("Square") ||
                opcode.equals("LeftFlipper") || opcode.equals("RightFlipper")
                || opcode.equals("Circle")){
            return true;
        }

        return false;
    }
/*
    public void openFile() throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader("file.txt"));
        StringTokenizer tokens;
        String line = reader.readLine();
        String opcode;
        String opcodeName;
        String x,y, vx,vy,x2,y2;
        String opcodeNameConsumer;
        String keyString, keyValue,pressType;
        String mu,mu2;

        while(line != null){

            tokens = new StringTokenizer(line);

            if(!tokens.hasMoreTokens()){
                line = reader.readLine();
                continue;

            }
            opcode = tokens.nextToken();

            opcodeName = tokens.nextToken();
            if(isGizmo(opcode)){

                x = tokens.nextToken();
                y = tokens.nextToken();

                createGizmo(opcodeName, opcode, x ,y);
            }
            else{
                if(opcode.equals("Model.Ball")){
                    x = tokens.nextToken();
                    y = tokens.nextToken();

                    vx = tokens.nextToken();
                    vy = tokens.nextToken();



                    createBall(opcodeName, x,y,vx,vy);
                }
                else if(opcode.equals("Model.Absorber")){
                    x = tokens.nextToken();
                    y = tokens.nextToken();

                    x2 = tokens.nextToken();
                    y2 = tokens.nextToken();
                    createAbsorber(opcodeName, x,y,x2,y2);


                }
                else if(opcode.equals("Rotate")){
                    rotate(opcodeName);

                }
                else if(opcode.equals("Delete")){
                    delete(opcodeName);
                }
                else if(opcode.equals("Move")){
                    x = tokens.nextToken();
                    y = tokens.nextToken();

                    move(opcodeName, x , y);

                }
                else if(opcode.equals("Connect")){
                    opcodeNameConsumer = tokens.nextToken();
                    connect(opcodeName, opcodeNameConsumer);
                }
                else if(opcode.equals("KeyConnect")){
                        keyString = tokens.nextToken();
                        keyValue = tokens.nextToken();
                        pressType = tokens.nextToken();
                        opcodeName = tokens.nextToken();

                        keyConnect(keyValue , pressType , opcodeName);

                }
                else if(opcode.equals("Gravity")){
                    String gravity = tokens.nextToken();
                    setGravity(gravity);


                }
                else if(opcode.equals("Friction")){
                    mu = tokens.nextToken();
                    mu2 = tokens.nextToken();

                    setFriction(mu, mu2);
                }

            }

        }


    }*/



    public File saveDirectory(){

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
    public void openFile2(File openFile) throws IOException{
        map.clear();
        File file = openFile;
        BufferedReader reader = new BufferedReader(new FileReader(file));


        //BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Josh\\Downloads\\gizmoball\\src\\text"));
        StringTokenizer tokens;
        String line = reader.readLine();
        String opcode;
        String opcodeName;
        String x, y, vx, vy, x2, y2;
        String opcodeNameConsumer;
        String keyString, keyValue, pressType;
        String mu, mu2;

        while (line != null) {

            tokens = new StringTokenizer(line);

            if (!tokens.hasMoreTokens()) {
                line = reader.readLine();
                continue;

            }

            opcode = tokens.nextToken();



            if (isGizmo(opcode)) {
                if (tokens.countTokens() != 3 ) {
                    line = reader.readLine();


                }else {
                    opcodeName = tokens.nextToken();
                    x = tokens.nextToken();
                    y = tokens.nextToken();

                    createGizmo(opcodeName, opcode, x, y);
                }
            } else {
                switch (opcode) {
                    case "Ball":
                        if (tokens.countTokens() != 5) {
                            line = reader.readLine();
                        } else {
                            opcodeName = tokens.nextToken();
                            x = tokens.nextToken();
                            y = tokens.nextToken();
                            vx = tokens.nextToken();
                            vy = tokens.nextToken();
                            createBall(opcodeName, x, y, vx, vy);
                        }

                        break;
                    case "Absorber":
                        if(tokens.countTokens() != 5){
                            line = reader.readLine();
                        }else {
                            opcodeName = tokens.nextToken();
                            x = tokens.nextToken();
                            y = tokens.nextToken();

                            x2 = tokens.nextToken();
                            y2 = tokens.nextToken();
                            createAbsorber(opcodeName, x, y, x2, y2);
                        }
                        break;
                    case "Rotate":
                        if(tokens.countTokens() !=1){
                            line = reader.readLine();
                        }else {
                            opcodeName = tokens.nextToken();
                            rotate(opcodeName);
                        }
                        break;
                    case "Delete":
                        if(tokens.countTokens() != 1){
                            line = reader.readLine();
                        }else {
                            opcodeName = tokens.nextToken();
                            delete(opcodeName);
                        }
                        break;
                    case "Move":
                        if(tokens.countTokens() != 3){
                            line = reader.readLine();
                        }else {
                            opcodeName = tokens.nextToken();
                            x = tokens.nextToken();
                            y = tokens.nextToken();
                            move(opcodeName, x, y);
                        }
                        break;
                    case "Connect":
                        if(tokens.countTokens() != 2){
                            line = reader.readLine();
                        }else {
                            opcodeName = tokens.nextToken();
                            opcodeNameConsumer = tokens.nextToken();
                            connect(opcodeName, opcodeNameConsumer);
                        }
                        break;
                    case "Velocity":
                        if(tokens.countTokens() != 2){
                            line = reader.readLine();
                        }else{
                             x = tokens.nextToken();
                             y = tokens.nextToken();
                             setVelocity(x,y);
                        }
                        break;
                    case "Portal":
                        if(tokens.countTokens() != 3){
                            line = reader.readLine();
                        }else{
                            opcodeName = tokens.nextToken();
                            x = tokens.nextToken();
                            y = tokens.nextToken();
                            createPortal(opcodeName, x,y);
                        }
                        break;

                }


            }

            line = reader.readLine();

        }




    }

    private void createPortal(String opcodeName, String x, String y) throws NumberFormatException {
        try {
            int xInt = Integer.parseInt(x) * 25;
            int yInt = Integer.parseInt(y) * 25;

            Portal port = new Portal(xInt, yInt);
            map.put(opcodeName, port);
            portals.add(port);

        }catch (NumberFormatException e){
        }

    }

    private void setVelocity(String x, String y) throws NumberFormatException{
        try{
            if(ball != null){
            int xVel = Integer.parseInt(x);
            int yVel = Integer.parseInt(y);
                ball.setVelo(new Vect(xVel,yVel));
            }

        }catch (NumberFormatException e){

        }

    }

    public void printDetailsToKnowImRight(){



    }


    private void setFriction(String mu, String mu2) {
    }

    private void setGravity(String gravity) {
    }

    private void keyConnect(String keyValue, String pressType, String opcodeName) {

    }

    private void connect(String opcodeName, String opcodeNameConsumer) {
        Gizmos gizmo1 = map.get(opcodeName);
        Gizmos gizmo2 = map.get(opcodeNameConsumer);
        if(gizmo1 != null && gizmo2 != null){
            gizmo1.addConnection(gizmo2);
        }else{

        }
    }


    private void moveGizmo(String opcodeName, String x, String y) throws NumberFormatException {
       try {
           Gizmos giz = map.get(opcodeName);
           int xInt = Integer.parseInt(x) * 25;
           int yInt = Integer.parseInt(y) * 25;
           if (giz != null) {
               giz.setX(xInt);
               giz.setY(yInt);
           }
       } catch (NumberFormatException e){

       }

    }
    private void moveBall(String opcodeName, String x, String y) {



    }

    private void move(String opcodeName, String x, String y) {
        if(x.contains(".") && y.contains(".")){
            moveBall(opcodeName,x,y);
        }
        else {
            moveGizmo(opcodeName,x,y);
        }

    }

    private void delete(String opcodeName) {
        //boardContents.remove(map.get(opcodeName));
    }

    private void rotate(String opcodeName) {
        Gizmos giz = map.get(opcodeName);
        if(giz != null){
            giz.rotate();

        }



    }

    private void createAbsorber(String opcodeName, String x, String y, String x2, String y2) throws NumberFormatException {
        try {
            int xInt = Integer.parseInt(x) * 25;
            int yInt = Integer.parseInt(y) * 25;
            int x2Int = Integer.parseInt(x2) * 25;
            int y2Int = Integer.parseInt(y2) * 25;
            Absorber abs = new Absorber(xInt, yInt, x2Int, y2Int);
            // Object abs = new Model.Absorber(x,y,x2,y2);
            map.put(opcodeName, abs);
            absorbers.add(abs);

        }catch (NumberFormatException e){
        }
    }

    private void createBall(String opcodeName, String x, String y, String vx, String vy) throws NumberFormatException {
//        int xInt = Integer.parseInt(x);
//        int yInt = Integer.parseInt(y);
//        int vxInt = Integer.parseInt(vx);
//        int vyInt = Integer.parseInt(vy);
     try {
         double xInt = Double.parseDouble(x) * 25;
         double yInt = Double.parseDouble(y) * 25;
         double vxInt = Double.parseDouble(vx) * 25;
         double vyInt = Double.parseDouble(vy) * 25;
         Ball ball = new Ball(xInt, yInt, vxInt, vyInt);
         // Object ball = new Ball(x ,y ,vx ,vy);
         map.put(opcodeName, ball);
         this.ball = ball;

     }catch (NumberFormatException e) {

     }
    }

 /*   private void createGizmo(String opcodeName, String opcode, String x, String y) {
       switch(opcode){
           case "Triangle":
               Triangle tri  = new Triangle(x ,y);
               //Object tri  = new Triangle(x ,y);
               map.put(opcodeName,tri);
               boardContents.add(tri);


           case "Square":
               Square sq = new Square(x ,y);
              // Object sq = new Square(x ,y);
               map.put(opcodeName,sq);
               boardContents.add(sq);




           case "Circle":
               Circle cir = new Circle(x, y);
              // Object cir = new Circle(x, y);
               map.put(opcodeName,cir);
               boardContents.add(cir);



           case "LeftFlipper":
               LeftFlipper lf = new LeftFlipper(x,y);
               //Object lf = new LeftFlipper(x,y);
               map.put(opcodeName,lf);
               boardContents.add(lf);


           case "RightFlipper":
               RightFlipper rf = new RightFlipper(x,y);
              // Object rf = new RightFlipper(x,y);
               map.put(opcodeName,rf);
               boardContents.add(rf);



       }

    } */

    private void createGizmo(String opcodeName, String opcode, String x, String y) {
      try {
          int xInt = Integer.parseInt(x) * 25;
          int yInt = Integer.parseInt(y) * 25;
          if (opcode.equals("Triangle")) {
              Triangle tri = new Triangle(xInt, yInt);
              //Object tri  = new Triangle(x ,y);
              map.put(opcodeName, tri);
              triangles.add(tri);

          } else if (opcode.equals("Square")) {
              Square sq = new Square(xInt, yInt);
              // Object sq = new Square(x ,y);
              map.put(opcodeName, sq);
              squares.add(sq);

          } else if (opcode.equals("Circle")) {
              CircleGiz cir = new CircleGiz(xInt, yInt);
              // Object cir = new Circle(x, y);
              map.put(opcodeName, cir);
              circles.add(cir);
          }
      }catch (NumberFormatException e){

      }

    }




    public void saveToFile(Model model, File file) throws IOException {
        int ballCount= 0,circleCount = 0 , squareCount= 0 ,triCount= 0 ,absCount = 0, portalCount = 0;

        List<Square> squares = model.getSquares();
        List<Absorber> absorbers = model.getAbsorbers();
        Ball ball = model.getBall();
        List<CircleGiz> circles = model.getCircle();
        List<Triangle> triangles = model.getTriangle();
        List<Portal> portals = model.getPortalOne();
        BufferedWriter bw = new BufferedWriter(new FileWriter(file,false));
        HashMap<Gizmos, String> gizmoToString = new HashMap<>();
        ArrayList<Gizmos> connectedGizmos = new ArrayList<>();
            if (ball != null) {
                bw.write("Ball" + " " + "B" + ballCount + " " + ball.getExactX()/25 + " " + ball.getExactY()/25 + " " + ball.getVelo().x() + " " + ball.getVelo().y());
               // ballCount++;
                bw.newLine();
                bw.write("");

            } for(CircleGiz circle: circles) {
                bw.write("Circle" + " " + "C"+circleCount + " " + circle.getX()/25+ " " + circle.getY()/25);
                gizmoToString.put(circle,"C"+circleCount);
                circleCount++;
                bw.newLine();

            } for(Square square : squares){
                bw.write("Square " + " " +"S" +squareCount + " " + square.getX()/25+ " " + square.getY()/25);
                gizmoToString.put(square,"S"+squareCount);
                squareCount++;
                bw.newLine();

            } for(Triangle triangle: triangles) {
                bw.write("Triangle" + " " + "T"+ triCount+ " " +  triangle.getX()/25+ " "+ triangle.getY()/25);
                gizmoToString.put(triangle,"T"+triCount);
                int numRotates = triangle.getRotation();

                for(int i=0; i < numRotates; i++){
                    bw.newLine();
                    bw.write("Rotate" + " "+ "T" + triCount);
                }
                triCount++;
                bw.newLine();

            }  for(Absorber ab : absorbers){
                bw.write("Absorber" + " " + "AB" + absCount + " " + ab.getX()/25 + " " + ab.getY()/25 + " " + ab.getX2()/25 + " " + ab.getY2()/25);
                gizmoToString.put(ab,"AB"+absCount);
                absCount++;
                bw.newLine();
            } for(Portal portal : portals){
                bw.write("Portal" + " " + "P" + portalCount + " "+ portal.getX() + " " + portal.getY());
                portalCount++;
                bw.newLine();

        }

        for(CircleGiz circle: circles) {
            connectedGizmos = circle.getConnections();
            String gizmo1Name = gizmoToString.get(circle);
            for(Gizmos giz : connectedGizmos){
                String gizmo2Name = gizmoToString.get(giz);
                bw.write("Connect" + " " + gizmo1Name + " " + gizmo2Name);
                bw.newLine();
            }

        } for(Square square : squares){
            connectedGizmos = square.getConnections();
            String gizmo1Name = gizmoToString.get(square);
            for(Gizmos giz : connectedGizmos){
                String gizmo2Name = gizmoToString.get(giz);
                bw.write("Connect" + " " + gizmo1Name + " " + gizmo2Name);
                bw.newLine();
            }

        } for(Triangle triangle: triangles) {
            connectedGizmos = triangle.getConnections();
            String gizmo1Name = gizmoToString.get(triangle);
            for(Gizmos giz : connectedGizmos){
                String gizmo2Name = gizmoToString.get(giz);
                bw.write("Connect" + " " + gizmo1Name + " " + gizmo2Name);
                bw.newLine();
            }

        }

        bw.close();

        }

    public ArrayList<Square> getSquares(){
        return squares;
    }

    public ArrayList<Absorber> getAbsorbers() {
        return absorbers;
    }

    public ArrayList<CircleGiz> getCircles() {
        return circles;
    }


    public ArrayList<Triangle> getTriangles() {
        return triangles;
    }

    public ArrayList<Portal> getPortals() {
        return portals;
    }
    public Ball getBall() {
        return ball;
    }


}


