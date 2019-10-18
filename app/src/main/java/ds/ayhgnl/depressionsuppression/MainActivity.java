package ds.ayhgnl.depressionsuppression;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    SharedPreferences pref; // = getSharedPreferences("com.answer.storage", Context.MODE_PRIVATE);
    boolean clicked;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pref = this.getSharedPreferences("com.answer.storage", Context.MODE_PRIVATE);

        clicked = pref.getBoolean("InitialQuestionsCompleted", false);
        System.out.println(clicked + "clicked");

        if(clicked) {

            setContentView(R.layout.activity_main);
            SharedPreferences.Editor pfEditor = pref.edit();
            pfEditor.putBoolean("InitialQuestionsCompleted", clicked);
            pfEditor.apply();

        }
        else {
            Intent intent = new Intent(getApplicationContext(), InitialQuestions.class);
            startActivity(intent);
        }



    }
    @Override
    protected void onPause() {
        System.out.println("here");
        super.onPause();
        SharedPreferences.Editor pfEditor = pref.edit();
        pfEditor.putBoolean("InitialQuestionsCompleted", clicked);
        pfEditor.apply();
    }
}