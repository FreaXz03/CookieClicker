package ch.nc.fm.cookieclicker.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import ch.nc.fm.cookieclicker.MainActivity;
import ch.nc.fm.cookieclicker.R;
import ch.nc.fm.cookieclicker.ShopActivity;

public class CursorFragment extends Fragment {
    View view;
    Button btn_addCursor;
    TextView txv_owned;

    int cursors;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_cursor, container, false);

        cursors = getArguments().getInt(ShopActivity.CURSOR_INTENT);

        btn_addCursor = (Button) view.findViewById(R.id.btn_addCursor);
        txv_owned = (TextView) view.findViewById(R.id.tx_itemCursorOwned);

        txv_owned.setText("Owned: " + MainActivity.cursors);

        btn_addCursor.setOnClickListener(v -> {
            if (MainActivity.cookies >= 30) {
                MainActivity.cookies = MainActivity.cookies - 30;
                MainActivity.cursors++;
                txv_owned.setText("Owned: " + MainActivity.cursors);
            }
        });

        return view;
    }

}