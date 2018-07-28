package com.sorinmarti.sphinx;

import android.view.View;
import android.widget.Button;

import com.sorinmarti.sphinx.quiz.Question;
import com.sorinmarti.sphinx.quiz.QuizLibrary;
import com.sorinmarti.sphinx.quiz.QuizStatistics;

/**
 * Created by SOMA on 22.10.2017.
 */

public class QuizTypeAnswerFragmentMultipleChoice extends QuizTypeAnswerFragment {

    protected void setUpListeners(View view) {

        if(question.getType()== Question.AnswerType.MULTIPLE_CHOICE) {
            final Button[] buttons = new Button[4];

            buttons[0] = view.findViewById(R.id.btnAnswer1);
            buttons[1] = view.findViewById(R.id.btnAnswer2);
            buttons[2] = view.findViewById(R.id.btnAnswer3);
            buttons[3] = view.findViewById(R.id.btnAnswer4);

            for(int i=0;i<4;i++) {
                if(question.getNumAnswers()>i) {
                    final int answerNumber = i;
                    buttons[i].setBackgroundResource(R.drawable.answer_button);
                    buttons[i].setVisibility(View.VISIBLE);
                    buttons[i].setText(question.getAnswer(i).getAnswerText());
                    buttons[i].setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            QuizStatistics.Result result = question.solveQuestion(question.getAnswer(answerNumber));
                            QuizLibrary.getInstance().getCurrentQuizStatistics().addQuestionResult(question, result);

                            for (int j = 0; j < 4; j++) {
                                if (question.getNumAnswers() > j) {
                                    if (question.getAnswer(j).isCorrect()) {
                                        if (result == QuizStatistics.Result.CORRECT_ANSWER) {
                                            buttons[j].setBackgroundResource(R.drawable.answer_correct);
                                        } else {
                                            buttons[j].setBackgroundResource(R.drawable.answer_incorrect);
                                        }
                                    } else if(j==answerNumber) {
                                        buttons[answerNumber].setBackgroundResource(R.drawable.answer_wrong);
                                    } else {
                                        buttons[j].setVisibility(View.INVISIBLE);
                                    }
                                }
                            }
                            fireQuestionAnswered();
                        }
                    });
                }
                else {
                    buttons[i].setVisibility(View.INVISIBLE);
                }
            }
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.quiz_type_multiplechoice;
    }
}
