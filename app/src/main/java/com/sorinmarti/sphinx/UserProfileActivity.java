package com.sorinmarti.sphinx;

import android.content.DialogInterface;
import android.content.Intent;
import android.app.AlertDialog;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.sorinmarti.sphinx.quiz.SettingsManager;

public class UserProfileActivity extends AppCompatActivity implements MenuFragment.OnMenuFragmentInteraction {

    SettingsManager manager;
    TextView usernameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.userprofileTitleFragment, TitleFragment.newInstance("Spielerprofil", "Verwalte dein Profil."));
        transaction.replace(R.id.userprofileMenuFragment, MenuFragment.newInstance(true, false, false));
        transaction.commit();

        manager = new SettingsManager( UserProfileActivity.this );
        usernameView = (TextView) findViewById(R.id.txtUsername);
        usernameView.setText( manager.getUsername() );
    }

    public void showStatistics(View view) {
        MenuActions.goToStatistics( this );
    }

    public void changeUsername(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter your user name");
        builder.setMessage("(no special chars)");

        final EditText editText = new EditText(this);
        editText.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView( editText );

        // Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String newUsername = editText.getText().toString();
                // TODO Save & Display new Username
                manager.setUsername( newUsername );
                usernameView.setText( newUsername );
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    public void deleteStatistics(View view) {
        // Confirm dialog
    }

    @Override
    public void onSphinxBackPressed() {
        MenuActions.goToMenu( this );
    }

    @Override
    // The button is not shown and this method never used.
    public void onSphinxExitPressed() {
        MenuActions.quitGame( this );
    }

    @Override
    // The button is not shown and this method never used.
    public void onSphinxMenuPressed() {
        MenuActions.goToMenu( this );
    }
}
