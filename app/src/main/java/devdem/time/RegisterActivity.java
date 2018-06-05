package devdem.time;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

import static devdem.time.MainActivity.APP_PREFERENCES;
import static devdem.time.MainActivity.APP_PREFERENCES_COUNTER;
import static devdem.time.MainActivity.APP_PREFERENCES_COUNTER2;
import static devdem.time.MainActivity.APP_PREFERENCES_STYLE;

public class RegisterActivity extends AppCompatActivity {
    private static final String APP_PREFERENCES_PERFORMANCE = "graphics";
    private SharedPreferences mNames;

    private void nextActivity(int activ) {
        Intent intent = new Intent(this, MainActivity.class);
        if (activ == 1) intent = new Intent(this, LoginActivity.class);
        else if (activ == 2) intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.anim_activity_out, R.anim.anim_activity_in);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
        EditText etPs = findViewById(R.id.etPasswordr);
        EditText etCps = findViewById(R.id.etConfimPassr);
        EditText etEmail = findViewById(R.id.etEmail);
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
            etEmail.setAnimation(anim);
            etEmail.animate();
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        nextActivity(1);
    }
    public void RegisterClick(final View view) {
        EditText etPs = findViewById(R.id.etPasswordr);
        EditText etCps = findViewById(R.id.etConfimPassr);
        final String password = etPs.getText().toString();
        final String cpassword = etCps.getText().toString();
        if(!Objects.equals(password, cpassword)) {
            Toast toast = Toast.makeText(this, R.string.psnot, Toast.LENGTH_SHORT);
            toast.show();
        }
        else {
            final ImageView oval = findViewById(R.id.imageView3);
            final Animation anim = AnimationUtils.loadAnimation(this, R.anim.oval_in);
            oval.setVisibility(View.VISIBLE);
            oval.setAnimation(anim);
            oval.animate();
            view.setClickable(false);
            final TextView signuptx = findViewById(R.id.etSignup);
            signuptx.setVisibility(View.VISIBLE);
            Animation anim2 = AnimationUtils.loadAnimation(RegisterActivity.this, R.anim.anim_loginreg);
            signuptx.setAnimation(anim2);
            EditText etUs = findViewById(R.id.etUsername);
            EditText etName = findViewById(R.id.etName);
            CheckBox spamc = findViewById(R.id.chbEmail);
            EditText etEmail = findViewById(R.id.etEmail);
            String spam = "0";
            if (spamc.isChecked()) spam = "1";
            final String name = etName.getText().toString();
            final String login = etUs.getText().toString();
            final String email = etEmail.getText().toString();
            String zagolovok = "";
            String opisanie = "";
            if (mNames.contains(APP_PREFERENCES_COUNTER)) {
                zagolovok = mNames.getString(APP_PREFERENCES_COUNTER, "");
                opisanie = mNames.getString(APP_PREFERENCES_COUNTER2, "");
            }
            if (name.length() < 2 || login.length() < 2 || email.length() < 2) {
                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                builder.setMessage(R.string.nopole)
                        .setNegativeButton(R.string.retry,  new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                nextActivity(2);
                            }})
                        .create()
                        .show();
            } else {
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);

                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {
                                nextActivity(1);
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                builder.setMessage(R.string.regfail)
                                        .setNegativeButton(R.string.retry, new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                nextActivity(2);
                                            }
                                        })
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                RegisterRequest registerRequest = new RegisterRequest(name, login, email, password, zagolovok, opisanie, Integer.parseInt(spam), responseListener);
                RequestQueue queue = Volley.newRequestQueue(this);
                queue.add(registerRequest);
            }
        }
    }

}
