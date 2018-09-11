package com.framgia.music_30.screen.home.fragment.genre;

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
import java.util.List;

public class GenreAdapter extends RecyclerView.Adapter<GenreAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<Genre> mGenreArrayList;
    private OnItemClickListener mListener;

    public GenreAdapter(Context context, ArrayList<Genre> genreArrayList, OnItemClickListener listener) {
        mContext = context;
        mGenreArrayList = genreArrayList;
        mListener = listener;
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

    interface OnItemClickListener {
        void onGenreClicked(String typeGenre);
    }

    public void addData(List<Genre> genres) {
        if (genres != null) {
            mGenreArrayList.addAll(genres);
            notifyDataSetChanged();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView mImageViewGenre;

        public ViewHolder(View itemView) {
            super(itemView);
            mImageViewGenre = itemView.findViewById(R.id.image_view_genre);
            mImageViewGenre.setOnClickListener(this);
        }

        private void bindData(Genre genre) {
            mImageViewGenre.setImageResource(genre.getImageGenre());
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.image_view_genre:
                    if (mListener != null) {
                        mListener.onGenreClicked(mGenreArrayList.get(getPosition()).getNameGenre());
                    }
                    break;
            }
        }
    }
}
