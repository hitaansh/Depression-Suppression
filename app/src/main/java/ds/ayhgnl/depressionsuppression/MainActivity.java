package ds.ayhgnl.depressionsuppression;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;



public class MainActivity extends AppCompatActivity {
    SharedPreferences pref; // = getSharedPreferences("com.answer.storage", Context.MODE_PRIVATE);
    CheckBox option1; CheckBox option2; CheckBox option3; CheckBox option4; CheckBox option5; TextView question; Button next; int questionnum=0;
    boolean clicked; int score = 0; Object[] options; String[] questions = {"My appetite was poor.","I could not shake off the blues.","I had trouble keeping my mind on what I was doing."
    ,"I felt depressed."
    ,"My sleep was restless."
    ,"I felt sad."
    , "I could not get going."
    , "Nothing made me happy."
    , "I felt like a bad person."
    ,"I lost interest in my usual activities."
    ,"I slept much more than usual."
    ,"I felt like I was moving too slowly."
    ,"I felt fidgety."
    ,"I wished I were dead."
    ,"I wanted to hurt yourself."
    ,"I was tired all the time."
    ,"I did not like myself."
    ,"I lost a lot of weight without trying to."
    ,"I had a lot of trouble getting to sleep."
    ,"I could not focus on the important things."};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        option1 = (CheckBox)findViewById(R.id.option1);
        option2 = (CheckBox)findViewById(R.id.option2);
        option3 = (CheckBox)findViewById(R.id.option3);
        option4 = (CheckBox)findViewById(R.id.option4);
        option5 = (CheckBox)findViewById(R.id.option5);
        question = findViewById(R.id.question);
        next = findViewById(R.id.submitButton);
        options = new Object[5];
        options[0] = option1; options[1] = option2; options[2] = option3; options[3] = option4; options[4] = option5;

        pref = this.getSharedPreferences("com.answer.storage", Context.MODE_PRIVATE);
        questionnum=1;
        clicked = pref.getBoolean("InitialQuestionsCompleted", false);
        System.out.println(clicked + "clicked");
        next.setEnabled(false);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i=0; i<options.length; i++){
                    CheckBox check = (CheckBox)options[i];
                    if(check.isChecked()){
                        if(i==4){
                            score+=3;
                        }
                        else{
                            score+=i;
                        }
                    }
                    check.setChecked(false);
                }
                question.setText(questions[questionnum]);
                questionnum++;
                if(questionnum==20){
                    questionnum=0;
                }
            }
        });


        if(clicked) {


            SharedPreferences.Editor pfEditor = pref.edit();
            pfEditor.putBoolean("InitialQuestionsCompleted", clicked);
            pfEditor.apply();


        }
        else {
            Intent intent = new Intent(getApplicationContext(), InitialQuestions.class);
            startActivity(intent);
        }




    }
    public void checked(View view){
        next.setEnabled(true);
        CheckBox checkBox = (CheckBox)view;
        for(int i=0; i<options.length; i++){
            CheckBox c = (CheckBox)options[i];
            if(c!=checkBox){
                c.setChecked(false);
            }
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