package codekamp;


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
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.stream.IntStream;

public  class Demo implements KeyListener,MouseListener {
    private static int driverXCord=50;
    private static int driverYCord=450;
    private static int car1YCord=-200;
    private static int car1XCord=90;
    private static int car2YCord=-200;
    private static int car2XCord=420;
    private static int driverXVel=0;
    private static int carYVel=0;
    private static int bgvel=10;
    private static int bgYcord=0;
    private static int bg1vel=10;
    private static int bg1Ycord=-650;
    private static boolean paused = false;
    private static boolean crash = false;
    private static int score=0;
    private static AudioClip bg;
    private static AudioClip cra;
    private static JPanel p;
    private static Demo d;

    public static void main(String[] args) {
        JFrame frame = new JFrame();
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Demo.p = new JPanel();
        Demo.p = new JPanel();
        Demo.p.setPreferredSize(new Dimension(600, 650));
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

        Graphics ImageGraphics;
        Graphics PanelGraphics;

        Image bigImage = new BufferedImage(600,650,BufferedImage.TYPE_INT_RGB);
        ImageGraphics=bigImage.getGraphics();
        PanelGraphics=p.getGraphics();

        Image bg;
        Image drive;
        Image car1;
        Image car2;
        Image play;

        try{

            bg = ImageIO.read(Demo.class.getResource("RoadRash/bg.png"));
            drive = ImageIO.read(Demo.class.getResource("RoadRash/drive.png"));
            car1 = ImageIO.read(Demo.class.getResource("RoadRash/car1.png"));
            car2= ImageIO.read(Demo.class.getResource("RoadRash/car2.png"));
            play= ImageIO.read(Demo.class.getResource("RoadRash/play.png"));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Unable to load images. seems installation is corrupt. Please reinstall.");
            return;
        }
        URL bg1=Demo.class.getResource("so/UpbeatFunk.wav");
        Demo.bg= Applet.newAudioClip(bg1);
        Demo.cra= Applet.newAudioClip(Demo.class.getResource("RoadRash/hit.wav"));
        Demo.bg.loop();
        while (true) {

            try {
                Thread.sleep(70);
            } catch (InterruptedException e) {

            }if(crash)
                continue;


            if ( Demo.areColliding(Demo.driverXCord, Demo.driverYCord, 90, 200, Demo.car1XCord, Demo.car1YCord, 92, 216)) {
                ImageGraphics.setColor(Color.RED);
                ImageGraphics.setFont(new Font("Times New Roman",Font.BOLD,50));
                ImageGraphics.drawString("Game Over",100,100);
                PanelGraphics.drawImage(bigImage,0,0,null);
                cra.play();
                crash=true;
                Demo.p.removeMouseListener(Demo.d);
                continue;
            }

            if ( Demo.areColliding(Demo.driverXCord, Demo.driverYCord, 90, 200, Demo.car2XCord, Demo.car2YCord, 105, 210)) {
                cra.play();
                ImageGraphics.setColor(Color.RED);
                ImageGraphics.setFont(new Font("Times New Roman",Font.BOLD,50));
                ImageGraphics.drawString("Game Over",100,100);
                PanelGraphics.drawImage(bigImage,0,0,null);
                crash=true;
                cra.play();
                Demo.p.removeMouseListener(Demo.d);
                continue;
            }PanelGraphics.drawImage(bigImage,0,0,null);
            if(Demo.paused) {
                ImageGraphics.setColor(Color.blue);
                ImageGraphics.drawString("Game Paused",150,300);
                continue;
            }
            Demo.score++;
            if(Demo.driverXCord>=420)
            {
                Demo.driverXVel=0;
                Demo.driverXCord=420;
            }

            if(Demo.driverXCord<50)
            {
                Demo.driverXVel=0;
                Demo.driverXCord=50;
            }
            Demo.car1YCord+=5;
            Demo.car2YCord+=5;

            if (Demo.car1YCord ==-200||Demo.car1YCord>600) {
                car1YCord = -200;
                Random r1 = new Random();
                Demo.car1XCord = r1.nextInt(100)+40;
            }
            Demo.carYVel=20;
            Demo.car1YCord+=Demo.carYVel;
            if (Demo.car2YCord==-200||Demo.car2YCord > 600) {
                car2YCord = -200;
                Random r2 = new Random();
                Demo.car2XCord = r2.nextInt(100) + 300;
            }
            Demo.carYVel=15;
            Demo.car2YCord+=Demo.carYVel;
            bgYcord+=bgvel;
            bg1Ycord+=bg1vel;
            if(bgYcord>=650)
                bgYcord=-650;
            if(bg1Ycord>=650)
                bg1Ycord=-650;
            ImageGraphics.drawImage(bg, 0, bgYcord, null);
            ImageGraphics.drawImage(bg, 0, bg1Ycord, null);
            ImageGraphics.drawImage(car1,Demo.car1XCord, Demo.car1YCord, null);
            ImageGraphics.drawImage(car2,Demo.car2XCord, Demo.car2YCord, null);
            ImageGraphics.drawImage(drive, Demo.driverXCord, 450, null);
            ImageGraphics.drawImage(play,0,0,null);
            ImageGraphics.setColor(Color.BLACK);
            ImageGraphics.setFont(new Font("Times New Roman",Font.BOLD,50));
            ImageGraphics.drawString("Score " + score, 350, 40);
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT) {

            Demo.driverXVel = 10;

            Demo.driverXCord += Demo.driverXVel;


        }

        if(e.getKeyCode() == KeyEvent.VK_LEFT) {

            Demo.driverXVel= -10;
            Demo.driverXCord += Demo.driverXVel;
        }
        if(e.getKeyCode()== KeyEvent.VK_ENTER&&(Demo.crash))
        {//Demo.bg.loop();
            score=0;
            driverXCord=50;
            driverYCord=450;
            car1YCord=-200;
            car1XCord=90;
            car2YCord=-200;
            car2XCord=420;
            driverXVel=0;
            bgYcord=0;
            bg1Ycord=-650;
            bg1vel=10;
            bgvel=10;
            carYVel=0;
            paused = false;
            crash = false;
            Demo.p.addMouseListener(Demo.d);


        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
    private static boolean areColliding(int x1, int y1, int w1, int h1, int x2, int y2, int w2, int h2) {
        boolean xOverLapping = (x1 > x2 && x1 < x2 + w2) || (x2 > x1 && x2 < x1 + w1);
        boolean yOverLapping = (y1 > y2 && y1 < y2 + h2) || (y2 > y1 && y2 < y1 + h1);

        return xOverLapping && yOverLapping;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        int clickX = e.getX();
        int clickY = e.getY();
        if(clickX > 0 && clickX < 90 && clickY > 0 && clickY < 90) {

            Demo.paused = !Demo.paused;
            if(Demo.paused)Demo.p.removeKeyListener(Demo.d);
            else
                Demo.p.addKeyListener(Demo.d);


        }
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
