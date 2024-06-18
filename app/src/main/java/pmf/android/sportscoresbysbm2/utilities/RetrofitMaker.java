package pmf.android.sportscoresbysbm2.utilities;
import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.IOException;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import pmf.android.sportscoresbysbm2.SportScoresBySBM;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitMaker {

    public static APIInterface getRetrofit() {


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APICredentials.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(getOkHttpClient())
                .build();
        return retrofit.create(APIInterface.class);
    }

    private static OkHttpClient getOkHttpClient() {
        File cacheFile = new File(SportScoresBySBM.getAppContext().getCacheDir(), "cache");
        long cacheSize = (10 * 1024 * 1024); // 10 MiB
        okhttp3.Cache myCache = new Cache(cacheFile, cacheSize);
        File cacheMedia = new File(cacheFile, ".kurina");
        if (!cacheMedia.exists()) {
            try {
                cacheMedia.createNewFile();
                Log.i("CACHE", "Cache created");
            } catch (IOException e) {
                Log.i("CACHE", "Couldn't create .nomedia file");
                e.printStackTrace();
            }


        }
        return new OkHttpClient.Builder()
                .cache(myCache)
                .addInterceptor(getNewInterceptor())
                .build();
    }


    public static okhttp3.Interceptor getNewInterceptor() {
        return new okhttp3.Interceptor() {
            @Override
            public okhttp3.Response intercept(okhttp3.Interceptor.Chain chain) throws IOException {
                okhttp3.Request request = chain.request();
                //okhttp3.Response response = chain.proceed(request);
                if(hasNetwork(SportScoresBySBM.getAppContext())) {
                    okhttp3.Response response = chain.proceed(request);
                    return response.newBuilder().header("Cache-Control", "public, max-age=" + 5).build();
                } else {
                    okhttp3.Response response = chain.proceed(request);
                    response.cacheResponse();
                    return response.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7).build();

                }
            }
        };
    }

    private static boolean hasNetwork(Context appContext) {
        return ((android.net.ConnectivityManager) appContext.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo() != null;
    }

}

