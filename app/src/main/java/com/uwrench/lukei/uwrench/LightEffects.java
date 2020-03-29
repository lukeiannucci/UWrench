package com.uwrench.lukei.uwrench;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class LightEffects extends AppCompatActivity {
    private FolderAndCommands folder;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private List<String> mDataSet = new ArrayList<String>();
    public LightEffects() {
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.light_effects);
        String target = getIntent().getStringExtra("FolderAndCommands");
        Gson gson = new Gson();
        Type type = new TypeToken<FolderAndCommands>(){}.getType();
        folder = gson.fromJson(target, type);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar_lights);
        setSupportActionBar(myToolbar);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewLights);
        mAdapter = new LightEffectsAdapter(folder, this);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
