package bookmarks;

import javax.naming.NamingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookMarksModel {

    // Create db-connection class objects
    public static Connection connection;
    public static Statement stat;

    public static void ConnectionWithDb() throws ClassNotFoundException, SQLException {
        // Create connection with database
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:/Users/viktorsmacbook/SQLite/bookmarks.sqlite");
    }

    public static void addBookmark(BookMark bookmark) throws ClassNotFoundException, SQLException {

        try {
            ConnectionWithDb();
            stat = connection.createStatement();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO BOOKMARKS (TITLE, CONTENT) VALUES (?, ?)");
            statement.setObject(1, bookmark.title);
            statement.setObject(2, bookmark.content);
            statement.execute();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            stat.close();
            CloseDB();
        }
    }

    /*
    public static List<BookMark> getAllBookmarks() throws ClassNotFoundException, SQLException, NamingException
    {
        //Create a list for bookmarks
        List<BookMark> bookmarks = new ArrayList<BookMark>();

        ConnectionWithDb();
        stat = connection.createStatement();
        //Save the result of the request
        ResultSet rs = stat.executeQuery("SELECT * FROM BOOKMARKS");

        //Save all received objects
        while (rs.next()) {
            bookmarks.add(new BookMark(rs.getInt("id"),
                                       rs.getString("title"),
                                       rs.getString("content")));
        }

        rs.close();
        stat.close();
        CloseDB();

        return bookmarks;
    }
    */

    public static void deleteBookmark(int id) throws SQLException, ClassNotFoundException {

        try {
            ConnectionWithDb();
            stat = connection.createStatement();
            PreparedStatement statement = connection.prepareStatement("DELETE FROM BOOKMARKS WHERE ID = ?");
            statement.setObject(1, id);
            statement.execute();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            stat.close();
            CloseDB();
        }
    }

    public static List<BookMark> filterBookmarks(String key) throws ClassNotFoundException, SQLException, NamingException
    {
        //Create a list for filtered bookmarks
        List<BookMark> bookmarks = new ArrayList<BookMark>();

        ConnectionWithDb();
        stat = connection.createStatement();
        //Save the result of the request
        PreparedStatement statement = connection.prepareStatement("SELECT ID, TITLE, CONTENT FROM BOOKMARKS WHERE TITLE LIKE ? " +
                                                                                                                 "OR CONTENT LIKE ?");
        statement.setString(1, "%" + key + "%");
        statement.setString(2, "%" + key + "%");
        ResultSet rs = statement.executeQuery();

        System.out.println(key);
        //Save all received objects
        while (rs.next()) {
            bookmarks.add(new BookMark(rs.getInt("id"),
                                       rs.getString("title"),
                                       rs.getString("content")));
        }

        rs.close();
        statement.close();
        stat.close();
        CloseDB();

        return bookmarks;
    }

    public static void CloseDB() throws ClassNotFoundException, SQLException {
        connection.close();
    }

}
