package pmf.android.sportscoresbysbm2.util;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import pmf.android.sportscoresbysbm2.SportScoresBySBM;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import okhttp3.logging.HttpLoggingInterceptor;
//import androidx.compose.ui.tooling.preview.Preview;

public class RetrofitMaker {

    private static final String TAG = "ServiceGenerator";
    public static final String HEADER_CACHE_CONTROL = "Cache-Control";
    public static final String HEADER_PRAGMA = "Pragma";
    private static final int cacheSize = 10 * 1024 * 1024;

    public static APIFootballInterface getRetrofit() {


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APICredentials.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient())
                .build();
        return retrofit.create(APIFootballInterface.class);
    }



    private static OkHttpClient okHttpClient(){
        return new OkHttpClient.Builder()
                .cache(cache())
                .addInterceptor(httpLoggingInterceptor()) // used if network off OR on
                .addNetworkInterceptor(networkInterceptor()) // only used when network is on
                .addInterceptor(offlineInterceptor())
                .build();
    }


    private static Cache cache(){
        return new Cache(new File(SportScoresBySBM.getInstance().getCacheDir(),"retrofitResponses"), cacheSize);
    }

    private static boolean hasNetwork(Context appContext) {
        return ((android.net.ConnectivityManager) appContext.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo() != null;
    }

    private static Interceptor offlineInterceptor() {
        return new Interceptor() {
            @NonNull
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();

                // prevent caching when network is on. For that we use the "networkInterceptor"
                if (!SportScoresBySBM.hasNetwork() || SportScoresBySBM.isOnline()) {
                    Log.d("OFFLINE INTERCEPTOR", "offline interceptor: called.");

                    CacheControl cacheControl = new CacheControl.Builder()
                            .maxStale(5, TimeUnit.DAYS)
                            .build();

                    request = request.newBuilder()
                            .removeHeader(HEADER_PRAGMA)
                            .removeHeader(HEADER_CACHE_CONTROL)
                            .cacheControl(cacheControl)
                            .build();
                }

                return chain.proceed(request);
            }
        };
    }

    /**
     * This interceptor will be called ONLY if the network is available
     * @return
     */
    private static Interceptor networkInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Log.d("TAG newtork interceptor", "network interceptor: called.");

                Response response = chain.proceed(chain.request());

                CacheControl cacheControl = new CacheControl.Builder()
                        .maxAge(5, TimeUnit.DAYS)
                        .build();

                return response.newBuilder()
                        .removeHeader(HEADER_PRAGMA)
                        .removeHeader(HEADER_CACHE_CONTROL)
                        .header(HEADER_CACHE_CONTROL, cacheControl.toString())
                        .build();
            }
        };
    }

    private static HttpLoggingInterceptor httpLoggingInterceptor ()
    {
        HttpLoggingInterceptor httpLoggingInterceptor =
                new HttpLoggingInterceptor( new HttpLoggingInterceptor.Logger()
                {
                    @Override
                    public void log (String message)
                    {
                        Log.d(TAG, "log: http log: " + message);
                    }
                } );
        httpLoggingInterceptor.setLevel( HttpLoggingInterceptor.Level.BODY);
        return httpLoggingInterceptor;
    }



    /*
    @Preview
    @Composable


     */
}

