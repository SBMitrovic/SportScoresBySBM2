package pmf.android.sportscoresbysbm2;

import android.app.Application;
import android.content.Context;

import com.jakewharton.threetenabp.AndroidThreeTen;
public class SportScoresBySBM  extends Application {

    public static Context appContext;

    public static Context getAppContext() {
        return appContext;
    }

    // Initialize the timezone information in your Application.onCreate() method:
    @Override
    public void onCreate() {
        super.onCreate();
        AndroidThreeTen.init(this);
        appContext = getApplicationContext();
    }
}
