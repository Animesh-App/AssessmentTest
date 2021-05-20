package com.example.assessmenttest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class SongListDetailActivity extends AppCompatActivity {
    SongListData songListData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_list_detail);
        getSupportActionBar().setTitle("Song Details Screen");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //Get data from intent
        Intent i = getIntent();
        Bundle bundle = i.getExtras();
        songListData = (SongListData) bundle.getSerializable("songData");
        //Find View by Ids
        ImageView imageIv = findViewById(R.id.imageIV);
        TextView artistNameTV = findViewById(R.id.artistNameTV);
        TextView collectionNameTV = findViewById(R.id.collectionNameTV);


        //Set Value
        Glide.with(this).load(songListData.getArtworkUrl60()).apply(RequestOptions.centerCropTransform()).into(imageIv);
        artistNameTV.setText(songListData.getArtistName());
        collectionNameTV.setText(songListData.getCollectionName() != null ? songListData.getCollectionName() : " ");


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}