package com.example.s0woo.signal;

import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.skp.Tmap.TMapData;
import com.skp.Tmap.TMapGpsManager;
import com.skp.Tmap.TMapPoint;
import com.skp.Tmap.TMapView;


public class MainActivity extends AppCompatActivity{

    private TMapView mMapView = null;
    public static String mApiKey = "73a7a315-a395-350e-9bff-14b10cd0f738";
    TMapGpsManager gps = null;

    private void configureMapView() {
        mMapView.setSKPMapApiKey(mApiKey);
        //mMapView.setSKPMapBizappId(mBizAppID);
    }

    public TextView viewLct;
    public Button btnLct;
    public TextView viewLatLog;

    int cnt=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewLct = (TextView) findViewById(R.id.lct_view);
        btnLct = (Button)findViewById(R.id.lct_btn);
        viewLatLog = (TextView)findViewById(R.id.latlog_view);

        mMapView = new TMapView(this);
        configureMapView();

        if ( Build.VERSION.SDK_INT >= 23 &&
                ContextCompat.checkSelfPermission( this, android.Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED ) {
            ActivityCompat.requestPermissions( this, new String[] {  android.Manifest.permission.ACCESS_FINE_LOCATION  },
                    0 );
        }

        btnLct.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                mMapView.setCompassMode(true);
                gps = new TMapGpsManager(MainActivity.this);
                gps.setMinTime(1000);
                gps.setMinDistance(5);
                gps.setProvider(gps.NETWORK_PROVIDER);
                //gps.setProvider(TMapGpsManager.GPS_PROVIDER);
                gps.OpenGps();

                TMapPoint point = gps.getLocation();
                //TMapPoint point = mMapView.getCenterPoint();

                cnt++;
                LogManager.printLog(point.toString());
                viewLatLog.setText(cnt + " " + point.toString());

                TMapData tmapdata = new TMapData();

                if (mMapView.isValidTMapPoint(point)) {
                    tmapdata.convertGpsToAddress(point.getLatitude(), point.getLongitude(), new TMapData.ConvertGPSToAddressListenerCallback() {
                        @Override
                        public void onConvertToGPSToAddress(String strAddress) {
                            LogManager.printLog("변경된 값 : " + strAddress);
                            viewLct.setText(strAddress);
                        }
                    });
                }
            }
        });
    }
}

