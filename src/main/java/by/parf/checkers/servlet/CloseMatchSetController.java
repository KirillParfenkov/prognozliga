package by.parf.checkers.servlet;

import by.parf.checkers.beans.MatchSet;
import by.parf.checkers.dao.MatchDao;
import by.parf.checkers.factory.MatchFactory;
import by.parf.checkers.service.Constant;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: parf
 * Date: 13.10.13
 * Time: 19.03
 */
@WebServlet(
        name="closeMatchSetController",
        urlPatterns = {Constant.URL_CLOSE_MATCH_SET_CONTROLLER}
)
public class CloseMatchSetController extends AbstractController{

    @Override
    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        Integer matchSetId = Integer.valueOf(request.getParameter(Constant.KEY_MATCH_SET_ID));
        MatchDao matchDao = MatchFactory.getClassFromFactory();
        MatchSet matchSet = matchDao.getMatchSetById(matchSetId);
        matchSet.setClosed(true);
        matchDao.updateMatchSet(matchSet);

        JSONObject result = new JSONObject();
        try {
            result.put("type", "closeMatchSet");
            result.put("content", matchSet.getId());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().write(result.toString());
        matchDao.close();
    }
}
