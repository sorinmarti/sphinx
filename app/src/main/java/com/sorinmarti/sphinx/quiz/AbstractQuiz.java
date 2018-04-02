package com.sorinmarti.sphinx.quiz;

/**
 * Created by SOMA on 02.04.2018.
 */
public abstract class AbstractQuiz implements Quiz {
    private final String filename;
    private String title;
    private String picture;
    private String description;

    protected AbstractQuiz(String filename) {
        this.filename = filename;
        this.title = "";
        this.description = "";
    }

    @Override
    public String getQuizTitle() {
        return this.title;
    }

    @Override
    public String getQuizPicture() {
        return this.picture;
    }

    @Override
    public String getQuizDescription() {
        return this.description;
    }

    @Override
    public String getFilename() {
        return this.filename;
    }

    @Override
    public String getFolderName() {
        return this.filename.substring(0, filename.indexOf(".quiz"));
    }

    public void setQuizTitle(String quizTitle) {
        this.title = quizTitle;
    }

    public void setQuizPicture(String picture) {
        this.picture = picture;
    }

    public void setQuizDescription(String quizDescription) {
        this.description = quizDescription;
    }
}
