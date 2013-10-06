package by.parf.checkers.servlet;

import by.parf.checkers.beans.MatchSet;
import by.parf.checkers.dao.MatchDao;
import by.parf.checkers.factory.MatchFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: parf
 * Date: 6.10.13
 */
@WebServlet(
        name = "showMatchSetListController",
        urlPatterns = {"/showMatchSetListController"}
)
public class ShowMatchSetListController extends AbstractController {

    final long  INIT_NUMBER_MATCHES = 10;

    @Override
    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        MatchDao matchDao = MatchFactory.getClassFromFactory();

        List<MatchSet> matchSets = matchDao.getLimitedMatchSetListFromId(-1, INIT_NUMBER_MATCHES);

        JSONObject result = new JSONObject();
        JSONArray matchSetList = new JSONArray();
        JSONObject tmp = null;

        try {

            result.put("type", "matchSetList" );

            for (MatchSet matchSet: matchSets) {
                tmp = new JSONObject();
                tmp.put("id", matchSet.getId());
                tmp.put("date", matchSet.getDate());
                tmp.put("title", matchSet.getTitle());
                tmp.put("closed", matchSet.getClosed());
                matchSetList.put(tmp);
            }

            result .put("content", matchSetList);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().write(result.toString());
        matchDao.close();

    }
}
