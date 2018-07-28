package com.sorinmarti.sphinx;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.widget.TextView;

import com.sorinmarti.sphinx.quiz.IO_Utils;

/**
 * Created by SOMA on 17.03.2018.
 */

public class QuizTypeQuestionFragmentPictured extends QuizTypeQuestionFragment {

    protected void setUpFragment(View view, Context context) {
        TextView questionText = view.findViewById(R.id.questionText);
        questionText.setText(question.getQuestionText());

        //ImageView questionPicture = (ImageView) view.findViewById(R.id.questionPicture);

        boolean exists = IO_Utils.fileExists(IO_Utils.DATA_FOLDER+"_"+question.getFolderName(), question.getQuestionPicture(), context);

        if(exists) {
            try {
                Bitmap bitmap = IO_Utils.getLocalBitmap(IO_Utils.DATA_FOLDER+"_"+question.getFolderName(), question.getQuestionPicture(), context);
                BitmapDrawable drawableLeft = new BitmapDrawable(getResources(), bitmap);
                questionText.setCompoundDrawablesWithIntrinsicBounds(drawableLeft, null, null, null);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    protected int getLayoutId() {
        return R.layout.question_type_pictured;
    }
}
