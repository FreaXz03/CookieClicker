package ch.nc.fm.cookieclicker;


import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.util.Timer;

public class CookieService extends Service {

    private final IBinder binder = new CookieBinder();


    public class CookieBinder extends Binder {
        public CookieService getService() {
            return CookieService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public float cookieGeneration(Integer cursorAmount, Integer bakerAmount, Integer factoryAmount) {
        float newlyGeneratedCookies = 0;
        newlyGeneratedCookies = (cursorAmount.floatValue() / 10) + (bakerAmount.floatValue() / 5) + (factoryAmount.floatValue());

        return newlyGeneratedCookies;
    }
}
