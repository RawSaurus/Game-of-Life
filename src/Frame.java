import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {

    Panel panel = new Panel();
    Frame(){


        this.setBackground(Color.DARK_GRAY);

        this.add(panel);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setLocationRelativeTo(null);
        this.pack();
        this.setVisible(true);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

}
