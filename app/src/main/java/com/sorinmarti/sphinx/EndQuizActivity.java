package com.sorinmarti.sphinx;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.sorinmarti.sphinx.quiz.QuizLibrary;
import com.sorinmarti.sphinx.quiz.QuizStatistics;

public class EndQuizActivity extends AppCompatActivity {

    public static final String QUIZ_STATISTICS = "QUIZ_STATISTICS";
    public static final String USER_STATISTICS = "USER_STATISTICS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_quiz);

        MenuActions.commitTitleTransaction( this, R.id.endquizTitleFragment,"Quiz beendet", "...und so hast du abgeschnitten:" );

        QuizStatistics stats = QuizLibrary.getInstance().getCurrentQuizStatistics();
        ((TextView)findViewById(R.id.txtEndGameStatisrics)).setText(+stats.getNumCorrectAnswers()+" von "+stats.getTotalNumAnswers()+" Fragen richtig beantwortet.");
    }

    public void goBackToMenu(View view) {
        MenuActions.goToMenu( this );
    }
}
