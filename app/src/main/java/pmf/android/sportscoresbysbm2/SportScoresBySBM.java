package pmf.android.sportscoresbysbm2;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

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

    private boolean isNetworkConnected(){
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
    }

}
