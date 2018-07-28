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

    QuestionType getQuestionType();

    AnswerType getType();
    String getQuestionText();
    String getQuestionPicture();
    String getFolderName();

    Answer[] getAnswers();
    Answer getAnswer(int number);

    int getNumAnswers();
    void setAnswerText(String answerText);

    QuizStatistics.Result solveQuestion(Answer answer);
    QuizStatistics.Result solveQuestion(String answer);

    void shuffleAnswers();
}
