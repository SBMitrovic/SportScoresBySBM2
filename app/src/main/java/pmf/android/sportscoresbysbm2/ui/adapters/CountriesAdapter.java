package pmf.android.sportscoresbysbm2.ui.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;
import pmf.android.sportscoresbysbm2.R;
import pmf.android.sportscoresbysbm2.data.model.Country;
import pmf.android.sportscoresbysbm2.ui.activities.CountriesActivity;
import pmf.android.sportscoresbysbm2.ui.viewholder.CountriesViewHolder;
import pmf.android.sportscoresbysbm2.util.RecyclerViewClickListenerInterface;


public class CountriesAdapter extends RecyclerView.Adapter<CountriesViewHolder> {
    private Context context;
    private List<Country> countryList;
    private final RecyclerViewClickListenerInterface listener;

    public CountriesAdapter(Context context, List<Country> countryList, RecyclerViewClickListenerInterface listener) {
        this.context = context;
        this.listener = listener;
        if(this.countryList!=null){
            this.countryList.clear();
            this.countryList.addAll(countryList);
        }else{
            this.countryList = countryList;
        }
    }

    @NonNull
    @Override
    public CountriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =  LayoutInflater.from(parent.getContext()).inflate(R.layout.list_country, parent, false);
        return new CountriesViewHolder(view, listener);

    }
    @Override
    public void onBindViewHolder(@NonNull CountriesViewHolder holder, int position) {
        String flagUrl = "";
        if(countryList.get(position).getCode() != null) {
            flagUrl = "https://flags.fmcdn.net/data/flags/h80/" + countryList.get(position).getCode().toLowerCase() + ".png";
                Picasso.get().load(flagUrl).into(holder.countryFlag);

        }
        holder.countryNameListItem.setText(countryList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return countryList.size();
    }

    public void filterCountries(String text){
        List<Country> temp = new ArrayList();
        for(Country d: countryList){
            if(d.getName().toLowerCase().contains(text.toLowerCase())){
                temp.add(d);
            }
        }
        countryList = temp;
        notifyDataSetChanged();
    }
}




