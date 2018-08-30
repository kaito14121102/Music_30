package com.framgia.music_30.screen.songgenre;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.framgia.music_30.R;
import com.framgia.music_30.data.model.Song;
import com.framgia.music_30.screen.home.fragment.GenreFragment;

import java.util.List;

public class SongGenreActivity extends AppCompatActivity implements SongGenreContract.View {
    public static final String TYPE_GENRE = "com.framgia.music_30.TYPE_GENRE";

    public static Intent getGenreIntent(Context context, String typeGenre) {
        Intent intent = new Intent(context, SongGenreActivity.class);
        intent.putExtra(TYPE_GENRE, typeGenre);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_genre);
        getTypeGenre();
    }

    @Override
    public void onGetDataSuccess(List<Song> songList) {
        //Test
        Toast.makeText(this, songList.size() + "", Toast.LENGTH_SHORT).show();
    }

    private void getTypeGenre() {
        String genre = getIntent().getStringExtra(TYPE_GENRE);
    }
}
