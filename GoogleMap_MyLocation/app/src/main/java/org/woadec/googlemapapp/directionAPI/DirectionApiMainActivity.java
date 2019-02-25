package org.woadec.googlemapapp.directionAPI;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.FontRequest;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.LocationSource;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.woadec.googlemapapp.R;

public class DirectionApiMainActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap googleMap;

    private ProgressBar mProgressBar;
    private SupportMapFragment supportMapFragment;

    private Button myLocation, searchLocation;


    private LocationManager locationManager;

    private Context mContext;

    private Location location;

    private Marker marker;
    private MarkerOptions markerOptions;


    private Intent openIntenet;

    private GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.direction_main_activity);

        this.mContext = getApplicationContext();
        markerOptions = new MarkerOptions();

        mProgressBar = (ProgressBar) findViewById(R.id.map_progress_bar);

        myLocation = (Button) findViewById(R.id.focus_my_location);
        searchLocation = (Button) findViewById(R.id.search_location);

        myLocation.setOnClickListener(new ButtonClickListener());
        searchLocation.setOnClickListener(new ButtonClickListener());


        supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.show_map);

        supportMapFragment.getMapAsync(this);


        // System.out.println(isGPSOn(mContext));


    }

    @Override
    protected void onStart() {
        super.onStart();


    }

    @Override
    protected void onStop() {
        super.onStop();
    }


    /**
     * MyLocationListener is used to point my location
     */

    private class MyLocationListener implements android.location.LocationListener {

        @Override
        public void onLocationChanged(Location location) {




            LatLng position = new LatLng(location.getLatitude(), location.getLongitude());
            markerOptions.position(position);
            googleMap.addMarker(markerOptions);

            CameraUpdate zoom = CameraUpdateFactory.newLatLngZoom(position, 12);

            googleMap.animateCamera(zoom);

            mProgressBar.setVisibility(View.INVISIBLE);

            Log.e("Loc", location.toString());
            System.out.println("Lat : " + location.getLatitude() + " Lng : " + location.getLongitude());


        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    }

    /**
     *
     *
     */


    private void checkConnection() {

        if (!isGPSOn(this)) {
            showAlert();
        }
    }

    private void showAlert() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Enable Location");
        builder.setMessage("Your Location setting is off");

        builder.setPositiveButton("Location On", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                System.out.println("Hello Dialog");
                openIntenet = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(openIntenet);
            }
        });


        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });


        builder.show();

    }


    /**
     * GPS status checker
     */
    private boolean isGPSOn(Context context) {
        locationManager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }


    /**
     * Button click listener
     */
    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.focus_my_location:
                    mProgressBar.setVisibility(View.VISIBLE);
                    checkConnection();

                    if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                    }
                    locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, new MyLocationListener());
                    return;
                case R.id.search_location:


                    return;
                default:
                    return;
            }

        }
    }

    /**
     * Map Click Listener
     */
    private class MapClickListener implements GoogleMap.OnMapClickListener {
        @Override
        public void onMapClick(LatLng latLng) {

            //mProgressBar.setVisibility(View.VISIBLE);

            markerOptions.position(latLng);

            googleMap.addMarker(markerOptions);

            Log.e("latLng", latLng.toString());

        }
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        googleMap.setOnMapClickListener(new MapClickListener());


    }
}
