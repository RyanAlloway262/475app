package com.rxa392.shake;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SettingsActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<String> names;
    private final String infoText = "This app is created to design a simple and effective solution to most " +
            "flashlight apps. It adds a chop feature that allows you to chop your phone to activate" +
            "the flashlight. Ideally, I would like to implement features to add an app that you want to " +
            "open when you shake or chop your phone for instant access to calling and or other media.";

    private final String lorumIpsum = "Paladin grandfather bother. Four-day why raining reproductions knocked burst friendship bleat pity's Thorin riddle twig. Suspicious breach crowbill sadness fellow's Déagol bond foul rotten strike lightest. The dark fire will not avail you, flame of Udun! Mutilated dominion poured Samwise two yore boots listen griping early? Wit handy Wilds sway? Race pity year fast helped text too guarded sister king's refused Frodo? Delivery bigger despite Proudfoots coward man's allegiance fruitless offering knives. Ear studies Sauron's.\n\n" +
            "Watchful coast chap promises shields lsildur's Isildur's sigh wore! Causeway intact unnatural Dotard inside. Mercenaries chief Air patrol. Deposit suffering foreign yearned tomatoes Arwen's folk challenge source swept you've? Wheel name Athelas homeland wisdom front ought safekeeping breathing waybread morrow. Mattered burglar conjured pack story raven chips search Wilds thrice bacon bygone. Cross royal suffered caught pipe-weed Adamant quest deem know dungeon rumored. I think we should get off the road.\n\n" +
            "Unwise desire appearances holidays capable cousin Rohan's wretched blind race fool chooses. King's rode failed hearth? Nobly upset oaths honorably surround Legolas archer hairy hell evacuate? Cut wife reason before Snowbourn tart get man's beside drawing parent fools. Stick airmuch broil warn head-on cracking Láthspell whoa clot-head's wait. Lied wielder while pages treasure sight wager everything comings Shirkers ahead help. Wind waits road doorstep adjusted ending Bilbo's ax approaches generous! I gave you the chance of aiding me willingly, but you have elected the way of pain!";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_settings);
        recyclerView=findViewById(R.id.recyclerView);
        names=new ArrayList<>();


        names.add("Email: ");
        names.add("Password: \n\n");
        names.add("Information about this app: "+infoText+"\n");
        names.add("Lorum Ipsum to show recycler view works: "+lorumIpsum);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        ActionAdapter actionAdapter = new ActionAdapter((ArrayList<String>) names, SettingsActivity.this);
        recyclerView.setAdapter(actionAdapter);
    }

    public void BackButton(View view){
        Intent mainActivityIntent = new Intent(this, MainActivity.class);
        startActivity(mainActivityIntent); //send to new activity
    }

}
