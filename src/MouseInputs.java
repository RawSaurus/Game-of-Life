import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Arrays;
import javax.swing.*;

public class MouseInputs implements MouseListener, MouseMotionListener {

    Panel panel;

    MouseInputs(Panel panel) {
        this.panel = panel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        e.getLocationOnScreen();
        System.out.println("-------------------------------------");
        System.out.println("e.getLocation x = " + e.getX() + " y = " + e.getY());
        int x = e.getX() - e.getX() % (panel.getWidth()/panel.width);
        int y = e.getY() - e.getY() % (panel.getHeight()/panel.height);
        System.out.println("x = " + x + " y = " + y);
        System.out.println("Cell x = " + panel.getWidth()/ panel.width + " y = " + panel.getHeight()/ panel.height);
        System.out.println("Position x: " + (e.getX()/ (panel.getWidth()/panel.width)));
        System.out.println("Position y: " + (e.getY()/ (panel.getHeight()/ panel.height)));
        System.out.println("--------------------------------------");
        int indexX = e.getX()/(panel.getWidth()/panel.width);
        int indexY = e.getY()/(panel.getHeight()/panel.height);
        System.out.println(panel.currentRound);

//        panel.round[x][y] = true;
//        panel.fillOrDelete(x,y);
//        Coordinates coordinates = new Coordinates(x, y);

//        PointerInfo pointer =MouseInfo.getPointerInfo();
//        Point coord = pointer.getLocation();

//        Color color;

//        try {
//            Robot robot = new Robot();
//            if(e.getClickCount() == 1){
//                coord = MouseInfo.getPointerInfo().getLocation();
//                Color col = robot.getPixelColor((int)coord.getX(), (int)coord.getY());
//                color = robot.getPixelColor(x +12,y + 31 +12);
////                color = robot.getPixelColor(panel.round.get(1).getX() +24,panel.round.get(1).getY() + 43);
//                panel.fillOrDelete(x,y);
//                System.out.println("Upper left corner x = " + x + " y = " +y);
//                System.out.println("Mouse click x = " + coord.getX() + " y = " + coord.getY());
//                System.out.println("-------------------------------------");
//                System.out.println("col = " + col);
//                System.out.println("color = " + color);
//            }
//        } catch (
//                AWTException ex) {
//            throw new RuntimeException(ex);
//        }
//        System.out.println(color);
//        System.out.println(Color.WHITE);
//        System.out.println(Color.DARK_GRAY);
    }


    @Override
    public void mousePressed(MouseEvent e) {


        panel.drawBoxes(panel.getGraphics(), e.getX(), e.getY());
//        panel.checkSurroundings(e.getX(), e.getY());
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

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
