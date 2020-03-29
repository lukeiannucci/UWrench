package com.uwrench.lukei.uwrench;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public final class SendCommand {
    public static String port;
    public static String IPAddress;
    public static String Username;
    public static String Password;
    public static void executeSSHcommand(String command, Context context){


        if(IPAddress != null) {
            try {
                JSch jsch = new JSch();
                Session session = jsch.getSession(Username, IPAddress, Integer.parseInt(port));
                session.setPassword(Password);
                session.setConfig("StrictHostKeyChecking", "no");
                session.setTimeout(10000);
                session.connect();
                ChannelExec channel = (ChannelExec) session.openChannel("exec");
                channel.setCommand(command);
                channel.connect();
                try {
                    Thread.sleep(1000);
                } catch (Exception ee) {
                }
                channel.disconnect();

            } catch (JSchException e) {
                //todo

            }
        }
        else
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setMessage("You must fill out all text fields before confirming!").setTitle("Empty Text Fields");
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    //do nothing
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }
    public static void Connect(final String command, final Context context)
    {
        new AsyncTask<Integer, Void, Void>(){
            @Override
            protected Void doInBackground(Integer... params) {
                try {
                    executeSSHcommand(command, context);
                } catch (Exception e) {
                    //Toast.makeText(this, "Error connecting to: " + remoteData[0] , Toast.LENGTH_LONG).show();
                }
                return null;
            }
        }.execute(1);
    }
}
