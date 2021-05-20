package com.example.assessmenttest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements clickItemListener {
    List<SongListData> songList;
    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        songList = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerAdapter = new RecyclerAdapter(getApplicationContext(), songList, this);
        recyclerView.setAdapter(recyclerAdapter);

        //calling the method to display the songs data
        getSongListData();
    }

    private void getSongListData() {
        Call<SongListResponse> call = RetrofitClient.getInstance().getMyApi().getSongsList();
        call.enqueue(new Callback<SongListResponse>() {
            @Override
            public void onResponse(Call<SongListResponse> call, Response<SongListResponse> response) {
                progressBar.setVisibility(View.GONE);
                if (response.body().getResults().size() > 0) {
                    songList = response.body().getResults();
                    Log.d("TAG", "Response = " + songList.size());
                    recyclerAdapter.setSongListData(songList);


                }

            }

            @Override
            public void onFailure(Call<SongListResponse> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("DATA_ERROR : ", t.getMessage().toString());

            }
        });

    }

    @Override
    public void onItemClick(SongListData songListData) {
        Log.d("TAG : ", songListData.getArtistName());
        Intent intent = new Intent(this, SongListDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("songData", songListData);
        intent.putExtras(bundle); //pass bundle to your intent
        startActivity(intent); }
}