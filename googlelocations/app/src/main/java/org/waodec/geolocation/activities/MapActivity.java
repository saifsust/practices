package org.waodec.geolocation.activities;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener {


    private GoogleMap gMap, googleMap;
    private Marker firstMarker, secondMarker;
    private List<LatLng> markedPoints = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_activity);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map_customized_fragment);
        mapFragment.getMapAsync(this);


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        gMap = googleMap;

        gMap.setOnMapClickListener(this);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

        }

        gMap.setMyLocationEnabled(true);

    }

    @Override
    public void onMapClick(LatLng latLng) {


        System.out.println("Mapp clicked : " + latLng.latitude + "  " + latLng.longitude);

      /*  if (marker != null) marker.remove();
        marker = gMap.addMarker(new MarkerOptions().position(latLng).title("Marked Man !"));
        gMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));*/
        //gMap.animateCamera(CameraUpdateFactory.zoomTo(11));


        MarkerOptions markerOptions = new MarkerOptions();

        switch (markedPoints.size()) {

            case 0:
                markedPoints.add(latLng);
                markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
                break;
            case 1:
                markedPoints.add(latLng);
                markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
                break;
            default:
                markedPoints.remove(1);

                markedPoints.add(latLng);
                markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
                break;

        }


        if (markedPoints.size() == 1) {
            if (firstMarker != null) firstMarker.remove();
            firstMarker = gMap.addMarker(markerOptions.position(markedPoints.get(0)).title("FIRST MARKED"));

        }


        if (markedPoints.size() == 2) {
            if (secondMarker != null) secondMarker.remove();

            String link = Downloader.createUrlLink(markedPoints.get(0), markedPoints.get(1));
            System.out.println(link);
            secondMarker = gMap.addMarker(markerOptions.position(markedPoints.get(1)).title("SECOND MARKED"));

        }


        System.out.println(markedPoints.size());


    }


}
