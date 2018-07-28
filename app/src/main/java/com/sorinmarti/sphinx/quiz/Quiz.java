package com.sorinmarti.sphinx.quiz;

/**
 * Created by SOMA on 06.10.2017.
 */

public interface Quiz {

    String getQuizTitle();
    String getQuizPicture();
    String getQuizDescription();

    String getFilename();
    String getFolderName();
    Question getNextQuestion();

    int getCurrentQuestionNumber();
    int getTotalQuestionNumber();

    void shuffleQuestions();
}
