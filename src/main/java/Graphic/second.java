package Graphic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 * Created with IntelliJ IDEA.
 * User: shade
 * Date: 30.11.13
 * Time: 0:16
 * To change this template use File | Settings | File Templates.
 */
public class second extends JFrame implements ActionListener{
    private static JLabel label;
    private static  JTextField canChange;
    private static JTextField cantChange;
    private JMenuBar bar;
    private JMenu menu;
    private JRadioButtonMenuItem red;
    private JRadioButtonMenuItem blue;
    private JRadioButtonMenuItem green;

    public void menu() {
        bar = new JMenuBar();
        menu = new JMenu("Colors");
        red = new JRadioButtonMenuItem("Red");
        blue = new JRadioButtonMenuItem("Blue");
        green = new JRadioButtonMenuItem("Green");
        setLayout(new FlowLayout());
        setSize(300, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("colors");
        bar.add(menu);
        menu.add(red);
        menu.add(blue);
        menu.add(green);
        ButtonGroup group = new ButtonGroup();
        group.add(red);
        group.add(blue);
        group.add(green);
        red.addActionListener(this);
        blue.addActionListener(this);
        green.addActionListener(this);
        add(bar);

    }

    public void createTextf() {
        canChange = new JTextField("Default Text");
        cantChange = new JTextField("Cant change me");
        setLayout(null);
        Dimension size1 = canChange.getPreferredSize();
        Dimension size2 = cantChange.getPreferredSize();
        canChange.setBounds(100, 200, size1.width, size1.height);
        cantChange.setBounds(100, 55, size2.width, size2.height);
        cantChange.setEditable(false);
        add(canChange);
        add(cantChange);
        setSize(350,300);
        setTitle("Text Field");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    public void createTitle() {
        label = new JLabel("This is a label");
        setLayout(new FlowLayout());
        setSize(350, 300);
        setTitle("First Component");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(label);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == red) {
            setBackground(Color.RED);
        }
        if (e.getSource() == blue) {
            setBackground(Color.BLUE);
        }
        if (e.getSource() == green) {
            setBackground(Color.GREEN);
        }
    }
}
