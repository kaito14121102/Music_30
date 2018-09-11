package com.framgia.music_30.screen.home.fragment.mysong;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.framgia.music_30.R;
import com.framgia.music_30.data.model.Song;

import java.util.ArrayList;
import java.util.List;

public class MySongAdapter extends RecyclerView.Adapter<MySongAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<Song> mSongs;
    private OnItemClickListener mListener;

    public MySongAdapter(Context context, ArrayList<Song> songs, OnItemClickListener listener) {
        mContext = context;
        mSongs = songs;
        mListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_song, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindData(mSongs.get(position));
    }

    @Override
    public int getItemCount() {
        return mSongs != null ? mSongs.size() : 0;
    }

    interface OnItemClickListener {
        void onSongClicked(int position);
    }

    public void addData(List<Song> songs) {
        if (songs != null) {
            mSongs.addAll(songs);
            notifyDataSetChanged();
        }
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mSongTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            mSongTitle = itemView.findViewById(R.id.text_name_song);
            itemView.setOnClickListener(this);
        }

        private void bindData(Song song) {
            mSongTitle.setText(song.getTitle());
        }

        @Override
        public void onClick(View view) {
            if (mListener != null) {
                mListener.onSongClicked(getPosition());
            }
        }
    }
}

