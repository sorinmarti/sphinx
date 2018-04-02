package com.sorinmarti.sphinx.quiz;

/**
 * Created by SOMA on 06.10.2017.
 */

class AnswerImpl implements Answer {

    private String text;
    private boolean isCorrect;

    public AnswerImpl() {

    }

    @Override
    public String getAnswerText() {
        return text;
    }

    public void setAnswerText(String text) {
        this.text = text;
    }

    public void setCorrect(boolean correct) {
        this.isCorrect = correct;
    }

    public boolean isCorrect() {
        return this.isCorrect;
    }
}
