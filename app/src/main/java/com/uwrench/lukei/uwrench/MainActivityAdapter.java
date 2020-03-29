package com.uwrench.lukei.uwrench;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;


public class MainActivityAdapter extends RecyclerView.Adapter<MainActivityViewHolder> {
    private List<FolderAndCommands> mDataset;
    Context context;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder

    // Provide a suitable constructor (depends on the kind of dataset)
    public MainActivityAdapter(List<FolderAndCommands> myDataset, Context context) {
        mDataset = myDataset;
        this.context = context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MainActivityViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        //TextView v = (TextView) LayoutInflater.from(parent.getContext())
        //        .inflate(R.layout.my_text_view, parent, false);
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout, parent, false);
        MainActivityViewHolder holder = new MainActivityViewHolder(v);
        //ViewHolder vh = new ViewHolder(v);
        return holder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MainActivityViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.title.setText(mDataset.get(position).FolderName.toString());
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                //LightEffects newPage = new LightEffects(mDataset.get(position));
                Intent i = new Intent(context, LightEffects.class);
                try {
                    Gson gson = new Gson();
                    Type type = new TypeToken<FolderAndCommands>() {
                    }.getType();
                    String json = gson.toJson(mDataset.get(position), type);
                    i.putExtra("FolderAndCommands", json);
                } catch (Exception e) {
                    Log.d("oops", "oops");
                }

                context.startActivity(i);
            }
        });

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
