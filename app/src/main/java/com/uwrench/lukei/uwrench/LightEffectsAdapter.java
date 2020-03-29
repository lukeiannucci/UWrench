package com.uwrench.lukei.uwrench;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

public class LightEffectsAdapter extends RecyclerView.Adapter<LightEffectsViewHolder> {
    private FolderAndCommands mDataset;
    Context context;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder

    // Provide a suitable constructor (depends on the kind of dataset)
    public LightEffectsAdapter(FolderAndCommands myDataset, Context context) {
        mDataset = myDataset;
        this.context = context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public LightEffectsViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        //TextView v = (TextView) LayoutInflater.from(parent.getContext())
        //        .inflate(R.layout.my_text_view, parent, false);
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout, parent, false);
        LightEffectsViewHolder holder = new LightEffectsViewHolder(v);
        return holder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(LightEffectsViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.title.setText(mDataset.Commands.get(position).toString());
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                Toast.makeText(context, mDataset.Commands.get(position).toString2(), Toast.LENGTH_LONG).show();
                SendCommand.Connect(mDataset.Commands.get(position).toString2(), context);
            }
        });

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.Commands.size();
    }
}
