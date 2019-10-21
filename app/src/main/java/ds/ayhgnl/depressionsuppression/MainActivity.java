package ds.ayhgnl.depressionsuppression;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    SharedPreferences pref; // = getSharedPreferences("com.answer.storage", Context.MODE_PRIVATE);
    CheckBox option1; CheckBox option2; CheckBox option3; CheckBox option4; CheckBox option5; TextView question; Button next;
    boolean clicked;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);
        option5 = findViewById(R.id.option5);
        question = findViewById(R.id.question);
        next = findViewById(R.id.submitButton);
        Object[] options = new Object[5];
        options[0] = option1; options[1] = option2; options[2] = option3; options[3] = option4; options[4] = option5;
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
    protected void checkAnswers(Object[] options){

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