package fr.esigelec.quiz;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class OptionActivity extends AppCompatActivity {

    public static final String PREFS_NAME = "Option";
    private EditText adresseDuServeur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);
        adresseDuServeur = (EditText) findViewById(R.id.opt_adresse);
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        adresseDuServeur.setText(settings.getString("serverAdress", "srvinfodev.esigelec.fr:8080/quiz"));

    }

    public void saveAndReturn(View v){
        // We get the server address put by the user
        String serverAdress = adresseDuServeur.getText().toString();

        //we save the adress in a SharedPreferences
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("serverAdress", serverAdress);
        editor.commit();
        finish();
    }
}
