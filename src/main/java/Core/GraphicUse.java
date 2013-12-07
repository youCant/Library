package Core;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created with IntelliJ IDEA.
 * User: shade
 * Date: 06.12.13
 * Time: 22:44
 * To change this template use File | Settings | File Templates.
 */
public class GraphicUse extends JFrame implements ActionListener {
    private  JLabel label;
    private JLabel secLabel;
    private JTextField htextField;
    private JTextField adtextField;
    private JTextField adsecTextField;
    private JTextField givtextField;
    private JTextField taketextField;
    private JTextField textField;
    private JButton hisB;
    private JButton givB;
    private JButton addB;
    private JButton takeB;
    private JButton aviB;
    private JButton exitB;
    private String storeData = "";
    private Integer storeId;
    private MySQLHelper helper = MySQLHelper.getInstance();





    public GraphicUse() {
        setTitle("Library");
        setLayout(null);
        setSize(700,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        historyBut();
        addBookBut();
        takeBookBut();
        giveBookBut();
        aviBookBut();
        exitBookBut();

    }

    private void historyBut(){
        label = new JLabel("Book Id : ");
        hisB = new JButton("History of book");
        htextField = new JTextField();
        hisB.setBounds(10, 5, 120, 25);
        label.setBounds(140, 5, 120, 30);
        htextField.setBounds(200, 5, 25, 25);
        hisB.addActionListener(this);
        add(hisB);
        add(label);
        add(htextField);
    }


    private void addBookBut(){
        label = new JLabel("Book Title: ");
        addB = new JButton("Add new Book");
        adtextField = new JTextField();
        secLabel = new JLabel("Author Name : ");
        adsecTextField = new JTextField();
        addB.setBounds(10, 40, 120, 25);
        label.setBounds(140, 40, 120, 30);
        adtextField.setBounds(210, 40, 150, 25);
        secLabel.setBounds(370, 40, 120, 30);
        adsecTextField.setBounds(455, 40, 150, 25);
        addB.addActionListener(this);
        add(addB);
        add(label);
        add(adtextField);
        add(secLabel);
        add(adsecTextField);
    }


    private void takeBookBut(){
        label = new JLabel("Book Id : ");
        takeB = new JButton("Take book");
        taketextField = new JTextField();
        takeB.setBounds(10, 85, 120, 25);
        label.setBounds(140, 85, 120, 30);
        taketextField.setBounds(200, 85, 25, 25);
        takeB.addActionListener(this);
        add(takeB);
        add(label);
        add(taketextField);
    }

    private void giveBookBut(){
        label = new JLabel("Book Id : ");
        givB = new JButton("Give book");
        givtextField = new JTextField();
        givB.setBounds(10, 120, 120, 25);
        label.setBounds(140, 120, 120, 30);
        givtextField.setBounds(200, 120, 25, 25);
        givB.addActionListener(this);
        add(givB);
        add(label);
        add(givtextField);
    }

    private void aviBookBut(){
        label = new JLabel("Dont Work now/ ");
        aviB = new JButton("Dont work");
        textField = new JTextField();
        aviB.setBounds(10, 155, 120, 25);
        label.setBounds(140, 155, 120, 30);
        textField.setBounds(270, 155, 25, 25);
        textField.setEditable(false);
        aviB.addActionListener(this);
        add(aviB);
        add(label);
        add(textField);
    }

    private void exitBookBut(){
        exitB = new JButton("Exit");
        exitB.setBounds(550, 210, 120, 25);
        exitB.addActionListener(this);
        add(exitB);
    }



    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == hisB) {
            storeData = htextField.getText();
            storeId = Integer.parseInt(storeData);
            helper.history(storeId);
        }
        if (e.getSource() == addB) {
            helper.addBook(adtextField.getText(), adsecTextField.getText());
        }
        if (e.getSource() == takeB) {
            storeId = Integer.parseInt(taketextField.getText());
            helper.takeBook(storeId);
        }
        if (e.getSource() == givB) {
            storeId = Integer.parseInt(givtextField.getText());
            helper.giveBook(storeId);
        }
        if (e.getSource() == aviB) {
            storeId = Integer.parseInt(textField.getText());

        }
        if (e.getSource() == exitB) {
            System.exit(0);
        }



    }


}
