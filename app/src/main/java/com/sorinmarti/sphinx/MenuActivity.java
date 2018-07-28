package com.sorinmarti.sphinx;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MenuActivity extends AppCompatActivity implements MenuFragment.OnMenuFragmentInteraction {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.menuTitleFragment, TitleFragment.newInstance("Sphinx Menu", "Select an option."));
        transaction.replace(R.id.menuMenuFragment, MenuFragment.newInstance(false, true, false));
        transaction.commit();

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
