package devdem.time;
// импорты
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
    public static final String APP_PREFERENCES_PERFORMANCE = "graphics"; // настройки графики
    public static final String APP_PREFERENCES = "names"; // сами настройки

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about); // топ контент снова
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);  // блокируем ориентацию, мы тут не на пидоров пришли смотреть
        SharedPreferences mNames = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE); // настроечки
       if (mNames.getBoolean(APP_PREFERENCES_PERFORMANCE, true)) { // ебать, графика
            ImageView sun = findViewById(R.id.sun); // СОЛНЦЕ
            TextView about1 = findViewById(R.id.textView5); // ТЕКСТ (какой-то, не помню ,хз ваще)
            ScrollView about2 = findViewById(R.id.scrollView2); // СКРОЛЛ
            ImageView about3 = findViewById(R.id.sky); // НЕБО!
            final ImageView about4 = findViewById(R.id.grass); // ТРЫН-ТРАВА
            final Animation sunRiseAnimation = AnimationUtils.loadAnimation(this, R.anim.sun_anim); // анимация "солнца"
           // ставим анимации
            sun.setAnimation(sunRiseAnimation);
            about1.setAnimation(sunRiseAnimation);
            Animation anim = AnimationUtils.loadAnimation(this, R.anim.sun_anim2);
            about4.setAnimation(anim);
            about2.setAnimation(sunRiseAnimation);
            about3.setAnimation(sunRiseAnimation);
        }



    }
}
