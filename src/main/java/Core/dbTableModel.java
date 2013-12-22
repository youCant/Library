package Core;

import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.ArrayList;

public class DBTableModel extends DefaultTableModel {

    private Object[][] content;     //хранит данные
    private String[] columnNames;   //хранит названия заголовков
    private Class[] columnClasses;  //хранит типы полей (столбцов)

    public DBTableModel(Connection con, String query, String tableName)  {
        super();
        try{
            getTableContent(con, query, tableName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void getTableContent(Connection conn, String query, String tableName) throws SQLException {
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
                    colTypesList.add(java.sql.Date.class);
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

        ArrayList rowList = new ArrayList();

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
                else if (columnClasses[i] == java.sql.Date.class) {
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
        content = new Object[rowList.size()][];
        for (int i = 0; i < content.length; i++) {
            content[i] = (Object[]) rowList.get(i);
        }
        rs.close();
        st.close();
        }catch (SQLException e){
            e.printStackTrace();

        }
    }


    public Object[][] getContent() {
        return content;
    }



    public String[] getColumnNames(){
        return  columnNames;
    }

}