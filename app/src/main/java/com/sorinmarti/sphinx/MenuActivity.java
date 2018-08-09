package com.sorinmarti.sphinx;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MenuActivity extends AppCompatActivity implements MenuFragment.OnMenuFragmentInteraction {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        MenuActions.commitTitleTransaction( this, R.id.menuTitleFragment, "Sphinx Menu", "Select an option." );
        MenuActions.commitMenuTransaction(  this, R.id.menuMenuFragment, false, true, false );

        findViewById(R.id.btnContinueQuiz).setEnabled(false);
        //findViewById(R.id.btnShowStatistics).setEnabled(false);
    }

    public void showQuizSelection(View view) {
        MenuActions.goToQuizSelection( this );
    }

    public void continueQuiz(View view) {
        // TODO
    }

    public void showUserProfile(View view) {
        MenuActions.goToUserProfile( this );
    }

    public void showStatistics(View view) {
        MenuActions.goToStatistics( this );
    }

    @Override
    public void onSphinxBackPressed() {
        // never used
    }

    @Override
    public void onSphinxExitPressed() {
        MenuActions.quitGame( this);
    }

    @Override
    public void onSphinxMenuPressed() {
        // never used
    }
}
