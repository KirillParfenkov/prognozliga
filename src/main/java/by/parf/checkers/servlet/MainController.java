package by.parf.checkers.servlet;

import by.parf.checkers.service.Constant;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: parf
 * Date: 18.8.13
 * Time: 15.41
 */
@WebServlet(
        name="mainController",
        urlPatterns = {Constant.URL_MAIN_CONTROLLER}
)
public class MainController extends AbstractController {

    public MainController() {
        super();
    }


    @Override
    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();


        jump("/main.jsp", request, response);
    }
}
