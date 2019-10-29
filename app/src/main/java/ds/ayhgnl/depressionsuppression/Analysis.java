package ds.ayhgnl.depressionsuppression;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class Analysis extends AppCompatActivity {
    SharedPreferences pref;
    TextView scoreBox, appetiteBox, interestBox, sleepBox, concentrationBox, worthlessnessBox, fatigueBox, movementBox, suicideBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysis);
        scoreBox = findViewById(R.id.scoreBox);
        interestBox = findViewById(R.id.interestBox);
        appetiteBox = findViewById(R.id.appetiteBox);
        sleepBox = findViewById(R.id.sleepBox);
        concentrationBox = findViewById(R.id.concentrationBox);
        worthlessnessBox = findViewById(R.id.worthBox);
        fatigueBox = findViewById(R.id.fatigueBox);
        movementBox = findViewById(R.id.movementBox);
        suicideBox = findViewById(R.id.suicideBox);

        pref = this.getSharedPreferences("com.answer.storage", Context.MODE_PRIVATE);
        scoreBox.setText(pref.getInt("Score", 0) + "");
        interestBox.setText(pref.getInt("LossOfInterest", 0) + "");
        appetiteBox.setText(pref.getInt("Appetite", 0) + "");
        sleepBox.setText(pref.getInt("Sleep", 0) + "");
        concentrationBox.setText(pref.getInt("Concentration", 0) + "");
        worthlessnessBox.setText(pref.getInt("Worthlessness", 0) + "");
        fatigueBox.setText(pref.getInt("Fatigue", 0) + "");
        movementBox.setText(pref.getInt("Movement", 0) + "");
        suicideBox.setText(pref.getInt("SuicidalIntention", 0) + "");

    }
}
