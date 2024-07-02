package pmf.android.sportscoresbysbm2.ui.viewholder;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pmf.android.sportscoresbysbm2.R;
import pmf.android.sportscoresbysbm2.data.model.Country;



    public class ToDoViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewListItem;

        //private final TextView nase_polje;
        public ToDoViewHolder(View view, List<Country> countryList) {
            super(view);
            textViewListItem = view.findViewById(R.id.textViewListItem);
        }

    }

