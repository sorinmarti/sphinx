package com.sorinmarti.sphinx.quiz;

import java.util.List;

/**
 * Created by SOMA on 06.10.2017.
 */

public interface Quiz {

    public String getQuizTitle();
    public String getQuizPicture();
    public String getQuizDescription();

    public String getFilename();
    public String getFolderName();
    public Question getNextQuestion();

    public int getCurrentQuestionNumber();
    public int getTotalQuestionNumber();

    public void shuffleQuestions();
}
