import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import javax.swing.*;

public class Panel extends JPanel {

    private MouseInputs mouseInputs;
    List<Coordinates> activeBoxes;
    int width = 200;
    int height = 100;
    boolean[][] grid = new boolean[width][height];

    Panel(){
        activeBoxes = new ArrayList<>();

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
//       cool 3D perspective
//       g.fillRect(x, y, width * x/this.getWidth(), height * y/this.getHeight());
        g.fillRect(x +1, y+1 , wid-2, hei-2);
//        System.out.println("x = " + (x + 1 + wid - 2));
    }

    public void drawBoxes(Graphics g, Coordinates coord, Color color){

        int wid = (int)Math.round((double)this.getWidth()/(double)width);
        int hei = (int)Math.round((double)this.getHeight()/(double)height);
        int x = coord.getX() - coord.getX() % wid;
        int y = coord.getY() - coord.getY() % hei;

        g.setColor(color);
        g.fillRect(x +1, y+1 , wid-2, hei-2);
    }

    public void addActiveBox(int x, int y){
        x = x/(int)Math.round(((double)this.getWidth()/(double)width));
        y = y/(int)Math.round(((double)this.getHeight()/(double)height));
        activeBoxes.add(new Coordinates(x, y));
    }

    public boolean fillOrDelete(int x, int y){
        x = x/(int)Math.round(((double)this.getWidth()/(double)width));
        y = y/(int)Math.round(((double)this.getHeight()/(double)height));
        boolean flag = false;
        for(Coordinates c : activeBoxes){
           if(c.contains(x,y)) {
               System.out.println("already there");
               flag = true;
               activeBoxes.remove(c);
               grid[x][y] = false;
               return true;
           }
        }
        if(!flag) {
            System.out.println("not there");
            activeBoxes.add(new Coordinates(x,y));
            grid[x][y] = true;
        }
        return false;
    }


    public int checkSurroundings(int x, int y){
        x = x/(int)Math.round(((double)this.getWidth()/(double)width));
        y = y/(int)Math.round(((double)this.getHeight()/(double)height));
        int count = 0;
        int indexY = y - 1;
        for(int i = 0; i<3;){
            int indexX = x - 1;
            for(int j = 0; j<3; j++){
                System.out.println("index x :" + indexX + " index y :" + indexY);
                if((indexX != x && indexY != y) || (indexX != x || indexY != y)){
                    System.out.println("if");
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

    public void logic(){
        for(Coordinates c : activeBoxes){
            int surroundings = checkSurroundings(c.getX(), c.getY());
            if(grid[c.getX()][c.getY()]){
               if(surroundings <= 1 || surroundings >= 4){

                   drawBoxes(getGraphics(), c, Color.DARK_GRAY);
               }
            }

        }
    }

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
            System.out.println(activeBoxes);
        }
        if(e.getKeyCode() == KeyEvent.VK_Z){
            logic();
        }
        if(e.getKeyCode() == KeyEvent.VK_W){
        }
    }
}


}
