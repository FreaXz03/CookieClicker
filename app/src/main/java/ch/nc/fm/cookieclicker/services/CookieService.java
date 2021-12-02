package ch.nc.fm.cookieclicker.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

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
        float newlyGeneratedCookies;
        newlyGeneratedCookies = (cursorAmount.floatValue() / 10) + (bakerAmount.floatValue() / 2) + (factoryAmount.floatValue());

        return newlyGeneratedCookies;
    }
}
