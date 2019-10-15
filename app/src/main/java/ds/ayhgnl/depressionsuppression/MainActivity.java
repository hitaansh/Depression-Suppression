package ds.ayhgnl.depressionsuppression;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        pref = this.getSharedPreferences("com.answer.storage", Context.MODE_PRIVATE);
        boolean clicked = pref.getBoolean("InitialQuestionsCompleted", false);
        if(clicked) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
        }
        else {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_initial_questions);
        }



    }
}
