package by.parf.checkers.dao;

import by.parf.checkers.beans.Evaluation;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: parf
 * Date: 19.9.13
 * Time: 16.06
 */
public interface EvaluationDao {

    Evaluation getEvaluationById(long id);
    List<Evaluation> getEvaluationListByMatchId(long matchId);
    long getMaxId();
    void addEvaluation(Evaluation evaluation);
    void close();
}
