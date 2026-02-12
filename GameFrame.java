package play;

import javax.swing.*;
import java.awt.*;
import play.GameFrame;
import play.SnakePanel;
// to create GUI Interface
public class GameFrame extends JFrame {
    public static void Frame(){
        GameFrame gm = new GameFrame();
        SnakePanel gp = new SnakePanel();

        gm.setTitle("SwingSlither");
        gm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gm.setResizable(false);

        gm.add(gp);

        gp.setPreferredSize(new Dimension(SnakePanel.panelHeight, SnakePanel.panelWidth));

        gm.pack();
        gm.setVisible(true);
        gm.setLocationRelativeTo(null);

    }
}
