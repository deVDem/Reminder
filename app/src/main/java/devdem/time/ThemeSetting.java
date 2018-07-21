package devdem.time;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.anjlab.android.iab.v3.BillingProcessor;
import com.anjlab.android.iab.v3.TransactionDetails;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.List;

import static devdem.time.MainActivity.APP_PREFERENCES_AD;

public class ThemeSetting extends AppCompatActivity implements AdapterView.OnItemSelectedListener, BillingProcessor.IBillingHandler {
    public static final String APP_PREFERENCES = "names";
    public static final String APP_PREFERENCES_STYLE = "style";
    public static final String APP_PREFERENCES_PERFORMANCE = "graphics";

    BillingProcessor bp;
    SharedPreferences mNames;

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
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.anim_activity_out, R.anim.anim_activity_in);
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
        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        if (mNames.getBoolean(APP_PREFERENCES_AD, false)) {
            mAdView.setVisibility(View.INVISIBLE);
        }

         if (mNames.getBoolean(APP_PREFERENCES_PERFORMANCE, false)) {
            Switch kek=findViewById(R.id.switch2);
            kek.setChecked(true);
        }
        if (mNames.getBoolean(APP_PREFERENCES_AD, false)) {
            findViewById(R.id.button5).setEnabled(false);
        }
        Spinner spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        Context context = getApplicationContext();
        Resources res = context.getResources();
        List<String> elements = new ArrayList<>();
        elements.add(res.getString(R.string.blacktheme));
        elements.add(res.getString(R.string.whitetheme));
        elements.add(res.getString(R.string.orangetheme));
        elements.add(res.getString(R.string.greentheme));
        elements.add(res.getString(R.string.bluetheme));
        elements.add(res.getString(R.string.pinktheme));
        elements.add(res.getString(R.string.darkgreentheme));
        //Создаем для spinner адаптер:
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, elements);
        //Настраиваем внешний вид выпадающего списка, используя готовый системный шаблон:
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //Присоединяем адаптер данных к spinner:
        spinner.setAdapter(dataAdapter);
        spinner.setSelection(needselect);

        bp = new BillingProcessor(this, "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAmjupeoyV65KNYu5m1ovoE/jbIkf3xsI+tiXKhYGy9bwVm705nKMVayH8ff22yXYEbCZ2pfaZRRtIXTtaxWs03doa/MDN1NjsApT+ct8vzD1JsLekUTQWSoLw/l1X6A81aSx691nVYlMmqptQnUjytYgFXluIn6j2PS+VzMFQaqnOXRkpQy6i9VoJoHFm1X3nDqE9iAyWDrDvwZ0tcW9YE1jr44CCMS0wbd84ZwbnAdJWVQmDViQ16PzVQeVxpCz9R01itdXQuY2HcHJ8l4txfF9Z95flouRPeAIJ9M3+/W4CaQp86qTldJKiRCjIVwBFVR2eDBR9EK1UE7M0hR7TqQIDAQAB", this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        mNames = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
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
    public void onPurchaseClick(View view) {
        bp.purchase(this, "devdem.time.noad");
    }
    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onProductPurchased(@NonNull String productId, @Nullable TransactionDetails details) {
        if(productId.equals("devdem.time.noad")) {
            Toast.makeText(this, R.string.fornoad, Toast.LENGTH_LONG).show();
            SharedPreferences.Editor editor = mNames.edit();
            editor.putBoolean("noad", true);
            editor.apply();
        }
    }

    @Override
    public void onPurchaseHistoryRestored() {

    }

    @Override
    public void onBillingError(int errorCode, @Nullable Throwable error) {
        Toast.makeText(this, R.string.error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBillingInitialized() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(!bp.handleActivityResult(requestCode, resultCode, data))
            super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        if(bp!=null)
            bp.release();
        super.onDestroy();
    }
}
