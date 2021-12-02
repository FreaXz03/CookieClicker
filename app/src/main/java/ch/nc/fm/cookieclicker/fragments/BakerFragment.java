package ch.nc.fm.cookieclicker.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import ch.nc.fm.cookieclicker.MainActivity;
import ch.nc.fm.cookieclicker.R;
import ch.nc.fm.cookieclicker.ShopActivity;

public class BakerFragment extends Fragment {
    View view;
    Button btn_addBaker;
    TextView txv_owned;
    TextView txv_cookies;

    int bakers;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_baker, container, false);

        bakers = getArguments().getInt(ShopActivity.BAKER_INTENT);

        btn_addBaker = (Button) view.findViewById(R.id.btn_addBaker);
        txv_owned = (TextView) view.findViewById(R.id.tx_itemBakerOwned);
        txv_cookies= (TextView) view.findViewById(R.id.cookies);

        txv_owned.setText("Owned: " + MainActivity.bakers);

        btn_addBaker.setOnClickListener(v -> {
            if (MainActivity.cookies >= 150) {
                MainActivity.cookies = MainActivity.cookies - 150;
                MainActivity.bakers++;
                txv_owned.setText("Owned: " + MainActivity.bakers);
                txv_cookies.setText(MainActivity.cookies + " cookies");
            }
        });

        return view;
    }
}