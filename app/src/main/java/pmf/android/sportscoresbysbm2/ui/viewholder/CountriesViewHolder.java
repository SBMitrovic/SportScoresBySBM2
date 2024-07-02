package pmf.android.sportscoresbysbm2.ui.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pmf.android.sportscoresbysbm2.R;
import pmf.android.sportscoresbysbm2.data.model.Country;



    public class CountriesViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewListItem, countryFlagListitem, countryNameListItem;
        public ImageView countryFlag;

        //private final TextView nase_polje;
        public CountriesViewHolder(View view, List<Country> countryList) {
            super(view);
            textViewListItem = view.findViewById(R.id.textViewListItem);
            countryFlagListitem = view.findViewById(R.id.countryFlagListItem);
            countryNameListItem = view.findViewById(R.id.countryNameListItem);
            countryFlag = view.findViewById(R.id.countryFlag);

        }

    }

