package devdem.time;
/* сердце всего приложения
* сколько же тут было ошибок.. :) */
// дохуя импортов

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

// реклама :333 и запросы

public class MainActivity extends AppCompatActivity {

    public int version = BuildConfig.VERSION_CODE; // текущая версия приложения
    public String inputtext1; // временное значение заголовка
    public String inputtext2; // временное значение описания
    public boolean big; // май дик из вери биг
    private static final int NOTIFY_ID = 1323; // ИД уведомления(хз зачем)
    public static final String APP_PREFERENCES = "names"; // настройки
    public static final String APP_PREFERENCES_COUNTER = "text1"; // заголовок
    public static final String APP_PREFERENCES_COUNTER2 = "text2"; // описание
    public static final String APP_PREFERENCES_STYLE = "style"; // тема
    public static final String APP_PREFERENCES_PERFORMANCE = "graphics"; // ГРАФИКА
    public static final String APP_PREFERENCES_DIALOG = "dialog"; // просьба об отзыве
    public static final String APP_PREFERENCES_AD = "noad"; // ДОНАТ НА РЕКЛАМУ :333333333333333333
    public static final String APP_PREFERENCES_ADVANCE = "advance"; // расширенное уведомление ?
    public static final String APP_PREFERENCES_LOGIN = "login"; // логин
    public static final String APP_PREFERENCES_PASSWORD = "password"; // пароль
    public static final String APP_PREFERENCES_ACCOUNT = "account"; // АККАУНТ
    String one;
    String two;
    private SharedPreferences mNames; // настройки
    // новый тип
    final Context context = this;

    private DrawerLayout mDrawerLayout; // менюшка справа
    private void nextActivity(int activ, boolean finishs) // функция посылания нахуй
    {
        Intent intent = new Intent(this, MainActivity.class);
            if(activ==1)  intent = new Intent(this, SplashActivity.class);
            if(activ==2)  intent = new Intent(this, AboutActivity.class);
            if(activ==3)  intent = new Intent(this, NoteActivity.class);
            if(activ==4)  intent = new Intent(this, ThemeSetting.class);
            if(activ==5)  intent = new Intent(this, LoginActivity.class);
            if(activ==6)  intent = new Intent(this, UserActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.anim_activity_out, R.anim.anim_activity_in);
        if (finishs) finish();
    }
    public int getStatusBarHeight() {
        int result = 0;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
            if (resourceId > 0) {
                result = getResources().getDimensionPixelSize(resourceId);
            }
        }
        return result;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mNames = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE); // настройки
        // я тупой и сразу не додумался до этого
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
        setContentView(R.layout.activity_main); // контент
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); // ориентация

        Toolbar toolbar = findViewById(R.id.toolbar); // сверху такая хуйнюшка
        toolbar.setPadding(0, getStatusBarHeight(), 0, 0);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        if (actionbar != null) {
            actionbar.setDisplayHomeAsUpEnabled(true);
        }
        if (actionbar != null) {
            actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }

        mDrawerLayout = findViewById(R.id.drawer_layout); // менюшка

        NavigationView navigationView = findViewById(R.id.nav_view); // ищем менюшку
        navigationView.setNavigationItemSelectedListener( // "слушатель менюшки"
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                        mDrawerLayout.closeDrawers();
                        int id = menuItem.getItemId();

                        switch (id) {
                           /* case R.id.action_clear:
                                onClear();
                                nextActivity(1, true);
                                menuItem.setChecked(true);
                                return true; */
                            case R.id.action_about:
                                nextActivity(2, false);
                                return true;
                            case R.id.action_note:
                                nextActivity(3, true);
                                menuItem.setChecked(true);
                                return true;
                            case R.id.action_themesetting:
                                nextActivity(4, true);
                                menuItem.setChecked(true);
                                return true;
                            case R.id.action_account:
                                if(mNames.getBoolean(APP_PREFERENCES_ACCOUNT, false)) nextActivity(6, true);
                                else nextActivity(5, true);
                                menuItem.setChecked(true);
                                return true;
                        }
                        return true;
                    }
                });

        // реклама :3333333
        AdView mAdView = findViewById(R.id.adView);
        MobileAds.initialize(this, "ca-app-pub-7389415060915567~5491997504"); // ad
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        if (mNames.getBoolean(APP_PREFERENCES_AD, false)) {
            mAdView.setVisibility(View.INVISIBLE);
        }

        final ImageView spenner = findViewById(R.id.spenner); // картиночка на фоне ,мля
        // анимация картиночки
        final Animation anim = AnimationUtils.loadAnimation(this, R.anim.spenner_anim);
        if (mNames.getBoolean(APP_PREFERENCES_PERFORMANCE, true)) {
            new CountDownTimer(50000000, 60000) {

                @Override
                public void onTick(long countDownInterval) {
                    spenner.startAnimation(anim);
                    spenner.animate();
                }

                @Override
                public void onFinish() {
                }
            }.start();
        } else {
            spenner.setVisibility(View.INVISIBLE);
        }
        // проверка на настройки
        if (mNames.contains(APP_PREFERENCES_COUNTER)) {
            onOpen();
        }
        if (isOnline()) { // проверяем версию приложения
            String urlxml = "http://devdemprojects.my1.ru/Projects/Napominalka/update.xml";
            HandleXml obj = new HandleXml(urlxml);
            obj.fetchXML();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (!obj.parsingComplete) {
                if (Integer.parseInt(obj.getVersion()) > version) {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            R.string.aver, Toast.LENGTH_LONG); // оповещаем пользователя, если есть обнова
                    toast.show();
                }
            }

        }
        if (mNames.getBoolean(APP_PREFERENCES_DIALOG, true)) {
            Toast toast = Toast.makeText(getApplicationContext(),
                    R.string.rate, Toast.LENGTH_LONG); // показываем просьбу об отзыве
            toast.show();
            SharedPreferences.Editor editor = mNames.edit();
            editor.putBoolean("dialog", false);
            editor.apply();
        }
        if (mNames.getBoolean(APP_PREFERENCES_ACCOUNT, false)) {
            Response.Listener<String> responseListener = new Response.Listener<String>() { // слушатель запроса
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonResponse = new JSONObject(response); // пытаемся получить результат запроса
                        boolean success = jsonResponse.getBoolean("success"); // получаем результат success
                        if (success) {
                            String name = jsonResponse.getString("name"); // получаем имя
                            one = jsonResponse.getString("zagolovok"); // получаем заголовок
                            two = jsonResponse.getString("opisanie"); // получаем описание
                            if (!Objects.equals(inputtext1, one) || !Objects.equals(inputtext2, two)) { // проверяем, совпадает ли заголовок\описание
                                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this); // создаём окошечко, если нет
                                builder.setMessage(R.string.dbsearch) // пищем об различии
                                        .setCancelable(false)
                                        // кнопка "Сервер"
                                        .setPositiveButton(R.string.server,
                                                new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int id) {
                                                        SyncData();
                                                        dialog.cancel();
                                                    }
                                                })
                                        // кнопка "телефон"
                                        .setNegativeButton(R.string.phone,
                                                new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int id) {
                                                        inputtext2 = two;
                                                        inputtext1 = one;
                                                        onSave();
                                                        if (!big) {
                                                            onCreateNotification(inputtext1, inputtext2);
                                                        } else {
                                                            onCreateNotificationBig(inputtext1, inputtext2);
                                                        }
                                                        dialog.cancel();
                                                    }
                                                })
                                        // кнопка "отмена"
                                        .setNeutralButton(R.string.cancel,
                                                new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int id) {
                                                        dialog.cancel();
                                                    }
                                                });
                                    builder.create();
                                    builder.show();
                            }
                            Context c = MainActivity.this; // получаем контент
                            Toast info = Toast.makeText(MainActivity.this, c.getString(R.string.welcome)+", "+name + "!", Toast.LENGTH_LONG);
                            info.show();
                        } else {
                            Toast tss = Toast.makeText(MainActivity.this, R.string.notpas, Toast.LENGTH_LONG); // пароль не подходит(
                            tss.show();
                            SharedPreferences.Editor editor = mNames.edit(); // удаляем значения аккаунта
                            editor.putBoolean(APP_PREFERENCES_ACCOUNT, false);
                            editor.apply();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            };
            // запрос на логин
            LoginRequest loginRequest = new LoginRequest(mNames.getString(APP_PREFERENCES_LOGIN, ""), mNames.getString(APP_PREFERENCES_PASSWORD, ""), responseListener, null);
            RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
            queue.add(loginRequest);
        }

    }

    // создаём расширенное уведомление
    public void onCreateNotificationBig(String zagolovok, String opisanie) {
        Context context = getApplicationContext(); // контекст приложения
        Intent notificationIntent = new Intent(context, MainActivity.class); // откуда уведомление?
        PendingIntent contentIntent = PendingIntent.getActivity(context,
                0, notificationIntent,
                PendingIntent.FLAG_CANCEL_CURRENT);
        Notification.Builder builder = new Notification.Builder(context); // инициируем уведомление
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) { // проверяем на андроид 5.0+
            // создание уведомления
            builder.setContentIntent(contentIntent)
                    .setSmallIcon(R.drawable.ic_launcher)
                    .setTicker(zagolovok)
                    .setWhen(System.currentTimeMillis())
                    .setAutoCancel(true)
                    .setContentTitle(zagolovok)
                    .setContentText(opisanie)
                    .setPriority(Notification.PRIORITY_MAX)
                    .setVisibility(Notification.VISIBILITY_PUBLIC)
                    .setSmallIcon(R.drawable.notification_icon)
                    .setOngoing(true);
            Notification notification = new Notification.BigTextStyle(builder)
                    .bigText(opisanie).build();
            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
            notificationManager.cancel(NOTIFY_ID); // скрываем прошлое уведомление..
            notificationManager.notify(NOTIFY_ID, notification); // ..и показываем его
        } else {
            Toast toast = Toast.makeText(getApplicationContext(),
                    R.string.notsup, Toast.LENGTH_LONG);
            toast.show();
        }
    }
    // кнопка "создать уведомление"
    public void onUpdateNotification(View view) {
        onSave(); // сохраняем залупу всю
        if (!big) { // проверяем, какое уведомление нам надо
            onCreateNotification(inputtext1, inputtext2);
        } else {
            onCreateNotificationBig(inputtext1, inputtext2);
        }
        new CountDownTimer(1000,100) {
                public void onTick ( long millisUntilFinished){

                }
                public void onFinish() {
                    Button button1 = findViewById(R.id.button);
                    button1.setEnabled(true);
                }
        }.start();
    }

    public void onSave() { // сохранение всей залупы
        SharedPreferences.Editor editor = mNames.edit();
        editor.putString(APP_PREFERENCES_COUNTER, inputtext1);
        editor.putString(APP_PREFERENCES_COUNTER2, inputtext2);
        editor.putBoolean(APP_PREFERENCES_ADVANCE, big);
        editor.apply();
    }
    // создаём маленькое, как чей-нибудь член уведомление
    public void onCreateNotification(String zagolovok, String opisanie) {
        // бла-бла-бла, одно и тоже, что и в onCreateNotificationBig()
        Context context = getApplicationContext();
        Intent notificationIntent = new Intent(context, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(context,
                0, notificationIntent,
                PendingIntent.FLAG_CANCEL_CURRENT);
        Resources res = context.getResources();
        Notification.Builder builder = new Notification.Builder(context);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder.setContentIntent(contentIntent)
                    .setSmallIcon(R.drawable.ic_launcher)
                    .setLargeIcon(BitmapFactory.decodeResource(res, R.drawable.ic_launcher))
                    .setTicker(zagolovok)
                    .setWhen(System.currentTimeMillis())
                    .setAutoCancel(true)
                    .setContentTitle(zagolovok)
                    .setContentText(opisanie)
                    .setPriority(Notification.PRIORITY_MAX)
                    .setVisibility(Notification.VISIBILITY_PUBLIC)
                    .setSmallIcon(R.drawable.notification_icon)
                    .setOngoing(true);

        } else {
            builder.setContentIntent(contentIntent)
                    .setSmallIcon(R.drawable.ic_launcher)
                    .setLargeIcon(BitmapFactory.decodeResource(res, R.drawable.ic_launcher))
                    .setTicker(zagolovok)
                    .setWhen(System.currentTimeMillis())
                    .setAutoCancel(true)
                    .setContentTitle(zagolovok)
                    .setSmallIcon(R.drawable.notification_icon)
                    .setContentText(opisanie)
                    .setOngoing(true);
        }
        Notification notification = builder.build();
        NotificationManager notificationManager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        if (notificationManager != null) {
            notificationManager.cancel(NOTIFY_ID);
            notificationManager.notify(NOTIFY_ID, notification);
        }

    }
    public void onOpen() { // открытие настроек
        inputtext1 = mNames.getString(APP_PREFERENCES_COUNTER, "");
        inputtext2 = mNames.getString(APP_PREFERENCES_COUNTER2, "");
        big = mNames.getBoolean(APP_PREFERENCES_ADVANCE, false);
        if (!big) {
            onCreateNotification(inputtext1, inputtext2);
        } else {
            onCreateNotificationBig(inputtext1, inputtext2);
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) { // проверка на нажатие кнопки меню
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void onDeleteNo(View view) { // функция нажатия на кнопку "удалить"
        Context context = getApplicationContext();
        Notification.Builder builder = new Notification.Builder(context);
        builder.build();
        NotificationManager notificationManager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        if (notificationManager != null) {
            notificationManager.cancel(NOTIFY_ID);
        }
    }

    protected boolean isOnline() { // функция проверки на онлайн
        ConnectivityManager cm = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        assert cm != null;
        return cm.getActiveNetworkInfo() != null;
    }

    public void onChange(View view) { // функция нажатия кнопки "настройки уведомления"
        // очень сложная залупень
        LayoutInflater li = LayoutInflater.from(context);
        @SuppressLint("InflateParams") View promptsView = li.inflate(R.layout.dialog_change, null);
        AlertDialog.Builder mDialogBuilder = new AlertDialog.Builder(context);
        mDialogBuilder.setView(promptsView);
        final EditText zag = promptsView.findViewById(R.id.edittext3);
        final EditText sod = promptsView.findViewById(R.id.edittext4);
        zag.setText(inputtext1);
        sod.setText(inputtext2);
        mDialogBuilder
                .setCancelable(true)
                .setPositiveButton(R.string.yes,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                inputtext1 = String.valueOf(zag.getText());
                                inputtext2 = String.valueOf(sod.getText());
                                big = true;
                                onSave();
                                if (mNames.getBoolean(APP_PREFERENCES_ACCOUNT, false)) SyncData();
                                else {
                                    Toast toast = Toast.makeText(getApplicationContext(),
                                            R.string.savesettings, Toast.LENGTH_LONG);
                                    toast.show();
                                }
                            }
                        })
                .setNegativeButton(R.string.no,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                inputtext1 = String.valueOf(zag.getText());
                                inputtext2 = String.valueOf(sod.getText());
                                big=false;
                                onSave();
                                if (mNames.getBoolean(APP_PREFERENCES_ACCOUNT, false)) SyncData();
                                else {
                                    Toast toast = Toast.makeText(getApplicationContext(),
                                            R.string.savesettings, Toast.LENGTH_LONG);
                                    toast.show();
                                }
                                dialog.cancel();
                            }
                        });
        AlertDialog alertDialog = mDialogBuilder.create();

        //и отображаем всё это:
        alertDialog.show();
    }
    public void SyncData() { // синхронизейшен инфы
        Response.Listener<String> responseListeners = new Response.Listener<String>() { // слушатель запроса
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponses = new JSONObject(response);
                    boolean success = jsonResponses.getBoolean("success");
                    if (success) {
                        Toast toast = Toast.makeText(getApplicationContext(),
                                R.string.savesettingssync, Toast.LENGTH_LONG);
                        toast.show();
                    } else {
                        Toast updfail = Toast.makeText(MainActivity.this, R.string.savesettingssyncerr, Toast.LENGTH_LONG);
                        updfail.show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        // сам запрос
        UpdateRequest updateRequest = new UpdateRequest(mNames.getString(APP_PREFERENCES_LOGIN, ""), mNames.getString(APP_PREFERENCES_PASSWORD, ""), inputtext1, inputtext2, responseListeners);
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        queue.add(updateRequest);
    }
    // фух.. 527 строчек..
}
