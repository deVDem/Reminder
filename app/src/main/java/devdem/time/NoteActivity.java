package devdem.time;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import static devdem.time.MainActivity.APP_PREFERENCES_COUNTER;
import static devdem.time.MainActivity.APP_PREFERENCES_COUNTER2;

public class NoteActivity extends AppCompatActivity {
    private final static String FILENAME = "sample.txt"; // имя файла
    public static final String APP_PREFERENCES = "names";
    public static final String APP_PREFERENCES_STYLE = "style";
    private SharedPreferences mNames;
    private EditText mEditText;
    boolean needed=false;

    final Context context = this;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.anim_activity_out, R.anim.anim_activity_in);
        finish();
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

    public void onCreate(Bundle savedInstanceState) {
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
        setContentView(R.layout.activity_note);
        Toolbar toolbar = findViewById(R.id.toolbar2);
        toolbar.setPadding(0, getStatusBarHeight(), 0, 0);
        setSupportActionBar(toolbar);
        mEditText = findViewById(R.id.editText);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_note, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Resources res = context.getResources();
        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent intent = new Intent(this, NoteSetting.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_activity_out, R.anim.anim_activity_in);
                return true;
            case R.id.action_export:
                LayoutInflater li = LayoutInflater.from(context);
                @SuppressLint("InflateParams") View promptsView = li.inflate(R.layout.dialog_export, null);
                AlertDialog.Builder mDialogBuilder = new AlertDialog.Builder(context);
                mDialogBuilder.setView(promptsView);
                mDialogBuilder
                        .setCancelable(true)
                        .setPositiveButton(res.getText(R.string.sodershanie),
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        SharedPreferences.Editor editor = mNames.edit();
                                        editor.putString(APP_PREFERENCES_COUNTER2, String.valueOf(mEditText.getText()));
                                        editor.apply();
                                        needed=true;
                                    }
                                })
                        .setNegativeButton(res.getText(R.string.zagolovok),
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        SharedPreferences.Editor editor = mNames.edit();
                                        editor.putString(APP_PREFERENCES_COUNTER, String.valueOf(mEditText.getText()));
                                        editor.apply();
                                        needed=true;
                                        dialog.cancel();
                                    }
                                });
                AlertDialog alertDialog = mDialogBuilder.create();

                //и отображаем его:
                alertDialog.show();
                return true;
            default:
                return true;
        }
    }
    private void openFile(String fileName) {
        Context context = getApplicationContext();
        Resources res = context.getResources();
        try {
            InputStream inputStream = openFileInput(fileName);

            if (inputStream != null) {
                InputStreamReader isr = new InputStreamReader(inputStream);
                BufferedReader reader = new BufferedReader(isr);
                String line;
                StringBuilder builder = new StringBuilder();

                while ((line = reader.readLine()) != null) {
                    builder.append(line).append("\n");
                }

                inputStream.close();
                mEditText.setText(builder.toString());
            }
        } catch (Throwable t) {
            Toast.makeText(getApplicationContext(),
                    res.getString(R.string.error)+": " + t.toString(), Toast.LENGTH_LONG).show();
        }
    }
    private void saveFile(String fileName) {
        Context context = getApplicationContext();
        Resources res = context.getResources();
        try {
            OutputStream outputStream = openFileOutput(fileName, 0);
            OutputStreamWriter osw = new OutputStreamWriter(outputStream);
            osw.write(mEditText.getText().toString());
            osw.close();
        } catch (Throwable t) {
            Toast.makeText(getApplicationContext(),
                    res.getString(R.string.error)+": " + t.toString(), Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences prefs = PreferenceManager
                .getDefaultSharedPreferences(this);
            openFile(FILENAME);
        // читаем размер шрифта из EditTextPreference
        float fSize = Float.parseFloat(prefs.getString(
                getString(R.string.pref_size), "20"));
// применяем настройки в текстовом поле
        mEditText.setTextSize(fSize);
        // читаем стили текста из ListPreference
        String regular = prefs.getString(getString(R.string.pref_style), "");
        int typeface = Typeface.NORMAL;

        if (regular.contains("Полужирный")|| regular.contains("Bold")|| regular.contains("Напівжирний"))
            typeface += Typeface.BOLD;

        if (regular.contains("Курсив")|| regular.contains("Italy"))
            typeface += Typeface.ITALIC;

// меняем настройки в EditText
        mEditText.setTypeface(null, typeface);
    }
    public void onPause() {
        super.onPause();
        saveFile(FILENAME);
        if (needed) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
