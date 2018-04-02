package com.sorinmarti.sphinx.quiz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by SOMA on 06.10.2017.
 */

public class QuizLibrary {

    private static QuizLibrary instance = null;
    private Map<String, Quiz> savedQuiz;
    private QuizStatistics currentQuizStatistics;
    private GameStatistics gameStatistics;

    public static QuizLibrary getInstance() {
        if(instance==null) {
            instance = new QuizLibrary();
        }
        return  instance;
    }

    private QuizLibrary() {
        savedQuiz = new HashMap<String, Quiz>();
        gameStatistics = new GameStatisticsImpl("XXX");      // TODO load it from assets
    }

    public void saveQuiz(Quiz createdQuiz) {
        if(createdQuiz==null) {
            throw new NullPointerException();
        }
        savedQuiz.put(createdQuiz.getFilename(), createdQuiz);
    }

    public Quiz getQuiz(String quizFilename) {
        return savedQuiz.get(quizFilename);
    }

    public GameStatistics getGameStatistics() {
        return gameStatistics;
    }

    public int getNumberOfQuizzes() {
        return savedQuiz.size();
    }

    public void setStatistics(GameStatistics stats) {
        this.gameStatistics = stats;
    }

    public void startNewQuizStatistics(String quizName) {
        currentQuizStatistics = new QuizStatisticsImpl(quizName);
    }

    public QuizStatistics getCurrentQuizStatistics() {
        return currentQuizStatistics;
    }

    public Quiz[] getAllQuizzes() {
        List<Quiz> list = new ArrayList<Quiz>(savedQuiz.values());
        Quiz[] array = new Quiz[list.size()];
        array = list.toArray(array);
        return array;
    }
}
