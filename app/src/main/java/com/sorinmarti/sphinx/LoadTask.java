package com.sorinmarti.sphinx;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.sorinmarti.sphinx.quiz.IO_Utils;
import com.sorinmarti.sphinx.quiz.Question;
import com.sorinmarti.sphinx.quiz.Quiz;
import com.sorinmarti.sphinx.quiz.QuizCreator;
import com.sorinmarti.sphinx.quiz.QuizUpdater;

import java.util.List;

/**
 * Created by SOMA on 21.10.2017.
 */

public class LoadTask extends AsyncTask<String, String, String> {

    Context context;
    TextView statusText;
    ProgressBar progressBar;
    Button[] controlledButtons;

    public LoadTask(Context context, TextView statusText, ProgressBar progressBar, Button... controlledButtons) {
        this.context = context;
        this.statusText = statusText;
        this.progressBar = progressBar;
        this.controlledButtons = controlledButtons;

        for(Button btn : controlledButtons) {
            btn.setEnabled(false);
        }
    }

    @Override
    protected String doInBackground(String... sUrl) {
        publishProgress(context.getResources().getString(R.string.load_saved_quizzes));

        String[] filenames = IO_Utils.getFileList(IO_Utils.DATA_FOLDER, "quiz", context);
        if(filenames.length==0) {
            // TODO no quizzes saved. Do an update.
            return null;
        }
        for(String filename : filenames) {
            try {
                Quiz quiz = QuizCreator.createFromXMLString(filename, context);
                if(quiz!=null) {
                    publishProgress("Loaded "+quiz.getQuizTitle());
                }
            } catch (Exception e) {
                publishProgress("Failed loading "+filename);
            }
            sleep();
        }
        publishProgress("Loading complete");
        return null;
    }

    private void sleep() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onProgressUpdate(String... values) {
        statusText.setText(values[0]);
    }

    @Override
    protected void onPostExecute(String result) {
        progressBar.setIndeterminate(false);
        progressBar.setProgress( progressBar.getMax() );
        for(Button btn : controlledButtons) {
            btn.setEnabled(true);
        }
    }
}
