package Graphic;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;


public class TableAddTest extends JPanel {

    private static final int N_ROWS = 8;
    private static String[] header = {"ID", "String", "Number", "Boolean"};
    private DefaultTableModel dtm = new DefaultTableModel(null, header) {

        @Override
        public Class<?> getColumnClass(int col) {
            return getValueAt(0, col).getClass();
        }
    };
    private JTable table = new JTable(dtm);
    private JScrollPane scrollPane = new JScrollPane(table);
    private JScrollBar vScroll = scrollPane.getVerticalScrollBar();
    private int row;
    private boolean isAutoScroll;

    public TableAddTest() {
        this.setLayout(new BorderLayout());
        Dimension d = new Dimension(320, N_ROWS * table.getRowHeight());
        table.setPreferredScrollableViewportSize(d);
        for (int i = 0; i < N_ROWS; i++) {
            addRow();
        }
        scrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        vScroll.addAdjustmentListener(new AdjustmentListener() {

            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                isAutoScroll = !e.getValueIsAdjusting();
            }
        });
        this.add(scrollPane, BorderLayout.CENTER);
        JPanel panel = new JPanel();
        panel.add(new JButton(new AbstractAction("Add Row") {

            @Override
            public void actionPerformed(ActionEvent e) {
                addRow();
            }
        }));
        this.add(panel, BorderLayout.SOUTH);
    }

    private void addRow() {
        char c = (char) ('A' + row++ % 26);
        dtm.addRow(new Object[]{
                Character.valueOf(c),
                String.valueOf(c) + String.valueOf(row),
                Integer.valueOf(row),
                Boolean.valueOf(row % 2 == 0)
        });
    }

    public static void main(String[] args) {

                JFrame f = new JFrame();
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                TableAddTest nlt = new TableAddTest();
                f.add(nlt);
                f.pack();
                f.setLocationRelativeTo(null);
                f.setVisible(true);

    }
}