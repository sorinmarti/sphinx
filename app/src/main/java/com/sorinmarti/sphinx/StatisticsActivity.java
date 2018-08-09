package com.sorinmarti.sphinx;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class StatisticsActivity extends AppCompatActivity implements MenuFragment.OnMenuFragmentInteraction {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        MenuActions.commitTitleTransaction( this, R.id.statisticsTitleFragment, "Statistik", "Deine Benutzerstatistik" );
        MenuActions.commitMenuTransaction(  this, R.id.statisticsMenuFragment, true, false, true );
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
