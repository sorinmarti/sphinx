package com.sorinmarti.sphinx.quiz;

/**
 * Created by SOMA on 06.10.2017.
 */

public interface Question {

    enum QuestionType {
        PLAIN,
        PICTURED
    }

    enum AnswerType {
        MULTIPLE_CHOICE,
        ENTER_INT_VALUE,
        ENTER_STRING_VALUE
    }

    public QuestionType getQuestionType();

    public AnswerType getType();
    public String getQuestionText();
    public String getQuestionPicture();
    public String getFolderName();

    public Answer[] getAnswers();
    public Answer getAnswer(int number);

    public int getNumAnswers();
    public void setAnswerText(String answerText);

    public QuizStatistics.Result solveQuestion(Answer answer);
    public QuizStatistics.Result solveQuestion(String answer);

    public void shuffleAnswers();
}
