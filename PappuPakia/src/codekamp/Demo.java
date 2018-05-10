package codekamp;

import javafx.scene.layout.Pane;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Random;

public class Demo implements KeyListener {
    private static int pappuXcord = 45;
    private static int pappuYcord = 320;
    private static int pappuAcc = 0;
    private static int pappuVel = 0;
    private  static int c1=50;
    private static AudioClip bg;
    private static boolean p=false;
    private static  boolean paused=false;
    private static boolean crash=false;
    private static int bgx = 0;
    private static int bgVel = 0;
    private static int bg1x = 1000;
    private static int bg1Vel = 0;
    private  static Image log = null;
    private static int index = 0;
    private static Image[] playerImages;
    private static Graphics ImageGraphics;
    private static Graphics PanelGraphics;

    private static Demo d;
    private static int logdcc = 0;
    private static int logx = 50;
    private static int logy = 379;
    private static int h1X = 1100;
    private static int h1vel = 0;
    private static int h3X = 1800;
    private static int h3Xvel = 0;
    private static int score=0;
    private static int counter=0;
    private static int starVel=0;
    private static int starXcord=500;
    private static int berriesVel=0;
    private static int barriesXcord=1100;

    private static JPanel Panel;
    public static void main(String[] args) {
        JFrame Frame = new JFrame();
        Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Panel = new JPanel();
        Panel.setPreferredSize(new Dimension(1000, 500));
        Panel.setFocusable(true);
        Panel.requestFocus();
        Frame.add(Panel);
        Panel.addKeyListener(new Demo());
        Frame.pack();
        Frame.setVisible(true);
        Image bigimage=new BufferedImage(1000,500,BufferedImage.TYPE_INT_RGB);

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
        }
        PanelGraphics = Panel.getGraphics();
        ImageGraphics=bigimage.getGraphics();
        ImageGraphics.setColor(Color.red);
        ImageGraphics.setFont(new Font("Georgia", Font.BOLD, 20));
        ImageGraphics.drawString("Loading................", 100, 100);
        PanelGraphics.drawImage(bigimage,0,0,null);
        Image bgground = null;
        Image ground = null;
        Image p1 = null;
        Image p2 = null;
        Image p3 = null;
        Image p4 = null;
        Image p5 = null;
        Image p6 = null;
        Image p7 = null;
        Image p8 = null;
        Image h1 = null;
        Image h3 = null;
        Image h4 = null;
        Image h5 = null;
        Image star=null;
        Image berries=null;
        Image hap=null;

        try {
            bgground = ImageIO.read(Demo.class.getResource("img/bg_combined.png"));

            log = ImageIO.read(Demo.class.getResource("img/log.png"));
            p1 = ImageIO.read(Demo.class.getResource("Pappu-Pakia Image/pakiaOne.png"));
            p2 = ImageIO.read(Demo.class.getResource("Pappu-Pakia Image/pakiaTwo.png"));
            p3 = ImageIO.read(Demo.class.getResource("Pappu-Pakia Image/pakiaThree.png"));
            p4 = ImageIO.read(Demo.class.getResource("Pappu-Pakia Image/pakiaFour.png"));
            p5 = ImageIO.read(Demo.class.getResource("Pappu-Pakia Image/pakiaFive.png"));
            p6 = ImageIO.read(Demo.class.getResource("Pappu-Pakia Image/pakiaSix.png"));
            p7 = ImageIO.read(Demo.class.getResource("Pappu-Pakia Image/pakiaSeven.png"));
            p8 = ImageIO.read(Demo.class.getResource("Pappu-Pakia Image/pakiaEight.png"));
            h1 = ImageIO.read(Demo.class.getResource("img/branch.png"));
            h3 = ImageIO.read(Demo.class.getResource("img/fork_handle.png"));
            h4 = ImageIO.read(Demo.class.getResource("img/fork_head.png"));
            h5 = ImageIO.read(Demo.class.getResource("img/h5.png"));
            star = ImageIO.read(Demo.class.getResource("img/star.png"));
            berries = ImageIO.read(Demo.class.getResource("img/berries.png"));

            hap = ImageIO.read(Demo.class.getResource("img/happy_pakia.png"));
            URL bg1=Demo.class.getResource("sound/bg.wav");
            Demo.bg= Applet.newAudioClip(bg1);
            Demo.bg.loop();
        } catch (IOException e) {
            System.out.println("image ni mile");
        }
        Image[] playerImages = {p1, p2, p3, p4, p5, p6, p7, p8, p7, p6, p5, p4, p3, p2};


        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {

            }
            if(crash) {
                continue;

            }
            if (Demo.paused) {
                ImageGraphics.setColor(Color.BLUE);
                ImageGraphics.drawString("Game paused",150, 300);

                continue;
            }
            counter+=score;
            index += 2;
            if (index == 14)
                index = 0;
            Demo.pappuVel += Demo.pappuAcc;
            Demo.pappuYcord += pappuVel;

            bgx -= bgVel;
            bg1x -= bg1Vel;
            if (bgx <= -1000)
                bgx = 1000;
            if (bg1x <= -1000)
                bg1x = 1000;

            Random r1 = new Random();
            Demo.h1X += Demo.h1vel;
            if (h1X <= -400)
                h1X = r1.nextInt(100) + 1000;
            Demo.h3X += Demo.h3Xvel;
            if (h3X <= -400) {
                h3X = r1.nextInt(100) + 1000;
            }
            if(pappuYcord<=-60||pappuYcord>=560)
            {

                ImageGraphics.setColor(Color.RED);
                ImageGraphics.setFont(new Font("Arial",Font.BOLD,50));
                ImageGraphics.drawString("Game Over",350,300);
                ImageGraphics.drawString("Press Enter To Play",280,350);
                PanelGraphics.drawImage(bigimage,0,0,null);

                crash=true;
                continue;
            }

            if(  Demo.areColliding(pappuXcord,pappuYcord,60,60,h1X+20,0,31,100)&&p!=true)
            {
                ImageGraphics.setColor(Color.RED);
                ImageGraphics.setFont(new Font("Times New Roman",Font.BOLD,50));
                ImageGraphics.drawString("Game Over",350,300);
                ImageGraphics.drawString("Press Enter To Play",280,350);
                PanelGraphics.drawImage(bigimage,0,0,null);
                crash=true;
                continue;
            }
            if(  Demo.areColliding(pappuXcord,pappuYcord,60,60,h1X+420,0,31,200)&&p!=true)
            {
                ImageGraphics.setColor(Color.RED);
                ImageGraphics.setFont(new Font("Times New Roman",Font.BOLD,50));
                ImageGraphics.drawString("Game Over",350,300);
                PanelGraphics.drawImage(bigimage,0,0,null);
                crash=true;
                continue;
            }
            if(  Demo.areColliding(pappuXcord,pappuYcord,60,60,h3X+20,312,22,312)&&(p!=true))
            {
                ImageGraphics.setColor(Color.RED);
                ImageGraphics.setFont(new Font("Times New Roman",Font.BOLD,50));
                ImageGraphics.drawString("Game Over",350,300);ImageGraphics.drawString("Press Enter To Play",280,350);
                PanelGraphics.drawImage(bigimage,0,0,null); crash=true;
                continue;
            }
            if(  Demo.areColliding(pappuXcord,pappuYcord,60,60,h3X+420,212,22,312)&&p!=true)
            {
                ImageGraphics.setColor(Color.RED);
                ImageGraphics.setFont(new Font("Times New Roman",Font.BOLD,50));
                ImageGraphics.drawString("Game Over",350,300);ImageGraphics.drawString("Press Enter To Play",280,350);
                crash=true;
                continue;}
            if(  Demo.areColliding(pappuXcord,pappuYcord,60,60,h3X+20,255,33,59)&&p!=true)
            {
                ImageGraphics.setColor(Color.RED);
                ImageGraphics.setFont(new Font("Times New Roman",Font.BOLD,50));
                ImageGraphics.drawString("Game Over",350,300);ImageGraphics.drawString("Press Enter To Play",280,350);
                PanelGraphics.drawImage(bigimage,0,0,null); crash=true;
                continue;
            }
            if(  Demo.areColliding(pappuXcord,pappuYcord,60,60,h1X+20,100,33,59)&&p!=true)
            {
                ImageGraphics.setColor(Color.RED);
                ImageGraphics.setFont(new Font("Times New Roman",Font.BOLD,50));
                ImageGraphics.drawString("Game Over",350,300);ImageGraphics.drawString("Press Enter To Play",280,350);
                PanelGraphics.drawImage(bigimage,0,0,null);crash=true;
                continue;
            }
            if(  Demo.areColliding(pappuXcord,pappuYcord,60,60,starXcord,250,41,39)) {
                p=true;

            }



            starXcord+=starVel;
            if(starXcord<=0)
                starXcord=2000;

            barriesXcord+=berriesVel;
            if(barriesXcord<=0)
                barriesXcord=2000;

            ImageGraphics.drawImage(bgground, bgx, 0, null);
            ImageGraphics.drawImage(bgground, bg1x, 0, null);
            Demo.logx += Demo.logdcc;

            ImageGraphics.drawImage(log, logx, logy, null);
            ImageGraphics.drawImage(playerImages[index], pappuXcord, pappuYcord, null);
            ImageGraphics.drawImage(h1, h1X, -400, null);
            ImageGraphics.drawImage(h1, h1X + 400, -300, null);
            ImageGraphics.drawImage(h4, h1X, 100, null);
            ImageGraphics.drawImage(h5, h3X, 255, null);
            ImageGraphics.drawImage(h3, h3X, 312, null);
            ImageGraphics.drawImage(h3, h3X + 400, 212, null);
            ImageGraphics.drawImage(star,starXcord,250,null);
            ImageGraphics.drawImage(berries,barriesXcord,250,null);


            //g.drawImage(hap,HapappuXcord,HapappuYcord,null);
            ImageGraphics.setColor(Color.BLUE);
            ImageGraphics.setFont(new Font("Times New Roman",Font.BOLD,30));
            ImageGraphics.drawString("Score :"+counter,850,40);

            if(p==true)
            {starXcord=2000;
                playerImages[0]=hap;
                playerImages[1]=hap;
                playerImages[2]=hap;playerImages[3]=hap;playerImages[4]=hap;playerImages[5]=hap;playerImages[6]=hap;playerImages[7]=hap;
                playerImages[8]=hap;playerImages[9]=hap;playerImages[10]=hap;playerImages[11]=hap;playerImages[12]=hap;playerImages[13]=hap;
                ImageGraphics.setColor(Color.RED);
                ImageGraphics.setFont(new Font("Times New Roman", Font.BOLD, 40));
                ImageGraphics.drawString("Power :" + c1, 50, 50);
                c1--;
            }
            if(c1==0){
                p=false;
                c1=50;}
            if(p==false) {
                playerImages[0] = p1;
                playerImages[1] = p2;
                playerImages[2] = p3;
                playerImages[3] = p4;
                playerImages[4] = p5;
                playerImages[5] = p6;
                playerImages[6] = p7;
                playerImages[7] = p8;
                playerImages[8] = p7;
                playerImages[9] = p6;
                playerImages[10] = p5;
                playerImages[11] = p4;
                playerImages[12] = p3;
                playerImages[13] = p2;
            }

PanelGraphics.drawImage(bigimage,0,0,null);
        }}

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {

            Demo.logdcc = -10;

            Demo.pappuAcc = 5;
            Demo.pappuVel = -35;
            Demo.h1vel = -20;
            Demo.h3Xvel = -20;
            Demo.bg1Vel = 20;
            Demo.bgVel = 20;
            Demo.score=1;
            starVel=-20;
            berriesVel=-20;


        }
        if(e.getKeyCode()==KeyEvent.VK_P)
        {Demo.paused=!Demo.paused;

        }
        if(e.getKeyCode()==KeyEvent.VK_ENTER&&Demo.crash){

            pappuXcord = 45;
            pappuYcord = 320;
            pappuAcc = 0;
            pappuVel = 0;
            paused=false;
            crash=false;
            bgx = 0;
            bgVel = 0;
            bg1x = 1000;
            bg1Vel = 0;
            c1=50;
            index = 0;
            Image[] playerImages;

            logdcc = 0;
            logy = 379;
            logx = 50;
            h1X = 1100;
            h1vel = 0;
            h3X = 1800;
            h3Xvel = 0;
            AudioClip bg;
            score=0;
            counter=0;
            starVel=0;
            starXcord=500;
            berriesVel=0;
            barriesXcord=1100;
            p=false;


        }}
    @Override
    public void keyReleased(KeyEvent e) {

    }


    private static boolean areColliding(int x1, int y1, int w1, int h1, int x2, int y2, int w2, int h2) {
        boolean xOverLapping = (x1 > x2 && x1 < x2 + w2) || (x2 > x1 && x2 < x1 + w1);
        boolean yOverLapping = (y1 > y2 && y1 < y2 + h2) || (y2 > y1 && y2 < y1 + h1);

        return xOverLapping && yOverLapping;
    }
}