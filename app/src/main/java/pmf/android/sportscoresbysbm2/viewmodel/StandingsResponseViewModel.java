package pmf.android.sportscoresbysbm2.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import pmf.android.sportscoresbysbm2.data.model.CountriesResponse;
import pmf.android.sportscoresbysbm2.data.model.StandingsResponse;
import pmf.android.sportscoresbysbm2.data.repository.CountryRepository;
import pmf.android.sportscoresbysbm2.data.repository.StandingsRepository;

public class StandingsResponseViewModel extends ViewModel {


    private LiveData<StandingsResponse> mStandingsResponse;
    private StandingsRepository mStadningsRepository;



    public StandingsResponseViewModel(){
        this.mStadningsRepository = StandingsRepository.getInstance();
    }

    public LiveData<StandingsResponse> getStandingsResponse(long leagueId, String season){
        return this.mStadningsRepository.fetchStadnings(leagueId, season);
    }
}
