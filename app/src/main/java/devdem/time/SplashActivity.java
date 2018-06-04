package devdem.time;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {
    public static final String APP_PREFERENCES = "names"; // название файла с настройками
    public static final String APP_PREFERENCES_FIRSTTIME = "firsttime"; // название переменой первого запуска
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences mNames = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        super.onCreate(savedInstanceState);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        if (!mNames.getBoolean(APP_PREFERENCES_FIRSTTIME, true)) {
            Intent intent = new Intent(this, MainActivity.class); // обозначения активностей
            startActivity(intent); // запустить активность
            overridePendingTransition(R.anim.anim_activity_out, R.anim.anim_activity_in);
            finish(); // закрыть активность
        }
        else {
            Intent intent = new Intent(this, FirstTimeActivity.class); // обозначения активностей
            startActivity(intent);// запустить активность
            overridePendingTransition(R.anim.anim_activity_out, R.anim.anim_activity_in);
            finish(); // закрыть активность
        }
    }
}
