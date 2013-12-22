package Graphic;

import Core.MySQLHelper;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.sql.*;
import java.util.ArrayList;

public class dbTableModel extends AbstractTableModel {

    private Object[][] content;     //хранит данные
    private String[] columnNames;   //хранит названия заголовков
    private Class[] columnClasses;  //хранит типы полей (столбцов)
    private MySQLHelper helper = MySQLHelper.getInstance();

    public dbTableModel()  {
        //super();
        try{
            getTableContent(helper.getConnection(), "select * from books", "books");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private ArrayList getTableContent(Connection conn, String query, String tableName) throws SQLException {
        ArrayList rowList = null;
        try {
        DatabaseMetaData md = conn.getMetaData();
        ResultSet rs = md.getColumns(null, null, tableName, null);

        ArrayList colNamesList = new ArrayList();
        ArrayList colTypesList = new ArrayList();

        while (rs.next()) {
            colNamesList.add(rs.getString("COLUMN_NAME"));

            int dbType = rs.getInt("DATA_TYPE");
            switch (dbType) {
                case Types.INTEGER:
                    colTypesList.add(Integer.class);
                    break;
                case Types.FLOAT:
                    colTypesList.add(Float.class);
                    break;
                case Types.DOUBLE:
                case Types.REAL:
                    colTypesList.add(Double.class);
                    break;
                case Types.DATE:
                case Types.TIME:
                case Types.TIMESTAMP:
                    colTypesList.add(Date.class);
                    break;
                default:
                    colTypesList.add(String.class);
                    break;
            }
        }

        columnNames = new String[colNamesList.size()];
        colNamesList.toArray(columnNames);

        columnClasses = new Class[colTypesList.size()];
        colTypesList.toArray(columnClasses);

        Statement st = conn.createStatement();
        rs = st.executeQuery(query);

            rowList = new ArrayList();

        while (rs.next()) {
            ArrayList cellList = new ArrayList();
            for (int i = 0; i < columnClasses.length; i++) {
                Object cellValue = null;
                if (columnClasses[i] == String.class) {
                    cellValue = rs.getString(columnNames[i]);
                }
                else if (columnClasses[i] == Integer.class) {
                    cellValue = new Integer(rs.getInt(columnNames[i]));
                }
                else if (columnClasses[i] == Float.class) {
                    cellValue = new Float(rs.getInt(columnNames[i]));
                }
                else if (columnClasses[i] == Double.class) {
                    cellValue = new Double(rs.getDouble(columnNames[i]));
                }
                else if (columnClasses[i] == Date.class) {
                    cellValue = rs.getDate(columnNames[i]);
                }
                else {
                    System.out.println("Can't define column type " + columnNames[i]);
                }
                cellList.add(cellValue);

            }// for
            Object[] cells = cellList.toArray();
            rowList.add(cells);
        }// while
        createTable(rowList);
        rs.close();
        st.close();
        }catch (SQLException e){
            e.printStackTrace();

        }
        return rowList;
    }

    private void createTable(ArrayList rowList) {
        content = new Object[rowList.size()][];
        for (int i = 0; i < content.length; i++) {
            content[i] = (Object[]) rowList.get(i);
        }
        JTable table = new JTable(content, columnNames);
        JFrame f = new JFrame();
        f.setSize(300, 300);
        f.add(new JScrollPane(table));
        f.setVisible(true);
    }

    @Override
    public int getRowCount() {
        return content.length;
    }

    @Override
    public int getColumnCount() {
        if (content.length == 0) {
            return 0;
        } else {
            return content[0].length;
        }
    }

    @Override
     public Object getValueAt(int row, int col) {
        return content[row][col];
    }


    @Override
    public Class getColumnClass(int col) {
        return columnClasses[col];
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }


    @Override
    public boolean isCellEditable(int rowIndex, int colIndex) {
        if (colIndex == 1) {
            return false;
        } else {
            return true;
        }
    }
}