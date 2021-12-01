package ch.nc.fm.cookieclicker;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

public class BakerFragment extends Fragment {
    View view;

    Button btn_addBaker;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        view = inflater.inflate(R.layout.fragment_baker, container, false);

        btn_addBaker = (Button) view.findViewById(R.id.btn_addBaker);
        btn_addBaker.setText("add 0.1cps (" + "0" + ")");


        btn_addBaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return view;
    }
}