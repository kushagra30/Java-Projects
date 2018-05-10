package codekamp;

import com.sun.org.apache.regexp.internal.RE;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator implements ActionListener {
    private JTextField Result;
    public JPanel panel;
    private JButton ButtonOne;
    private JButton ButtonTwo;
    private JButton ButtonThree;
    private JButton AddButton;
    private JButton ButtonFour;
    private JButton ButtonFive;
    private JButton ButtonSix;
    private JButton ButtonSeven;
    private JButton ButtonEight;
    private JButton ButtonNine;
    private JButton MultiplyButton;
    private JButton ButtonZero;
    private JButton AnswerButton;
    private JButton DivideButton;
    private JButton MinusButton;
    private JButton DELButton;
    private JButton DecimalButton;
    private JButton CLRButton;
    private JButton ONOFFButton;

    public JPanel getPanel() {
        return panel;
    }

    public Calculator() {
        this.ButtonOne.addActionListener(this);
        this.ButtonTwo.addActionListener(this);
        this.ButtonThree.addActionListener(this);
        this.ButtonFour.addActionListener(this);
        this.ButtonFive.addActionListener(this);
        this.ButtonSix.addActionListener(this);

        this.ButtonSeven.addActionListener(this);
        this.ButtonEight.addActionListener(this);
        this.ButtonNine.addActionListener(this);
        this.ButtonZero.addActionListener(this);
        this.DivideButton.addActionListener(this);
        this.AddButton.addActionListener(this);
        this.MultiplyButton.addActionListener(this);
        this.MinusButton.addActionListener(this);
        this.AnswerButton.addActionListener(this);
        this.DELButton.addActionListener(this);
        this.CLRButton.addActionListener(this);
        this.DecimalButton.addActionListener(this);
        this.ONOFFButton.addActionListener(this);
        Result.setFont(new Font("Lucida Calligraphy", Font.BOLD, 20));

Result.setText("OFF..............Press ON");
    }

    static double a = 0, b = 0, res = 0;
    int i = 0;
    static int operator = 0;
    Boolean on = false;


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == ONOFFButton) {
            on = true;
            Result.setText("");
        }

        if (on) {
//        {Result.setFont(new Font("Arial",Font.BOLD,20));
//            Result.setText("OFF");

            if (e.getSource() == ButtonOne) {
                Result.setFont(new Font("Arial", Font.BOLD, 20));
                Result.setText(Result.getText().concat('1' + ""));

            }

            if (e.getSource() == ButtonTwo) {

                Result.setFont(new Font("Arial", Font.BOLD, 20));

                Result.setText(Result.getText() + 2);
            }
            if (e.getSource() == ButtonThree) {
                Result.setFont(new Font("Arial", Font.BOLD, 20));
                Result.setText(Result.getText() + 3);
            }
            if (e.getSource() == ButtonFour) {
                Result.setFont(new Font("Arial", Font.BOLD, 20));

                Result.setText(Result.getText() + 4);
            }
            if (e.getSource() == ButtonFive) {
                Result.setFont(new Font("Arial", Font.BOLD, 20));

                Result.setText(Result.getText() + 5);

            }
            if (e.getSource() == ButtonSix) {

                Result.setFont(new Font("Arial", Font.BOLD, 20));

                Result.setText(Result.getText() + 6);
            }
            if (e.getSource() == ButtonSeven) {
                Result.setFont(new Font("Arial", Font.BOLD, 20));

                Result.setText(Result.getText() + 7);
            }
            if (e.getSource() == ButtonEight) {

                Result.setFont(new Font("Arial", Font.BOLD, 20));

                Result.setText(Result.getText() + 8);
            }
            if (e.getSource() == ButtonNine) {
                Result.setFont(new Font("Arial", Font.BOLD, 20));

                Result.setText(Result.getText() + 9);
            }
            if (e.getSource() == ButtonZero) {
                Result.setFont(new Font("Arial", Font.BOLD, 20));

                Result.setText(Result.getText() + 0);
            }
            if (e.getSource() == AddButton) {
                Result.setFont(new Font("Arial", Font.BOLD, 20));


                i = Result.getText().length();
                a = Double.parseDouble(Result.getText());
                operator = 1;
                Result.setText(Result.getText() + "+");


            }
            if (e.getSource() == MultiplyButton) {
                Result.setFont(new Font("Arial", Font.BOLD, 20));
                i = Result.getText().length();
                a = Double.parseDouble(Result.getText());
                operator = 2;
                Result.setText(Result.getText() + "*");
            }
            if (e.getSource() == MinusButton) {
                Result.setFont(new Font("Arial", Font.BOLD, 20));
                i = Result.getText().length();
                a = Double.parseDouble(Result.getText());
                operator = 3;
                Result.setText(Result.getText() + "-");
            }

            if (e.getSource() == DivideButton) {
                i = Result.getText().length();
                Result.setFont(new Font("Arial", Font.BOLD, 20));
                a = Double.parseDouble(Result.getText());
                operator = 4;
                Result.setText(Result.getText() + "/");

            }
            if (e.getSource() == AnswerButton) {
                Result.setFont(new Font("Arial", Font.BOLD, 20));
                //Result.setText(Result.getText()+"=");
//
//            String Ans = Result.getText();
//            int num2=0;
//            int num1=0;
//            int res=0;
//
//            for (int i = 0; i < Ans.length()-2; i++) {
//                 Ans = Result.getText();
//                if (Integer.parseInt(Ans.charAt(i)+"") ==1|| Integer.parseInt(Ans.charAt(i)+"") == 2) {
//
//                     num1 = Integer.parseInt(Ans.charAt(i)+"");
//                }
//                if (Ans.charAt(i + 1) == '+') {
//                    if(Integer.parseInt(Ans.charAt(i)+"")==1|| Integer.parseInt(Ans.charAt(i+2)+"")==2)
//                         num2=Integer.parseInt(Ans.charAt(i+2)+"");
//
//                }
//                res=res+num1+num2;
//            }
//            Result.setText(res+"");
                String s = Result.getText();
                String g = s.substring(i + 1, s.length());
                b = Double.parseDouble(g);
                switch (operator) {
                    case 1:
                        res = a + b;
                        break;
                    case 2:
                        res = a * b;
                        break;
                    case 3:
                        res = a - b;
                        break;
                    case 4:
                        res = a / b;
                        break;
                    default:
                        res = 0;
                }
                Result.setText("" + res);

            }

            if (e.getSource() == DELButton) {
                if (Result.getText().length() > 0) {
                    String s = Result.getText();
                    String l = s.substring(0, s.length() - 1);
                    Result.setText(l);
                }
            }
            if (e.getSource() == CLRButton) {
                Result.setText("");
            }
            if (e.getSource() == DecimalButton) {
                Result.setFont(new Font("Arial", Font.BOLD, 20));
                Result.setText(Result.getText() + '.');

            }

        }
    }
}