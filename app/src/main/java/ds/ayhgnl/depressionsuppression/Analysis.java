package ds.ayhgnl.depressionsuppression;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import android.os.CountDownTimer;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

public class Analysis extends AppCompatActivity {
    SharedPreferences pref;
    int maxScore = 60, score, tier1 = 16, tier2 = 24, tier3 = 36, tier4 = 42, tier5 = 48, tier6 = 54, tier7 = 60;
    int interest; int appetite; int sleep; int concentration; int worthlessness; int movement; int fatigue; int suicide; int oldScore;
    TextView scoreBox, appetiteBox, interestBox, sleepBox, concentrationBox, worthlessnessBox, fatigueBox, movementBox, suicideBox, tier, condition, time, act1, act2, act3;
    CountDownTimer timer;
    String base = "You are at";
    String good = "You are in a good place of mind. Continue to stay positive and do enjoyable things!";
    String bad = "You are not in a good place of mind. Try to stay positive and do activites you enjoy. Your emergency contacts have been informed";
    public static final String CHANNEL_1_ID = "channel";
    private NotificationManagerCompat notificationManager;
    private boolean timerRunning;
    private long timeLeftMillis, endTime;

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
        tier = findViewById(R.id.tier);
        condition = findViewById(R.id.condition);
        time = findViewById(R.id.timeLeft);
        act1 = findViewById(R.id.activity1);
        act2 = findViewById(R.id.activity2);
        act3 = findViewById(R.id.activity3);


        pref = this.getSharedPreferences("com.answer.storage", Context.MODE_PRIVATE);
        scoreBox.setText(pref.getInt("Score", 0) + "");
        score = pref.getInt("Score", 0);
        interestBox.setText(pref.getInt("LossOfInterest", 0) + "");
        interest = pref.getInt("LossOfInterest", 0);
        appetiteBox.setText(pref.getInt("Appetite", 0) + "");
        appetite = pref.getInt("Appetite", 0);
        sleepBox.setText(pref.getInt("Sleep", 0) + "");
        sleep = pref.getInt("Sleep", 0);
        concentrationBox.setText(pref.getInt("Concentration", 0) + "");
        concentration = pref.getInt("Concentration", 0);
        worthlessnessBox.setText(pref.getInt("Worthlessness", 0) + "");
        worthlessness = pref.getInt("Worthlessness", 0);
        fatigueBox.setText(pref.getInt("Fatigue", 0) + "");
        fatigue = pref.getInt("Fatigue", 0);
        movementBox.setText(pref.getInt("Movement", 0) + "");
        movement = pref.getInt("Movement", 0);
        suicideBox.setText(pref.getInt("SuicidalIntention", 0) + "");
        suicide = pref.getInt("SuicidalIntention", 0);
        act1.setText(pref.getString("Act1", ""));
        act2.setText(pref.getString("Act2", ""));
        act3.setText(pref.getString("Act3", ""));
        oldScore = pref.getInt("OldScore", -1);
        int [] subscores = {interest, appetite, sleep, concentration, worthlessness, fatigue, movement, suicide};
        String[] categories = {"interest", "appetite", "sleep", "concentration", "worthlessness", "fatigue", "movement", "suicide"};
        //if sadness  or sleep are 8 or greater, it is concerning
        //for everything else, if it is 5 or greater, it is concerning
        ArrayList<String> concerns  = new ArrayList<String>();
        for(int i=0; i<subscores.length; i++){
            if(i==2 && subscores[i]>=8)
                concerns.add("sleep");
            else if(subscores[i]>=5)
                concerns.add(categories[i]);
        }
        if(score < tier1) {
            String toDisplay = base + " Tier 1";
            tier.setText(toDisplay);
            condition.setText(good);
        }
        else if(score < tier2) {
            String toDisplay = base + " Tier 2";
            tier.setText(toDisplay);
            condition.setText(good);
        }
        else {
            if(score < tier3) {
                String toDisplay = base + " Tier 3";
                tier.setText(toDisplay);
                condition.setText(bad);
                condition.setTextColor(-65536); // red color
            }
            else if(score < tier4) {
                String toDisplay = base + " Tier 4";
                tier.setText(toDisplay);
                condition.setText(bad);
                condition.setTextColor(-65536); // red color
            }
            else if(score < tier5) {
                String toDisplay = base + " Tier 5";
                tier.setText(toDisplay);
                condition.setText(bad);
                condition.setTextColor(-65536); // red color
            }
            else if(score < tier6) {
                String toDisplay = base + " Tier 6";
                tier.setText(toDisplay);
                condition.setText(bad);
                condition.setTextColor(-65536); // red color
            }
            else if(score < tier7) {
                String toDisplay = base + " Tier 7";
                tier.setText(toDisplay);
                condition.setText(bad);
                condition.setTextColor(-65536); // red color
            }
            // text(concerns);
        }

        if(oldScore != -1) {
            double change = score - oldScore;
            double s = oldScore;
            if((change/s) > 0.3){
                String drop = "Your score has increased by more than 30% since your last survey. You are entering a dangerous state of mind. Try to stay positive and do activites you enjoy. Your emergency contacts have been informed";
                condition.setText(drop);
                condition.setTextColor(-65536); // red color
                //text(concerns);
            }
        }


        startTimer();

    }

    private void startTimer() {
        //endTime = System.currentTimeMillis() + timeLeftMillis;
        timer = new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timerRunning = true;
                timeLeftMillis = millisUntilFinished;
                long hours = (millisUntilFinished)/(3600000);
                long minutes = (millisUntilFinished)/(60000) - (hours * 60);
                long seconds = (millisUntilFinished)/(1000) - (minutes * 60) - (hours * 3600);
                String hoursLeft = ((int)hours) + "" + ":";
                String minutesLeft = ((int)minutes) + "" + ":";
                String secondsLeft = ((int)seconds) + "";
                time.setText("Time until next survey: " + hoursLeft + minutesLeft + secondsLeft);
            }
            @Override
            public void onFinish() {
                notifySurvey();
                timerRunning = false;
                pref = getSharedPreferences("com.answer.storage", Context.MODE_PRIVATE);
                SharedPreferences.Editor edit = pref.edit();
                edit.putBoolean("quizDone", false);
                edit.putInt("OldScore", score);
                edit.putInt("Score", 0);
                edit.apply();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        };
        timer.start();
        timerRunning = true;
    }

    private void notifySurvey() {
        String notifTitle = "Depression Suppression!";
        String notifDesc = "Come take your weekly survey";

        createNotificationChannels();
        notificationManager = NotificationManagerCompat.from(this);
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_1_ID)
                .setSmallIcon(R.drawable.dslogo)
                .setContentTitle(notifTitle)
                .setContentText(notifDesc)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();
        notificationManager.notify(1, notification);
    }



    private void createNotificationChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel1 = new NotificationChannel(
                    CHANNEL_1_ID,
                    "Depression Suppresion!",
                    NotificationManager.IMPORTANCE_HIGH
            );
            channel1.setDescription("Come take your weekly survey");
            NotificationManager manger = getSystemService(NotificationManager.class);
            manger.createNotificationChannel(channel1);
        }
    }

    private void text(ArrayList<String> concerns) {
        SmsManager smgr = SmsManager.getDefault();
        pref = this.getSharedPreferences("com.answer.storage", Context.MODE_PRIVATE);
        String emg1 = pref.getString("Contact1", "");
        String emg2 = pref.getString("Contact2", "");
        String emg3 = pref.getString("Contact3", "");
        String Message = pref.getString("Name", "") + " " + "is not in a good mental place. Please talk to them.";
        String allConcerns = "";
        for(String i:concerns)
            allConcerns+=(i + ", ");
        allConcerns = allConcerns.substring(0, allConcerns.length()-2);
        System.out.println(allConcerns);
        Message+=(pref.getString("Name", "") + " is showing concerning signs with " + allConcerns);
        if(!emg1.equals("")) {
            smgr.sendTextMessage(emg1, null, Message, null, null);
        }
        if(!emg2.equals("")) {
            smgr.sendTextMessage(emg2, null, Message, null, null);
        }
        if(!emg3.equals("")) {
            smgr.sendTextMessage(emg3, null, Message, null, null);
        }
    }

//    @Override
//    public void onStop(){
//        super.onStop();
//        pref = this.getSharedPreferences("com.answer.storage", Context.MODE_PRIVATE);
//        SharedPreferences.Editor edit = pref.edit();
//        edit.putLong("mLeft", timeLeftMillis);
//        edit.putLong("endTime", endTime);
//        edit.putBoolean("running", timerRunning);
//        edit.apply();
//        if(timer != null) {
//            timer.cancel();
//        }
//    }

//    @Override
//    public void onStart() {
//        super.onStart();
//        pref = this.getSharedPreferences("com.answer.storage", Context.MODE_PRIVATE);
//        timeLeftMillis = pref.getLong("mLeft", 0);
//        timerRunning = pref.getBoolean("running", false);
//
//        if(timerRunning) {
//            endTime = pref.getLong("endTime", 0);
//            timeLeftMillis = endTime - System.currentTimeMillis();
//
//            if(timeLeftMillis < 0) {
//                timeLeftMillis = 0;
//            }
//        }
//    }
}
