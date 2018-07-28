package com.sorinmarti.sphinx.quiz;

/**
 * Created by SOMA on 09.10.2017.
 */

public interface QuizStatistics {

    enum Result {
        CORRECT_ANSWER("Correct Answer"),
        WRONG_ANSWER("Incorrect Answer");

        final String text;

        Result(String text) {
            this.text = text;
        }
        public String getText() {
            return this.text;
        }
    }

    String getCorrespondingQuizName();
    void addQuestionResult(Question question, Result result);
    QuestionStatistics getQuestionResult(Question question);

    int getNumCorrectAnswers();
    int getNumIncorrectAnswers();
    int getTotalNumAnswers();
}
