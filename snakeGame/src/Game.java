import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class Game extends JPanel implements KeyListener, ActionListener {

    private int[] XsnakeLength = new int [750];
    private int[] YsnakeLength = new int [750];
    private boolean up = false;
    private boolean down = false;
    private boolean right = false;
    private boolean left = false;

    private ImageIcon upmouth;
    private ImageIcon downmouth;
    private ImageIcon rightmouth;
    private ImageIcon leftmouth;
    private ImageIcon snakeimage;
    private ImageIcon titleImage;
    private ImageIcon enemyimage ;

    
    private Timer timer;
    private int delay = 100;
    private int snakeLength = 3; //default length of snake
    private int moves =0;

    private int [] XenemyPos = {25,50,75,100,125,150,175,200,225,250,275,300,325,350,375,
            400,425,450,475,500,525,550,575,600,625,650,675,700,725,750,775,800, 825, 850};

    private int [] YenemyPos = {75,100,125,150,175,200,225,250,275,300,325,350,375,
            400,425,450,475,500,525,550,575,600,625};

    private Random random = new Random();

    private int Xposition = random.nextInt(34);
    private int Yposition = random.nextInt(23);

    private int score = 0;

    public Game() {

        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer =new Timer(delay, this);
        timer.start();

    }



    public void paint (Graphics g){

        if (moves == 0){
            XsnakeLength [2] = 50;
            XsnakeLength [1] = 75;
            XsnakeLength [0] = 100;

            YsnakeLength [2] = 100;
            YsnakeLength [1] = 100;
            YsnakeLength [0] = 100;

        }

        //Title image's border
        g.setColor(Color.white);
        g.drawRect(24,10,851,55);

        //Title image
        titleImage= new ImageIcon("src/snaketitle.jpg");
        titleImage.paintIcon(this, g,25,11);

        // Border for game
        g.setColor(Color.WHITE);
        g.drawRect(24, 74,851,577);

        // Background for the Game
        g.setColor(Color.black);
        g.fillRect(25,75,850,575);

        //Create a label called scores for how many enemies the snake eats and adding the score right beside it
        g.setColor(Color.white);
        g.setFont(new Font("arial", Font.PLAIN, 14));
        g.drawString("Scores: "+score, 780,30);

        //Create a label for the length of the snake and adding the length right beside it
        g.setColor(Color.white);
        g.setFont(new Font("arial", Font.PLAIN, 14));
        g.drawString("Length: "+snakeLength, 780,50);

        //Print out the rightmouth image
        rightmouth = new ImageIcon ("src/rightmouth.png");
        rightmouth.paintIcon(this, g, XsnakeLength[0],YsnakeLength[0]);

        for (int i =0; i< snakeLength; i++){

            if (i==0 && up){
                upmouth = new ImageIcon ("src/upmouth.png");
                upmouth.paintIcon(this, g, XsnakeLength[i],YsnakeLength[i]);
            }

            if (i==0 && down){
                downmouth = new ImageIcon ("src/downmouth.png");
                downmouth.paintIcon(this, g, XsnakeLength[i],YsnakeLength[i]);
            }

            if (i==0 && right){
                rightmouth = new ImageIcon ("src/rightmouth.png");
                rightmouth.paintIcon(this, g, XsnakeLength[i],YsnakeLength[i]);

            }

            if (i==0 && left){
                leftmouth = new ImageIcon ("src/leftmouth.png");
                leftmouth.paintIcon(this, g, XsnakeLength[i],YsnakeLength[i]);
            }

            if (i!=0){
                snakeimage = new ImageIcon ("src/snakeimage.png");
                snakeimage.paintIcon(this, g, XsnakeLength[i],YsnakeLength[i]);
            }

        }

        //Print out the enemy image
        enemyimage = new ImageIcon("src/enemy.png");

        if ((XenemyPos[Xposition] == XsnakeLength[0]) && YenemyPos[Yposition]== YsnakeLength[0]){

            score ++;
            snakeLength++;

            Xposition = random.nextInt(34);
            Yposition = random.nextInt(23);
        }

        enemyimage.paintIcon(this, g, XenemyPos[Xposition], YenemyPos[Yposition]);

        for (int i = 1; i< snakeLength; i++){

            if (XsnakeLength[i] == XsnakeLength[0] && YsnakeLength[i]== YsnakeLength[0]) {

                right = false;
                left = false;
                up = false;
                down = false;

                g.setColor(Color.white);
                g.setFont(new Font("arial",Font.BOLD, 50));
                g.drawString("Game Over", 300, 300);

                g.setFont(new Font("arial",Font.BOLD, 20));
                g.drawString("Press Space to RESTART", 350, 340);

            }

        }

        g.dispose();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        timer.start();
        if (right){

            for (int r = snakeLength-1; r>=0; r--){

                YsnakeLength[r+1] = YsnakeLength[r];
            }
            for (int r = snakeLength; r>=0; r--){

                if(r==0){
                    XsnakeLength[r] = XsnakeLength[r]+25;

                }
                else{
                    XsnakeLength[r] = XsnakeLength[r-1];
                }
                if(XsnakeLength[r]>850){

                    XsnakeLength[r] = 25;
                }
            }

            repaint();
        }

        if (left){

            for (int r = snakeLength-1; r>=0; r--){

                YsnakeLength[r+1] = YsnakeLength[r];
            }
            for (int r = snakeLength; r>=0; r--){

                if(r==0){
                    XsnakeLength[r] = XsnakeLength[r]-25;

                }
                else{
                    XsnakeLength[r] = XsnakeLength[r-1];
                }
                if(XsnakeLength[r]< 25){

                    XsnakeLength[r] = 850;
                }
            }

            repaint();
        }


        if (up){

            for (int r = snakeLength-1; r>=0; r--){

                XsnakeLength[r+1] = XsnakeLength[r];
            }
            for (int r = snakeLength; r>=0; r--){

                if(r==0){
                    YsnakeLength[r] = YsnakeLength[r]-25;

                }
                else{
                    YsnakeLength[r] = YsnakeLength[r-1];
                }
                if(YsnakeLength[r]< 75){

                    YsnakeLength[r] = 625;
                }
            }

            repaint();

        }
        if (down){
            for (int r = snakeLength-1; r>=0; r--){

                XsnakeLength[r+1] = XsnakeLength[r];
            }
            for (int r = snakeLength; r>=0; r--){

                if(r==0){
                    YsnakeLength[r] = YsnakeLength[r]+25;

                }
                else{
                    YsnakeLength[r] = YsnakeLength[r-1];
                }
                if(YsnakeLength[r]> 625){

                    YsnakeLength[r] = 75;
                }
            }

            repaint();

        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() ==  KeyEvent.VK_SPACE) {
            moves = 0;
            score = 0;
            snakeLength = 3;
            repaint();
        }

        if (e.getKeyCode() ==  KeyEvent.VK_RIGHT ) {
            moves ++;
            right= true;

            if(!left){
                right = true;
            }
            else{
                right =false;
                left = true;
            }
            up = false;
            down = false;
        }

        if (e.getKeyCode() ==  KeyEvent.VK_LEFT ) {
            moves ++;
            left = true;

            if(!right){
                left = true;
            }
            else{
                left =false;
                right = true;
            }
            up = false;
            down = false;
        }

        if (e.getKeyCode() ==  KeyEvent.VK_UP ) {
            moves ++;
            up= true;

            if(!down){
                up = true;
            }
            else{
                up =false;
                down = true;
            }
            right = false;
            left = false;
        }

        if (e.getKeyCode() ==  KeyEvent.VK_DOWN ) {
            moves ++;
            down= true;

            if(!up){
                down = true;
            }
            else{
                down =false;
                up = true;
            }
            right = false;
            left = false;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

