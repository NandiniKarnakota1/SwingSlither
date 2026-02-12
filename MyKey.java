package play;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

// to control snake movements we use this class
public class MyKey extends KeyAdapter {
    private SnakePanel snakePanel;
    public MyKey(SnakePanel snakePanel){
        this.snakePanel = snakePanel;
    }
    // to handle key events by matching direction
    public void keyPressed(KeyEvent e){
        char currentDirection = snakePanel.getDirection();
        switch (e.getKeyCode()){
            case KeyEvent.VK_LEFT :
                if(currentDirection != 'R'){
                    // current direction is not right move to left
                    snakePanel.setDirection('L');
                }
                break;
            case KeyEvent.VK_RIGHT:
                if(currentDirection != 'L'){
                    // current direction is not left move to right
                    snakePanel.setDirection('R');
                }
                break;
            case KeyEvent.VK_UP:
                if(currentDirection != 'D'){
                    // current direction is not down move to up
                    snakePanel.setDirection('U');
                }
                break;
            case KeyEvent.VK_DOWN:
                if(currentDirection != 'U'){
                    // current direction is not up move to down
                    snakePanel.setDirection('D');
                }
                break;
        }

    }
}
