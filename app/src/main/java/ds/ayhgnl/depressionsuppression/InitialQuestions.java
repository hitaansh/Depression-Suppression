package ds.ayhgnl.depressionsuppression;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.content.SharedPreferences;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class InitialQuestions extends AppCompatActivity {
    boolean clicked = false;
    SharedPreferences pref;
    Button sub;
    EditText contact1, contact2, contact3, name, age , gender, activity1, activity2, activity3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial_questions);
        pref = this.getSharedPreferences("com.answer.storage", Context.MODE_PRIVATE);
        sub = findViewById(R.id.submit);
        contact1 = findViewById(R.id.emgC1);
        contact2 = findViewById(R.id.emgC2);
        contact3 = findViewById(R.id.emgC3);
        name = findViewById(R.id.name);
        age = findViewById(R.id.age);
        gender = findViewById(R.id.gender);
        activity1 = findViewById(R.id.act1);
        activity2 = findViewById(R.id.act2);
        activity3 = findViewById(R.id.act3);
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicked = true;
                SharedPreferences.Editor preferencesEditor = pref.edit();
                preferencesEditor.putBoolean("InitialQuestionsCompleted", clicked );
                preferencesEditor.apply();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

    }
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor preferencesEditor = pref.edit();
        preferencesEditor.putBoolean("InitialQuestionsCompleted", clicked );
        preferencesEditor.apply();
    }
}
