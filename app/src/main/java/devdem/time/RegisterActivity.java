package devdem.time;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import static devdem.time.MainActivity.APP_PREFERENCES;
import static devdem.time.MainActivity.APP_PREFERENCES_STYLE;

public class RegisterActivity extends AppCompatActivity {
    private static final String APP_PREFERENCES_PERFORMANCE = "graphics";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences mNames;
        mNames = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        if (mNames.getInt(APP_PREFERENCES_STYLE, 1)==1) {
            setTheme(R.style.AppTheme);
        } else if (mNames.getInt(APP_PREFERENCES_STYLE, 1)==2) {
            setTheme(R.style.AppThemeLight);
        } else if (mNames.getInt(APP_PREFERENCES_STYLE, 1)==3) {
            setTheme(R.style.AppThemeLightOrange);
        } else if (mNames.getInt(APP_PREFERENCES_STYLE, 1)==4) {
            setTheme(R.style.AppThemeLightGreen);
        } else if (mNames.getInt(APP_PREFERENCES_STYLE, 1)==5) {
            setTheme(R.style.AppThemeLightBlue);
        } else if (mNames.getInt(APP_PREFERENCES_STYLE, 1)==6) {
            setTheme(R.style.AppThemeLightPink);
        } else if (mNames.getInt(APP_PREFERENCES_STYLE, 1)==7) {
            setTheme(R.style.AppThemeDarkGreen);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        EditText etPs = findViewById(R.id.etPassword);
        EditText etCps = findViewById(R.id.etConfimPass);
        CheckBox spam = findViewById(R.id.chbEmail);
        Button loginbtn = findViewById(R.id.button4);

        if (mNames.getBoolean(APP_PREFERENCES_PERFORMANCE, false)) {
            Animation anim = AnimationUtils.loadAnimation(this, R.anim.anim_loginreg);
            etPs.setAnimation(anim);
            etPs.animate();
            loginbtn.setAnimation(anim);
            loginbtn.animate();
            etCps.setAnimation(anim);
            etCps.animate();
            spam.setAnimation(anim);
            spam.animate();
        }
    }
}
