package com.uwrench.lukei.uwrench;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private String[] remoteData;
    private FolderAndCommandsManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        remoteData = new String[4];
        remoteData[0] = "";
        remoteData[1] = "";
        remoteData[2] = "";
        remoteData[3] = "";
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        manager = new FolderAndCommandsManager();
        File file = new File(this.getFilesDir().getPath() + "/uwrenchdata.txt");
        if(file.exists()){
            Log.d("TAG: ", "Exists");
            Load();
            mAdapter = new MainActivityAdapter(manager.mFoldersAndCommands, MainActivity.this);
            recyclerView.setAdapter(mAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }
        else
        {
            List<StringPair> defaultStart = new ArrayList<StringPair>();
            //This is for default speed commands
            StringPair speedCommand = new StringPair("sudo ./remote -m20", "Speed 1"); //600ms (slowest first)
            StringPair speedCommand2 = new StringPair("sudo ./remote -m21", "400 ms"); //400 ms
            StringPair speedCommand3 = new StringPair("sudo ./remote -m22", "300 ms"); //300 ms
            StringPair speedCommand4 = new StringPair("sudo ./remote -m23", "200 ms"); //200 ms
            StringPair speedCommand5 = new StringPair("sudo ./remote -m24", "100 ms"); //100 ms
            StringPair speedCommand6 = new StringPair("sudo ./remote -m25", "50 ms"); //50 ms
            StringPair speedCommand7 = new StringPair("sudo ./remote -m26", "Speed 7"); //25ms (fastest last)
            defaultStart.add(speedCommand);
            defaultStart.add(speedCommand2);
            defaultStart.add(speedCommand3);
            defaultStart.add(speedCommand4);
            defaultStart.add(speedCommand5);
            defaultStart.add(speedCommand6);
            defaultStart.add(speedCommand7);
            FolderAndCommands folderAndCommands = new FolderAndCommands("Speed", defaultStart);
            manager.mFoldersAndCommands.add(folderAndCommands);



            //This is for default Display commands
            defaultStart = new ArrayList<StringPair>();
            for(int i = 0; i <= 10; i++){
                StringPair displayCommand = new StringPair(String.format("sudo ./remote -m%s", Integer.toString(i)), String.format("Pattern %s", Integer.toString(i+1)));
                defaultStart.add(displayCommand);
            }
            //StringPair displayCommand = new StringPair("sudo ./remote -m0", "Speed 1"); //600ms (slowest first)
            //StringPair displayCommand2 = new StringPair("sudo ./remote -m1", "400 ms"); //400 ms
            //StringPair displayCommand3 = new StringPair("sudo ./remote -m2", "300 ms"); //300 ms
            //defaultStart.add(displayCommand);
            //defaultStart.add(displayCommand2);
            //defaultStart.add(displayCommand3);
            folderAndCommands = new FolderAndCommands("Display", defaultStart);
            manager.mFoldersAndCommands.add(folderAndCommands);


            //This is for default Color commands
            defaultStart = new ArrayList<StringPair>();
            //StringPair colorCommand = new StringPair("sudo ./remote -m20", "Speed 1"); //600ms (slowest first)
            //defaultStart.add(speedCommand);
            folderAndCommands = new FolderAndCommands("Color", defaultStart);
            manager.mFoldersAndCommands.add(folderAndCommands);
            Save();
            mAdapter = new MainActivityAdapter(manager.mFoldersAndCommands, this);
            recyclerView.setAdapter(mAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }
    }


    public void Save() {
        try {
            Gson gson = new Gson();
            Type type = new TypeToken<FolderAndCommandsManager>() {
            }.getType();
            String json = gson.toJson(manager, type);
            try {
                //String path = this.getFilesDir().getPath() + "uwrenchdata.txt";
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(this.openFileOutput("uwrenchdata.txt", Context.MODE_PRIVATE));
                outputStreamWriter.write(json);
                outputStreamWriter.close();
            }
            catch (IOException e) {
                Log.e("Exception", "File write failed: " + e.toString());
            }
        } catch(Exception e){
            e.printStackTrace();
        }

    }
    public void Load(){
        try{
            InputStream inputStream = this.openFileInput("uwrenchdata.txt");
            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                String ret = stringBuilder.toString();
                Gson gson = new Gson();
                Type type = new TypeToken<FolderAndCommandsManager>(){}.getType();
                manager = gson.fromJson(ret, type);
            }


        } catch(IOException e){
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            if(resultCode == RESULT_OK){
                remoteData = data.getStringArrayExtra("data");
                SendCommand.IPAddress = remoteData[0];
                SendCommand.Password = remoteData[3];
                SendCommand.port = remoteData[1];
                SendCommand.Username = remoteData[2];
            }
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.folder:
                //mDataSet.add("test");
                break;
            case R.id.connect:
                Intent i = new Intent(MainActivity.this, Pop.class);
                startActivityForResult(i, 1);
                break;
        }
        mAdapter.notifyDataSetChanged();
        return super.onOptionsItemSelected(item);
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
