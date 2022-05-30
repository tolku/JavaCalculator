package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.company.InfixExpression;

public class CalculatorForm {
    private JPanel panel1;
    private JButton a1Button;
    private JTextArea screen;
    private JButton minusButton;
    private JButton multiplyButton;
    private JButton a6Button;
    private JButton a5Button;
    private JButton a4Button;
    private JButton a2Button;
    private JButton a3Button;
    private JButton a0Button;
    private JButton pointButton;
    private JButton equalsButton;
    private JButton additionButton;
    private JButton a7Button;
    private JButton a8Button;
    private JButton a9Button;
    private JButton closingBracketButton;
    private JButton divisionButton;
    private JButton openingBracketButton;
    private JButton CLRButton;
    private JButton HMMButton;
    private static CalculatorForm form;

    public CalculatorForm() {
        a1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                screen.append("1");
            }
        });
        a2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                screen.append("2");
            }
        });
        a3Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                screen.append("3");
            }
        });
        a4Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                screen.append("4");
            }
        });
        a5Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                screen.append("5");
            }
        });
        a6Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                screen.append("6");
            }
        });
        a7Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                screen.append("7");
            }
        });
        a8Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                screen.append("8");
            }
        });
        a9Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                screen.append("9");
            }
        });
        a0Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                screen.append("0");
            }
        });
        minusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                screen.append("-");
            }
        });
        multiplyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                screen.append("*");
            }
        });
        additionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                screen.append("+");
            }
        });
        pointButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                screen.append(".");
            }
        });
        closingBracketButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                screen.append(")");
            }
        });
        openingBracketButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                screen.append("(");
            }
        });
        divisionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                screen.append("/");
            }
        });
        CLRButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                screen.setText("");
            }
        });
        equalsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InfixExpression infixExpression = new InfixExpression(screen.getText());
                double[] ax = infixExpression.fillNumberArr(infixExpression.getInfix().toCharArray());
                String conv = infixExpression.infixConverter();
                String x = infixExpression.shuntingYardAlgo(conv);
                double res = infixExpression.shuntingYardToValue(x, ax);
                screen.setText(Double.toString(res));
            }
        });
    }

    public static void main(String... args) {
        JFrame frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setMinimumSize(new Dimension(330, 260));
        form = new CalculatorForm();
        frame.setContentPane(form.panel1);
        frame.setVisible(true);
        form.screen.setEditable(false);
    }
}