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
    private JLabel label;
    private JLabel secLabel;
    private JTextField historyTextField;
    private JTextField bookTitleTextField;
    private JTextField authorNameTextField;
    private JTextField giveBookTextField;
    private JTextField takeBookTextField;
    private JTextField textField;
    private JButton historyButton;
    private JButton giveBookButton;
    private JButton addBookButton;
    private JButton takeBookButton;
    private JButton aviableBookButton;
    private JButton exitButton;
    private JPanel historyPanel = new JPanel();
    private JPanel addBookPanel = new JPanel();
    private String storeData = "";
    private Integer storeId;
    public MySQLHelper helper = MySQLHelper.getInstance();







    public GraphicUse(){


    }

                    public JPanel getHistoryPanel() {
                         historyButton = new JButton("History of book");
                         historyTextField = new JTextField();
                         historyTextField.setSize(30, 30);
                         historyPanel.add(historyButton);
                         historyPanel.add(historyTextField);
                         historyButton.addActionListener(this);
                        return historyPanel;

                     }


                     public JPanel getAddBookPanel() {
                         label = new JLabel("Book Title: ");
                         addBookButton = new JButton("Add new Book");
                         bookTitleTextField = new JTextField();
                         secLabel = new JLabel("Author Name : ");
                         authorNameTextField = new JTextField();
                         addBookButton.addActionListener(this);
                         addBookPanel.add(addBookButton);
                         addBookPanel.add(label);
                         addBookPanel.add(bookTitleTextField);
                         addBookPanel.add(secLabel);
                         addBookPanel.add(authorNameTextField);
                         return  addBookPanel;
                     }


                     public void takeBookFrame() {
                         label = new JLabel("Book Id : ");
                         takeBookButton = new JButton("Take book");
                         takeBookTextField = new JTextField();
                         takeBookButton.setBounds(10, 85, 120, 25);
                         label.setBounds(140, 85, 120, 30);
                         takeBookTextField.setBounds(200, 85, 25, 25);
                         takeBookButton.addActionListener(this);
                         add(takeBookButton);
                         add(label);
                         add(takeBookTextField);
                     }

                     public void giveBookFrame() {
                         label = new JLabel("Book Id : ");
                         giveBookButton = new JButton("Give book");
                         giveBookTextField = new JTextField();
                         giveBookButton.setBounds(10, 120, 120, 25);
                         label.setBounds(140, 120, 120, 30);
                         giveBookTextField.setBounds(200, 120, 25, 25);
                         giveBookButton.addActionListener(this);
                         add(giveBookButton);
                         add(label);
                         add(giveBookTextField);
                     }

                     public void aviBookFrame() {
                         label = new JLabel("Dont Work now/ ");
                         aviableBookButton = new JButton("Dont work");
                         textField = new JTextField();
                         aviableBookButton.setBounds(10, 155, 120, 25);
                         label.setBounds(140, 155, 120, 30);
                         textField.setBounds(270, 155, 25, 25);
                         textField.setEditable(false);
                         aviableBookButton.addActionListener(this);
                         add(aviableBookButton);
                         add(label);
                         add(textField);
                     }

                     public void exitBookButton() {
                         exitButton = new JButton("Exit");
                         exitButton.setBounds(550, 610, 120, 25);
                         exitButton.addActionListener(this);
                         add(exitButton);
                     }





                     public void actionPerformed(ActionEvent e) {

                         if (e.getSource() == historyButton) {
                             storeData = historyTextField.getText();
                             storeId = Integer.parseInt(storeData);
                             helper.bookHistory(storeId);
                             historyTextField.setText("");
                         }
                         if (e.getSource() == addBookButton) {
                             helper.addBook(bookTitleTextField.getText(), authorNameTextField.getText());
                             bookTitleTextField.setText("");
                             authorNameTextField.setText("");
                         }
                         if (e.getSource() == takeBookButton) {
                             storeId = Integer.parseInt(takeBookTextField.getText());
                             helper.takeBook(storeId);
                             takeBookTextField.setText("");
                         }
                         if (e.getSource() == giveBookButton) {
                             storeId = Integer.parseInt(giveBookTextField.getText());
                             helper.giveBook(storeId);
                             giveBookTextField.setText("");
                         }
                         if (e.getSource() == aviableBookButton) {
                             storeId = Integer.parseInt(textField.getText());
                             //start
                         }

                         if (e.getSource() == exitButton) {
                             System.exit(0);
                         }


                     }


                 }
