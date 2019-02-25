package org.woadec.googlemapapp.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Xml;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.GeoApiContext;
import com.google.maps.RoadsApi;
import com.google.maps.model.LatLng;
import com.google.maps.model.SnappedPoint;
import com.google.maps.model.SpeedLimit;

import org.woadec.googlemapapp.R;
import org.xmlpull.v1.XmlPullParser;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    private static final int PAGE_SIZE_LIMIT = 100;

    /**
     * Define the number of data points to re-send at the start of subsequent requests. This helps
     * to influence the API with prior data, so that paths can be inferred across multiple requests.
     * You should experiment with this value for your use-case.
     */
    private static final int PAGINATION_OVERLAP = 5;

    private Intent next;
    private Button map;

    private GoogleMap googleMap;
    private GeoApiContext mGeoApiContext;

    private ProgressBar mProgressBar;

    private List<LatLng> mCapturedLocations;
    private List<SnappedPoint> mSnappedPoints;
    private Map<String, SpeedLimit> mSpeedLimit;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map_fragment);


        mapFragment.getMapAsync(this);


        mGeoApiContext = new GeoApiContext().setApiKey(getString(R.string.google_maps_web_services_key));


    }

    private List<LatLng> loadGpxData(XmlPullParser pullParser, InputStream gpxIn) throws Exception {
        List<LatLng> latLngs = new ArrayList<>();

        pullParser.setInput(gpxIn, null);

        pullParser.nextTag();
        while (pullParser.next() != XmlPullParser.END_DOCUMENT) {

            if (pullParser.getEventType() != XmlPullParser.START_TAG) continue;

            if (pullParser.getName().equals("wpt")) {
                latLngs.add(new LatLng(
                        Double.valueOf(pullParser.getAttributeValue(null, "lat")),
                        Double.valueOf(pullParser.getAttributeValue(null, "lon"))));
            }

        }

        return latLngs;
    }


    public void onGpxButtonClick(View view) {


        try {

            System.out.println("Hello\n\n");
            mCapturedLocations = loadGpxData(Xml.newPullParser(), getResources().openRawResource(R.raw.gpx_data));

            findViewById(R.id.snap_to_roads).setEnabled(true);
            findViewById(R.id.load_gpx_data).setEnabled(false);

            LatLngBounds.Builder builder = LatLngBounds.builder();


            PolylineOptions polyline = new PolylineOptions();

            for (LatLng latLng : mCapturedLocations) {

                com.google.android.gms.maps.model.LatLng mapPoint =
                        new com.google.android.gms.maps.model.LatLng(latLng.lat, latLng.lng);

                builder.include(mapPoint);
                polyline.add(mapPoint);

                //System.out.println(mapPoint);

            }

            googleMap.addPolyline(polyline.color(Color.RED));
            googleMap.animateCamera(CameraUpdateFactory.newLatLngBounds(builder.build(), 0));


        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }


    public void onSnapToRoadsButtonClick(View view) {

        if (view.getId() == R.id.snap_to_roads) {
            mSnapTORoadTask.execute();
        }

    }

    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            next = new Intent(MainActivity.this, MapsActivity.class);
            startActivity(next);
            finish();
        }
    }


    /**
     * @return snapped point list
     * @throws Exception
     */

    private List<SnappedPoint> snapToRoad(GeoApiContext mGeoApiContext) throws Exception {

        List<SnappedPoint> snappedPoints = new ArrayList<>();


        int offSet = 0;

        while (offSet < mCapturedLocations.size()) {
            if (offSet > 0) offSet -= PAGINATION_OVERLAP;

            int lowerBound = offSet;
            int upperBound = Math.min(offSet + PAGE_SIZE_LIMIT, mCapturedLocations.size());

            LatLng[] pages = mCapturedLocations.subList(lowerBound, upperBound).toArray(new LatLng[upperBound - lowerBound]);

            SnappedPoint[] points = RoadsApi.snapToRoads(mGeoApiContext, true, pages).await();

            boolean passedOverlap = false;

            for (SnappedPoint point : points) {
                if (offSet == 0 || point.originalIndex >= PAGINATION_OVERLAP) {
                    passedOverlap = true;
                }
                if (passedOverlap) {
                    snappedPoints.add(point);
                }
            }

            offSet=upperBound;

        }


        return snappedPoints;
    }


    /**
     * method is load google map instance
     *
     * @param googleMap
     */


    @Override
    public void onMapReady(GoogleMap googleMap) {

        this.googleMap = googleMap;
    }

    //AsyncTask

    private AsyncTask<Void, Void, List<SnappedPoint>> mSnapTORoadTask = new AsyncTask<Void, Void, List<SnappedPoint>>() {
        @Override
        protected List<SnappedPoint> doInBackground(Void... voids) {
            try {
                return snapToRoad(mGeoApiContext);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            mProgressBar.setVisibility(View.VISIBLE);
            mProgressBar.setIndeterminate(true);
        }

        @Override
        protected void onPostExecute(List<SnappedPoint> snappedPoints) {
            super.onPostExecute(snappedPoints);

            mSnappedPoints = snappedPoints;
            mProgressBar.setVisibility(View.INVISIBLE);

            findViewById(R.id.snap_to_roads).setEnabled(false);
            findViewById(R.id.speed_limits).setEnabled(true);

            com.google.android.gms.maps.model.LatLng[] mapPoints = new com.google.android.gms.maps.model.LatLng[snappedPoints.size()];

            LatLngBounds.Builder builder = new LatLngBounds.Builder();


            System.out.println("Hello World\n\n");
            for (int i = 0; i < snappedPoints.size(); ++i) {

                mapPoints[i] = new com.google.android.gms.maps.model.LatLng(snappedPoints.get(i).location.lat, snappedPoints.get(i).location.lng);
                builder.include(mapPoints[i]);
                System.out.println(mapPoints[i]);

            }

            googleMap.addPolyline(new PolylineOptions().add(mapPoints).color(Color.GREEN));




        }
    };


}
