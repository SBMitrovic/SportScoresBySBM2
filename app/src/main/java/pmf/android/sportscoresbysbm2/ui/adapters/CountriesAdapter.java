package pmf.android.sportscoresbysbm2.ui.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pmf.android.sportscoresbysbm2.R;
import pmf.android.sportscoresbysbm2.data.model.Country;
import pmf.android.sportscoresbysbm2.ui.activities.CountriesActivity;
import pmf.android.sportscoresbysbm2.ui.viewholder.ToDoViewHolder;

/*
public class CountriesAdapter extends RecyclerView.Adapter<CountriesAdapter.MyViewHolder> {

    private Context context;
    private List<Country> countryList;

    public CountriesAdapter(Context context, List<Country> newCountryList) {
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_countries, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder viewHolder, int position) {

            Country country = countryList.get(position);
            //ispisujemo ime drzave
            //viewHolder.nase_polje.setText(country.getName());
            viewHolder.bind(country);
    }

    @Override
    public int getItemCount() {
        Log.i("CountriesAdapter ITEM COUNT", String.valueOf(countryList.size()));
        return countryList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView nase_polje;

        public MyViewHolder (View view){
            super(view);
            nase_polje=(TextView) view.findViewById(R.id.nase_polje);

        }
        public void bind(Country country) {
            // Bind data to views
        }

      / public TextView getTextView() {
            return textView;
        }


    }
} */


public class CountriesAdapter extends RecyclerView.Adapter<ToDoViewHolder> {
    private Context context;
    private List<Country> countryList;

    public CountriesAdapter(Context context, List<Country> countryList) {
        this.context = context;
        this.countryList = countryList;
    }

    @NonNull
    @Override
    public ToDoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_country, parent, false);
        ToDoViewHolder vh = new ToDoViewHolder(view, countryList);
        return vh;

    }
    @Override
    public void onBindViewHolder(@NonNull ToDoViewHolder holder, int position) {
        holder.textViewListItem.setText(countryList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return countryList.size();
    }
}




