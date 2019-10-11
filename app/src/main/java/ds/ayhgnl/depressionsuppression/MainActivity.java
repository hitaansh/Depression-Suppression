package ds.ayhgnl.depressionsuppression;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pref = this.getSharedPreferences("com.answer.storage", Context.MODE_PRIVATE);
        String intialQuestionsKey = "com.answer.storage.init";
    }
}
