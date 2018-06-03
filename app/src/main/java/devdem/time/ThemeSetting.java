package devdem.time;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Switch;

import java.util.ArrayList;
import java.util.List;

import static devdem.time.MainActivity.APP_PREFERENCES_AD;

public class ThemeSetting extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    public static final String APP_PREFERENCES = "names";
    public static final String APP_PREFERENCES_STYLE = "style";
    public static final String APP_PREFERENCES_PERFORMANCE = "graphics";
    int needselect=0;
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        SharedPreferences mNames = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

        Switch keks=findViewById(R.id.switch2);
        if (keks.isChecked()) {
            SharedPreferences.Editor editor = mNames.edit();
            editor.putBoolean(APP_PREFERENCES_PERFORMANCE, true);
            editor.apply();
        } else {
            SharedPreferences.Editor editor = mNames.edit();
            editor.putBoolean(APP_PREFERENCES_PERFORMANCE, false);
            editor.apply();
        }
        Switch keksi=findViewById(R.id.switch4);
        if (keksi.isChecked()) {
            SharedPreferences.Editor editor = mNames.edit();
            editor.putBoolean(APP_PREFERENCES_AD, true);
            editor.apply();
        } else {
            SharedPreferences.Editor editor = mNames.edit();
            editor.putBoolean(APP_PREFERENCES_AD, false);
            editor.apply();
        }
        Intent intent = new Intent(this, SplashActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        SharedPreferences mNames = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        if (mNames.getInt(APP_PREFERENCES_STYLE, 1)==1) {
            setTheme(R.style.AppTheme);
            needselect=0;
        }
        if (mNames.getInt(APP_PREFERENCES_STYLE, 1)==2) {
            setTheme(R.style.AppThemeLight);
            needselect=1;

        } if (mNames.getInt(APP_PREFERENCES_STYLE, 1)==3) {
            setTheme(R.style.AppThemeLightOrange);
            needselect=2;
        } if (mNames.getInt(APP_PREFERENCES_STYLE, 1)==4) {
            setTheme(R.style.AppThemeLightGreen);
            needselect=3;
        } if (mNames.getInt(APP_PREFERENCES_STYLE, 1)==5) {
            setTheme(R.style.AppThemeLightBlue);
            needselect=4;
        } if (mNames.getInt(APP_PREFERENCES_STYLE, 1)==6) {
            setTheme(R.style.AppThemeLightPink);
            needselect=5;
        } if (mNames.getInt(APP_PREFERENCES_STYLE, 1)==7) {
            setTheme(R.style.AppThemeDarkGreen);
            needselect=6;
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme_setting);
         if (mNames.getBoolean(APP_PREFERENCES_PERFORMANCE, false)) {
            Switch kek=findViewById(R.id.switch2);
            kek.setChecked(true);
        }
        if (mNames.getBoolean(APP_PREFERENCES_AD, false)) {
            Switch kek=findViewById(R.id.switch4);
            kek.setChecked(true);
        }
        Spinner spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        Context context = getApplicationContext();
        Resources res = context.getResources();
        List<String> elements = new ArrayList<String>();
        elements.add(res.getString(R.string.blacktheme));
        elements.add(res.getString(R.string.whitetheme));
        elements.add(res.getString(R.string.orangetheme));
        elements.add(res.getString(R.string.greentheme));
        elements.add(res.getString(R.string.bluetheme));
        elements.add(res.getString(R.string.pinktheme));
        elements.add(res.getString(R.string.darkgreentheme));
        //Создаем для spinner адаптер:
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, elements);
        //Настраиваем внешний вид выпадающего списка, используя готовый системный шаблон:
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //Присоединяем адаптер данных к spinner:
        spinner.setAdapter(dataAdapter);
        spinner.setSelection(needselect);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        SharedPreferences mNames = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        long item = parent.getSelectedItemId();
        if (item==0) {
            SharedPreferences.Editor editor = mNames.edit();
            editor.putInt(APP_PREFERENCES_STYLE, 1);
            editor.apply();
        } else if(item==1) {
            SharedPreferences.Editor editor = mNames.edit();
            editor.putInt(APP_PREFERENCES_STYLE, 2);
            editor.apply();
        } else if(item==2) {
            SharedPreferences.Editor editor = mNames.edit();
            editor.putInt(APP_PREFERENCES_STYLE, 3);
            editor.apply();
        }else if(item==3) {
            SharedPreferences.Editor editor = mNames.edit();
            editor.putInt(APP_PREFERENCES_STYLE, 4);
            editor.apply();
        } else if(item==4) {
            SharedPreferences.Editor editor = mNames.edit();
            editor.putInt(APP_PREFERENCES_STYLE, 5);
            editor.apply();
        } else if(item==5) {
            SharedPreferences.Editor editor = mNames.edit();
            editor.putInt(APP_PREFERENCES_STYLE, 6);
            editor.apply();
        } else if(item==6) {
            SharedPreferences.Editor editor = mNames.edit();
            editor.putInt(APP_PREFERENCES_STYLE, 7);
            editor.apply();
        }

    }

    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }
   public void onClick2 (View view) {
        SharedPreferences mNames = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        Switch keks=findViewById(R.id.switch2);
        if (keks.isChecked()) {
            SharedPreferences.Editor editor = mNames.edit();
            editor.putBoolean(APP_PREFERENCES_PERFORMANCE, true);
            editor.apply();
        } else {
            SharedPreferences.Editor editor = mNames.edit();
            editor.putBoolean(APP_PREFERENCES_PERFORMANCE, false);
            editor.apply();
        }
    }
    public void onClick3(View view) {
        SharedPreferences mNames = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        Switch keksi=findViewById(R.id.switch4);
        if (keksi.isChecked()) {
            SharedPreferences.Editor editor = mNames.edit();
            editor.putBoolean(APP_PREFERENCES_AD, true);
            editor.apply();
        } else {
            SharedPreferences.Editor editor = mNames.edit();
            editor.putBoolean(APP_PREFERENCES_AD, false);
            editor.apply();
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
