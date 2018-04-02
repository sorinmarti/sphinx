package com.sorinmarti.sphinx.quiz;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by SOMA on 02.04.2018.
 */
public class QuizDynamicImpl extends AbstractQuiz {

    private String question;
    private List<String> imageResources = new ArrayList<>();
    private Map<String, String> answers = new HashMap<>();
    private int lastQuestionIdx = 0;

    protected QuizDynamicImpl(String filename) {
        super(filename);
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void addQuestionImage(String imageName) {
        imageResources.add(imageName);
    }

    public void addAnswer(String text, String imageName) {
        answers.put(imageName, text);
    }

    @Override
    public Question getNextQuestion() {
        if(imageResources.size()>lastQuestionIdx) {
            String imageResource = imageResources.get(lastQuestionIdx);
            QuestionImpl question = new QuestionImpl(getFolderName());
            question.setQuestionText( this.question );
            question.setPicture(imageResource);
            question.setQuestionType(Question.QuestionType.PICTURED);
            question.setType(Question.AnswerType.MULTIPLE_CHOICE);
            AnswerImpl correctAnswer = new AnswerImpl();
            correctAnswer.setAnswerText(answers.get(imageResource));
            correctAnswer.setCorrect(true);
            question.addAnswer( correctAnswer );
            for(int i=0;i<3;i++) {
                AnswerImpl wrongAnswer = new AnswerImpl();
                wrongAnswer.setCorrect(false);
                String answer = imageResource;
                Random generator = new Random();
                Object[] values = answers.values().toArray();
                while(answer.equals(imageResource)) {
                    boolean answerExists = false;
                    String newAnswer = (String)values[generator.nextInt(values.length)];
                    for(Answer savedAnswer : question.getAnswers()) {
                        if(newAnswer.equals(savedAnswer.getAnswerText())) {
                            answerExists = true;
                            break;
                        }
                    }
                    if(!answerExists) {
                        answer = newAnswer;
                    }

                }
                wrongAnswer.setAnswerText(answer);
                question.addAnswer(wrongAnswer);
            }
            question.shuffleAnswers();
            lastQuestionIdx++;
            return question;
        }
        lastQuestionIdx = 0;
        shuffleQuestions();
        return null;
    }

    @Override
    public int getCurrentQuestionNumber() {
        return lastQuestionIdx;
    }

    @Override
    public int getTotalQuestionNumber() {
        return imageResources.size();
    }

    @Override
    public void shuffleQuestions() {
        Collections.shuffle(imageResources);
    }
}
