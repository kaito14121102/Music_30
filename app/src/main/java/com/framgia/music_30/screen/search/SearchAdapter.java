package com.framgia.music_30.screen.search;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.framgia.music_30.R;
import com.framgia.music_30.data.model.Song;
import com.framgia.music_30.screen.songgenre.SongGenreAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<Song> mSongs;
    private SearchAdapter.OnItemClickListener mListener;


    public SearchAdapter(Context context, SearchAdapter.OnItemClickListener listener) {
        mContext = context;
        mSongs = new ArrayList<>();
        mListener = listener;
    }

    @NonNull
    @Override
    public SearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_song, null);
        return new SearchAdapter.ViewHolder(mContext, view, mSongs, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.ViewHolder holder, int position) {
        holder.bindData(mSongs.get(position));
    }

    @Override
    public int getItemCount() {
        return mSongs != null ? mSongs.size() : 0;
    }

    public void addData(List<Song> songs) {
        mSongs.addAll(songs);
        notifyDataSetChanged();
    }

    interface OnItemClickListener {
        void onSongClicked(int position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView mImageSong;
        private TextView mTextNameSong;
        private Context mContext;
        private ArrayList<Song> mSongs;
        private OnItemClickListener mListener;

        public ViewHolder(Context context, View itemView, ArrayList<Song> songs, OnItemClickListener listener) {
            super(itemView);
            mContext = context;
            mSongs = songs;
            mListener = listener;
            mImageSong = itemView.findViewById(R.id.image_song);
            mTextNameSong = itemView.findViewById(R.id.text_name_song);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mListener != null) {
                mListener.onSongClicked(getPosition());
            }
        }

        private void bindData(Song song) {
            Picasso.with(mContext)
                    .load(song.getImageSong())
                    .error(R.drawable.zing)
                    .into(mImageSong);
            mTextNameSong.setText(song.getTitle());
        }
    }
}
