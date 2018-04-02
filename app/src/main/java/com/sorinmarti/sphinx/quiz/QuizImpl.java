package com.sorinmarti.sphinx.quiz;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by SOMA on 06.10.2017.
 */

class QuizImpl extends AbstractQuiz {


    private List<Question> questions;
    private int lastQuestionIdx = 0;

    public QuizImpl(String filename) {
        super(filename);
        questions = new ArrayList<>();
    }

    @Override
    public Question getNextQuestion() {
        if(questions.size()>lastQuestionIdx) {
            Question question = questions.get(lastQuestionIdx);
            lastQuestionIdx++;
            return question;
        }
        lastQuestionIdx = 0;
        shuffleQuestions();
        return null;
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public int getCurrentQuestionNumber() {
        return (lastQuestionIdx);
    }
    public int getTotalQuestionNumber() {
        return questions.size();
    }

    public void shuffleQuestions() {
        for(Question question : questions) {
            question.shuffleAnswers();
        }
        Collections.shuffle( questions );
    }
}
