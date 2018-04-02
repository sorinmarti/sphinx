package com.sorinmarti.sphinx.quiz;

/**
 * Created by SOMA on 09.10.2017.
 */

public interface QuestionStatistics {

    public void addQuestionResult(QuizStatistics.Result result);

    public int getNumberOfWrongAnswers();
    public int getNumberOfRightAnswers();
}
