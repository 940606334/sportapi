package cn.yearcon.sportapi.util;

/**
 * 通过经纬度计算两点之前的距离
 *
 * @author itguang
 * @create 2018-01-07 10:24
 **/
public class DistanceUtil {
    private static final double EARTH_RADIUS = 6378137;
    //把经纬度转为度（°）
    private static double rad(double d){
        return d * Math.PI / 180.0;
    }

    /**
     * 根据两点间经纬度坐标（double值），计算两点间距离，单位为千米
     * @param lng1
     * @param lat1
     * @param lng2
     * @param lat2
     * @return
     */
    public static double getDistance(double lng1, double lat1, double lng2, double lat2){
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);
        double s = 2 * Math.asin(
                Math.sqrt(
                        Math.pow(Math.sin(a/2),2)
                                + Math.cos(radLat1)*Math.cos(radLat2)*Math.pow(Math.sin(b/2),2)
                )
        );
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000) / 10000;
        //保留小数后两位
        java.text.DecimalFormat   df   =new   java.text.DecimalFormat("#.00");
        df.format((s=s/1000));
        return s;
    }


    /**
     * test
     * @param args
     */
    public static void main(String[] args){
        double distance = getDistance(121.491909,31.233234,121.411994,31.206134);
        System.out.println("Distance is:"+distance);
    }
}
