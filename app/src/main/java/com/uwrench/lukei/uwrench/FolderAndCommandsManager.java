package com.uwrench.lukei.uwrench;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class FolderAndCommandsManager implements Serializable{
    public static String fileName = "manager.ser";
    public List<FolderAndCommands> mFoldersAndCommands;
    public FolderAndCommandsManager(){
        mFoldersAndCommands = new ArrayList<FolderAndCommands>();
    }

//    public void Save(){
//        //String pathToAppFolder = context.getExternalFilesDir(null).getAbsolutePath();
//        //String filePath = pathToAppFolder + File.separator + "manager.ser";
//        try{
//            FileOutputStream fileOutputStream = context.openFileOutput("manager.ser", Context.MODE_PRIVATE);
//            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
//            objectOutputStream.writeObject(this);
//            objectOutputStream.close();
//            fileOutputStream.close();
//
//        } catch(IOException e){
//            Log.d("TAG", e.toString());
//        }
//    }

}
