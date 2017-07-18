package com.example.s0woo.signal;

import android.app.Activity;
import android.location.Location;
import android.os.Bundle;


import com.skp.Tmap.TMapData;
import com.skp.Tmap.TMapData.ConvertGPSToAddressListenerCallback;
import com.skp.Tmap.TMapGpsManager;
import com.skp.Tmap.TMapView;
import com.skp.Tmap.TMapPoint;


/**
 * Created by s0woo on 2017-07-12.
 */

public abstract class MapActivity extends Activity implements TMapGpsManager.onLocationChangedCallback {

    //TMap api 실행을 위한 api key
    private TMapView mMapView = null;
    public static String mApiKey = "73a7a315-a395-350e-9bff-14b10cd0f738";

    private void configureMapView() {
        mMapView.setSKPMapApiKey(mApiKey);
        //mMapView.setSKPMapBizappId(mBizAppID);
    }

    TMapGpsManager gps = null;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMapView = new TMapView(this);
        configureMapView();

        gps = new TMapGpsManager(MapActivity.this);
        gps.setMinTime(1000);
        gps.setMinDistance(5);
        gps.setProvider(gps.NETWORK_PROVIDER);
        gps.OpenGps();

    }

    protected void onDestroy() {
        super.onDestroy();
        gps.CloseGps();
    }

    public static void convertToAddress() {
        /*

        TMapPoint point = mMapView.getCenterPoint();

        TMapData tmapdata = new TMapData();

        if (mMapView.isValidTMapPoint(point)) {
            tmapdata.convertGpsToAddress(point.getLatitude(), point.getLongitude(), new ConvertGPSToAddressListenerCallback() {
                @Override
                public void onConvertToGPSToAddress(String strAddress) {
                    LogManager.printLog("변경된 값" + strAddress);
                }
            });
        }
        */
    }
}

