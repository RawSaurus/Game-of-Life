import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import javax.swing.*;

public class Panel extends JPanel {

    private MouseInputs mouseInputs;
    List<Coordinates> currentRound;
    List<Coordinates> nextRound;
    int width = 200;
    int height = 100;
    boolean[][] grid = new boolean[width][height];

    Panel(){
        currentRound = new ArrayList<>();
        nextRound = new ArrayList<>();

        this.setPanelSize();

        mouseInputs = new MouseInputs(this);

        this.setFocusable(true);
        this.addKeyListener(new AL());
        this.addMouseListener(mouseInputs);
        this.setSize(this.getWidth(), this.getHeight());
        this.setVisible(true);

    }

    public void paint(Graphics g){
        drawGrid(g);
        g.setColor(Color.WHITE);

    }

    public void drawBoxes(Graphics g, int x, int y){
            if(!fillOrDelete(x, y))
                g.setColor(Color.WHITE);
            else
                g.setColor(Color.DARK_GRAY);
        int wid = (int)Math.round((double)this.getWidth()/(double)width);
        int hei = (int)Math.round((double)this.getHeight()/(double)height);
        x = x - x % wid;
        y = y - y % hei;

//        System.out.println("draw Boxes x: " + x + " y: " + y);

//       cool 3D perspective
//       g.fillRect(x, y, width * x/this.getWidth(), height * y/this.getHeight());
        g.fillRect(x +1, y+1 , wid-2, hei-2);
//        System.out.println("x = " + (x + 1 + wid - 2));
    }

    public void drawBoxess(Graphics g){
        int wid = (int)Math.round((double)this.getWidth()/(double)width);
        int hei = (int)Math.round((double)this.getHeight()/(double)height);
//        x = x - x % wid;
//        y = y - y % hei;
//        g.fillRect(x +1, y+1 , wid-2, hei-2);
        for(Coordinates c : nextRound){
            if(grid[c.getX()][c.getY()])
                g.setColor(Color.WHITE);
            else
                g.setColor(Color.DARK_GRAY);

            g.fillRect(c.getX()*wid+1, c.getY()*hei+1, wid-2, hei-2);
        }
    }

//    public void drawBoxes(Graphics g, Coordinates coord, Color color){
//
//        int wid = (int)Math.round((double)this.getWidth()/(double)width);
//        int hei = (int)Math.round((double)this.getHeight()/(double)height);
//        int x = coord.getX() - coord.getX() % wid;
//        int y = coord.getY() - coord.getY() % hei;
//
//        g.setColor(color);
//        g.fillRect(x +1, y+1 , wid-2, hei-2);
//    }

//    public void addActiveBox(int x, int y){
//        x = x/(int)Math.round(((double)this.getWidth()/(double)width));
//        y = y/(int)Math.round(((double)this.getHeight()/(double)height));
//        round.add(new Coordinates(x, y));
//    }

    public boolean fillOrDelete(int x, int y){
        x = x/(int)Math.round(((double)this.getWidth()/(double)width));
        y = y/(int)Math.round(((double)this.getHeight()/(double)height));

//        System.out.println("fillOrDelete x: " + x + " y: " + y);

        Coordinates c = new Coordinates(x,y);
        if(!currentRound.contains(c)){
            currentRound.add(c);
        }

        if(grid[x][y]){
            grid[x][y] = false;
            return true;
        }
        else{
            grid[x][y] = true;
        }


//        boolean flag = false;
//        for(Coordinates c : currentRound){
//           if(c.contains(x,y)) {
//               System.out.println("already there");
//               flag = true;
//               currentRound.remove(c);
////               nextRound.add(c);
//               grid[x][y] = false;
//               return true;
//           }
//        }
//        if(!flag) {
//            System.out.println("not there");
//            Coordinates c = new Coordinates(x,y);
//            currentRound.add(c);
////            if(nextRound.contains(c))
////                nextRound.remove(c);
//            grid[x][y] = true;
//        }
        return false;
    }


    public int checkSurroundings(int x, int y){
//        x = x/(int)Math.round(((double)this.getWidth()/(double)width));
//        y = y/(int)Math.round(((double)this.getHeight()/(double)height));
        int count = 0;
        int indexY = y - 1;
        for(int i = 0; i<3;){
            int indexX = x - 1;
            for(int j = 0; j<3; j++){
//                System.out.println("index x :" + indexX + " index y :" + indexY);
                if((indexX != x && indexY != y) || (indexX != x || indexY != y)){
//                    System.out.println("if");
                   if(grid[indexX][indexY])
                       count++;
                }
                indexX++;
            }
            indexY++;
            i++;
        }


        System.out.println(count);
        return count;
    }
    private List<Coordinates> addSurroundings(int x, int y){
//        x = x/(int)Math.round(((double)this.getWidth()/(double)width));
//        y = y/(int)Math.round(((double)this.getHeight()/(double)height));
        boolean flag = false;
        List<Coordinates> surroundings =new ArrayList<>();
        int indexY = y - 1;
        for(int i = 0; i<3;){
            int indexX = x - 1;
            for(int j = 0; j<3; j++){
//                System.out.println("index x :" + indexX + " index y :" + indexY);
                if((indexX != x && indexY != y) || (indexX != x || indexY != y)){
                    for(Coordinates cor : currentRound){
                        if(cor.contains(indexX, indexY))
                            flag = true;
                    }
                    if(!flag) {
                        Coordinates c = new Coordinates(indexX, indexY);
                        surroundings.add(c);
//                        System.out.println("addCoord x: " + indexX + " y: " + indexY);
                    }
                    flag = false;
                }
                indexX++;
            }
            indexY++;
            i++;
        }
        System.out.println(surroundings.size());
        return surroundings;
    }

    public void logic(){
        for(int i  = 0; i<currentRound.size(); i++){
            Coordinates c = new Coordinates(currentRound.get(i).getX(), currentRound.get(i).getY());
            int surroundings = checkSurroundings(c.getX(), c.getY());
            if(grid[c.getX()][c.getY()]){
               if(surroundings <= 1 || surroundings >= 4) {
                   List<Coordinates> temp = addSurroundings(c.getX(),c.getY());
                   currentRound.addAll(temp);
                   c.setValue(false);
                   nextRound.add(c);
                   /*
                   * add other 8 adjacent cells to currentRound to check
                   * either save boolean as object value or next grid(another array)
                   * add to nextRound
                   * draw the nextRound(outside of the loop)
                   * */
               }
               else if(surroundings == 2 || surroundings == 3){
                   /*
                   * remove from current round*/

                   continue;
                   }
            }
            if(!grid[c.getX()][c.getY()]){
                /*
                 * add other 8 adjacent cells to currentRound to check
                 * either save boolean as object value or next grid(another array)
                 * add to nextRound
                 * draw the nextRound(outside of the loop)
                * */
                if(surroundings == 3){
                    List<Coordinates> temp = addSurroundings(c.getX(),c.getY());
                    currentRound.addAll(temp);
                    c.setValue(true);
                    nextRound.add(c);
                }
            }
        }
        for(Coordinates c : nextRound){
            grid[c.getX()][c.getY()] = c.getValue();
        }
        drawBoxess(getGraphics());
        currentRound.clear();
        currentRound.addAll(nextRound);
//        currentRound = nextRound;
        nextRound.clear();
        /*
        * for loop nextRound - set values from object to the grid and redraw
        * currentRound = nextRound
        * */
    }
    /*
    click -> fill or delete to add to list, call draw every time
    logic -> called only every round, check collision use logic to add and remove from lists and call other draw method
    */

    public void drawGrid(Graphics g){

        g.setColor(Color.BLACK);

        for(int i = 0; i<this.getHeight(); ){

            g.drawLine(0,(i*(int)Math.round((double)this.getHeight()/(double)height)), this.getWidth(), i*(int)Math.round((double)this.getHeight()/(double)height));
            i++;
        }

        for(int i = 0; i<this.getWidth(); ){
            g.drawLine(i*(int)Math.round((double)this.getWidth()/(double)width), 0, i*(int)Math.round((double)this.getWidth()/(double)width), this.getHeight());
            i++;
        }

    }

    private void setPanelSize(){
        Dimension size = new Dimension(1980, 1080);
        setMinimumSize(size);
        setPreferredSize(size);
        setPreferredSize(size);
    }

    public class AL extends KeyAdapter{

    @Override
    public void keyPressed(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_Q){
            System.exit(0);
        }
        if(e.getKeyCode() == KeyEvent.VK_A){
            System.out.println(currentRound);
        }
        if(e.getKeyCode() == KeyEvent.VK_Z){
            while(true) {
                logic();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_W){
        }
    }
}


}
