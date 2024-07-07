package pmf.android.sportscoresbysbm2.ui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import pmf.android.sportscoresbysbm2.R;
import pmf.android.sportscoresbysbm2.data.model.StandingsResponse;
import pmf.android.sportscoresbysbm2.ui.adapters.StandingsAdapter;
import pmf.android.sportscoresbysbm2.util.RecyclerViewClickListenerInterface;
import pmf.android.sportscoresbysbm2.viewmodel.StandingsResponseViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StandingsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StandingsFragment extends Fragment  implements RecyclerViewClickListenerInterface {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private static int counter = 0;


    private  ArrayList<StandingsResponse.Standing> standingsList;
    private StandingsResponseViewModel mStandingsViewModel;
    private LinearLayoutManager layoutManager;
    RecyclerView recyclerViewStandings;
    StandingsAdapter adapter;



    public StandingsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StandingsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StandingsFragment newInstance(String param1, String param2) {
        StandingsFragment fragment = new StandingsFragment();
        Bundle args = new Bundle();
        args.getParcelableArrayList("standings");
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        savedInstanceState = getArguments();
        if (savedInstanceState != null) {
            standingsList = savedInstanceState.getParcelableArrayList("standings");
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view =  inflater.inflate(R.layout.fragment_standings, container, false);
        //TextView textView = view.findViewById(R.id.textView2);
        Log.i("Standings fragment created", String.valueOf(counter));
        counter++;

        initialization(view);


        return view;
    }


    private void initialization(View view) {

        recyclerViewStandings = view.findViewById(R.id.recyclerViewStandings);
        recyclerViewStandings.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerViewStandings.setLayoutManager(layoutManager);


        //Initialize lists
        List<StandingsResponse.Standing> list = standingsList;
        //Adapter
        adapter = new StandingsAdapter(getContext(), list, this);
        recyclerViewStandings.setAdapter(adapter);



        //ViewModel
        mStandingsViewModel = new ViewModelProvider(this).get(StandingsResponseViewModel.class);


    }


    @Override
    public void onItemClick(int position) {

    }
}