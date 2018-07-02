package com.example.khomyakovruslan.meet11practice;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements IActivityCallbacks {

    private IMyAidlInterface dataInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment1 fragment1 = new Fragment1();
        Fragment2 fragment2 = new Fragment2();
        fragmentManager.beginTransaction().add(R.id.fragment_1_container,fragment1).commit();
        fragmentManager.beginTransaction().add(R.id.fragment_2_container,fragment2).commit();


    }

    ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            dataInterface = IMyAidlInterface.Stub.asInterface(iBinder);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            dataInterface = null;
        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        unbindService(mConnection);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = new Intent(this, MyService.class);
        intent.setAction(IMyAidlInterface.class.getName());
        bindService(intent, mConnection, BIND_AUTO_CREATE);
    }

    @Override
    public void setText(String text) {
        try {
            dataInterface.setRandomString(text);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getText() {
        String result = null;
        try {
            result = dataInterface.getRandomString();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return result;
    }
}
