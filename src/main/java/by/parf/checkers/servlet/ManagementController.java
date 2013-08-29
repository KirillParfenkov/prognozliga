package by.parf.checkers.servlet;

import by.parf.checkers.service.Constant;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: parf
 * Date: 29.8.13
 * Time: 22.33
 */
@WebServlet(
        name="managementController",
        urlPatterns = {Constant.URL_MANAGEMENT_CONTROLLER}
)
public class ManagementController extends AbstractController {

    @Override
    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        jump(Constant.URL_MANAGEMENT_PAGE, request, response);
    }
}
