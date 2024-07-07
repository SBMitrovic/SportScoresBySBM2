package pmf.android.sportscoresbysbm2.ui.viewholder;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import pmf.android.sportscoresbysbm2.R;
import pmf.android.sportscoresbysbm2.util.RecyclerViewClickListenerInterface;

public class StandingsGroupViewHolder extends RecyclerView.ViewHolder {

        public TextView standingsGroup, competitionName;
        public ImageView competitionLogo;


        public StandingsGroupViewHolder(@androidx.annotation.NonNull View itemView , RecyclerViewClickListenerInterface listener) {
            super(itemView);
            standingsGroup = itemView.findViewById(R.id.standingsGroup);
            competitionName = itemView.findViewById(R.id.competitionName);
            competitionLogo = itemView.findViewById(R.id.competitionLogo);

            itemView.setOnClickListener(new View.OnClickListener() {
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


