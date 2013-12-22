package Core;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

/**
 * Created by shade on 22.12.13.
 */
public class AddTable extends JPanel{
    private static final int N_ROWS = 10;
    public MySQLHelper helper = MySQLHelper.getInstance();
    private BooksTableDataGenerator tableModel = new BooksTableDataGenerator(helper.getConnection(), "select * from books", "books");
    private DefaultTableModel defaultTableModel = new DefaultTableModel(tableModel.getContent(), tableModel.getColumnNames());
    private JTable table = new JTable(defaultTableModel);
    private JScrollPane scrollPane = new JScrollPane(table);
    private JScrollBar vScroll = scrollPane.getVerticalScrollBar();
    private Boolean isAutoScroll;
    private JTextField bookTitleTextField;
    private JTextField authorNameTextField;

    public AddTable() {
        this.setLayout(new BorderLayout());
        Dimension d = new Dimension(320, N_ROWS * table.getRowHeight());
        table.setPreferredScrollableViewportSize(d);
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
        JLabel label = new JLabel("Book Title: ");
        bookTitleTextField = new JTextField();
        JLabel secLabel = new JLabel("Author Name : ");
        authorNameTextField = new JTextField();
        panel.add(label);
        panel.add(bookTitleTextField);
        panel.add(secLabel);
        panel.add(authorNameTextField);
        panel.add(new JButton(new AbstractAction("Add book") {

            @Override
            public void actionPerformed(ActionEvent e) {
                addRow();
            }
        }));

        this.add(panel, BorderLayout.SOUTH);
    }

    private void addRow() {
       helper.addBook(bookTitleTextField.getText(), authorNameTextField.getText());
        if (helper.isDataBaseWasChanged) {
        defaultTableModel.addRow(tableModel.getLastContent(helper.getConnection(), "SELECT * FROM books ORDER BY id DESC LIMIT 1"));
        bookTitleTextField.setText("");
        authorNameTextField.setText("");
        }
    }
    }


