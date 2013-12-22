package Core;

import java.io.IOException;
import java.sql.*;

/**
 * Created with IntelliJ IDEA.
 * User: shade
 * Date: 25.11.13
 * Time: 19:55
 * To change this template use File | Settings | File Templates.
 */
public class MySQLHelper {
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private static MySQLHelper mySQLHelperInstance;

    public static MySQLHelper getInstance() {
            if (mySQLHelperInstance == null){
                mySQLHelperInstance = new MySQLHelper() ;
            }
        return mySQLHelperInstance;
    }
    //initialisation without name
    private MySQLHelper() {
        // register jdbc driver
        try{
        Class.forName("com.mysql.jdbc.Driver");
        /*ConnectionToDB();
        DropDB("library");
        CreateDB("Library");*/
        ConeectionToDB("Library");
//        CreateTable();

        Runtime.getRuntime().exec("cmd.exe cd C:\\Program Files\\coolMySQL\\MySQL Server 5.6\\bin\\ mysql -uroot -p4mjqi3h3cawE library < C:\\Users\\shade\\Desktop\\mysql\\de.vogella.mysql.first\\bd\\Res\\1.sql");
        }catch (SQLException | ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }



    public void ConnectionToDB() throws SQLException
    {
        try{
            connect = DriverManager
                    .getConnection("jdbc:mysql://localhost/?" + "user=root&password=4mjqi3h3cawE");
        }
        catch(SQLException se){
            se.printStackTrace();
        }
    }

    public void ConeectionToDB(String DBName) throws SQLException
    {
        try{
            connect = DriverManager
                    .getConnection("jdbc:mysql://localhost/" + DBName + "?" + "user=root&password=4mjqi3h3cawE");
        }
        catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }
    }


    // close connection
    public void CloseConnection() {
        try{
            if(statement!=null)
                connect.close();
        }catch(SQLException se){
        }// do nothing

    }


    // delete DB
    public void DropDB(String DbName)throws SQLException
    {
        try{
            statement = connect.createStatement();
            String sql = "DROP DATABASE if exists " + DbName;
            statement.executeUpdate(sql);
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }
    }


    // create DB
    public void CreateDB(String DbName) throws SQLException
    {
        try{
            statement = connect.createStatement();
            String sql = "CREATE DATABASE " + DbName;
            statement.executeUpdate(sql);
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }


    // create Table const
    public void CreateTable() throws SQLException
    {
        try{
            statement = connect.createStatement();

            String sql = "CREATE TABLE BOOKS " +
                    "(id INTEGER NOT NULL AUTO_INCREMENT, " +
                    " AuthorName VARCHAR(45) DEFAULT NULL," +
                    " BooksName VARCHAR(45) DEFAULT NULL," +
                    " Aviable tinyint(1) DEFAULT NULL, " +
                    " PRIMARY KEY ( `id` )," +
                    "UNIQUE INDEX id_UNIQUE (id))";
            statement.executeUpdate(sql);
             sql = "CREATE TABLE YCHET " +
                    "(idYchet INTEGER not NULL, " +
                    "info varchar(45) DEFAULT NULL," +
                    "data varchar(45) DEFAULT NULL) ";
            statement.executeUpdate(sql);
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }
    }



    // update Table (give book)
    public void giveBook(Integer BookId)  {
        try {
            if (bookAviable(BookId)) {
                String sql = "UPDATE Library.books SET Aviable = 0 WHERE id = " + BookId;
                statement.executeUpdate(sql);
                sql =  "INSERT INTO Library.ychet (idYchet,info,data) VALUES (" + BookId + " , \"pick\", \"" + java.util.Calendar.getInstance().getTime() + "\")";
                statement.executeUpdate(sql);
                System.out.println("see you soon, book ;(");
            }
            else
                System.out.print("This book not aviable");

        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }
    }


    // update Table (take book ID)
    public void takeBook(Integer BookId)  {
        try {
            if (!bookAviable(BookId)) {
                String sql = "UPDATE Library.books SET Aviable = 1 WHERE id = " + BookId;
                statement.executeUpdate(sql);
                sql =  "INSERT INTO Library.ychet (idYchet,info,data) VALUES (" + BookId + " , \"vozvrat\", \"" + java.util.Calendar.getInstance().getTime() + "\")";
                statement.executeUpdate(sql);
                System.out.println("Book back home, luke :)");
            }
            else
                System.out.print("This book aviable");

        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }
    }


    // book bookHistory
    public void bookHistory(int BookId)  {
        try {

            statement = connect.createStatement();
            resultSet = statement
                    .executeQuery("select * from library.ychet where idYchet = " + BookId);
            while (resultSet.next())
                System.out.println(resultSet.getString("idYchet") + " " + resultSet.getString("info") + " " + resultSet.getString("data"));
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }
    }

    //book Aviable count
    public void bookAviableNow() throws SQLException  {
        try{
            statement = connect.createStatement();
            resultSet = statement
                    .executeQuery("select * from library.books where aviable = 1");
            if (!resultSet.isBeforeFirst())
                System.out.println("NO aviable books now");
            else
            while (resultSet.next())
                System.out.println(resultSet.getInt("id") + " " + resultSet.getString("AuthorName") + " " + resultSet.getString("BooksName") + " " + resultSet.getBoolean("aviable"));
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }
    }


    public void addBook(String AuthorName, String BookName)  {
        try{
            statement = connect.createStatement();
            if ((AuthorName.equals("")) || (BookName.equals("")))
                    System.out.println("not enough information");
            else {
            String sql = "INSERT INTO library.books (AuthorName, BooksName, aviable) VALUES (\"" + AuthorName + "\", \"" + BookName + "\", 1)";
            statement.executeUpdate(sql);
            System.out.println("Book added"); }
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        }
    }

    // Доступна ли книга
    public boolean bookAviable(int BookId)  {
        boolean t = false;
        try {
            statement = connect.createStatement();
            resultSet = statement
                    .executeQuery("select aviable from library.books where id = " + BookId);
            resultSet.next();
            t = resultSet.getBoolean("aviable");
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        }
        return t;
    }

    public Connection getConnection() {
        return connect;
    }
}
