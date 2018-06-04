package devdem.time;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Random;

import static devdem.time.MainActivity.APP_PREFERENCES_AD;

public class FirstTimeActivity extends AppCompatActivity {
    private Animation anim = null;
    private Animation anim2 = null;
    public static final String APP_PREFERENCES_FIRSTTIME = "firsttime";
    public static final String APP_PREFERENCES = "names";
    public static final String APP_PREFERENCES_COUNTER = "text1";
    public static final String APP_PREFERENCES_COUNTER2 = "text2";
    public static final String APP_PREFERENCES_STYLE = "style";
    private SharedPreferences mNames;
    private final static String FILENAME = "sample.txt"; // имя файла

    private static final int MY_PERMISSIONS_REQUEST = 23401;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_time);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        final TextView text = findViewById(R.id.textView312);
        final TextView text2 = findViewById(R.id.textView2);
        final TextView text3 = findViewById(R.id.textView3);
        final ProgressBar pbar = findViewById(R.id.progressBar);
        final ImageView frtsbg=findViewById(R.id.frstbgs);
        anim = AnimationUtils.loadAnimation(this, R.anim.text_anim);
        text.startAnimation(anim);
        anim2=AnimationUtils.loadAnimation(this, R.anim.text_anim2);
        text2.setVisibility(View.INVISIBLE);
        text3.setVisibility(View.INVISIBLE);
        pbar.setVisibility(View.INVISIBLE);
        frtsbg.setVisibility(View.INVISIBLE);
        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(129), rnd.nextInt(129), rnd.nextInt(129));
        frtsbg.setColorFilter(color);
        mNames = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        new CountDownTimer(4000, 5000) {
            public void onTick(long millisUntilFinished) {

            }
            public void onFinish() {
                text.startAnimation(anim2);
                new CountDownTimer(2500, 1000) {
                    public void onTick(long millisUntilFinished) {

                    }
                    public void onFinish() {
                        text.setAlpha(0);
                        new CountDownTimer(2500, 1000) {
                            public void onTick(long millisUntilFinished) {

                            }
                            public void onFinish() {
                                text2.setVisibility(View.VISIBLE);
                                pbar.setVisibility(View.VISIBLE);
                                frtsbg.setVisibility(View.VISIBLE);
                                frtsbg.setAnimation(anim);
                                text2.startAnimation(anim);
                                pbar.setAnimation(anim);
                                // разрешения
                                new CountDownTimer(5000, 1000) {
                                    public void onTick(long millisUntilFinished) {
                                    }

                                    public void onFinish() {
                                        text2.setVisibility(View.INVISIBLE);
                                        pbar.setVisibility(View.INVISIBLE);
                                        text3.setVisibility(View.VISIBLE);
                                        text3.setAnimation(anim);
                                        new CountDownTimer(2500, 2500) {
                                            public void onTick(long millisUntilFinished) {
                                            }

                                            public void onFinish() {
                                                Context context = getApplicationContext();
                                                Resources res = context.getResources();
                                                SharedPreferences.Editor editor = mNames.edit();
                                                editor.putBoolean(APP_PREFERENCES_FIRSTTIME, false);
                                                editor.putString(APP_PREFERENCES_COUNTER, res.getString(R.string.app_name));
                                                editor.putString(APP_PREFERENCES_COUNTER2, "");
                                                editor.putBoolean(APP_PREFERENCES_AD, false);
                                                editor.putInt(APP_PREFERENCES_STYLE, 1);
                                                editor.putBoolean("dialog", true);
                                                editor.putBoolean("advance", false);
                                                editor.apply();
                                                saveFile(FILENAME);
                                                Intent intent = new Intent(FirstTimeActivity.this, MainActivity.class);
                                                startActivity(intent);
                                                overridePendingTransition(R.anim.anim_activity_out, R.anim.anim_activity_in);
                                                finish();
                                            }
                                        }.start();
                                    }
                                }.start();
                            }
                                }.start();

                            }
                        }.start();
                    }
                }.start();
            }
    private void saveFile(String fileName) {
        Context context = getApplicationContext();
        Resources res = context.getResources();
        try {
            OutputStream outputStream = openFileOutput(fileName, 0);
            OutputStreamWriter osw = new OutputStreamWriter(outputStream);
            osw.write("Hello world :)");
            osw.close();
        } catch (Throwable t) {
            Toast.makeText(getApplicationContext(),
                    res.getString(R.string.error) + t.toString(), Toast.LENGTH_LONG).show();
        }
    }
}
