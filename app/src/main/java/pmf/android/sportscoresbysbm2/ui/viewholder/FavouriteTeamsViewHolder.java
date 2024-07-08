package pmf.android.sportscoresbysbm2.ui.viewholder;


import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import pmf.android.sportscoresbysbm2.R;
import pmf.android.sportscoresbysbm2.util.RecyclerViewClickListenerInterface;

public class FavouriteTeamsViewHolder extends RecyclerView.ViewHolder {
    public TextView teamName;

    public FavouriteTeamsViewHolder(@NonNull View itemView, RecyclerViewClickListenerInterface listenerInterface) {
        super(itemView);
        this.teamName = itemView.findViewById(R.id.teamName);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listenerInterface != null) {
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION) {
                        listenerInterface.onItemClick(position);
                    }
                }
            }
        });
    }
}

