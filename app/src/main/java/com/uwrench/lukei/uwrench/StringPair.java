package com.uwrench.lukei.uwrench;

public class StringPair {
    private String command; //= new String();
    private String description; //= new String();
    public StringPair(String command, String description){
        this.command = command;
        this.description = description;
    }
    public void AddStrings(String command, String description){
        this.command = command;
        this.description = description;
    }

    public String toString2(){
        return this.command;
    }
    @Override
    public String toString(){
        return this.description;
    }
}
