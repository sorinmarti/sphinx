package com.sorinmarti.sphinx;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by SOMA on 27.07.2018.
 */
class MenuActions {

    public static void quitGame(final Activity activity) {
        new AlertDialog.Builder(activity)
                .setTitle(R.string.dialog_quit_title)
                .setMessage(R.string.dialog_quit_message)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        activity.finishAffinity();
                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        activity.startActivity(intent);
                    }})
                .setNegativeButton(android.R.string.no, null).show();
    }

    public static void goToMenu(final Activity activity) {
        Intent intent = new Intent(activity, MenuActivity.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    public static void goToUserProfile(final Activity activity) {
        Intent intent = new Intent(activity, UserProfileActivity.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    public static void goToStatistics(final Activity activity) {
        Intent intent = new Intent(activity, StatisticsActivity.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    public static void goToQuizSelection(final Activity activity) {
        Intent intent = new Intent(activity, QuizSelectionActivity.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    public static void commitTitleTransaction(FragmentActivity activity, int fragmentId, String title, String subtitle) {
        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
        transaction.replace(fragmentId, TitleFragment.newInstance(title, subtitle));
        transaction.commit();
    }

    public static void commitMenuTransaction(FragmentActivity activity, int fragmentId, boolean showBack, boolean showExit, boolean showMenu) {
        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
        transaction.replace(fragmentId, MenuFragment.newInstance(showBack, showExit, showMenu));
        transaction.commit();
    }
}
