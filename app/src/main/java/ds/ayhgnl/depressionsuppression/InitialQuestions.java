package ds.ayhgnl.depressionsuppression;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.content.SharedPreferences;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InitialQuestions extends AppCompatActivity {
    boolean clicked = false;
    SharedPreferences pref;
    Button sub;
    EditText contact1, contact2, contact3, name, age , gender, activity1, activity2, activity3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("on create iq");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial_questions);
        pref = getSharedPreferences("com.answer.storage", Context.MODE_PRIVATE);
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
                if(!(contact1.getText().toString().equals("")||activity1.getText().toString().equals("")
                        ||activity2.getText().toString().equals("")||activity3.getText().toString().equals("")
                        ||age.getText().toString().equals("")||name.getText().toString().equals("")
                        ||gender.getText().toString().equals(""))){
                    clicked = true;
                    SharedPreferences.Editor preferencesEditor = pref.edit();
                    preferencesEditor.putBoolean("InitialQuestionsCompleted", clicked );
                    preferencesEditor.putString("Name", name.getText().toString());
                    preferencesEditor.putString("Age", age.getText().toString());
                    preferencesEditor.putString("Gender", gender.getText().toString());
                    preferencesEditor.putString("Contact1", contact1.getText().toString());
                    if(!contact2.getText().toString().equals(""))
                        preferencesEditor.putString("Contact2", contact2.getText().toString());
                    if(!contact3.getText().toString().equals(""))
                        preferencesEditor.putString("Contact3", contact3.getText().toString());
                    preferencesEditor.putString("Act1", activity1.getText().toString());
                    preferencesEditor.putString("Act2", activity2.getText().toString());
                    preferencesEditor.putString("Act3", activity3.getText().toString());
                    preferencesEditor.apply();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
                else {
                    Context context = getApplicationContext();
                    CharSequence text = "Please Fill in All Required Fields";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
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
