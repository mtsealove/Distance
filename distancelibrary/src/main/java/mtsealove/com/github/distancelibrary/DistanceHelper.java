package mtsealove.com.github.distancelibrary;

import android.location.Location;

public class DistanceHelper {
    final static  int Meter=0, KiloMeter=1, Mile=2;

    int DistanceType;   //거리 종류

    public  DistanceHelper(int DistanceType){
        this.DistanceType=DistanceType;
    }

    public double GetDistanceByDouble(double lat1, double lon1, double lat2, double lon2) {

        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));

        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;

        if (DistanceType == KiloMeter) {
            dist = dist * 1.609344;
        } else if(DistanceType == Meter){
            dist = dist * 1609.344;
        }

        return (dist);
    }

    public  double GetDistanceByLocation(Location location1, Location location2) {
        double lat1=location1.getLatitude();
        double lon1=location1.getLongitude();
        double lat2=location2.getLatitude();
        double lon2=location2.getLongitude();

        return GetDistanceByDouble(lat1, lon1, lat2, lon2);
    }


    // This function converts decimal degrees to radians
    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    // This function converts radians to decimal degrees
    private static double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }
}
