package com.owono.android.serviceunderstanding;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    MyService myService; //variable of MyService class to handle the service methods in this class
    boolean isBind = false; //variable that indicates if the service is bound or not
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.text_view);

        Intent intent = new Intent(this, MyService.class); //Intent object used to bind the service
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE); //method used to bind the service
    }

    public void getFirstServiceMessage(View view)
    {
        String message;
        message = myService.getFirstMessage();
        textView.setText(message);
    }

    public void getSecondServiceMessage(View view)
    {
        String message;
        message = myService.getSecondMessage();
        textView.setText(message);
    }

    /**
     * ServiceConnection object that is used to indicate what to do when the service gets bound or when it gets disconnected
     */
    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder service) {

            MyService.LocalBinder localBinder = (MyService.LocalBinder) service;
            myService = localBinder.getService();
            isBind = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

            myService = null;
            isBind = false;
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isBind)
        {
            unbindService(mConnection);
            isBind = false;
        }
    }
}
