package com.sorinmarti.sphinx;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.sorinmarti.sphinx.quiz.Answer;
import com.sorinmarti.sphinx.quiz.IO_Utils;
import com.sorinmarti.sphinx.quiz.Question;
import com.sorinmarti.sphinx.quiz.Quiz;
import com.sorinmarti.sphinx.quiz.QuizCreator;
import com.sorinmarti.sphinx.quiz.QuizUpdater;

import java.util.List;

/**
 * Created by SOMA on 21.10.2017.
 */

public class UpdateTask extends AsyncTask<String, String, String> {

    Context context;
    TextView statusText;
    ProgressBar progressBar;
    Button button;

    public UpdateTask(Context context, TextView statusText, ProgressBar progressBar, Button button) {
        this.context = context;
        this.statusText = statusText;
        this.progressBar = progressBar;
        this.button = button;
    }

    @Override
    protected String doInBackground(String... sUrl) {
        publishProgress("Starting Update...");
        sleep();
        int numActions = 0;
        try {
            if(QuizUpdater.gameNeedsUpdating( context )) {
                publishProgress("Getting update actions...");
                sleep();
                List<QuizUpdater.UpdateAction> actions = QuizUpdater.getUpdateActions( context );
                numActions = actions.size();
                for(QuizUpdater.UpdateAction action : actions) {
                    publishProgress(action.getActionText());
                    action.processAction( context );
                    sleep();
                }
                publishProgress("Update completed!");
                sleep();
            }
        } catch (Exception e) {
            e.printStackTrace();    // TODO
        }

        publishProgress("Parsing Data...");
        sleep();
        try {
            String[] filenames = IO_Utils.getFileList(IO_Utils.DATA_FOLDER, "quiz", context);
            for(String filename : filenames) {
                publishProgress("Creating quiz "+filename);
                QuizCreator.createFromXMLString(filename, context);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i("STARTUP","Quiz Library loaded.");

        publishProgress("Library Loaded. "+numActions+" updates.");
        sleep();
        return null;
    }

    private void sleep() {
        try {
            Thread.sleep(1000);
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
        button.setEnabled(true);
    }
}
