package pmf.android.sportscoresbysbm2.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import pmf.android.sportscoresbysbm2.data.model.CountryList;
import pmf.android.sportscoresbysbm2.data.repository.CountryRepository;
import pmf.android.sportscoresbysbm2.util.RetrofitMaker;

public class CountryListViewModel extends ViewModel {

    private static CountryListViewModel sInstance;
    private LiveData<CountryList> mCountryList;
    private CountryRepository mCountryRepository;



    public CountryListViewModel(){
        this.mCountryRepository = CountryRepository.getInstance();
        this.mCountryList = mCountryRepository.fetchCountriesList();
    }

    public LiveData<CountryList> getCountryList(){
        return this.mCountryList;
    }

}
