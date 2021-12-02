package ch.nc.fm.cookieclicker;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

import ch.nc.fm.cookieclicker.services.CookieService;

public class MainActivity extends AppCompatActivity {
    SharedPreferences prefs;
    private Handler handler = new Handler();

    public static final String COOKIE_PARAM = "cookieKey";
    public static final String CURSOR_PARAM = "cursorKey";
    public static final String BAKER_PARAM = "bakerKey";
    public static final String FACTORY_PARAM = "factoryKey";

    public static float cookies = 0;
    public static Integer cursors = 0;
    public static Integer bakers = 0;
    public static Integer factories = 0;

    private ImageButton btn_add;
    private Button btn_shop;
    private Button btn_score;
    private TextView txv_cookies;
    private TextView txv_cps;

    private boolean isBound = false;
    private boolean taskRunning = false;
    private CookieService cookieService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get sharedPref
        prefs = getSharedPreferences("mypref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        // find all TextViews and Buttons
        btn_add = findViewById(R.id.btn_add);
        btn_shop = findViewById(R.id.btn_shop);
        btn_score = findViewById(R.id.btn_score);
        txv_cookies = findViewById(R.id.tx_cookies);
        txv_cps = findViewById(R.id.tx_cps);

        // set all the Upgrades from sharedPref
        cookies = prefs.getFloat(COOKIE_PARAM, 0);
        cursors = prefs.getInt(CURSOR_PARAM, 0);
        bakers = prefs.getInt(BAKER_PARAM, 0);
        factories = prefs.getInt(FACTORY_PARAM, 0);

        // update all fields with new content
        txv_cookies.setText(cookies + " cookies");

        //cookie button -> adds Cookies
        btn_add.setOnClickListener(v -> {
            cookies++;
            txv_cookies.setText(cookies + " cookies");
            editor.putFloat(COOKIE_PARAM, cookies).apply();
        });

        //shop button -> redirects to shop activity
        btn_shop.setOnClickListener(v -> {
            Intent intent = new Intent(this, ShopActivity.class);
            intent.putExtra(ShopActivity.COOKIE_INTENT, this.cookies);
            intent.putExtra(ShopActivity.CURSOR_INTENT, this.cursors);
            intent.putExtra(ShopActivity.BAKER_INTENT, this.bakers);
            intent.putExtra(ShopActivity.FACTORY_INTENT, this.factories);
            startActivity(intent);
        });

        btn_score.setOnClickListener(v -> {
            Intent intent = new Intent(this, ScoreActivity.class);
            intent.putExtra(ShopActivity.COOKIE_INTENT, this.cookies);
            startActivity(intent);
        });
    }

    public static float roundFloat(final float number, final int decimal) {
        float precision = 1.0F;
        for (int i = 0; i < decimal; i++, precision *= 10) ;
        return ((int) (number * precision + 0.5) / precision);
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            float newCookies = cookieService.cookieGeneration(cursors, bakers, factories);
            cookies = roundFloat(cookies + newCookies, 1);
            txv_cookies.setText(cookies + " cookies");
            txv_cps.setText(newCookies + " cps");
        }
    };

    public void startTimer() {
        handler.postDelayed(runnable, 1000);
    }

    public void cancelTimer() {
        handler.removeCallbacks(runnable);
    }

    public void doTask() {
        taskRunning = true;
        Timer timer = new Timer("Timer");
        TimerTask task = new TimerTask() {
            public void run() {
                startTimer();
            }
        };
        if (isBound) {
            try {
                timer.schedule(task, 0, 1000L);
            } catch (Exception e) {
                System.out.println(e);
            }
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        // bind the Service via Intent
        Intent bindCookieServiceIntent = new Intent(this, CookieService.class);
        bindService(bindCookieServiceIntent, connection, Context.BIND_AUTO_CREATE);

    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            CookieService.CookieBinder binder = (CookieService.CookieBinder) iBinder;
            cookieService = binder.getService();
            isBound = true;
            if (!taskRunning) {
                doTask();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            isBound = false;
        }
    };

    @Override
    protected void onStop() {
        super.onStop();
        // unbind Service again when App is closing
        unbindService(connection);
        isBound = false;
        handler.removeCallbacks(runnable);
    }

    public float getCookies() {
        return cookies;
    }

    public void setCookies(float cookies) {
        this.cookies = cookies;
    }

    public Integer getCursors() {
        return cursors;
    }

    public void setCursors(Integer cursors) {
        this.cursors = cursors;
    }

    public Integer getBakers() {
        return bakers;
    }

    public void setBakers(Integer bakers) {
        this.bakers = bakers;
    }

    public Integer getFactories() {
        return factories;
    }

    public void setFactories(Integer factories) {
        this.factories = factories;
    }
}