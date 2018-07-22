package devdem.time;
/*
ох,блять, это пиздец какой мудрённый код и длинный.
я ебал его исправлять каждый раз, сука
*/
// импорты

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
import static devdem.time.MainActivity.APP_PREFERENCES_PERFORMANCE;

// ну, тут уже по-круче)

public class FirstTimeActivity extends AppCompatActivity {
    private Animation anim = null; // АНИМАЦИЯ
    private Animation anim2 = null; // АНИМАЦИЯ
    public static final String APP_PREFERENCES_FIRSTTIME = "firsttime"; // МОЙ ПЕРВЫЙ РАЗ.......
    public static final String APP_PREFERENCES = "names"; // настроекчки
    public static final String APP_PREFERENCES_COUNTER = "text1"; // заголовок
    public static final String APP_PREFERENCES_COUNTER2 = "text2"; // описание
    public static final String APP_PREFERENCES_STYLE = "style"; // СТИЛЬНО, МОДНО, СОВРЕМЕННО
    private SharedPreferences mNames; // настроечки
    private final static String FILENAME = "sample.txt"; // название файлика


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_time); // топ контент русского ютуба
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); // опять блокируем ориентацию...
        final TextView text = findViewById(R.id.textView312); // текст
        final TextView text2 = findViewById(R.id.textView2); // текст
        final TextView text3 = findViewById(R.id.textView3);// текст
        final ProgressBar pbar = findViewById(R.id.progressBar); // кружочек :3
        final ImageView frtsbg=findViewById(R.id.frstbgs); // фон
        // АНИМАЦИЯ, ВАУ
        anim = AnimationUtils.loadAnimation(this, R.anim.text_anim);
        text.startAnimation(anim);
        anim2=AnimationUtils.loadAnimation(this, R.anim.text_anim2);
        text2.setVisibility(View.INVISIBLE);
        text3.setVisibility(View.INVISIBLE);
        pbar.setVisibility(View.INVISIBLE);
        frtsbg.setVisibility(View.INVISIBLE);
        // генерируем фоновый цвет :)
        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(129), rnd.nextInt(129), rnd.nextInt(129));
        frtsbg.setColorFilter(color);

        mNames = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE); // настроечки
        // анимация..
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
                                                // ура, сохраняем настройки :)
                                                Context context = getApplicationContext(); // контекст приложения
                                                Resources res = context.getResources(); // получаем ресурсы
                                                // сохраняем эти ёбанные настройки, сука
                                                SharedPreferences.Editor editor = mNames.edit();
                                                editor.putBoolean(APP_PREFERENCES_FIRSTTIME, false);
                                                editor.putString(APP_PREFERENCES_COUNTER, res.getString(R.string.app_name));
                                                editor.putString(APP_PREFERENCES_COUNTER2, "");
                                                editor.putBoolean(APP_PREFERENCES_AD, false);
                                                editor.putBoolean(APP_PREFERENCES_PERFORMANCE, true);
                                                editor.putInt(APP_PREFERENCES_STYLE, 1);
                                                editor.putBoolean("dialog", true);
                                                editor.putBoolean("advance", false);
                                                editor.apply();
                                                // сохраняем файлик
                                                saveFile(FILENAME);
                                                // идём нахуй, то бишь на главную страницу
                                                Intent intent = new Intent(FirstTimeActivity.this, MainActivity.class);
                                                startActivity(intent);
                                                overridePendingTransition(R.anim.anim_activity_out, R.anim.anim_activity_in); // идём с анимацией
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
                    res.getString(R.string.error) + t.toString(), Toast.LENGTH_LONG).show(); // бля, ошибка :(
        }
    }
}
