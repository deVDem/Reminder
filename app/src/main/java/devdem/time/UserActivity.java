package devdem.time;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import static devdem.time.MainActivity.APP_PREFERENCES;
import static devdem.time.MainActivity.APP_PREFERENCES_STYLE;

public class UserActivity extends AppCompatActivity {
    public static final String APP_PREFERENCES_LOGIN = "login";
    public static final String APP_PREFERENCES_PASSWORD = "password";
    public static final String APP_PREFERENCES_ACCOUNT = "account";
    String name;
    String email;
    String login;
    String spam;
    Boolean finals=false;
    SharedPreferences mNames;
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

    @SuppressLint("SetTextI18n")
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
        setContentView(R.layout.activity_user);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Response.Listener<String> responsseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    final boolean success = jsonResponse.getBoolean("success");
                    if (success) {
                        name = jsonResponse.getString("name");
                        email = jsonResponse.getString("email");
                        login = jsonResponse.getString("username");
                        spam = jsonResponse.getString("spam");
                        final TextView nametx = findViewById(R.id.hellotextss);
                        final TextView infotx = findViewById(R.id.infoacc);
                        final Button btnnamech = findViewById(R.id.btnnamech);
                        final Button btnemailch = findViewById(R.id.btnemailch);
                        View.OnClickListener btnemailchl = new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(UserActivity.this);
                                Context context = getApplicationContext();
                                LayoutInflater li = LayoutInflater.from(context);
                                @SuppressLint("InflateParams") View promptsView = li.inflate(R.layout.dialog_chname, null);
                                builder.setView(promptsView);
                                final EditText newnameet = promptsView.findViewById(R.id.editText2);
                                newnameet.setHint(R.string.newemail);
                                builder.setNeutralButton(R.string.changeemail,  new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        final String newemail = newnameet.getText().toString();
                                        if (newemail.length() < 7 || newemail.length() > 48) {
                                            Toast errt = Toast.makeText(UserActivity.this, R.string.erremail, Toast.LENGTH_LONG);
                                            errt.show();
                                        }
                                        else {
                                            Response.Listener<String> updemaill = new Response.Listener<String>() {
                                                @Override
                                                public void onResponse(String response) {
                                                    try {
                                                        JSONObject jsonObject = new JSONObject(response);
                                                        boolean success = jsonObject.getBoolean("success");
                                                        if (success) {
                                                            Toast ok = Toast.makeText(UserActivity.this, R.string.success, Toast.LENGTH_LONG);
                                                            ok.show();
                                                            nextActivity(4);
                                                        }
                                                    } catch (JSONException e) {
                                                        e.printStackTrace();
                                                    }
                                                }

                                            };

                                            ChangeEmailRequest changeEmailRequest = new ChangeEmailRequest(mNames.getString(APP_PREFERENCES_LOGIN, ""), mNames.getString(APP_PREFERENCES_PASSWORD, ""), newemail, updemaill);
                                            RequestQueue queue = Volley.newRequestQueue(UserActivity.this);
                                            queue.add(changeEmailRequest); }}}).create().show();
                            }
                        };
                        btnemailch.setOnClickListener(btnemailchl);
                        final Button btnpassch = findViewById(R.id.btnpassch);
                        View.OnClickListener btnpasschlis = new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(UserActivity.this);
                                Context context = getApplicationContext();
                                LayoutInflater li = LayoutInflater.from(context);
                                @SuppressLint("InflateParams") View promptsView = li.inflate(R.layout.dialog_chname, null);
                                builder.setView(promptsView);
                                final EditText newnameet = promptsView.findViewById(R.id.editText2);
                                newnameet.setHint(R.string.newpass);
                                builder.setNeutralButton(R.string.changepass,  new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        final String newpass = newnameet.getText().toString();
                                        if (newpass.length() < 2 || newpass.length() > 48) {
                                            Toast errt = Toast.makeText(UserActivity.this, R.string.lengtherr, Toast.LENGTH_LONG);
                                            errt.show();
                                        }
                                        else {
                                            Response.Listener<String> updpassl = new Response.Listener<String>() {
                                                @Override
                                                public void onResponse(String response) {
                                                    try {
                                                        JSONObject jsonObject = new JSONObject(response);
                                                        boolean success = jsonObject.getBoolean("success");
                                                        if (success) {
                                                            Toast ok = Toast.makeText(UserActivity.this, R.string.success, Toast.LENGTH_LONG);
                                                            ok.show();
                                                            SharedPreferences.Editor editor = mNames.edit();
                                                            editor.putString(APP_PREFERENCES_PASSWORD, newpass);
                                                            editor.apply();
                                                            nextActivity(4);
                                                        }
                                                    } catch (JSONException e) {
                                                        e.printStackTrace();
                                                    }
                                                }

                                            };

                                            ChangePassRequest changePassRequest = new ChangePassRequest(mNames.getString(APP_PREFERENCES_LOGIN, ""), mNames.getString(APP_PREFERENCES_PASSWORD, ""), newpass, updpassl);
                                            RequestQueue queue = Volley.newRequestQueue(UserActivity.this);
                                            queue.add(changePassRequest); }}}).create().show();
                            }
                        };
                        btnpassch.setOnClickListener(btnpasschlis);
                        final Button btnoffspam = findViewById(R.id.btnoffspam);
                        final Button btndelch = findViewById(R.id.btndelch);
                        View.OnClickListener btndelchlis = new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(UserActivity.this);
                                builder.setMessage(R.string.deleteaccount)
                                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                // ss
                                                Response.Listener<String> delaccl = new Response.Listener<String>() {
                                                    @Override
                                                    public void onResponse(String response) {
                                                         Toast ok = Toast.makeText(UserActivity.this, R.string.success, Toast.LENGTH_LONG);
                                                         ok.show();
                                                         SharedPreferences.Editor editor = mNames.edit();
                                                         editor.remove(APP_PREFERENCES_LOGIN);
                                                         editor.remove(APP_PREFERENCES_PASSWORD);
                                                         editor.putBoolean(APP_PREFERENCES_ACCOUNT, false);
                                                         editor.apply();
                                                         nextActivity(3);
                                                    }
                                                };
                                                DeleteAccountRequest deleteAccountRequest = new DeleteAccountRequest(mNames.getString(APP_PREFERENCES_LOGIN, ""), mNames.getString(APP_PREFERENCES_PASSWORD, ""), delaccl);
                                                RequestQueue queue = Volley.newRequestQueue(UserActivity.this);
                                                queue.add(deleteAccountRequest);
                                            }
                                        })
                                        .setNegativeButton(R.string.no, null)
                                .create()
                                .show();
                            }
                        };
                        btndelch.setOnClickListener(btndelchlis);
                        nametx.setText(getString(R.string.welcome) + ",\n" + name + "!");
                        if (spam.equals("1")) { btnoffspam.setText(R.string.offspam); infotx.setText(getString(R.string.name) + ": " + name + "\n" + "E-Mail: " + email + "\n" + getString(R.string.username) + ": " + login + "\n" + getString(R.string.spamon)); }
                        else { btnoffspam.setText(R.string.onspam); infotx.setText(getString(R.string.name) + ": " + name + "\n" + "E-Mail: " + email + "\n" + getString(R.string.username) + ": " + login + "\n" + getString(R.string.spamoff)); }
                        View.OnClickListener btnoffspamlis = new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Response.Listener<String> spamtoglis = new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        try {
                                            JSONObject jsonObject = new JSONObject(response);
                                            boolean success = jsonObject.getBoolean("success");
                                            if(success) {
                                                Toast ok = Toast.makeText(UserActivity.this, R.string.success, Toast.LENGTH_LONG);
                                                ok.show();
                                                nextActivity(4);
                                            }
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                };
                                SpamTogRequest spamTogRequest = new SpamTogRequest(mNames.getString(APP_PREFERENCES_LOGIN, ""), mNames.getString(APP_PREFERENCES_PASSWORD, ""), spamtoglis);
                                RequestQueue queue = Volley.newRequestQueue(UserActivity.this);
                                queue.add(spamTogRequest);
                            }
                        };
                        btnoffspam.setOnClickListener(btnoffspamlis);
                        View.OnClickListener btnchangename = new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(UserActivity.this);
                                Context context = getApplicationContext();
                                LayoutInflater li = LayoutInflater.from(context);
                                @SuppressLint("InflateParams") View promptsView = li.inflate(R.layout.dialog_chname, null);
                                builder.setView(promptsView);
                                final EditText newnameet = promptsView.findViewById(R.id.editText2);
                                builder.setNeutralButton(R.string.changename,  new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                String newname = newnameet.getText().toString();
                                                if (newname.length() < 2 || newname.length() > 48) {
                                                    Toast errt = Toast.makeText(UserActivity.this, R.string.lengtherr, Toast.LENGTH_LONG);
                                                    errt.show();
                                                }
                                                else {
                                                    Response.Listener<String> updnamel = new Response.Listener<String>() {
                                                        @Override
                                                        public void onResponse(String response) {
                                                            try {
                                                                JSONObject jsonObject = new JSONObject(response);
                                                                boolean success = jsonObject.getBoolean("success");
                                                                if (success) {
                                                                    Toast ok = Toast.makeText(UserActivity.this, R.string.success, Toast.LENGTH_LONG);
                                                                    ok.show();
                                                                    nextActivity(4);
                                                                }
                                                            } catch (JSONException e) {
                                                                e.printStackTrace();
                                                            }
                                                        }

                                                    };

                                                    ChangeNameRequest changeNameRequest = new ChangeNameRequest(mNames.getString(APP_PREFERENCES_LOGIN, ""), mNames.getString(APP_PREFERENCES_PASSWORD, ""), newname, updnamel);
                                                    RequestQueue queue = Volley.newRequestQueue(UserActivity.this);
                                                    queue.add(changeNameRequest); }}}).create().show();
                            }
                        };
                        btnnamech.setOnClickListener(btnchangename);

                    } else {
                        nextActivity(3);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        };
        LoginRequest loginRequest = new LoginRequest(mNames.getString(APP_PREFERENCES_LOGIN, ""), mNames.getString(APP_PREFERENCES_PASSWORD, ""), responsseListener);
        RequestQueue queue = Volley.newRequestQueue(UserActivity.this);
        queue.add(loginRequest);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        nextActivity(1);
    }
}
