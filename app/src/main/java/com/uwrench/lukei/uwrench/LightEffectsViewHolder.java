package com.uwrench.lukei.uwrench;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class LightEffectsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    CardView cv;
    TextView title;
    TextView description;
    ImageView imageView;
    private ItemClickListener itemClickListener;

    public void setItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }
    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view, getAdapterPosition());
    }

    public LightEffectsViewHolder(View itemView) {
        super(itemView);
        cv = (CardView) itemView.findViewById(R.id.cardView);
        title = (TextView) itemView.findViewById(R.id.title);
        description = (TextView) itemView.findViewById(R.id.description);
        itemView.setOnClickListener(this);
        //imageView = (ImageView) itemView.findViewById(R.id.imageView);
    }
}
