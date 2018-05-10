package Kushagra;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.Random;

public class Demo implements KeyListener,MouseListener {
    private static JPanel p;
    private static Demo d;


    private static int ballon1Xcord = 10;
    private static int ballon1Ycord = 750;
    private static int ballon1Speed = -15;

    private static int ballon2Xcord = 200;
    private static int ballon2Ycord = 760;
    private static int ballon2Speed = -15;

    private static int ballon3Ycord = 770;
    private static int ballon3Speed = -14;
    private static int ballon3Xcord = 270;

    private static int ballon4Ycord = 780;
    private static int ballon4Speed = -13;
    private static int ballon4Xcord;

    private static int ballon5Ycord = 790;
    private static int ballon5Speed = -15;
    private static int ballon5Xcord = 430;

    private static int bombYcord = 820;
    private static int bombSpeed = -15;
    private static int bombXcord = 300;

    private static int bomb1Ycord = 750;
    private static int bomb1Speed = -15;
    private static int bomb1Xcord = 100;

    private static boolean ballon1Visible = true;
    private static boolean ballon2Visible = true;
    private static boolean ballon3Visible = true;
    private static boolean ballon4Visible = true;
    private static boolean ballon5Visible = true;

    private static boolean bombVisible = true;
    private static boolean bomb1Visible = true;
    private static boolean paused = false;
    private static boolean crash = false;

    private static Graphics ImageGraphics;
    private  Graphics PanelGraphics;

    private static AudioClip bg;
    private static AudioClip hit;
    private static AudioClip bomb;



    private static int Score=0;
    private static int Counter=0;

    public static void main(String[] args) {
        JFrame frame = new JFrame();
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Demo.p = new JPanel();

        Demo.p.setPreferredSize(new Dimension(650, 650));
        Demo.p.setFocusable(true);
        frame.add(Demo.p);
        Demo.d = new Demo();
        Demo.p.addKeyListener(d);
        Demo.p.addMouseListener(d);
        frame.pack();
        frame.setVisible(true);


        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
        }

        Demo.p.requestFocus();
        ImageGraphics = p.getGraphics();

        Image bg;
        Image ballon1;
        Image bomb;
        Image ballon2;
        Image ballon3;
        Image ballon4;
        Image ballon5;
        Image  over;
  Image bigImage;

        try {        bigImage = new BufferedImage(650, 650, BufferedImage.TYPE_INT_RGB);

            bg = ImageIO.read(Demo.class.getResource("images/bg.jpg"));
            ballon1 = ImageIO.read(Demo.class.getResource("images/b1.png"));
            ballon2 = ImageIO.read(Demo.class.getResource("images/b2.png"));
            ballon3 = ImageIO.read(Demo.class.getResource("images/b3.png"));

            ballon4 = ImageIO.read(Demo.class.getResource("images/b4.png"));

            ballon5 = ImageIO.read(Demo.class.getResource("images/b5.png"));
            bomb = ImageIO.read(Demo.class.getResource("images/bomb.png"));
            over = ImageIO.read((Demo.class.getResource("images/over.png")));
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        URL bg1=Demo.class.getResource("images/bg.wav");
        Demo.bg= Applet.newAudioClip(bg1);
        Demo.hit= Applet.newAudioClip(Demo.class.getResource("images/hit.wav"));
        Demo.bomb= Applet.newAudioClip(Demo.class.getResource("images/bomb.wav"));
        Demo.bg.loop();

        while (true) {
            try {
                Thread.sleep(70);
            } catch (InterruptedException e) {

            }  Graphics PanelGraphics = p.getGraphics();
            ImageGraphics =bigImage.getGraphics();
            //ImageGraphics.clearRect(0,0,650,650);
            ImageGraphics.drawImage(bg, 0, 0, null);

            if (crash) {
                continue;
            }


            Demo.ballon1Ycord += Demo.ballon1Speed;
            Demo.ballon2Ycord += Demo.ballon2Speed;
            Demo.ballon3Ycord += Demo.ballon3Speed;
            Demo.ballon4Ycord += Demo.ballon4Speed;
            Demo.ballon5Ycord += Demo.ballon5Speed;
            Demo.bombYcord += Demo.bombSpeed;
            Demo.bomb1Ycord += Demo.bomb1Speed;

            if (ballon1Visible) {
                ImageGraphics.drawImage(ballon1, Demo.ballon1Xcord, Demo.ballon1Ycord, null);

            }
            if (ballon2Visible) {
                ImageGraphics.drawImage(ballon2, Demo.ballon2Xcord, Demo.ballon2Ycord, null);

            }

            if (ballon3Visible) {
                ImageGraphics.drawImage(ballon3, Demo.ballon3Xcord, Demo.ballon3Ycord, null);

            }
            if (ballon4Visible) {
                ImageGraphics.drawImage(ballon4, Demo.ballon4Xcord, Demo.ballon4Ycord, null);
            }
            if (ballon5Visible)
            {ImageGraphics.drawImage(ballon5, Demo.ballon5Xcord, Demo.ballon5Ycord, null);
            }
            ImageGraphics.setColor(Color.BLACK);
            ImageGraphics.setFont(new Font("Arial",Font.BOLD,40));
            ImageGraphics.drawString("Score:"+Score,450,30);
            ImageGraphics.drawImage(bomb, Demo.bombXcord, Demo.bombYcord, null);

            ImageGraphics.drawImage(bomb, Demo.bomb1Xcord, Demo.bomb1Ycord, null);
            if (Demo.paused) {
                ImageGraphics.setColor(Color.BLUE);
                ImageGraphics.setFont(new Font("Arial",Font.BOLD,40));
                ImageGraphics.drawString("Game paused",150, 300);
                PanelGraphics.drawImage(bigImage, 0, 0, null);
                p.removeMouseListener(d);
                ballon1Speed=0;
                ballon2Speed=0;
                ballon3Speed=0;
                ballon4Speed=0;
                ballon5Speed=0;
                bomb1Speed=0;
                bombSpeed=0;
                continue;
            }


            if(Demo.ballon1Ycord<0)
                Demo.Counter++;

            if(Demo.ballon2Ycord<0)
                Demo.Counter++;
            if(Demo.ballon3Ycord<0)
                Demo.Counter++;
            if(Demo.ballon4Ycord<0)
                Demo.Counter++;
            if(Demo.ballon5Ycord<0)
                Demo.Counter++;


            if (Demo.ballon1Ycord < 0 || Demo.ballon1Visible == false) {
                Demo.ballon1Ycord = 750;
                Random r1 = new Random();
                Demo.ballon1Xcord = r1.nextInt(450);
                ballon1Visible = true;

            }


            if (Demo.ballon2Ycord < 0 || Demo.ballon2Visible == false) {
                Demo.ballon2Ycord = 760;
                Random r1 = new Random();
                Demo.ballon2Xcord = r1.nextInt(450);
                ballon2Visible = true;
            }

            if (Demo.ballon3Ycord < 0 || Demo.ballon3Visible == false) {
                Demo.ballon3Ycord = 770;
                Random r1 = new Random();
                Demo.ballon3Xcord = r1.nextInt(450);
                ballon3Visible = true;
            }

            if (Demo.ballon4Ycord < 0 || Demo.ballon4Visible == false) {
                Demo.ballon4Ycord = 780;
                Random r1 = new Random();
                Demo.ballon4Xcord = r1.nextInt(450);
                ballon4Visible = true;
            }

            if (Demo.ballon5Ycord < 0 || Demo.ballon5Visible == false) {
                Demo.ballon5Ycord = 790;
                Random r1 = new Random();
                Demo.ballon5Xcord = r1.nextInt(450);
                ballon5Visible = true;
            }

            if (Demo.bombYcord < 0) {
                Demo.bombYcord = 830;
                Random r1 = new Random();
                Demo.bombXcord = r1.nextInt(450);
            }
            if (Demo.bomb1Ycord < 0) {
                Demo.bomb1Ycord = 750;
                Random r1 = new Random();
                Demo.bomb1Xcord = r1.nextInt(450);
            }

            if(Demo.Counter==20)
            {ImageGraphics.drawImage(over, 200, 20, null);
                PanelGraphics.drawImage(bigImage, 0, 0, null);
                crash=true;
                continue;}

            if (bombVisible == false)
            {ImageGraphics.drawImage(over, 200, 20, null);
                PanelGraphics.drawImage(bigImage, 0, 0, null);

                crash=true;
                continue;}
            if (bomb1Visible == false)
            { ImageGraphics.drawImage(over, 200, 20, null);
                PanelGraphics.drawImage(bigImage, 0, 0, null);
                crash=true;
                continue;}
            PanelGraphics.drawImage(bigImage, 0, 0, null);



            ImageGraphics.dispose();
            PanelGraphics.dispose();
        }}






    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_SPACE)
        {Demo.paused=!Demo.paused;
        if(!Demo.paused) {
            bombSpeed = -15;
            bomb1Speed = -15;
            ballon1Speed = -15;
            ballon2Speed = -15;
            ballon3Speed = -14;
            ballon4Speed = -13;
            ballon5Speed = -15;
        }
            p.addMouseListener(d);}
        if(crash){
            if(e.getKeyCode()==KeyEvent.VK_ENTER){
                JPanel p=new JPanel();
                Demo d=new Demo();

               ballon1Xcord = 10;
                ballon1Ycord = 750;
                ballon1Speed = -15;

                ballon2Xcord = 200;
                ballon2Ycord = 760;
                ballon2Speed = -15;
                ballon3Ycord = 770;
                ballon3Speed = -14;
                ballon3Xcord = 270;

                ballon4Ycord = 780;
                ballon4Speed = -13;
                ballon4Xcord=50;

                ballon5Ycord = 790;
                ballon5Speed = -15;
                ballon5Xcord = 430;

                bombYcord = 820;
                bombSpeed = -15;
                bombXcord = 300;
                bomb1Ycord = 750;
                bomb1Speed = -15;
                bomb1Xcord = 100;
                ballon1Visible = true;
                ballon2Visible = true;
                ballon3Visible = true;
                ballon4Visible = true;
                ballon5Visible = true;
                bombVisible = true;
                bomb1Visible = true;
                paused = false;
                Graphics ImageGraphics;
                Graphics PanelGraphics;
                crash=false;

                Score=0;
                Counter=0;
                p.addMouseListener(d);
            }}
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int a = e.getX();
        int b = e.getY();

        if (a > ballon1Xcord && (a < ballon1Xcord + 100) && b > ballon1Ycord && (b < ballon1Ycord + 100)) {
            Demo.ballon1Visible = false;
            Score += 10;

            hit.play();

        }
        if (a > ballon2Xcord && (a < ballon2Xcord + 100) && b > ballon2Ycord && (b < ballon2Ycord + 100)) {
            Demo.ballon2Visible = false;
            Score += 10;
            hit.play();
        }
        if (a > ballon3Xcord && (a < ballon3Xcord + 100) && b > ballon3Ycord && (b < ballon3Ycord + 100)) {
            Demo.ballon3Visible = false;
            Score += 10;
            hit.play();
        }
        if (a > ballon4Xcord && (a < ballon4Xcord + 100) && b > ballon4Ycord && (b < ballon4Ycord + 100)) {
            Demo.ballon4Visible = false;
            Score += 10;
            hit.play();
        }
        if (a > ballon5Xcord && (a < ballon5Xcord + 100) && b > ballon5Ycord && (b < ballon5Ycord + 100)) {
            Demo.ballon5Visible = false;
            Score += 10;
            hit.play();
        }

        if (a > bombXcord && (a < bombXcord + 100) && b > bombYcord && (b < bombYcord + 100)) {
            Demo.bombVisible = false;
            bomb.play();
        }


        if (a > bomb1Xcord && (a < bomb1Xcord + 100) && b > bomb1Ycord && (b < bomb1Ycord + 100)){
            Demo.bomb1Visible = false;
            bomb.play();}
    }

    @Override
    public void mousePressed(MouseEvent e) {

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
}
