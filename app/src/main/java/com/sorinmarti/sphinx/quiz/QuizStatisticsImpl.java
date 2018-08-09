package com.sorinmarti.sphinx.quiz;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by SOMA on 14.10.2017.
 *
 */

class QuizStatisticsImpl implements QuizStatistics {

    private final String correspondingQuizName;
    private final Map<Question, QuestionStatistics> questionStats;

    public QuizStatisticsImpl(String quizName) {
        this.correspondingQuizName = quizName;
        this.questionStats = new HashMap<>();
    }

    @Override
    public String getCorrespondingQuizName() {
        return correspondingQuizName;
    }

    @Override
    public void addQuestionResult(Question question, Result result) {
        QuestionStatistics questionStatistics;

        if(questionStats.containsKey(question)) {
            questionStatistics = questionStats.get(question);
        }
        else {
            questionStatistics = new QuestionStatisticsImpl();
        }
        questionStatistics.addQuestionResult( result );

        questionStats.put(question, questionStatistics);
    }

    @Override
    public QuestionStatistics getQuestionResult(Question question) {
        if(questionStats.containsKey(question)) {
            return questionStats.get(question);
        }
        QuestionStatistics stats = new QuestionStatisticsImpl();
        questionStats.put(question, stats);
        return stats;
    }

    @Override
    public int getNumCorrectAnswers() {
        int num = 0;
        for(QuestionStatistics qstat : questionStats.values()) {
            num += qstat.getNumberOfRightAnswers();
        }
        return num;
    }

    @Override
    public int getNumIncorrectAnswers() {
        int num = 0;
        for(QuestionStatistics qstat : questionStats.values()) {
            num += qstat.getNumberOfWrongAnswers();
        }
        return num;
    }

    @Override
    public int getTotalNumAnswers() {
        int num = 0;
        for(QuestionStatistics qstat : questionStats.values()) {
            num += qstat.getNumberOfRightAnswers();
            num += qstat.getNumberOfWrongAnswers();
        }
        return num;
    }
}
