package devdem.time;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import static devdem.time.MainActivity.APP_PREFERENCES;
import static devdem.time.MainActivity.APP_PREFERENCES_STYLE;

public class LoginActivity extends AppCompatActivity {
    private static final String APP_PREFERENCES_PERFORMANCE = "graphics";

    TextView registerbtn;
    ImageView logo;
    EditText etUn;
    EditText etPs;
    Button loginbtn;
    TextView tx18;
    TextView signintx;

    private void nextActivity(int activ, boolean finishs) {
        Intent intent = new Intent(this, MainActivity.class);
        if (activ == 1) intent = new Intent(this, MainActivity.class);
        if (activ == 2) intent = new Intent(this, RegisterActivity.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            startActivity(intent);
            overridePendingTransition(R.anim.anim_activity_out, R.anim.anim_activity_in);
        } else startActivity(intent);
        if (finishs) finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences mNames;
        mNames = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        if (mNames.getInt(APP_PREFERENCES_STYLE, 1) == 1) {
            setTheme(R.style.AppTheme);
        } else if (mNames.getInt(APP_PREFERENCES_STYLE, 1) == 2) {
            setTheme(R.style.AppThemeLight);
        } else if (mNames.getInt(APP_PREFERENCES_STYLE, 1) == 3) {
            setTheme(R.style.AppThemeLightOrange);
        } else if (mNames.getInt(APP_PREFERENCES_STYLE, 1) == 4) {
            setTheme(R.style.AppThemeLightGreen);
        } else if (mNames.getInt(APP_PREFERENCES_STYLE, 1) == 5) {
            setTheme(R.style.AppThemeLightBlue);
        } else if (mNames.getInt(APP_PREFERENCES_STYLE, 1) == 6) {
            setTheme(R.style.AppThemeLightPink);
        } else if (mNames.getInt(APP_PREFERENCES_STYLE, 1) == 7) {
            setTheme(R.style.AppThemeDarkGreen);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        TextView registerbtn = findViewById(R.id.textView12);
        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextActivity(2, true);
            }
        });
        logo = findViewById(R.id.imageView);
        etUn = findViewById(R.id.etUsername);
        etPs = findViewById(R.id.etPassword);
        loginbtn = findViewById(R.id.button4);
        tx18 = findViewById(R.id.textView18);
        signintx = findViewById(R.id.etSignin);
        if (mNames.getBoolean(APP_PREFERENCES_PERFORMANCE, false)) {
            Animation anim = AnimationUtils.loadAnimation(this, R.anim.anim_loginreg);
            logo.setAnimation(anim);
            logo.animate();
            etUn.setAnimation(anim);
            etUn.animate();
            etPs.setAnimation(anim);
            etPs.animate();
            loginbtn.setAnimation(anim);
            loginbtn.animate();
            tx18.setAnimation(anim);
            tx18.animate();
            registerbtn.setAnimation(anim);
            registerbtn.animate();
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        nextActivity(1, true);
    }

    public void LoginClick(final View view) {
        ImageView oval = findViewById(R.id.imageView2);
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.oval_in);
        oval.setVisibility(View.VISIBLE);
        oval.setAnimation(anim);
        oval.animate();
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                loginbtn.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                etUn.setVisibility(View.INVISIBLE);
                etPs.setVisibility(View.INVISIBLE);
                tx18.setVisibility(View.INVISIBLE);
                signintx.setVisibility(View.VISIBLE);
                Animation anim2 = AnimationUtils.loadAnimation(LoginActivity.this, R.anim.anim_loginreg);
                signintx.setAnimation(anim2);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

}

