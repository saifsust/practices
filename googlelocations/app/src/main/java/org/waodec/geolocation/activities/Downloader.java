package org.waodec.geolocation.activities;

import com.google.android.gms.maps.model.LatLng;


public class Downloader {


    private static final String HTTP_LINK = "https://maps.googleapis.com/maps/api/directions/json?";
    private static final String GOOGLE_MAP_KEY = "AIzaSyDZ0gpawlMuBtcOtxziTIsKw3DfITxWOvI";

    protected static String createUrlLink(LatLng source, LatLng destination) {
        final String SOURCE = "origin=" + source.latitude + "," + source.longitude;
        final String DESTINATION = "destination=" + destination.latitude + "," + destination.longitude;
        final String SENSOR = "sensor=false";
        final String KEY = "key=" + GOOGLE_MAP_KEY;
        final String PARAMETERS = SOURCE + "&" + DESTINATION + "&" + SENSOR + "&" + KEY;
        final String LINK = HTTP_LINK + PARAMETERS;
        return LINK;
    }

}
