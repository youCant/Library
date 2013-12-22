package Core;


import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) throws Exception {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                JFrame f = new JFrame();
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                AddTable nlt = new AddTable();
                f.add(nlt);
                f.pack();
                f.setLocationRelativeTo(null);
                f.setVisible(true);
            }
        });
    }
}
