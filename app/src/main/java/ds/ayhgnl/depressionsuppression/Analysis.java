package ds.ayhgnl.depressionsuppression;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.widget.TextView;

public class Analysis extends AppCompatActivity {
    SharedPreferences pref;
    int maxScore = 60, score, tier1 = 16, tier2 = 24, tier3 = 36, tier4 = 42, tier5 = 48, tier6 = 54, tier7 = 60;
    TextView scoreBox, appetiteBox, interestBox, sleepBox, concentrationBox, worthlessnessBox, fatigueBox, movementBox, suicideBox, tier, condition;
    String base = "You are at";
    String good = "You are in a good place of mind. Continue to stay positive and do enjoyable things!";
    String bad = "You are not in a good place of mind. Try to stay positive and do activites you enjoy. You emergency contacts have been informed";
    public static final String CHANNEL_1_ID = "channel";
    private NotificationManagerCompat notificationManager;
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

        pref = this.getSharedPreferences("com.answer.storage", Context.MODE_PRIVATE);
        scoreBox.setText(pref.getInt("Score", 0) + "");
        score = pref.getInt("Score", 0);
        interestBox.setText(pref.getInt("LossOfInterest", 0) + "");
        appetiteBox.setText(pref.getInt("Appetite", 0) + "");
        sleepBox.setText(pref.getInt("Sleep", 0) + "");
        concentrationBox.setText(pref.getInt("Concentration", 0) + "");
        worthlessnessBox.setText(pref.getInt("Worthlessness", 0) + "");
        fatigueBox.setText(pref.getInt("Fatigue", 0) + "");
        movementBox.setText(pref.getInt("Movement", 0) + "");
        suicideBox.setText(pref.getInt("SuicidalIntention", 0) + "");

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
            text();
        }
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
    private void text() {
        SmsManager smgr = SmsManager.getDefault();
        pref = this.getSharedPreferences("com.answer.storage", Context.MODE_PRIVATE);
        String emg1 = pref.getString("Contact1", "");
        String emg2 = pref.getString("Contact2", "");
        String emg3 = pref.getString("Contact3", "");
        String Message = pref.getString("Name", "") + " " + "is not a good mental place. Please talk to them.";
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
}
