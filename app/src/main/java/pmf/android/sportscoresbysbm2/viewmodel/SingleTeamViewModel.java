package pmf.android.sportscoresbysbm2.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import pmf.android.sportscoresbysbm2.data.model.CompetitionsResponse;
import pmf.android.sportscoresbysbm2.data.model.SingleTeamResponse;
import pmf.android.sportscoresbysbm2.data.repository.CompetitionsRepository;
import pmf.android.sportscoresbysbm2.data.repository.SingleTeamRepository;

public class SingleTeamViewModel extends ViewModel {

    private static CompetitionsViewModel sInstance;
    private LiveData<CompetitionsResponse> mCompetitionsResponse;
    private SingleTeamRepository mSingleTeamRepository;

    private SingleTeamViewModel() {
        mSingleTeamRepository = SingleTeamRepository.getInstance();
    }

    public LiveData<SingleTeamResponse> getSingleTeamResponse(long teamId) {
        return mSingleTeamRepository.fetchSingleTeam(teamId);
    }

}
