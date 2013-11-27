import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: shade
 * Date: 25.11.13
 * Time: 19:53
 * To change this template use File | Settings | File Templates.
 */
public class MySQLCommands extends MySQLHelper{
    private String s1,s2;
    private int i;
    private Scanner sc = new Scanner(System.in);

    public MySQLCommands() throws Exception {
        System.out.println("Aviable list commands \"-sp\"");
        boolean t = true;
        while(t)
        {
            s2 = sc.nextLine();
            s1 = s2.replace(" ", "");
            if (s1.equals("-his"))
            {
                System.out.print("Write book id: ");
                if(sc.hasNextInt()) { // возвращает истинну если с потока ввода можно считать целое число
                    s1 = sc.nextLine(); // считывает строку и сохраняем в переменную
                    i = Integer.parseInt(s1);
                } else {
                    System.out.println("you write fractional number.");
                }
            } else
            if (s1.equals("-addb"))
            {
                System.out.print("Write name Author book: ");
                String name = sc.nextLine();
                System.out.print("Write book title: ");
                String title = sc.nextLine();
                addBook(name, title);
            } else
            if (s1.equals("-takeb"))
            {
                System.out.print("Write book id: ");
                if(sc.hasNextInt()) { // возвращает истинну если с потока ввода можно считать целое число
                    s1 = sc.nextLine(); // считывает строку и сохраняем в переменную
                    i = Integer.parseInt(s1);
                    takeBook(i);
                } else {
                    System.out.println("you write fractional number");
                }

            } else
            if (s1.equals("-giveb"))
            {
                System.out.print("Write book id: ");
               if(sc.hasNextInt()) { // возвращает истинну если с потока ввода можно считать целое число
                    s1 = sc.nextLine(); // считывает строку и сохраняем в переменную
                    i = Integer.parseInt(s1);
                    giveBook(i);
                } else {
                    System.out.println("you write fractional number");
               }
            } else
            if (s1.equals("-quite"))
            {
                t =false;
            } else
            if (s1.equals("-sp"))
            {
                System.out.println("-sp Aviable list commands; \n" +
                        "-his Book history; \n" +
                        "-takeb pass book; \n" +
                        "-giveb give book; \n" +
                        "-addb add Book to table; \n" +
                        "-avbo Books aviable; \n" +
                        "-quite Exit." );
            } else
            if (s1.equals("-avbo"))
            {
                bookAvNow();
            }
            else
            {
                System.out.println("Unknown command");
            }


        }

    }
}
