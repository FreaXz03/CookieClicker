package ch.nc.fm.cookieclicker;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class CursorFragment extends Fragment {
    View view;

    Button btn_addCursor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        view = inflater.inflate(R.layout.fragment_cursor, container, false);

        btn_addCursor = (Button) view.findViewById(R.id.btn_addCursor);
        btn_addCursor.setText("add 0.1cps (" + "0" + ")");


        btn_addCursor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return view;
    }
}