package pmf.android.sportscoresbysbm2;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.util.Log;

import com.jakewharton.threetenabp.AndroidThreeTen;
public class SportScoresBySBM  extends Application {

    private static SportScoresBySBM instance;



    // Initialize the timezone information in your Application.onCreate() method:
    @Override
    public void onCreate() {
        super.onCreate();
        AndroidThreeTen.init(this);
        if (instance == null) {
            instance = this;
        }
    }

    public static SportScoresBySBM getInstance(){
        return instance;
    }
    public static boolean hasNetwork(){
        return instance.isNetworkConnected();
    }

    public static boolean isOnline(){
        return instance.isOnline2();
    }
    public boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        if (cm != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                Network network = cm.getActiveNetwork();
                if (network != null) {
                    NetworkCapabilities capabilities = cm.getNetworkCapabilities(network);
                    return capabilities != null &&
                            (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR));
                }
            } else {
                NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
                return activeNetwork != null && activeNetwork.isConnected();
            }
        }
        return false;
    }

    public boolean isOnline2() {
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }
}
