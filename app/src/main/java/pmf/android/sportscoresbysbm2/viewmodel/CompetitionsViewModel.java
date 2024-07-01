package pmf.android.sportscoresbysbm2.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import pmf.android.sportscoresbysbm2.data.model.CompetitionsResponse;
import pmf.android.sportscoresbysbm2.data.repository.CompetitionsRepository;

public class CompetitionsViewModel extends ViewModel {

    private static CompetitionsViewModel sInstance;
    private LiveData<CompetitionsResponse> mCompetitionsResponse;
    private CompetitionsRepository mCompetitionsRepository;

    private CompetitionsViewModel() {
        mCompetitionsRepository = CompetitionsRepository.getInstance();
    }

    public LiveData<CompetitionsResponse> getCompetitionsResponse(String countryName) {
        return mCompetitionsRepository.fetchCompetitions(countryName);
    }

}
