package by.parf.checkers.servlet;

import by.parf.checkers.beans.Evaluation;
import by.parf.checkers.beans.Match;
import by.parf.checkers.beans.MatchSet;
import by.parf.checkers.dao.EvaluationDao;
import by.parf.checkers.dao.MatchDao;
import by.parf.checkers.factory.EvaluationFactory;
import by.parf.checkers.factory.MatchFactory;
import by.parf.checkers.service.Constant;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    final long  INIT_NUMBER_MATCHES = 10;

    public MainController() {
        super();
    }


    @Override
    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        MatchDao matchDao = MatchFactory.getClassFromFactory();
        EvaluationDao evaluationDao = EvaluationFactory.getClassFromFactory();

        List<Evaluation> evaluationList = new ArrayList<Evaluation>();
        List<MatchSet> matchSetList = matchDao.getLimitedMatchSetListFromId(-1,INIT_NUMBER_MATCHES);

        for (MatchSet matchSet: matchSetList) {

            for (Match match: matchSet.getMatches()) {
                matchSet.addAllEvaluations(evaluationDao.getEvaluationListByMatchId(match.getId()));
            }
        }

        request.setAttribute(Constant.KEY_MATCH_SET_LIST, matchSetList);

        matchDao.close();
        evaluationDao.close();
        jump(Constant.URL_MAIN_PAGE, request, response);
    }
}
