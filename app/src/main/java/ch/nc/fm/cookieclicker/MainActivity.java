package ch.nc.fm.cookieclicker;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    SharedPreferences prefs;

    public static final String COOKIE_PARAM = "cookieKey";
    private int cookies = 0;

    private Button btn_add;
    private TextView txv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prefs = getSharedPreferences("mypref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        btn_add = findViewById(R.id.btn_add);
        txv = findViewById(R.id.tx);

        cookies = prefs.getInt(COOKIE_PARAM, 0);
        txv.setText(prefs.getInt(COOKIE_PARAM, 0) + " cookies");

        btn_add.setOnClickListener(v -> {
            cookies++;
            txv.setText(cookies + " cookies");
            editor.putInt(COOKIE_PARAM, cookies).apply();
        });
    }
}