package com.sorinmarti.sphinx;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sorinmarti.sphinx.quiz.QuizLibrary;
import com.sorinmarti.sphinx.quiz.QuizStatistics;

public class StatisticsActivity extends AppCompatActivity {

    public static final String QUIZ_STATISTICS = "QUIZ_STATISTICS";
    public static final String USER_STATISTICS = "USER_STATISTICS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        QuizStatistics stats = QuizLibrary.getInstance().getCurrentQuizStatistics();
        ((TextView)findViewById(R.id.txtStatistics)).setText("Dein Resultat: "+stats.getNumCorrectAnswers()+" von "+stats.getTotalNumAnswers()+" Fragen richtig beantwortet.");
    }

    public void goBackToMenu(View view) {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }
}
