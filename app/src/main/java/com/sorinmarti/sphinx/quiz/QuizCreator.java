package com.sorinmarti.sphinx.quiz;

import android.content.Context;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by SOMA on 06.10.2017.
 */

public class QuizCreator {

    public static Quiz createFromXMLString(String filename, Context context) throws Exception {
        String fileContents = IO_Utils.getLocalFile(IO_Utils.DATA_FOLDER, filename, context);
        Quiz quiz = parse(filename, fileContents);
        quiz.shuffleQuestions();

        if(quiz != null) {
            QuizLibrary.getInstance().saveQuiz( quiz );
        }
        return quiz;
    }

    private static Quiz parse(String filename, String xml) {
        XmlPullParserFactory factory = null;
        XmlPullParser parser = null;

        try {
            factory = XmlPullParserFactory.newInstance();

            factory.setNamespaceAware(true);
            parser = factory.newPullParser();

            parser.setInput( new StringReader(xml));

            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                if(eventType == XmlPullParser.START_TAG && parser.getName().equalsIgnoreCase("quiz")) {
                    for (int i = 0; i < parser.getAttributeCount(); i++) {
                        if (parser.getAttributeName(i).equals("dynamicType")) {
                            if (parser.getAttributeValue(i).equalsIgnoreCase("gallery")) {
                                return parseGalleryQuiz(filename, xml);
                            }
                        }
                    }
                    // All of the <quiz> parameters are checked and no Quiz is created: create default quiz
                    return parseDefaultQuiz(filename, xml);
                }
                // Next element
                eventType = parser.next();
            }
        } catch (XmlPullParserException e) {
            System.out.println("XML Ex");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("IO Ex");
            e.printStackTrace();
        }

        return null;
    }

    private static Quiz parseGalleryQuiz(String filename, String xml) {
        XmlPullParserFactory factory = null;
        XmlPullParser parser = null;

        QuizDynamicImpl quiz = new QuizDynamicImpl(filename);
        String text = null;
        String question = null;
        String last_matches = null;

        Map<String, AnswerImpl> possibleAnswers = new HashMap<>();

        try {
            factory = XmlPullParserFactory.newInstance();

            factory.setNamespaceAware(true);
            parser = factory.newPullParser();

            parser.setInput( new StringReader(xml));

            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String tagName = parser.getName();
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if (tagName.equalsIgnoreCase("answer")) {
                            last_matches = parser.getAttributeValue(null, "matches");
                            quiz.addQuestionImage(last_matches);
                        }
                        break;
                    case XmlPullParser.TEXT:
                        text = parser.getText();
                        break;
                    case XmlPullParser.END_TAG:
                        if (tagName.equalsIgnoreCase("title")) {
                            quiz.setQuizTitle(text);
                        } else if (tagName.equalsIgnoreCase("description")) {
                            quiz.setQuizDescription(text);
                        } else if (tagName.equalsIgnoreCase("question")) {
                            quiz.setQuestion(text);
                        } else if (tagName.equalsIgnoreCase("answer")) {
                            quiz.addAnswer(text, last_matches);
                        }
                        break;

                    default:
                        break;
                }
                // Next element
                eventType = parser.next();
            }

        } catch (XmlPullParserException e) {
            System.out.println("XML Ex");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("IO Ex");
            e.printStackTrace();
        }

        return quiz;
    }

    /**
     * Parses a static xml quiz file into a quiz object.
     * @param filename
     * @param xml
     * @return
     */
    private static Quiz parseDefaultQuiz(String filename, String xml) {
        XmlPullParserFactory factory = null;
        XmlPullParser parser = null;

        QuizImpl quiz = null;
        QuestionImpl question = null;
        AnswerImpl answer = null;
        String text = null;
        int nodeLevel = 0;

        try {
            factory = XmlPullParserFactory.newInstance();

            factory.setNamespaceAware(true);
            parser = factory.newPullParser();

            parser.setInput( new StringReader(xml));

            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String tagName = parser.getName();
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if (tagName.equalsIgnoreCase("quiz")) {
                            quiz = new QuizImpl(filename);
                        } else if(tagName.equalsIgnoreCase("question")) {
                            question = new QuestionImpl(quiz.getFolderName());
                            String answerType = parser.getAttributeValue(null, "type");
                            switch(answerType) {
                                case "MULTIPLE_CHOICE":
                                    question.setType(Question.AnswerType.MULTIPLE_CHOICE);
                                    break;
                                case "ENTER_INT":
                                    question.setType(Question.AnswerType.ENTER_INT_VALUE);
                                    break;
                                case "ENTER_STRING":
                                    question.setType(Question.AnswerType.ENTER_STRING_VALUE);
                                    break;
                            }

                            String questionType = parser.getAttributeValue(null, "questionType");
                            if(questionType!=null) {
                                switch (questionType) {
                                    case "PICTURED":
                                        question.setQuestionType(Question.QuestionType.PICTURED);
                                        break;
                                    default:
                                        question.setQuestionType(Question.QuestionType.PLAIN);
                                        break;
                                }
                            } else {
                                question.setQuestionType(Question.QuestionType.PLAIN);
                            }

                            nodeLevel = 1;
                        } else if(tagName.equalsIgnoreCase("answer")) {
                            answer = new AnswerImpl();
                            String isCorrect = parser.getAttributeValue(null, "isCorrect");
                            if(isCorrect!=null && !isCorrect.isEmpty()) {
                                answer.setCorrect(true);
                            }
                            nodeLevel = 2;
                        }
                        break;
                    case XmlPullParser.TEXT:
                        text = parser.getText();
                        break;
                    case XmlPullParser.END_TAG:
                        if (tagName.equalsIgnoreCase("question")) {
                            quiz.addQuestion(question);
                        } else if (tagName.equalsIgnoreCase("answer")) {
                            question.addAnswer(answer);
                        } else if (tagName.equalsIgnoreCase("title")) {
                            switch(nodeLevel) {
                                case 0: // quiz
                                    quiz.setQuizTitle(text);
                                    break;
                                case 1: // question
                                    question.setQuestionText(text);
                                    break;
                                case 2: // answer
                                    answer.setAnswerText(text);
                                    break;
                            }
                        } else if(tagName.equalsIgnoreCase("picture")) {
                            switch(nodeLevel) {
                                case 0: // quiz
                                    quiz.setQuizPicture(text);
                                    break;
                                case 1: // question
                                    question.setPicture(text);
                                    break;
                                case 2: // answer
                                    // no picture available
                                    break;
                            }
                        } else if (tagName.equalsIgnoreCase("description")) {
                            quiz.setQuizDescription(text);
                        } else if (tagName.equalsIgnoreCase("solution")) {
                            switch(nodeLevel) {
                                case 1: // question
                                    question.setAnswerText(text);
                                    break;
                            }
                        }
                        break;

                    default:
                        break;
                }
                // Next element
                eventType = parser.next();
            }

        } catch (XmlPullParserException e) {
            System.out.println("XML Ex");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("IO Ex");
            e.printStackTrace();
        }

        return quiz;
    }
}
