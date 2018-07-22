package devdem.time;
/* ох бля.
*  система аккаунтов.*/
// импорты

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
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
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import static devdem.time.MainActivity.APP_PREFERENCES;
import static devdem.time.MainActivity.APP_PREFERENCES_STYLE;

// запросы

public class LoginActivity extends AppCompatActivity {
    private static final String APP_PREFERENCES_PERFORMANCE = "graphics"; // графикс
    public static final String APP_PREFERENCES_LOGIN = "login"; // логин
    public static final String APP_PREFERENCES_PASSWORD = "password"; // обосранный король
    public static final String APP_PREFERENCES_ACCOUNT = "account"; // аккаунт
    SharedPreferences mNames; // мммммммм настройки

    TextView registerbtn; // текст регистрации
    EditText etUn; // поле для логина
    EditText etPs; // поле для пароля
    Button loginbtn; // кнопка логина
    TextView tx18; // хз чё это
    TextView signintx; // надпись ВХОД
    ImageView oval; // крутая анимация в виде овала(круга)

    // функция перехода на разные хуи
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
        mNames = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE); // подгружаем настройки
        // определяем тему
        switch (mNames.getInt(APP_PREFERENCES_STYLE, 1)) {
            case 1:
                setTheme(R.style.AppTheme);
                break;
            case 2:
                setTheme(R.style.AppThemeLight);
                break;
            case 3:
                setTheme(R.style.AppThemeLightOrange);
                break;
            case 4:
                setTheme(R.style.AppThemeLightGreen);
                break;
            case 5:
                setTheme(R.style.AppThemeLightBlue);
                break;
            case 6:
                setTheme(R.style.AppThemeLightPink);
                break;
            case 7:
                setTheme(R.style.AppThemeDarkGreen);
                break;
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login); // контент
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); // ориентация гейская
        oval = findViewById(R.id.imageView2l); // ищем овал(круг)
        registerbtn = findViewById(R.id.textView12l); // ищем кнопку
        // "слушатель нажатия кнопки"
        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextActivity(2); // идём нахуй
            }
        });
        etUn = findViewById(R.id.etUsernamel); // ищем поле для логина
        etPs = findViewById(R.id.etPasswordl); // ищем поле для пароля, чтобы спиздить его
        loginbtn = findViewById(R.id.button4l); // КНОПКА
        tx18 = findViewById(R.id.textView18l); // не ешь я текст
        signintx = findViewById(R.id.etSigninl); // текст входа .-.
        if (mNames.getBoolean(APP_PREFERENCES_PERFORMANCE, true)) { // ГРАФОООН
            // анимации
            Animation anim = AnimationUtils.loadAnimation(this, R.anim.anim_loginreg);
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
        // показываем залупу
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
        nextActivity(1); // идём нахуй
    }

    public void LoginClick(final View view) { // ебать, нажали на вход
        // анимейшен
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
        // получаем значения с полей
        final String username = etUn.getText().toString();
        final String password = etPs.getText().toString();

        Response.Listener<String> responseListener = new Response.Listener<String>() { // "слушатель ответа сервера"
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response); // пытаемся расшифровать JSON
                    boolean success = jsonResponse.getBoolean("success");
                    if (success) { // удачный вход
                        String name = jsonResponse.getString("name"); // получаем имя
                        Toast info = Toast.makeText(LoginActivity.this, "Привет, "+name+"!", Toast.LENGTH_LONG); // пишем приветсвие
                        info.show();
                        // редактируем настройки
                        SharedPreferences.Editor editor = mNames.edit();
                        editor.putString(APP_PREFERENCES_LOGIN, username);
                        editor.putString(APP_PREFERENCES_PASSWORD, password); // пиздим пароль в память телефона
                        editor.putBoolean(APP_PREFERENCES_ACCOUNT, true);
                        editor.apply();

                        nextActivity(4); // идём нахуй
                    }else{ // неудачный вход.... :(
                        Toast tss = Toast.makeText(LoginActivity.this, R.string.notpas, Toast.LENGTH_LONG);
                        tss.show();
                        nextActivity(3);
                    }
                } catch (JSONException e) { // ошибка, мля
                    e.printStackTrace();
                }

            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() { // "слушатель ошибка", бля
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LoginActivity.this, error.toString(), Toast.LENGTH_LONG).show(); // отображаем ошибку
                // спрашиваем, повторить ли попытку?
                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                builder.setMessage(R.string.timeouterror)
                        .setNegativeButton(R.string.retry, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                nextActivity(4); // идём нахуй
                            }
                        })
                        .create()
                        .show();
            }
        };
        // отправляем запрос логина
        LoginRequest loginRequest = new LoginRequest(username, password, responseListener, errorListener);
        RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
        queue.add(loginRequest);

        // бля, пойду посру, потом допишу код
        // TODO: доделать анимацию
    }

}

