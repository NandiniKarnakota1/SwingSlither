package play;

import java.awt.*;

public interface Game {
    // to track movements of snake & food
    public void move();
    // where the game should end
    public void checkHit();
    // to add food for snake
    public void addFood();
    // to provide food after the snake swallowed
    public void checkFood();
    // to start the game to place food and move snake
    public void playGame();
    // to represent graphics color
    public void draw(Graphics graphics);
    // to display game over graphic color, font size etc
    public void gameOver(Graphics graphics);
}
