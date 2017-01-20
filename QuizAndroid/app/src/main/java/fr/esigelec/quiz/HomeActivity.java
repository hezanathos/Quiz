package fr.esigelec.quiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    /**
     * Action of the Button, start the quiz
     * @param v
     */
    public void start(View v){
        Intent intent = new Intent(this, QuizActivity.class);
        startActivity(intent);
    }
}
