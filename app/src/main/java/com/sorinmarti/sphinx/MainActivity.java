package com.sorinmarti.sphinx;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.sorinmarti.sphinx.quiz.IO_Utils;
import com.sorinmarti.sphinx.quiz.Quiz;
import com.sorinmarti.sphinx.quiz.QuizCreator;


public class MainActivity extends AppCompatActivity {

    ProgressBar updateBar;
    TextView txtUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        updateBar = (ProgressBar)findViewById(R.id.barMainUpdate);

        txtUpdate = (TextView)findViewById(R.id.txtUpdateStatus);

        final LoadTask loadTask = new LoadTask(getBaseContext(),
                txtUpdate,
                updateBar);
        loadTask.execute();

        // TODO Create update task for local files. These files get loaded anyway.
        // TODO The internet update task can remain as is, It would need further options to select new quizzes, maybe.
    }

    public void showMenu() {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    class LoadTask extends AsyncTask<String, String, String> {

        private Context context;
        private TextView statusText;
        private ProgressBar progressBar;

        public LoadTask(Context context, TextView statusText, ProgressBar progressBar) {
            this.context = context;
            this.statusText = statusText;
            this.progressBar = progressBar;
        }

        @Override
        protected String doInBackground(String... sUrl) {
            publishProgress(context.getString(R.string.load_saved_quizzes));

            String[] filenames = IO_Utils.getFileList(IO_Utils.DATA_FOLDER, "quiz", context);
            if (filenames.length == 0) {
                // TODO no quizzes saved. Do an update.
                return null;
            }
            for (String filename : filenames) {
                try {
                    Quiz quiz = QuizCreator.createFromXMLString(filename, context);
                    if (quiz != null) {
                        publishProgress("Loaded " + quiz.getQuizTitle());
                    }
                } catch (Exception e) {
                    publishProgress("Failed loading " + filename);
                }
                sleep();
            }
            publishProgress(context.getString(R.string.load_complete));
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
            progressBar.setProgress(progressBar.getMax());
            sleep();
            showMenu();
        }
    }
}
