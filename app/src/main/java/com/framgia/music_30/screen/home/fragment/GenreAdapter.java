package com.framgia.music_30.screen.home.fragment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.framgia.music_30.R;
import com.framgia.music_30.data.model.Genre;

import java.util.ArrayList;

public class GenreAdapter extends RecyclerView.Adapter<GenreAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<Genre> mGenreArrayList;

    public GenreAdapter(Context context, ArrayList<Genre> genreArrayList) {
        mContext = context;
        mGenreArrayList = genreArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_genre, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindData(mGenreArrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return mGenreArrayList != null ? mGenreArrayList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageViewGenre;

        public ViewHolder(View itemView) {
            super(itemView);
            mImageViewGenre = itemView.findViewById(R.id.image_view_genre);
        }

        private void bindData(Genre genre) {
            mImageViewGenre.setImageResource(genre.getImageGenre());
        }
    }
}
