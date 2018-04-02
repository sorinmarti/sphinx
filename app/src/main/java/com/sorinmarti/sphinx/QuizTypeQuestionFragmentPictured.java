package com.sorinmarti.sphinx;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

        ImageView questionPicture = (ImageView) view.findViewById(R.id.questionPicture);
        System.out.println("Checking: "+IO_Utils.DATA_FOLDER+"_"+question.getFolderName()+" <> "+question.getQuestionPicture());
        boolean exists = IO_Utils.fileExists(IO_Utils.DATA_FOLDER+"_"+question.getFolderName(), question.getQuestionPicture(), context);

        System.out.println("Exists? "+exists);
        if(exists) {
            try {
                Bitmap bitmap = IO_Utils.getLocalBitmap(IO_Utils.DATA_FOLDER+"_"+question.getFolderName(), question.getQuestionPicture(), context);
                questionPicture.setImageBitmap(bitmap);
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
