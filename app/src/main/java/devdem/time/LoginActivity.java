package devdem.time;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import static devdem.time.MainActivity.APP_PREFERENCES;
import static devdem.time.MainActivity.APP_PREFERENCES_STYLE;

public class LoginActivity extends AppCompatActivity {
    private static final String APP_PREFERENCES_PERFORMANCE = "graphics";
    public static final String APP_PREFERENCES_LOGIN = "login";
    public static final String APP_PREFERENCES_PASSWORD = "password";
    public static final String APP_PREFERENCES_ACCOUNT = "account";
    SharedPreferences mNames;

    TextView registerbtn;
    ImageView logo;
    EditText etUn;
    EditText etPs;
    Button loginbtn;
    TextView tx18;
    TextView signintx;

    private void nextActivity(int activ) {
        Intent intent = new Intent(this, MainActivity.class);
        if (activ == 1) intent = new Intent(this, MainActivity.class);
        else if (activ == 2) intent = new Intent(this, RegisterActivity.class);
        else if (activ == 3) intent = new Intent(this, LoginActivity.class);
        else if (activ == 4) intent = new Intent(this, UserActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.anim_activity_out, R.anim.anim_activity_in);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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

        registerbtn = findViewById(R.id.textView12l);
        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextActivity(2);
            }
        });
        logo = findViewById(R.id.imageViewl);
        etUn = findViewById(R.id.etUsernamel);
        etPs = findViewById(R.id.etPasswordl);
        loginbtn = findViewById(R.id.button4l);
        tx18 = findViewById(R.id.textView18l);
        signintx = findViewById(R.id.etSigninl);
        if (mNames.getBoolean(APP_PREFERENCES_PERFORMANCE, true)) {
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
        registerbtn.setVisibility(View.VISIBLE);
        loginbtn.setVisibility(View.VISIBLE);
        etUn.setVisibility(View.VISIBLE);
        etPs.setVisibility(View.VISIBLE);
        tx18.setVisibility(View.VISIBLE);
        signintx.setVisibility(View.GONE);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        nextActivity(1);
    }

    public void LoginClick(final View view) {
        final ImageView oval = findViewById(R.id.imageView2l);
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.oval_in);
        oval.setVisibility(View.VISIBLE);
        oval.setAnimation(anim);
        oval.animate();
        registerbtn.setVisibility(View.GONE);
        view.setVisibility(View.GONE);
        view.setClickable(false);
        etUn.setVisibility(View.GONE);
        etPs.setVisibility(View.GONE);
        tx18.setVisibility(View.GONE);
        signintx.setVisibility(View.VISIBLE);
        Animation anim2 = AnimationUtils.loadAnimation(LoginActivity.this, R.anim.anim_loginreg);
        signintx.setAnimation(anim2);
        final String username = etUn.getText().toString();
        final String password = etPs.getText().toString();
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");
                    if (success) {
                        String name = jsonResponse.getString("name");
                        Toast info = Toast.makeText(LoginActivity.this, "Привет, "+name+"!", Toast.LENGTH_LONG);
                        info.show();
                        SharedPreferences.Editor editor = mNames.edit();
                        editor.putString(APP_PREFERENCES_LOGIN, username);
                        editor.putString(APP_PREFERENCES_PASSWORD, password);
                        editor.putBoolean(APP_PREFERENCES_ACCOUNT, true);
                        editor.apply();
                        nextActivity(4);
                    }else{
                        Toast tss = Toast.makeText(LoginActivity.this, R.string.notpas, Toast.LENGTH_LONG);
                        tss.show();
                        nextActivity(3);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        };

        LoginRequest loginRequest = new LoginRequest(username, password, responseListener);
        RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
        queue.add(loginRequest);


    }

}

