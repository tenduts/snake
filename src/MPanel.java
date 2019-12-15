import javafx.scene.shape.Circle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

/**
 * @CreateTime: 2019-11-21-14:22
 * @Author :杨阳
 * @ClassName :MPanel
 **/
public class MPanel extends JPanel implements KeyListener, ActionListener {
    //图片
    ImageIcon title = new ImageIcon("./image/title.jpg");
    ImageIcon body = new ImageIcon("./image/body.png");
    ImageIcon up = new ImageIcon("./image/up.png");
    ImageIcon down = new ImageIcon("./image/down.png");
    ImageIcon left = new ImageIcon("./image/left.png");
    ImageIcon right = new ImageIcon("./image/right.png");
    ImageIcon food = new ImageIcon("./image/food.png");


    int len = 3;

    int score = 0;
    int []snakex = new int[750];
    int []snakey = new int[750];
    String fx = "R";//方向
    boolean isStarted = false;//判断是否开始游戏
    boolean isFailed = false;
    int speed = 300-len*20;

    Timer timer = new Timer(speed,this);

    int foodx;
    int foody;
    Random random = new Random();


    public MPanel(){
        initSnake();
        this.setFocusable(true);//可不可以获取键盘焦点
        this.addKeyListener(this);
        timer.start();

    }

    public void paintComponent(Graphics g){


        super.paintComponent(g);
        this.setBackground(Color.white);

        title.paintIcon(this,g,25,11);

        g.fillRect(25,75,850,600);

        g.setColor(Color.WHITE);
        g.drawString("Len:"+len,750,35);
        g.drawString("Score:"+score,750,50);


        if(fx=="R")
            right.paintIcon(this,g,snakex[0],snakey[0]);
        else if(fx=="L")
            left.paintIcon(this,g,snakex[0],snakey[0]);
        else if(fx=="D")
            down.paintIcon(this,g,snakex[0],snakey[0]);
        else if(fx == "U")
            up.paintIcon(this,g,snakex[0],snakey[0]);

        for(int i=1;i<len;i++){
            body.paintIcon(this,g,snakex[i],snakey[i]);
        }

        food.paintIcon(this,g,foodx,foody);

        if(isStarted == false ){
            g.setColor(Color.WHITE);
            g.setFont(new Font("正楷",Font.BOLD,40));
            g.drawString("按空格开始",300,300);

        }

        if(isFailed){
            g.setColor(Color.RED);
            g.setFont(new Font("正楷",Font.BOLD,40));
            g.drawString("按空格重新开始",200,300);

        }

    }

    public void initSnake(){
        len = 3;
        snakex[0] = 100;
        snakey[0] = 100;
        snakex[1] = 75;
        snakey[1] = 100;
        snakex[2] = 50;
        snakey[2] = 100;
        foodx = 50+25*random.nextInt(32);
        foody = 75+25*random.nextInt(23);

        fx = "R";
    }


//  键盘反应
    @Override
    public void keyTyped(KeyEvent e) {//按下时

    }

    @Override
    public void keyPressed(KeyEvent e) {//按到底时
        int keycode = e.getKeyCode();
        if(keycode == KeyEvent.VK_SPACE){
            if(isFailed){
                isFailed = false;
                initSnake();
            }else {
                isStarted = !isStarted;
            }
            repaint();
        }else if(keycode == KeyEvent.VK_LEFT && fx !="R"){
            fx = "L";
        }else if(keycode == KeyEvent.VK_UP && fx !="D"){
            fx = "U";
        }else if(keycode == KeyEvent.VK_RIGHT &&fx !="L") {
            fx = "R";
        }else if(keycode == KeyEvent.VK_DOWN && fx!="U")
            fx = "D";

    }

    @Override
    public void keyReleased(KeyEvent e) {//抬回来时

    }

    @Override
    public void actionPerformed(ActionEvent e) {//时钟到了

        if(isStarted && !isFailed){
            for(int i=len-1;i>0;i--){
                snakex[i] = snakex[i-1];
                snakey[i] = snakey[i-1];
            }

            if(fx == "R"){
                snakex[0] = snakex[0]+25;
                if(snakex[0]>850) snakex[0] = 25;
            }else if(fx == "L"){
                snakex[0] = snakex[0]-25;
                if(snakex[0]<25) snakex[0] = 850;
            }else if(fx == "U"){
                snakey[0] = snakey[0]-25;
                if(snakey[0]<75) snakey[0] = 650;
            }else if(fx == "D"){
                snakey[0] = snakey[0]+25;
                if(snakey[0]>650) snakey[0] = 75;
            }

            if(snakex[0] == foodx && snakey[0] == foody){
                len++;
                score +=10;


                foodx = 25+25*random.nextInt(33);
                foody = 75+25*random.nextInt(23);
            }

            for(int i=1;i<len;i++){
                if(snakex[i] == snakex[0] && snakey[i] == snakey[0])
                    isFailed = true;
            }

            repaint();
        }


        timer.start();
    }
}
