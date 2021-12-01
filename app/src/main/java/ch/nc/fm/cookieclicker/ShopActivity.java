package ch.nc.fm.cookieclicker;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ShopActivity extends AppCompatActivity {
    public static final String COOKIE_INTENT = "cookies";
    public static final String CURSOR_INTENT = "cursors";
    public static final String BAKER_INTENT = "bakers";
    public static final String FACTORY_INTENT = "factories";

    private TextView txv_cookies;
    private float cookies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        txv_cookies = findViewById(R.id.cookies);
        createView();
    }

    private void createView() {
        Intent intentGetter = getIntent();
        cookies = intentGetter.getFloatExtra(COOKIE_INTENT, 0);

        txv_cookies.setText(cookies + " cookies");
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

}
