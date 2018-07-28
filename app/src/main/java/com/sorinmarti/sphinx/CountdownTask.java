package com.sorinmarti.sphinx;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ProgressBar;

/**
 * Created by SOMA on 28.07.2018.
 */
class CountdownTask extends AsyncTask<Integer, Integer, Integer> {

    private final Context context;
    private final ProgressBar progressBar;
    private final int seconds = 5;

    public CountdownTask(Context context, ProgressBar progressBar) {
        this.context = context;
        this.progressBar = progressBar;
    }

    @Override
    protected Integer doInBackground(Integer... integers) {

        for(int i=0;i<(seconds*100);i++) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                return null;
            }
            this.progressBar.setProgress( i );
        }
        return 0;
    }

    @Override
    protected void onPreExecute() {
        this.progressBar.setMax( (seconds*100) );
        this.progressBar.setProgress( 0 );
    }

    @Override
    protected void onProgressUpdate(Integer... values) {

    }
}
