package com.owono.android.serviceunderstanding;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by owono on 26.04.2018.
 * Service class that is used to run methods about the strings
 */

public class MyService extends Service {
    private final IBinder mBinder = new LocalBinder();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {

        // You can write here some methods to be called when we unbind the service to free up the resources.

        return super.onUnbind(intent);
    }

    /**
     * This is a local service that is extending the Binder class to create the user interface
     */
    public class LocalBinder extends Binder
    {
        MyService getService()
        {
            return MyService.this;
        }
    }

    /**
     * Down here you write all the functions that can be accessed trough binding to this service
     */

    public String getFirstMessage()
    {
        return "Hello y'all!!!";
    }

    public String getSecondMessage()
    {
        return "Bound service example";
    }
}
