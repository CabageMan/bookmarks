package bookmarks;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//@WebServlet("/bookmarks")
@WebServlet(name = "MainApp", urlPatterns = "/bookmarks")
public class MainApp extends HttpServlet {

    public static  void main(String[] args) {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Set character encoding
        request.setCharacterEncoding("UTF-8");

        if (request.getParameter("saveBookmark") != null ||
                request.getParameter("title") != null ||
                request.getParameter("content") != null) {

            try {
                //Send to database new bookmark and return changed list of bookmarks
                BookMark bookmark = new BookMark(0, request.getParameter("title"), request.getParameter("content"));
                BookMarksModel.addBookmark(bookmark);
                request.setAttribute("list", getBookmarks(""));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            request.getRequestDispatcher("/").forward(request, response);

        } else if (request.getParameter("filterBookmarks") != null ||
                    request.getParameter("filter") != null) {
            // Get filtered list of bookmarks by substring
            request.setAttribute("list", getBookmarks(request.getParameter("filter")));

            request.getRequestDispatcher("/").forward(request, response);

        } else if (request.getParameter("deleteBookmark") != null) {

            if (request.getParameter("delete") != null && !request.getParameter("delete").isEmpty()) {
                try {
                    //Call delete method with ID of deleting bookmark
                    BookMarksModel.deleteBookmark(Integer.parseInt(request.getParameter("delete")));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }

            //Return changed bookmarks list
            request.setAttribute("list", getBookmarks(""));

            request.getRequestDispatcher("/").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        //Get list of all bookmarks
        request.setAttribute("list", getBookmarks(""));

        RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
        dispatcher.forward(request, response);
    }

    public static List<String> getBookmarks(String key) throws ServletException, IOException {
        //Declare variables
        List<String> formatedBookmarks = new ArrayList<String>();
        String tmpString;

        try {
            //Get bookmarks array. Method filterBookmarks() is used for all queries of bookmarks list by parameter "key"
            List<BookMark> bookmarks = BookMarksModel.filterBookmarks(key);

            //Format each object in array and add it into new formated array
            for (BookMark bookmark : bookmarks) {
                tmpString = bookmark.toString();
                formatedBookmarks.add(tmpString);
            }

        } catch (ClassNotFoundException | NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return formatedBookmarks;

    }



}

