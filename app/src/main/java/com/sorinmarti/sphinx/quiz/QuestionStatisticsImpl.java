package com.sorinmarti.sphinx.quiz;

/**
 * Created by SOMA on 14.10.2017.
 */

class QuestionStatisticsImpl implements QuestionStatistics {

    private int wrongAnswers;
    private int rightAnswers;

    public QuestionStatisticsImpl() {
        wrongAnswers = 0;
        rightAnswers = 0;
    }

    @Override
    public void addQuestionResult(QuizStatistics.Result result) {
        if(result== QuizStatistics.Result.CORRECT_ANSWER) {
            rightAnswers++;
        }
        else {
            wrongAnswers++;
        }
    }

    @Override
    public int getNumberOfWrongAnswers() {
        return wrongAnswers;
    }

    @Override
    public int getNumberOfRightAnswers() {
        return rightAnswers;
    }
}
