package pmf.android.sportscoresbysbm2.ui.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import pmf.android.sportscoresbysbm2.R;
import pmf.android.sportscoresbysbm2.util.RecyclerViewClickListenerInterface;


public class CountriesViewHolder extends RecyclerView.ViewHolder {
        public TextView countryNameListItem;
        public ImageView countryFlag;

        //private final TextView nase_polje;
        public CountriesViewHolder(@NonNull View view, RecyclerViewClickListenerInterface listener) {
            super(view);
            countryNameListItem = view.findViewById(R.id.countryNameListItem);
            countryFlag = view.findViewById(R.id.countryFlag);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

