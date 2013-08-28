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
 * Date: 23.8.13
 * Time: 21.42
 */
@WebServlet(
        name = "logoutController",
        urlPatterns = {Constant.URL_LOGOUT_CONTROLLER}
)
public class LogoutController extends AbstractController {

    @Override
    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        session.removeAttribute(Constant.KEY_USER);

        jump(Constant.URL_MAIN_CONTROLLER, request, response);
    }
}
