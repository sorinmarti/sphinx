package com.sorinmarti.sphinx.quiz;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by SOMA on 02.06.2018.
 */
public class SettingsManager {

    private SharedPreferences sharedPrefs;

    public SettingsManager( Activity activity) {
        sharedPrefs = activity.getPreferences(Context.MODE_PRIVATE);
    }

    public String getUsername() {
        return sharedPrefs.getString("user_name", "<kein Name>");
    }

    public void setUsername(String username) {
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putString("user_name", username);
        editor.commit();
    }
}
