package pmf.android.sportscoresbysbm2.utilities;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitMaker {

    public static APIInterface getRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APICredentials.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(APIInterface.class);
    }

}
