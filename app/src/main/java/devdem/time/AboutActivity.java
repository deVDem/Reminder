package devdem.time;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;


public class AboutActivity extends AppCompatActivity {
    public static final String APP_PREFERENCES_PERFORMANCE = "graphics";
    public static final String APP_PREFERENCES = "names";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        SharedPreferences mNames = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
       if (mNames.getBoolean(APP_PREFERENCES_PERFORMANCE, false)) {
            ImageView sun = findViewById(R.id.sun);
            TextView about1 = findViewById(R.id.textView5);
            ScrollView about2 = findViewById(R.id.scrollView2);
            ImageView about3 = findViewById(R.id.sky);
            final ImageView about4 = findViewById(R.id.grass);
            final Animation sunRiseAnimation = AnimationUtils.loadAnimation(this, R.anim.sun_anim);
            sun.setAnimation(sunRiseAnimation);
            about1.setAnimation(sunRiseAnimation);
            Animation anim = AnimationUtils.loadAnimation(this, R.anim.sun_anim2);
            about4.setAnimation(anim);

            about2.setAnimation(sunRiseAnimation);
            about3.setAnimation(sunRiseAnimation);
        }



    }
}
