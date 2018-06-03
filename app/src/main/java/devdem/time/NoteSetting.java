package devdem.time;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;

public class NoteSetting extends PreferenceActivity {
    public static final String APP_PREFERENCES = "names";
    public static final String APP_PREFERENCES_STYLE = "style";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        SharedPreferences mNames = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        if (mNames.getInt(APP_PREFERENCES_STYLE, 1)==1) {
            setTheme(R.style.AppTheme);
        }
        if (mNames.getInt(APP_PREFERENCES_STYLE, 1)==2) {
            setTheme(R.style.AppThemeLight);

        } if (mNames.getInt(APP_PREFERENCES_STYLE, 1)==3) {
            setTheme(R.style.AppThemeLightOrange);
        } if (mNames.getInt(APP_PREFERENCES_STYLE, 1)==4) {
            setTheme(R.style.AppThemeLightGreen);
        } if (mNames.getInt(APP_PREFERENCES_STYLE, 1)==5) {
            setTheme(R.style.AppThemeLightBlue);
        } if (mNames.getInt(APP_PREFERENCES_STYLE, 1)==6) {
            setTheme(R.style.AppThemeLightPink);
        } if (mNames.getInt(APP_PREFERENCES_STYLE, 1)==7) {
            setTheme(R.style.AppThemeDarkGreen);
        }
        super.onCreate(savedInstanceState);
        // загружаем предпочтения из ресурсов
        addPreferencesFromResource(R.xml.preferences);
    }
}
