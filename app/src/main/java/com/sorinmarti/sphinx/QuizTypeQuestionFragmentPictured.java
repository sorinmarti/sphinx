package com.sorinmarti.sphinx;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sorinmarti.sphinx.quiz.IO_Utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by SOMA on 17.03.2018.
 */

public class QuizTypeQuestionFragmentPictured extends QuizTypeQuestionFragment {

    protected void setUpFragment(View view, Context context) {
        TextView questionText = (TextView) view.findViewById(R.id.questionText);
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
