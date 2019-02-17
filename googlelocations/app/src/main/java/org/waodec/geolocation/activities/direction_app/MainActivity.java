package org.waodec.geolocation.activities.direction_app;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;

import org.waodec.geolocation.activities.R;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback, Style.OnStyleLoaded {

    private final String MAPBOX_ACCESS_TOKEN = "pk.eyJ1Ijoic2FpZnN1c3QiLCJhIjoiY2pycmJhejhxMWRqbzQ5bDluZWR6eHJhYyJ9.eNC_pHV-53KNyoncJaNqKA";

    private MapView mapView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Mapbox.getInstance(getApplicationContext(), MAPBOX_ACCESS_TOKEN);
        setContentView(R.layout.direction_main_activity);
        mapView = (MapView) findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);

        mapView.getMapAsync(this);


    }


    @Override
    public void onMapReady(@NonNull MapboxMap mapboxMap) {

        mapboxMap.setStyle(Style.MAPBOX_STREETS);

    }

    @Override
    public void onStyleLoaded(@NonNull Style style) {

    }
}
