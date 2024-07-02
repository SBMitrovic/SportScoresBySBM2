package pmf.android.sportscoresbysbm2.ui.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import pmf.android.sportscoresbysbm2.R;

public class CompetitionsViewHolder extends RecyclerView.ViewHolder {
    public TextView competitonName;
    public ImageView competitionLogo;


    public CompetitionsViewHolder(@NonNull View itemView) {
        super(itemView);
        this.competitonName = itemView.findViewById(R.id.competitionName);
        this.competitionLogo = itemView.findViewById(R.id.competitionLogo);
    }
}
