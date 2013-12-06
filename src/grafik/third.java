package grafik;

import javax.swing.*;
import java.awt.event.*;

/**
 * Created with IntelliJ IDEA.
 * User: shade
 * Date: 30.11.13
 * Time: 0:41
 * To change this template use File | Settings | File Templates.
 */
public class third extends JFrame implements ActionListener {

    private  JLabel enterName;
    private JTextField name;
    private JButton click;
    private String storeName = "";

    public third() throws Exception{
        setLayout(null);
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        enterName = new JLabel("Enter Your Name: ");
        click = new JButton("click");
        name = new JTextField();
        enterName.setBounds(50, 30, 120, 30);
        name.setBounds(80, 69, 130, 30);
        click.setBounds(100, 190, 80, 30);
        click.addActionListener(this);
        add(click);
        add(name);
        add(enterName);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == click) {
            storeName = name.getText();
            JOptionPane.showMessageDialog(null, "hello " + storeName);
            System.exit(0);
        }
    }



}
