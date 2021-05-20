package com.example.assessmenttest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import org.w3c.dom.Text;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyviewHolder> {

    Context context;
    List<SongListData> songListData;
    private clickItemListener clickItemListener;

    public RecyclerAdapter(Context context, List<SongListData> songListData, clickItemListener clickItemListener) {
        this.context = context;
        this.songListData = songListData;
        this.clickItemListener = clickItemListener;
    }


    public void setSongListData(List<SongListData> songListData) {
        this.songListData = songListData;
        this.notifyDataSetChanged();
    }

    @Override
    public RecyclerAdapter.MyviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_adapter, parent, false);
        return new MyviewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.MyviewHolder holder, int position) {
        holder.tvSongName.setText(songListData.get(position).getArtistName());
        Glide.with(context).load(songListData.get(position).getArtworkUrl60()).apply(RequestOptions.centerCropTransform()).into(holder.image);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              clickItemListener.onItemClick(songListData.get(position));
            }

        });
    }


    @Override
    public int getItemCount() {
        return songListData.size();

    }

    public static class MyviewHolder extends RecyclerView.ViewHolder {
        TextView tvSongName;
        ImageView image;
        CardView cardView;

        public MyviewHolder(View itemView) {
            super(itemView);
            tvSongName = (TextView) itemView.findViewById(R.id.songName);
            image = (ImageView) itemView.findViewById(R.id.image);
            cardView = (CardView) itemView.findViewById(R.id.cardView);
        }
    }
}

