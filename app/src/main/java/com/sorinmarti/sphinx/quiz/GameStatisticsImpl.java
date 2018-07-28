package com.sorinmarti.sphinx.quiz;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SOMA on 14.10.2017.
 */

class GameStatisticsImpl implements GameStatistics {

    private final String name;
    private final List<QuizStatistics> results;

    public GameStatisticsImpl(String name) {
        this.name = name;
        this.results = new ArrayList<>();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void addResult(QuizStatistics result) {
        results.add(result);
    }

    @Override
    public QuizStatistics getQuizResult(String quizFilename) {
        for(QuizStatistics result : results) {
            if(result.getCorrespondingQuizName().equals(quizFilename)) {
                return result;
            }
        }
        return new QuizStatisticsImpl(quizFilename);
    }
}
