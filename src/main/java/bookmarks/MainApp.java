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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doPost(request, response);
        System.out.println("Bullsyeu");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        try {
            List<BookMark> bookmarks = BookMarksModel.getAllBookmarks();
            List<String> formatedBookmarks = new ArrayList<String>();
            for (BookMark bookmark : bookmarks) {
                //System.out.println(bookmark.toString());
                String title = bookmark.toString();
                formatedBookmarks.add(title);
            }
            request.setAttribute("list", formatedBookmarks);
            System.out.println(formatedBookmarks.toString());
            //BookMarksModel.deleteBookmark(3);
            //BookMarksModel.addBookmark(new BookMark(0, "First Bookmark", "First bookmark is great"));
        } catch (ClassNotFoundException | NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        /*
        String varTextA = "Hello World!";
        request.setAttribute("textA", varTextA);
        String varTextB = "It JSP.";
        request.setAttribute("textB", varTextB);
        */

        RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
        dispatcher.forward(request, response);
    }

    /*
    private static class RequestBookmark {

        public static BookMark fromRequest (HttpServletRequest request) {
            return new BookMark(0, request.getParameter("title"), request.getParameter("content"));
        }

        public void setAsRequestAttributes(HttpServletRequest request) {
            request.setAttribute("firstname", firstName);
            request.setAttribute("lastname", lastName);
            request.setAttribute("email", email);
        }

    }
    */

}

