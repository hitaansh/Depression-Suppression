package ds.ayhgnl.depressionsuppression;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.content.SharedPreferences;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

public class InitialQuestions extends AppCompatActivity {
    boolean clicked = false;
    SharedPreferences pref;
    Button sub;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial_questions);
        pref = this.getSharedPreferences("com.answer.storage", Context.MODE_PRIVATE);
        sub = findViewById(R.id.submit);
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
