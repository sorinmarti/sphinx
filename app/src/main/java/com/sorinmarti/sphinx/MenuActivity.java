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
        Intent intent = new Intent(this, QuizSelectionActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    public void continueQuiz(View view) {
        Intent intent = new Intent(this, QuizSelectionActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    public void showUserProfile(View view) {
        Intent intent = new Intent(this, UserProfileActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    public void showStatistics(View view) {
        Intent intent = new Intent(this, StatisticsActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    public void quitGame(View view) {
        new AlertDialog.Builder(this)
                .setTitle(R.string.dialog_quit_title)
                .setMessage(R.string.dialog_quit_message)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        finishAffinity();
                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }})
                .setNegativeButton(android.R.string.no, null).show();
    }

    @Override
    public void onSphinxBackPressed() {

    }

    @Override
    public void onSphinxExitPressed() {
        MenuActions.quitGame( this);
    }

    @Override
    public void onSphinxMenuPressed() {

    }
}
