package pmf.android.sportscoresbysbm2.ui.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ScrollView;
import android.widget.TextView;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import com.bumptech.glide.Glide;
import com.google.gson.ReflectionAccessFilter;
import com.squareup.picasso.Picasso;
import pmf.android.sportscoresbysbm2.R;
import pmf.android.sportscoresbysbm2.data.model.Country;
import pmf.android.sportscoresbysbm2.ui.activities.CountriesActivity;
import pmf.android.sportscoresbysbm2.ui.viewholder.CountriesViewHolder;
import pmf.android.sportscoresbysbm2.util.RecyclerViewClickListenerInterface;


public class CountriesAdapter extends RecyclerView.Adapter<CountriesViewHolder> implements Filterable {
    private Context context;
    private List<Country> countryList;
    private final RecyclerViewClickListenerInterface listener;
    private List<Country> tempList;
    private List<Country>  filteredList = new ArrayList<>();

    public CountriesAdapter(Context context, List<Country> countryList, RecyclerViewClickListenerInterface listener) {
        this.context = context;
        this.listener = listener;
        if(this.countryList!=null){
            this.countryList.clear();
            this.countryList.addAll(countryList);
        }else{
            this.countryList = countryList;
        }
        this.tempList = countryList;

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
        if (countryList == null){
            return 0;
        } else {return countryList.size();}
    }
    public Filter getFilter(){
        return searchCountriesFilter;
    }
    private Filter searchCountriesFilter = new Filter(){
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            String query = constraint.toString();
            if (query.isEmpty()){
                results.values = tempList;
            }
            else {
                filteredList.clear();

                for (Country country : tempList) {
                    Log.i("Country", country.getName() + " query " + query.toLowerCase().trim());
                    if (country.getName().toLowerCase().contains(query.toLowerCase().trim())) {
                        Log.i("uslo je u if", "uslo je u if");
                        filteredList.add(country);
                    }
                }
                results.values = filteredList;

            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults results) {
            countryList = (List<Country>) results.values;
            notifyDataSetChanged();
        }
    };

}




