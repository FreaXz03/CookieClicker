package ch.nc.fm.cookieclicker;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import ch.nc.fm.cookieclicker.fragments.BakerFragment;
import ch.nc.fm.cookieclicker.fragments.CursorFragment;
import ch.nc.fm.cookieclicker.fragments.FactoryFragment;

public class ShopActivity extends AppCompatActivity {
    public static final String COOKIE_INTENT = "cookies";
    public static final String CURSOR_INTENT = "cursors";
    public static final String BAKER_INTENT = "bakers";
    public static final String FACTORY_INTENT = "factories";

    private TextView txv_cookies;
    public Integer cursors;
    public Integer bakers;
    public Integer factories;

    Button btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_shop);
        super.onCreate(savedInstanceState);

        Intent intentGetter = getIntent();
        bakers = intentGetter.getIntExtra(BAKER_INTENT, 0);
        cursors = intentGetter.getIntExtra(CURSOR_INTENT, 0);
        factories = intentGetter.getIntExtra(FACTORY_INTENT, 0);

        btn_back = findViewById(R.id.back);

        txv_cookies = findViewById(R.id.cookies);
        createView();

        btn_back.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
    }

    private void createView() {
        Bundle bundle = new Bundle();
        bundle.putInt(CURSOR_INTENT, cursors);
        bundle.putInt(BAKER_INTENT, bakers);
        bundle.putInt(FACTORY_INTENT, factories);

        CursorFragment cursorFragment = new CursorFragment();
        BakerFragment bakerFragment = new BakerFragment();
        FactoryFragment factoryFragment = new FactoryFragment();

        androidx.fragment.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frameLayout_cursor, cursorFragment);
        ft.replace(R.id.frameLayout_baker, bakerFragment);
        ft.replace(R.id.frameLayout_factory, factoryFragment);
        ft.commit();

        cursorFragment.setArguments(bundle);
        bakerFragment.setArguments(bundle);
        factoryFragment.setArguments(bundle);

        txv_cookies.setText(MainActivity.cookies + " cookies");
    }
}