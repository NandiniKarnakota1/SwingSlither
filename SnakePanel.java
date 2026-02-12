package play;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class SnakePanel extends JPanel implements ActionListener,Game {
    static final int panelHeight = 500;
    static final int panelWidth = 500;
    static final int unit_size = 20;
    static final int num_of_units = (panelHeight * panelWidth)/(unit_size * unit_size);
    final int X[]=new int[num_of_units];
    final int Y[]=new int[num_of_units];
    int snakeLength = 2; // snake size initial
    int foodSwallowed; // score to be displayed when swallowed food
    private char direction='D'; // bydefault starting direction
    int foodX; // food at X-axis
    int foodY; // food at Y-axis
    Random random; // to place food in random positions
    Timer timer; // to track movements snake
    boolean running=false;

    //getters and setters for direction
    public char getDirection(){
        return direction;
    }
    public void setDirection(char direction){
        this.direction=direction;
    }

    //constructor
    public SnakePanel(){
        random=new Random(); // initialized random
        this.setSize(panelHeight,panelWidth);
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(new MyKey(this));
        playGame(); // internally called

    }


    @Override
    public void move() {

        //defining snake length
        for(int i=snakeLength; i>0; i--){
            X[i] = X[i-1];
            Y[i] = Y[i-1];
        }
        //Left direction
        if(direction == 'L'){
            X[0] = X[0] - unit_size;
        }
        // Right direction
        else if(direction == 'R'){
            X[0] = X[0] + unit_size;
        }
        // Up direction
        else if(direction == 'U'){
            Y[0] = Y[0] - unit_size;
        } // Down direction
        else {
            Y[0] = Y[0] + unit_size;
        }

    }

    @Override
    public void checkHit() {
        //running snake length
        for(int i=snakeLength; i>0; i--){
            if(X[0] == X[i] && Y[0] == Y[i]){ // if head & body touched game end
                running=false;
            }
            if(X[0] < 0 || X[0] > panelWidth || Y[0] < 0 || Y[0] > panelWidth){ // snake exceeded panel
                running=false;
            }
            if(!running){ // if not running
                timer.stop();
            }
        }

    }

    @Override
    public void addFood() {
        foodX=random.nextInt((int)(panelWidth/unit_size))* unit_size;
        foodY=random.nextInt((int)(panelWidth/unit_size))* unit_size;
    }

    @Override
    public void checkFood() {
        //if snake swallowed food
        if(X[0]==foodX && Y[0]==foodY){
            snakeLength++;
            foodSwallowed++;
            addFood();
        }
    }

    @Override
    public void playGame() {
        running=true;
        addFood();
        timer=new Timer(130,this); // time to move snake
        timer.start();
    }

    @Override
    public void draw(Graphics graphics) {
        if(running) {
            graphics.setColor(new Color(255, 217, 99)); // food
            graphics.fillOval(foodX, foodY, unit_size, unit_size);

            graphics.setColor(Color.white); // head
            graphics.fillRect(X[0], Y[0], unit_size, unit_size);

            for (int i = 1; i < snakeLength; i++) {
                graphics.setColor(new Color(44, 255, 5));
                graphics.fillRect(X[i], Y[i], unit_size, unit_size);
            }
            graphics.setColor(Color.red);
            graphics.setFont(new Font("Sans serif", Font.ROMAN_BASELINE, 25));
            FontMetrics metrics = getFontMetrics(graphics.getFont());
            graphics.drawString("Score:" + foodSwallowed, (panelWidth - metrics.stringWidth("score:" + foodSwallowed)) / 2, graphics.getFont().getSize());
        }
        else{
            gameOver(graphics);
        }
    }


    @Override
    public void gameOver(Graphics graphics) {
        graphics.setColor(Color.white);
        graphics.setFont(new Font("Sans serif",Font.ROMAN_BASELINE,25));
        FontMetrics metrics = getFontMetrics(graphics.getFont());
        graphics.drawString("Game Over",(panelWidth-metrics.stringWidth("Game Over"))/2,panelHeight/2);

        graphics.setColor(Color.red);
        graphics.setFont(new Font("Sans serif", Font.ROMAN_BASELINE, 25));
         metrics = getFontMetrics(graphics.getFont());
        graphics.drawString("Score:" + foodSwallowed, (panelWidth - metrics.stringWidth("score:" + foodSwallowed)) / 2, graphics.getFont().getSize());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(running){
            move();
            checkFood();
            checkHit();
        }
        repaint();
    }

    // to provide color for snake and food
    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        draw(graphics);
    }
}
