package pmf.android.sportscoresbysbm2.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import pmf.android.sportscoresbysbm2.data.model.CountryList;
import pmf.android.sportscoresbysbm2.data.repository.CountryRepository;
import pmf.android.sportscoresbysbm2.util.RetrofitMaker;

public class CountryListViewModel extends ViewModel {

    private static CountryListViewModel sInstance;
    private LiveData<CountryList> mCountryList;

    public static CountryListViewModel getInstance(){
        if(sInstance == null){
            sInstance = new CountryListViewModel();
        }
        return sInstance;
    }

    private CountryListViewModel(){
        mCountryList = CountryRepository.getInstance().fetchCountriesList();
        sInstance = this.getInstance();
    }

    public LiveData<CountryList> getCountryList(){
        return mCountryList;
    }
}
