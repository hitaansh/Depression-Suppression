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
import android.app.PendingIntent;
import android.content.Intent;
import android.app.AlarmManager;
import androidx.appcompat.app.AppCompatActivity;



public class MainActivity extends AppCompatActivity {
    SharedPreferences pref; // = getSharedPreferences("com.answer.storage", Context.MODE_PRIVATE);
    CheckBox option1; CheckBox option2; CheckBox option3; CheckBox option4; CheckBox option5; TextView question; Button next; int questionnum=0;
    int sadness=0; int lossOfInterest = 0; int appetite=0; int sleep = 0; int concentration = 0; int worthlessness = 0; int fatigue=0; int movement = 0; int suicidalIdeation=0;
    boolean clicked, done; int score = 0; Object[] options; String[] questions = {"My appetite was poor.","I could not shake off the blues.","I had trouble keeping my mind on what I was doing."
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
        if(clicked) {


            SharedPreferences.Editor pfEditor = pref.edit();
            pfEditor.putBoolean("InitialQuestionsCompleted", true);
            pfEditor.apply();


        }
        else {
            Intent intent = new Intent(getApplicationContext(), InitialQuestions.class);
            startActivity(intent);
        }

        done = pref.getBoolean("quizDone", false);
        System.out.println(clicked + "clicked");
        next.setEnabled(false);
//        Intent inte = new Intent(MainActivity.this, Receiver.class);
//        PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, REQUEST_CODE, inte, 0);
//        AlarmManager am = (AlarmManager)getSystemService(ALARM_SERVICE);
//        am.setRepeating(am.RTC_WAKEUP, System.currentTimeInMillis(), am.INTERVAL_DAY*7, pendingIntent);
        if(done) {
            Intent res = new Intent(getApplicationContext(), Analysis.class);
            startActivity(res);
        }
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(((CheckBox)options[0]).isChecked()||((CheckBox)options[1]).isChecked()
                        ||((CheckBox)options[2]).isChecked()||((CheckBox)options[3]).isChecked()
                        ||((CheckBox)options[4]).isChecked()){
                    if(questionnum != 20){
                        done = false;
                        for(int i=0; i<options.length; i++){
                            CheckBox check = (CheckBox)options[i];
                            if(check.isChecked()){
                                if(i==4){
                                    score+=3;
                                    if(questionnum==2 || questionnum==4 || questionnum==6)
                                        sadness+=3;
                                    if(questionnum==8 || questionnum==10)
                                        lossOfInterest+=3;
                                    if(questionnum==1 || questionnum==18)
                                        appetite+=3;
                                    if(questionnum==5 || questionnum==11 || questionnum==19)
                                        sleep+=3;
                                    if(questionnum==3 || questionnum==20)
                                        concentration+=3;
                                    if(questionnum==9 || questionnum==17)
                                        worthlessness+=3;
                                    if(questionnum==7 || questionnum==16)
                                        fatigue+=3;
                                    if(questionnum==12 || questionnum==13)
                                        movement+=3;
                                    if(questionnum==14 || questionnum==15)
                                        suicidalIdeation+=3;
                                }
                                else{
                                    score+=i;
                                    if(questionnum==2 || questionnum==4 || questionnum==6)
                                        sadness+=i;
                                    if(questionnum==8 || questionnum==10)
                                        lossOfInterest+=i;
                                    if(questionnum==1 || questionnum==18)
                                        appetite+=i;
                                    if(questionnum==5 || questionnum==11 || questionnum==19)
                                        sleep+=i;
                                    if(questionnum==3 || questionnum==20)
                                        concentration+=i;
                                    if(questionnum==9 || questionnum==17)
                                        worthlessness+=i;
                                    if(questionnum==7 || questionnum==16)
                                        fatigue+=i;
                                    if(questionnum==12 || questionnum==13)
                                        movement+=i;
                                    if(questionnum==14 || questionnum==15)
                                        suicidalIdeation+=i;
                                }
                            }
                            check.setChecked(false);
                        }
                        question.setText(questions[questionnum]);
                        questionnum++;
                    }
                    else {
                        pref = getSharedPreferences("com.answer.storage", Context.MODE_PRIVATE);
                        SharedPreferences.Editor edit = pref.edit();
                        done = true;
                        edit.putBoolean("quizDone", done);
                        edit.putInt("Score", score);
                        edit.putInt("LossOfInterest", lossOfInterest);
                        edit.putInt("Appetite", appetite);
                        edit.putInt("Sleep", sleep);
                        edit.putInt("Concentration", concentration);
                        edit.putInt("Worthlessness", worthlessness);
                        edit.putInt("Fatigue", fatigue);
                        edit.putInt("Movement", movement);
                        edit.putInt("SuicidalIntention", suicidalIdeation);
                        edit.apply();
                        Intent res = new Intent(getApplicationContext(), Analysis.class);
                        startActivity(res);
                        //System.out.println("Change Screens");
                        //transfer screens
                    }
                }
            }
        });







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
        super.onPause();
        pref = this.getSharedPreferences("com.answer.storage", Context.MODE_PRIVATE);
        SharedPreferences.Editor pfEditor = pref.edit();
        pfEditor.putBoolean("InitialQuestionsCompleted", clicked);
        pfEditor.apply();
    }
}