package com.app.meetmax;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.jitsi.meet.sdk.JitsiMeet;
import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;

import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    EditText codeBox;
    Button button,share;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        codeBox= findViewById(R.id.codeBox);
        button= findViewById(R.id.button);
        share= findViewById(R.id.share);

        URL serverUrl;

        try{
            serverUrl = new URL("https://meet.jit.si");
            JitsiMeetConferenceOptions defaultOption= new JitsiMeetConferenceOptions.Builder()
                    .setServerURL(serverUrl)
                    .build();
            JitsiMeet.setDefaultConferenceOptions(defaultOption);

        }catch(MalformedURLException e){
            e.printStackTrace();
        }


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text= codeBox.getText().toString();
                if(text.length()>0){

                    JitsiMeetConferenceOptions options= new JitsiMeetConferenceOptions.Builder()
                            .setRoom(text)
                            .setFeatureFlag("invite.enabled",false)
                            .build();
                    JitsiMeetActivity.launch(MainActivity.this,options);

                }
            }
        });


        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareIntent= new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_TEXT,"Enter the room code to join the meeting: "+ codeBox.getText().toString());
                shareIntent.setType("text/plain");
                startActivity(shareIntent);
            }
        });

    }
}