package by.parf.checkers.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: parf
 * Date: 18.8.13
 * Time: 13.55
 */
public abstract class AbstractController extends HttpServlet {

    public AbstractController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        performTask(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        performTask(request,response);
    }

    protected abstract void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

    protected void jump(String url, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
        rd.forward(request, response);
    }
}
