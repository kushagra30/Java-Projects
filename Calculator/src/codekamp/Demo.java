package codekamp;

import javax.swing.*;
import java.awt.*;

public class Demo {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);





        Calculator c=new Calculator();
        c.panel.setPreferredSize(new Dimension(350,400));
        frame.setLocation(500,200);

        frame.add(c.getPanel());

        frame.pack();
        frame.setVisible(true);
    }
}
