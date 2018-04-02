package com.sorinmarti.sphinx.quiz;

/**
 * Created by SOMA on 09.10.2017.
 */

public interface GameStatistics {

    public String getName();

    public void addResult(QuizStatistics result);
    QuizStatistics getQuizResult(String quizFilename);
}
