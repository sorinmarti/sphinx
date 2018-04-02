package com.sorinmarti.sphinx;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    ProgressBar updateBar;
    Button btnStart, btnUpdate, btnUserProfile;
    TextView txtStart, txtUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        updateBar = (ProgressBar)findViewById(R.id.barMainUpdate);
        btnStart = (Button) findViewById(R.id.btnStart);
        btnUserProfile = (Button) findViewById(R.id.btnUserProfile);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        txtStart = (TextView)findViewById(R.id.txtLoadStatus);
        txtUpdate = (TextView)findViewById(R.id.txtUpdateStatus);

        final LoadTask loadTask = new LoadTask(getBaseContext(),
                txtStart,
                updateBar,
                btnStart,
                btnUpdate,
                btnUserProfile);
        loadTask.execute();

        // TODO Create update task for local files. These files get loaded anyway.
        // TODO The internet update task can remain as is, It would need further options to select new quizzes, maybe.
    }

    public void showMenu(View view) {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    public void showUserProfile(View view) {
        Intent intent = new Intent(this, UserProfileActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    public void updateQuizzes(View view) {
        updateBar.setIndeterminate(true);

        final UpdateTask updateTask = new UpdateTask(getBaseContext(),
                txtUpdate,
                updateBar,
                btnUpdate);
        updateTask.execute();
    }
}
