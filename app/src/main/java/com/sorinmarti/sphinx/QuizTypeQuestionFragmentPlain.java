package com.sorinmarti.sphinx;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

/**
 * Created by SOMA on 17.03.2018.
 */

public class QuizTypeQuestionFragmentPlain extends QuizTypeQuestionFragment {

    protected void setUpFragment(View view, Context context) {
        TextView questionText = view.findViewById(R.id.questionText);
        questionText.setText(question.getQuestionText());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.question_type_plain;
    }
}
