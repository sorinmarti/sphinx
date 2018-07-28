package com.sorinmarti.sphinx.quiz;

/**
 * Created by SOMA on 09.10.2017.
 */

public interface QuestionStatistics {

    void addQuestionResult(QuizStatistics.Result result);

    int getNumberOfWrongAnswers();
    int getNumberOfRightAnswers();
}
