package com.sorinmarti.sphinx.quiz;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by SOMA on 06.10.2017.
 */

class QuestionImpl implements Question {

    private Question.QuestionType questionType;
    private Question.AnswerType type;
    private String text;
    private String picture;
    private String folderName;
    private List<Answer> answers;
    private String answerText;

    public QuestionImpl(String folderName) {
        this.folderName = folderName;
        answers = new ArrayList<Answer>();
    }

    @Override
    public QuestionType getQuestionType() {
        return questionType;
    }

    @Override
    public AnswerType getType() {
        return type;
    }

    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
    }

    public void setType(AnswerType answerType) {
        this.type = answerType;
    }

    @Override
    public String getQuestionText() {
        return text;
    }

    @Override
    public String getQuestionPicture() {
        return picture;
    }

    @Override
    public String getFolderName() {
        return folderName;
    }

    @Override
    public Answer[] getAnswers() {
        return answers.toArray(new Answer[answers.size()]);
    }

    @Override
    public Answer getAnswer(int number) {
        return answers.get(number);
    }

    @Override
    public int getNumAnswers() {
        return answers.size();
    }

    @Override
    public QuizStatistics.Result solveQuestion(Answer answer) {
        if(answer.isCorrect()) {
            return QuizStatistics.Result.CORRECT_ANSWER;
        }
        return QuizStatistics.Result.WRONG_ANSWER;
    }

    @Override
    public QuizStatistics.Result solveQuestion(String answer) {
        if(getType()==AnswerType.ENTER_INT_VALUE ||
           getType()==AnswerType.ENTER_STRING_VALUE) {
                if(getAnswerText().equals(answer)) {
                    return QuizStatistics.Result.CORRECT_ANSWER;
                }
        }
        return QuizStatistics.Result.WRONG_ANSWER;
    }

    @Override
    public void shuffleAnswers() {
        Collections.shuffle( answers );
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    private String getAnswerText() {
        return answerText;
    }

    public void addAnswer(Answer answer) {
        answers.add(answer);
    }

    public void setQuestionText(String questionText) {
        this.text = questionText;
    }

    public void setPicture(String picture) { this.picture = picture; }
}
