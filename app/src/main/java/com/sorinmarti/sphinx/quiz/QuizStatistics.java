package com.sorinmarti.sphinx.quiz;

/**
 * Created by SOMA on 09.10.2017.
 */

public interface QuizStatistics {

    enum Result {
        CORRECT_ANSWER("Correct Answer"),
        WRONG_ANSWER("Incorrect Answer");

        String text;

        Result(String text) {
            this.text = text;
        }
        public String getText() {
            return this.text;
        }
    }

    public String getCorrespondingQuizName();
    public void addQuestionResult(Question question, Result result);
    public QuestionStatistics getQuestionResult(Question question);

    public int getNumCorrectAnswers();
    public int getNumIncorrectAnswers();
    public int getTotalNumAnswers();
}
