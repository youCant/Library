package main;

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


    //initialisation without name
    public MySQLHelper() throws Exception
    {
        // register jdbc driver
        Class.forName("com.mysql.jdbc.Driver");
        ConToDB();
        DropDB("library");
        CreateDB("Library");
        ConToDB("Library");
        CreateTable();

        Runtime.getRuntime().exec("cmd.exe cd C:\\Program Files\\coolMySQL\\MySQL Server 5.6\\bin\\ mysql -uroot -p4mjqi3h3cawE library < C:\\Users\\shade\\Desktop\\mysql\\de.vogella.mysql.first\\bd\\Res\\1.sql");

    }



    // connection to DB without name
    public void ConToDB() throws Exception
    {
        try{
            connect = DriverManager
                    .getConnection("jdbc:mysql://localhost/?" + "user=root&password=4mjqi3h3cawE");
        }
        catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }
    }

    public void ConToDB(String DBName) throws Exception
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
    public void CloseCon() {
        try{
            if(statement!=null)
                connect.close();
        }catch(SQLException se){
        }// do nothing

    }


    // delete DB
    public void DropDB(String DbName)throws Exception
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
    public void CreateDB(String DbName) throws Exception
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
    public void CreateTable() throws Exception
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


    // create Table var
    public void CreateTable(String TBName) throws Exception
    {
        try{
            statement = connect.createStatement();
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }
    }
// Insert Value in table


    // update Table (give book)
    public void giveBook(Integer BookId) throws Exception
    {
        ConToDB();
        try {
            if (BookAv(BookId)) {
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
    public void takeBook(Integer BookId) throws Exception
    {
        try {
            if (!BookAv(BookId)) {
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


    // book history
    public void history(int BookId) throws Exception
    {
        ConToDB();
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
    public void bookAvNow() throws Exception
    {
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


    // add book
    public void addBook(String AuthorName, String BookName) throws Exception
    {
        try{
            statement = connect.createStatement();
            String sql = "INSERT INTO library.books (AuthorName, BooksName, aviable) VALUES (\"" + AuthorName + "\", \"" + BookName + "\", 1)";
            statement.executeUpdate(sql);
            System.out.println("Book added");
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        }
    }

    // Book Aviable
    public boolean BookAv(int BookId) throws Exception {
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
}
