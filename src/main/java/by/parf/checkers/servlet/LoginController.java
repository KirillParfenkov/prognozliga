package by.parf.checkers.servlet;

import by.parf.checkers.beans.User;
import by.parf.checkers.dao.UserDao;
import by.parf.checkers.factory.UserDAOFactory;
import by.parf.checkers.service.Constant;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: parf
 * Date: 18.8.13
 * Time: 13.40
 */
@WebServlet(
        name="loginController",
        urlPatterns = {Constant.URL_LOGIN_CONTROLLER}
)
public class LoginController extends AbstractController {

    public LoginController() {
        super();
    }

    @Override
    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        String email = request.getParameter(Constant.KEY_INPUT_EMAIL);
        String password = request.getParameter(Constant.KEY_INPUT_PASSWORD);
        Boolean successfulAuthentication = false;

        UserDao userDao = UserDAOFactory.getClassFromFactory();

        User user = userDao.getUserByEmail(email);


        if (user != null) {
            System.out.print("user not empty " + user.getEmail());

            successfulAuthentication = userDao.authenticate(user, password);
            if (successfulAuthentication) {
                session.setAttribute(Constant.KEY_USER, user);
                request.removeAttribute(Constant.KEY_ERROR_MESSAGE);
            } else {
                request.setAttribute(Constant.KEY_ERROR_MESSAGE, "Login failure");
            }
        } else {
            request.setAttribute(Constant.KEY_ERROR_MESSAGE, "Login failure");
        }

        userDao.close();
        jump(Constant.URL_MAIN_CONTROLLER, request, response);

    }
}
