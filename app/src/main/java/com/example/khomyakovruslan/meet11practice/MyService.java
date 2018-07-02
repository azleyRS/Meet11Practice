package com.example.khomyakovruslan.meet11practice;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.os.RemoteException;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new IMyAidlInterface.Stub() {
            @Override
            public String getRandomString() throws RemoteException {
                SharedPreferences preferences = getBaseContext().getSharedPreferences("PREF",MODE_PRIVATE);
                return preferences.getString("RANDOM_STRING",null);
            }

            @Override
            public void setRandomString(String text) throws RemoteException {
                SharedPreferences preferences = getBaseContext().getSharedPreferences("PREF",MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("RANDOM_STRING",text);
                editor.commit();
            }
        };
    }
}
