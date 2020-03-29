package com.uwrench.lukei.uwrench;

import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;



public class FolderAndCommands implements Serializable {
    public String FolderName;
    public List<StringPair> Commands;
    public FolderAndCommands(String name, List<StringPair> myCommands){
        FolderName = name;
        Commands = myCommands;
    }
    public void Add(StringPair newCommand){
        Commands.add(newCommand);
    }

    public void Remove(StringPair removeCommand){
        Commands.remove(removeCommand);
    }
}
