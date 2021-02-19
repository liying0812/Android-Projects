package com.project.lab3;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.util.Random;

public class MyService extends Service {
    private static final String TAG = "MyService";
    boolean isRun = false;
    Thread t;
    int rNum;

    public MyService() {
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "On Start Service");
        Log.i(TAG, "Thread ID: " + Thread.currentThread().getId());
        Log.i(TAG, "Start ID: " + startId);

        // Check is the generator is running
        // If we don't have this if condition, then every time we click on start service, it will create a new thread, which will create multiple threads
        // This to ensure one thread for the generateRandomeGenerator
        if (isRun == false) {
            Runnable runnable = () -> {
                isRun = true;
                generateRandomNumber();
            };
            t = new Thread(runnable);
            t.start();
        }

        return START_NOT_STICKY;
    }

    public void onDestroy() {
        Log.i(TAG, "On Destroy");
        isRun = false;
        t = null;
    }

    public void generateRandomNumber() {
        while (isRun == true) {
            Random rand = new Random();
            rNum = rand.nextInt();

            Log.i(TAG, "Thread ID: " + Thread.currentThread().getId() + ", " + "Random Number: " + rNum);
            // Log.i(TAG, "Random Number: " + rNum);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public int getRandomNumber() {
        return rNum;
    }

    class MyLocalBinder extends Binder {
        public MyService getService() {
            return MyService.this;
        }
    }
    private Binder myBinder = new MyLocalBinder();

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        Log.i(TAG, "On Bind");
        return myBinder;
        // throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(TAG, "On Unbind");
        return super.onUnbind(intent);
    }
}