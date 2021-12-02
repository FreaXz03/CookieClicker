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

public class FactoryFragment extends Fragment {
    View view;
    Button btn_addFactory;
    TextView txv_owned;
    TextView txv_cookies;

    int factories;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        factories = getArguments().getInt(ShopActivity.FACTORY_INTENT);

        view = inflater.inflate(R.layout.fragment_factory, container, false);

        btn_addFactory = (Button) view.findViewById(R.id.btn_addFactory);
        txv_owned = (TextView) view.findViewById(R.id.tx_itemFactoryOwned);
        txv_cookies= (TextView) view.findViewById(R.id.cookies);

        txv_owned.setText("Owned: " + MainActivity.factories);

        btn_addFactory.setOnClickListener(v -> {
            if (MainActivity.cookies >= 300) {
                MainActivity.cookies = MainActivity.cookies - 300;
                MainActivity.factories++;
                txv_owned.setText("Owned: " + MainActivity.factories);
                txv_cookies.setText(MainActivity.cookies + " cookies");
            }
        });

        return view;
    }
}