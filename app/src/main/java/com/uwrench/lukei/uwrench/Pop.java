package com.uwrench.lukei.uwrench;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class Pop extends Activity {
    EditText Username;
    EditText Port;
    EditText IpAddress;
    EditText Password;
    Button Confirm;
    Context context;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popwindow);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        context = this;
        getWindow().setLayout(((int)(width * .8)),((int)(height*.6)));
        Confirm = (Button) findViewById(R.id.bConfirm);
        Confirm.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v){
                String data[] = new String[4];
                if(IpAddress.getText().toString().equals("")||
                        Port.getText().toString().equals("")||
                        Username.getText().toString().equals("")||
                        Password.getText().toString().equals("")) {
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
                else {
                    data[0] = IpAddress.getText().toString();
                    data[1] = Port.getText().toString();
                    data[2] = Username.getText().toString();
                    data[3] = Password.getText().toString();
                    Intent i = new Intent();
                    i.putExtra("data", data);
                    setResult(RESULT_OK, i);
                    finish();
                }
            }
        });
        Username = (EditText) findViewById(R.id.tUsername);
        Port = (EditText)findViewById(R.id.tPort);
        IpAddress = (EditText)findViewById(R.id.tIPAddress);
        Password = (EditText)findViewById(R.id.tPassword);

    }

}
