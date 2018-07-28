package com.sorinmarti.sphinx;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sorinmarti.sphinx.quiz.QuizLibrary;
import com.sorinmarti.sphinx.quiz.QuizStatistics;

public class StatisticsActivity extends AppCompatActivity implements MenuFragment.OnMenuFragmentInteraction {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.statisticsTitleFragment, TitleFragment.newInstance("Statistik", "Deine Benutzerstatistik"));
        transaction.replace(R.id.statisticsMenuFragment, MenuFragment.newInstance(true, false, true));
        transaction.commit();
    }

    @Override
    public void onSphinxBackPressed() {
        MenuActions.goToUserProfile( this );
    }

    @Override
    public void onSphinxExitPressed() {
        MenuActions.quitGame( this );
    }

    @Override
    public void onSphinxMenuPressed() {
        MenuActions.goToMenu( this );
    }
}
