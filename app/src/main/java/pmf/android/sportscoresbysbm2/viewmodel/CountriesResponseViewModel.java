package pmf.android.sportscoresbysbm2.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import pmf.android.sportscoresbysbm2.data.model.CountriesResponse;
import pmf.android.sportscoresbysbm2.data.repository.CountryRepository;
import pmf.android.sportscoresbysbm2.util.RetrofitMaker;

public class CountriesResponseViewModel extends ViewModel {

    private static CountriesResponseViewModel sInstance;
    private LiveData<CountriesResponse> mCountriesResponse;
    private CountryRepository mCountryRepository;



    public CountriesResponseViewModel(){
        this.mCountryRepository = CountryRepository.getInstance();
        this.mCountriesResponse = mCountryRepository.fetchCountries();
    }

    public LiveData<CountriesResponse> getCountries(){
        return this.mCountriesResponse;
    }

}
