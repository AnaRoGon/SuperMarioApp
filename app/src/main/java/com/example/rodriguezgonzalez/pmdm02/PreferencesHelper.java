package com.example.rodriguezgonzalez.pmdm02;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Esta clase nos permite guardar y recuperar las preferencias de idioma
 * y el estado del radioButtom.
 */

public class PreferencesHelper {
    //Variables de clase
    private static final String PREF_LANGUAGE = "language_pref";
    private static final String KEY_LANGUAGE = "language";

    private static final String PREF_RADIO_BUTTON = "radio_button_pref";
    private static final String KEY_RADIO_BUTTON = "radio_button";

    /**
     * Método para guardar el idioma de preferencia.
     * Recibe el contexto de la aplicación y el idioma de preferencia.
     * @param context Contexto de la aplicación.
     * @param language Idioma de preferencia.
     * */
    public static void saveLanguagePreference(Context context, String language) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_LANGUAGE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_LANGUAGE, language);
        editor.apply();

    }

    /**
     * Método para recuperar el idioma de preferencia.
     * @param context Contexto de la aplicación.
     * */
    public static String getLanguagePreference(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_LANGUAGE, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_LANGUAGE, "en");
    }

    /**
     *Método para guardar el estado del radioButton.
     * @param context Contexto de la aplicación.
     * @param radioButtonId Id del radioButton.
     */
    public static void saveRadioButtomState(Context context, int radioButtonId) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_RADIO_BUTTON, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_RADIO_BUTTON, radioButtonId);
        editor.apply();
    }

    /**
     *  Método para recuperar el estado del radioButton.
     * @param context Contexto de la aplicación.
     */
    public static int getRadioButtomState(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_RADIO_BUTTON, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(KEY_RADIO_BUTTON, -1);
    }

}
