package com.sorinmarti.sphinx;

import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sorinmarti.sphinx.quiz.QuizLibrary;
import com.sorinmarti.sphinx.quiz.QuizStatistics;

/**
 * Created by SOMA on 22.10.2017.
 */

public class QuizTypeAnswerFragmentStringValue extends QuizTypeAnswerFragment {

    @Override
    protected void setUpListeners(final View view) {
        final EditText editText = (EditText)view.findViewById(R.id.txtAnswerString);

        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    QuizStatistics.Result result = question.solveQuestion(editText.getText().toString());
                    QuizLibrary.getInstance().getCurrentQuizStatistics().addQuestionResult(question, result);

                    Toast.makeText(view.getContext(), result.getText(), Toast.LENGTH_SHORT).show();

                    fireQuestionAnswered();
                }
                return false;
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.quiz_type_string;
    }
}
